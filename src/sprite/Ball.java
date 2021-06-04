package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Ball implements Sprite {
    private Point center;
    private double radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructor to create new ball.
     *
     * @param center          point of ball
     * @param radius          of ball
     * @param color           of ball
     * @param velocity        of ball
     * @param gameEnvironment of ball
     */
    public Ball(Point center , double radius , Color color , Velocity velocity , GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor to create new ball.
     *
     * @param x               value of center point of ball
     * @param y               value of center point of ball
     * @param radius          of ball
     * @param color           of ball
     * @param velocity        of ball
     * @param gameEnvironment of ball
     */
    public Ball(double x , double y , double radius , Color color , Velocity velocity ,
                GameEnvironment gameEnvironment) {
        this(new Point(x , y) , radius , color , velocity , gameEnvironment);
    }

    /**
     * constructor to create new ball.
     *
     * @param x      value of center point of ball
     * @param y      value of center point of ball
     * @param radius of ball
     * @param color  of ball
     */
    public Ball(double x , double y , double radius , java.awt.Color color) {
        this(new Point(x , y) , radius , color , new Velocity() , new GameEnvironment());
    }

    /**
     * constructor to create new ball.
     *
     * @param center point of ball
     * @param radius of ball
     * @param color  of ball
     */
    public Ball(Point center , int radius , java.awt.Color color) {
        this(center , radius , color , new Velocity() , new GameEnvironment());
    }

    /**
     * constructor to create new ball.
     *
     * @param center   point of ball
     * @param radius   of ball
     * @param color    of ball
     * @param velocity of ball
     */
    public Ball(Point center , double radius , Color color , Velocity velocity) {
        this(center , radius , color , velocity , new GameEnvironment());
    }

    /**
     * constructor to create new ball.
     *
     * @param center          point of ball
     * @param radius          of ball
     * @param color           of ball
     * @param gameEnvironment of ball
     */
    public Ball(Point center , double radius , Color color , GameEnvironment gameEnvironment) {
        this(center , radius , color , new Velocity() , gameEnvironment);
    }

    /**
     * @return center point of ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return x value of center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y value of center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return radius of the ball
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * @return gameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * set gameEnvironment.
     *
     * @param gameEnvironment1 to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment1) {
        this.gameEnvironment = gameEnvironment1;
    }

    /**
     * @return color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface using fillCircle method.
     *
     * @param surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX() , this.getY() , getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * set velocity to a ball by dx and dy.
     *
     * @param dx dx
     * @param dy dy
     */
    public void setVelocity(double dx , double dy) {
        this.velocity = new Velocity(dx , dy);
    }

    /**
     * @return velocity field of this ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set velocity to the ball.
     *
     * @param v velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * move the ball on screen consider any obstacle.
     * <p>
     * calculate next step of ball, if there is a collidable to hit in next step,
     * take the ball almost to hit point and then change velocity.
     * otherwise just make one step according to current velocity.
     * </p>
     */
    public void moveOneStep() {
        //next movement of ball
        Line trajectory = new Line(this.getCenter() , this.getVelocity().applyToPoint(this.getCenter()));
        CollisionInfo collisionInfo = this.getGameEnvironment().getClosestCollision(trajectory);
        //check if collision point exists
        if (collisionInfo == null) {
            //if not, make one step and return
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        Velocity newVel =
                collisionInfo.collisionObject().hit(this , collisionInfo.collisionPoint() , this.getVelocity());
        //special case of paddle run over the ball, in such case return the ball to collision point
        // and make one step until ball is out of collidable
        if (this.isInsideCollidable()) {
            setVelocity(newVel);
            this.center =
                    new Point(collisionInfo.collisionPoint().getX() - (-1) * Math.signum(this.getVelocity().getDx()) ,
                            collisionInfo.collisionPoint().getY() - (-1) * Math.signum(this.getVelocity().getDy()));
            return;
        }
        //if ball about to hit a collidable take it almost to hit point and set new velocity from hit method of block
        this.center = new Point(collisionInfo.collisionPoint().getX() + (-1) * Math.signum(this.getVelocity().getDx()) ,
                collisionInfo.collisionPoint().getY() + (-1) * Math.signum(this.getVelocity().getDy()));
        this.setVelocity(newVel);
    }

    /**
     * method checks if the ball is inside collidable, based of x,y values of collidable rectangle.
     *
     * @return true if ball is inside collidable, false otherwise
     */
    public boolean isInsideCollidable() {
        for (Collidable collidable : this.getGameEnvironment().getCollidableCollection()) {
            if (this.getCenter().getY() > collidable.getCollisionRectangle().getUpperLine().end().getY()
                    && this.getCenter().getY() < collidable.getCollisionRectangle().getLowerLine().end().getY()
                    && this.getCenter().getX() > collidable.getCollisionRectangle().getLeftLine().end().getX()
                    && this.getCenter().getX() < collidable.getCollisionRectangle().getRightLine().end().getX()) {
                return true;
            }
        }
        return false;
    }
}