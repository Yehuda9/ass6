package game;

import geometry.Line;
import geometry.Point;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yehuda Schwartz 208994285
 */
public class GameEnvironment {
    private List<Collidable> collidableCollection;

    /**
     * create new game environment.
     */
    public GameEnvironment() {
        this.collidableCollection = new LinkedList<>();
    }

    /**
     * @return collidableCollection.
     */
    public List<Collidable> getCollidableCollection() {
        return collidableCollection;
    }

    /**
     * @param c add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        collidableCollection.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * <p>
     * iterate collidable list, for every collidable find the closest intersection to start of trajectory
     * (which is center of ball).
     * calculate the distance between intersection point to start of trajectory.
     * at the end return collisionInfo holds the closest point of intersection to start of trajectory,
     * the point at which the collision occurs, the collidable object involved in the collision.
     * </p>
     *
     * @param trajectory line of ball movement
     * @return information about closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        Collidable collisionObject = null;
        double minDistance = Double.MAX_VALUE;
        for (Collidable collidable : collidableCollection) {
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()) != null
                    && trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle())
                            .distance(trajectory.start()) < minDistance) {
                collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                collisionObject = collidable;
                minDistance = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle())
                        .distance(trajectory.start());
            }
        }
        if (collisionObject == null || collisionPoint == null) {
            return null;
        }
        return new CollisionInfo(collisionPoint , collisionObject);
    }
}