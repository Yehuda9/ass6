package sprite;

import geometry.Point;

/**
 * @author Yehuda Schwartz 208994285
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * create new sprite.CollisionInfo holds collisionPoint and collisionObject.
     *
     * @param collisionPoint  the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return collisionPoint
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return collisionObject
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}