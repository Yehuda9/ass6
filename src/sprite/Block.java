package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import observer.HitListener;
import observer.HitNotifier;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * create new block with rectangle and color.
     *
     * @param rectangle of block
     * @param color     of block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * create new block with rectangle and default black color.
     *
     * @param rectangle of block
     */
    public Block(Rectangle rectangle) {
        this(rectangle, Color.black);
    }

    /**
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * set new color.
     *
     * @param color1 to set
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * getHitListeners.
     *
     * @return hitListeners list
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * add hit listeners.
     *
     * @param hl add hit listeners to list
     */
    public void addHitListeners(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @return rectangle of block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on the block).
     *
     * @param collisionPoint  expected point of collision
     * @param currentVelocity of ball
     * @return new velocity based on the force the object inflicted on the block.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        //if collision occur from right or left, change dx
        if ((getCollisionRectangle().getLeftLine().isOnLine(collisionPoint) && currentVelocity.getDx() > 0) || (
                getCollisionRectangle().getRightLine().isOnLine(collisionPoint) && currentVelocity.getDx() < 0)) {
            dx = -currentVelocity.getDx();
        }
        //if collision occur from above or bottom, change dy
        if ((getCollisionRectangle().getLowerLine().isOnLine(collisionPoint) && currentVelocity.getDy() < 0) || (
                getCollisionRectangle().getUpperLine().isOnLine(collisionPoint) && currentVelocity.getDy() > 0)) {
            dy = -currentVelocity.getDy();
        }
        //new velocity
        return new Velocity(dx, dy);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = hit(collisionPoint, currentVelocity);
        this.notifyHit(hitter);
        return velocity;
    }

    /**
     * notify all listener of blocks that hitting occur.
     *
     * @param hitter the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        /*Make a copy of the hitListeners before iterating over them.*/
        List<HitListener> listeners = new LinkedList<>(getHitListeners());
        /*Notify all listeners about a hit event*/
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * draw block on draw surface with fill rectangle method.
     * using draw rectangle method to create black frame around it.
     *
     * @param drawSurface to draw the block on it
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.getColor());
        drawSurface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        drawSurface.setColor(Color.black);
        drawSurface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * for now it is an empty method.
     */
    @Override
    public void timePassed() { }

    /**
     * add this block to sprite collection as well as collidable list of game.
     *
     * @param gameLevel to add block to.
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * remove block from the game.
     *
     * @param gameLevel reference of the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
}
