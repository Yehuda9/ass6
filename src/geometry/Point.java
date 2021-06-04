package geometry;

/**
 * @author Yehuda Schwartz
 */
public class Point {
    static final double COMPARISON_THRESHOLD = 1E-10;
    private double x;
    private double y;

    /**
     * @param x x value of point
     * @param y y value of point
     */
    public Point(double x , double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate distance by Pythagorean theorem.
     *
     * @param other point to measure distance to
     * @return distance from this point to other
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.getX() - this.getX() , 2) + Math.pow(other.getY() - this.getY() , 2));
    }

    /**
     * @param other point to compare with
     * @return true if other point equals to this point, false otherwise
     */
    public boolean equals(Point other) {
        return this.distance(other) <= COMPARISON_THRESHOLD;
    }

    /**
     * @return return x value of point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return return y value of point.
     */
    public double getY() {
        return this.y;
    }
}
