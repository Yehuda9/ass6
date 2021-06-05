package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private List<Line> rectangleLines = new ArrayList<>();
    private Line upperLine;
    private Line lowerLine;
    private Line rightLine;
    private Line leftLine;


    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft point of rectangle
     * @param width     of rectangle
     * @param height    of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        createRectangleLines();
    }

    public void setUpperLeftX(int x) {
        this.upperLeft = new Point(x, upperLeft.getY());
    }

    /**
     * create the fields of outer lines of rectangle, based on upper left point and width and height of rectangle.
     * also, make list of those fields as a field.
     */
    private void createRectangleLines() {
        List<Line> rectangleLinesList = new ArrayList<>();
        this.upperLine = new Line(this.getUpperLeft(),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()));
        this.leftLine = new Line(this.getUpperLeft(),
                new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + getHeight()));
        this.rightLine =
                new Line(upperLine.end(), new Point(upperLine.end().getX(), upperLine.end().getY() + getHeight()));
        this.lowerLine = new Line(leftLine.end(), rightLine.end());
        rectangleLinesList.add(leftLine);
        rectangleLinesList.add(rightLine);
        rectangleLinesList.add(lowerLine);
        rectangleLinesList.add(upperLine);
        this.rectangleLines = rectangleLinesList;
    }

    /**
     * @return lowerLine
     */
    public Line getLowerLine() {
        return lowerLine;
    }

    /**
     * @return leftLine
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * @return rightLine
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * @return upperLine
     */
    public Line getUpperLine() {
        return upperLine;
    }

    /**
     * @return list of all rectangle lines
     */
    public List<Line> getRectangleLines() {
        return rectangleLines;
    }

    /**
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return upperLeft geometry.Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * <p>
     * iterate rectangle lines and add to list every intersection.
     * </p>
     *
     * @param line to find intersection with this rectangle
     * @return list of intersection between this rectangle and the given line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPointsList = new ArrayList<>();
        for (Line l : this.getRectangleLines()) {
            if (l.isIntersecting(line) && l.intersectionWith(line) != null) {
                intersectionPointsList.add(l.intersectionWith(line));
            }
        }
        return intersectionPointsList;
    }
}