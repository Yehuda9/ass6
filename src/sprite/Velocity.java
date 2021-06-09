package sprite;

import geometry.Point;

/**
 * sprite.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Yehuda Schwartz 208994285
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * @param dx specify the change in position on the X axis
     * @param dy specify the change in position on the Y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * default constructor.
     */
    public Velocity() {
        this(0, 0);
    }

    /**
     * method get angle and speed and convert them to change in position on the `x` and the `y` axes.
     * <p>
     * using cos and sin function to convert angle and speed to movement on X and Y.
     * </p>
     *
     * @param angle angle
     * @param speed speed
     * @return new instance of velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return speed on X axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return movement on Y axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p point to apply velocity on.
     * @return new point which is the previous point with movement on X and Y by this velocity fields.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}