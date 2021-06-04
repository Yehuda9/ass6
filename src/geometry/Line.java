package geometry;

import java.util.List;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Line {
    static final double COMPARISON_THRESHOLD = 1E-10;
    private Point start;
    private Point end;

    /**
     * @param start start point of line
     * @param end   end point of line
     */
    public Line(Point start , Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1 x value of start point of line
     * @param y1 y value of start point of line
     * @param x2 x value of end point of line
     * @param y2 y value of start point of line
     */
    public Line(double x1 , double y1 , double x2 , double y2) {
        this.start = new Point(x1 , y1);
        this.end = new Point(x2 , y2);
    }

    /**
     * @return incline of segment based on (start.y-end.y)/(start.x-end.x).
     * if line parallel to X axis, return Double.POSITIVE_INFINITY
     */
    private double incline() {
        if (this.start().getX() == this.end().getX() && this.start().getY() != this.end().getY()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
    }

    /**
     * @return the length of the line using distance method
     */
    public double length() {
        return start().distance(end);
    }

    /**
     * @return the middle point of the line, with average between X's and Y's values.
     */
    public Point middle() {
        double xMid = (this.start().getX() + this.end().getX()) / 2;
        double yMid = (this.start().getY() + this.end().getY()) / 2;
        return new Point(xMid , yMid);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * method get 3 points and determine if can be drawn a clockwise line between them.
     * <p>
     * </p>
     *
     * @param a first point
     * @param b second point
     * @param c third point
     * @return true if 3 points in clockwise order, false otherwise.
     */
    private boolean isClockWise(Point a , Point b , Point c) {
        return (c.getY() - a.getY()) * (b.getX() - a.getX()) > (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    /**
     * check whether start or end point is on other line.
     *
     * @param other line to compare with
     * @return point of intersection
     */
    private Point startOrEndOnLine(Line other) {
        if (this.isOnLine(other.start())) {
            return other.start();
        }
        if (other.isOnLine(this.start())) {
            return this.start();
        }
        if (this.isOnLine(other.end())) {
            return other.end();
        }
        if (other.isOnLine(this.end())) {
            return this.end();
        }
        return null;
    }

    /**
     * check whether start or end point of other equals to start or end point of this.
     *
     * @param other line to compare with
     * @return point of intersection
     */
    private Point startEqualsToEnd(Line other) {
        if (this.start().equals(other.start()) || this.start().equals(other.end()) && !(this.end().equals(other.end())
                || this.end().equals(other.start()))) {
            return this.start();
        }
        if (this.end().equals(other.end()) || this.end().equals(other.start()) && !(this.start().equals(other.start())
                || this.start().equals(other.end()))) {
            return this.end();
        }
        return null;
    }

    /**
     * check whether both points of first line is on second line or if one line overlap the other.
     *
     * @param other line to compare with
     * @return true one of 2 lines (this,other) is a sub line, false otherwise
     */
    private boolean isSubLine(Line other) {
        if (this.isPoint() || other.isPoint()) {
            return false;
        }
        if (this.isOnLine(other.start()) && this.isOnLine(other.end())) {
            return true;
        }
        if (other.isOnLine(this.start()) && other.isOnLine(this.end())) {
            return true;
        }
        if (this.startOrEndOnLine(other) != null && this.incline() == other.incline() && (
                this.length() <= other.length() || other.length() <= this.length())
                && startEqualsToEnd(other) == null) {
            return true;
        }
        return false;
    }

    /**
     * @param point check if point is on both lines
     * @param other line to compare with
     * @return true if points is one both lines, false otherwise
     */
    private boolean isOnBothLines(Point point , Line other) {
        return this.isOnLine(point) && other.isOnLine(point);
    }

    /**
     * method to handle the case of segment parallel to Y axis.
     *
     * @param other line to compare with
     * @return point of intersection between this line and other line
     */
    private Point parallelToY(Line other) {
        //if one of the line is a point call startOrEndOnLine method that return point of intersection
        if (this.isPoint() || other.isPoint()) {
            return startOrEndOnLine(other);
        }
        Point possibleInter = null;
        //if only one line parallel to Y axis, create new point of possible intersection with the line equation
        if (other.incline() == Double.POSITIVE_INFINITY && this.incline() != Double.POSITIVE_INFINITY) {
            double y = (this.incline() * other.start().getX()) + this.yAxisIntersection();
            possibleInter = new Point(other.start().getX() , y);
        }
        //if only one line parallel to Y axis, create new point of possible intersection with the line equation
        if (this.incline() == Double.POSITIVE_INFINITY && other.incline() != Double.POSITIVE_INFINITY) {
            double y = (other.incline() * this.start().getX()) + other.yAxisIntersection();
            possibleInter = new Point(this.start().getX() , y);
        }
        //check if possible intersection is on both lines, and return
        if (isOnBothLines(possibleInter , other)) {
            return possibleInter;
        }
        return null;
    }

    /**
     * check if two segments intersecting.
     * <p>
     * if lines are equals or, start or end of one line is on other, return true
     * if the last condition is false so if inclines of segments isn't equals so no intersection exist
     * at the end, method check if every 3 point out of 4 is in different direction of clock,
     * is condition true so intersection exists and return true
     * </p>
     *
     * @param other line to compare with
     * @return true if exists one or more points of intersection
     */
    public boolean isIntersecting(Line other) {
        /*
         * handle equals lines, sub lines and case of one line start or end is start or end of other line*/
        if (this.equals(other) || this.startOrEndOnLine(other) != null || other.startOrEndOnLine(this) != null || this
                .isSubLine(other)) {
            return true;
        }
        if (other.incline() == this.incline()) {
            return false;
        }
        return isClockWise(this.start() , other.start() , other.end()) != isClockWise(this.end() , other.start() ,
                other.end()) && (isClockWise(this.start() , this.end() , other.start()) != isClockWise(this.start() ,
                this.end() , other.end()));
    }

    /**
     * @return true if segment is a point, false otherwise
     */
    private boolean isPoint() {
        return this.start.equals(this.end());
    }

    /**
     * method return point of intersection if exists only one point, if no intersection exists or more than one,
     * return null.
     * <p>
     * if this start equals to other end or the opposite and line is no sub line, return point.
     * if sub line, and lines isn't a point, or no intersection exist by isIntersection method, or line are equals,
     * return null.
     * if one of the lines parallel to Y axis, go to parallelToY method (include also case of line is a point).
     * if all those special cases aren't true, find lines equations and find possible intersection.
     * if possible intersection point is on both lines return point.
     * </p>
     *
     * @param other line to compare with
     * @return point of intersection between this and other
     */
    public Point intersectionWith(Line other) {
        if (startEqualsToEnd(other) != null && !isSubLine(other)) {
            return startEqualsToEnd(other);
        }
        if (isSubLine(other) && !this.isPoint() && !other.isPoint() || (!isIntersecting(other) || this.equals(other))) {
            return null;
        }
        if (this.incline() == Double.POSITIVE_INFINITY || other.incline() == Double.POSITIVE_INFINITY || other.isPoint()
                || this.isPoint()) {
            return parallelToY(other);
        }
        double x = (this.yAxisIntersection() - other.yAxisIntersection()) / (other.incline() - this.incline());
        double y = (this.incline() * x) + this.yAxisIntersection();
        Point possibleInter = new Point(x , y);
        if (isOnBothLines(possibleInter , other)) {
            return possibleInter;
        }
        return null;
    }


    /**
     * @param other line to compare with
     * @return true if this line equals to other, false otherwise.
     */
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end())
                || this.end().equals(other.start()) && this.start().equals(other.end());
    }

    /**
     * @return this line's intersection with Y axis
     */
    private double yAxisIntersection() {
        return this.start().getY() - this.start().getX() * incline();
    }

    /**
     * if the distance from point to end + distance from point to start is equals to the whole line length,
     * then point is on line.
     *
     * @param other point to compare with
     * @return true if other point is on this line
     */
    public boolean isOnLine(Point other) {
        return this.start().distance(other) + this.end().distance(other) <= this.length() + COMPARISON_THRESHOLD;
    }

    /**
     * iterate list of intersections with rectangle, and find the closest intersection to start of this line.
     * <p>
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line
     * </p>
     * if no intersection exist return null.
     *
     * @param rect to find closest intersection intersections with
     * @return closest intersection point to start of this line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        for (Point point : intersectionPoints) {
            if (point != null && point.distance(this.start()) < minDistance) {
                closestPoint = point;
                minDistance = point.distance(this.start());
            }
        }
        return closestPoint;
    }
}