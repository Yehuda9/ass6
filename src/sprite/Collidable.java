package sprite;

import geometry.Point;
import geometry.Rectangle;

/**
 * interface for all objects in the game, the ball can collide with.
 *
 * @author Yehuda Schwartz 208994285
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return the rectangle of collidable
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the point of expected collision
     * @param currentVelocity of ball
     * @return new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

    /**
     * calls old hit method and return its result,
     * also calls notifyHit method to let all listeners know about hitting.
     *
     * @param hitter          the ball that hit
     * @param collisionPoint  the point of expected collision
     * @param currentVelocity of ball
     * @return new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}