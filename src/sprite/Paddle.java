package sprite;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private int speed;

    /**
     * create new paddle with KeyboardSensor and block.
     *
     * @param keyboardSensor to move paddle
     * @param block          of paddle
     * @param s              speed of paddle
     */
    public Paddle(Block block, KeyboardSensor keyboardSensor, int s) {
        this.block = block;
        this.keyboard = keyboardSensor;
        this.speed = s;
    }

    /**
     * create new paddle with KeyboardSensor and default block.
     *
     * @param keyboardSensor to move paddle
     */
    public Paddle(KeyboardSensor keyboardSensor) {
        this(new Block(new Rectangle(new Point(360, 570), 150, 20), new Color(236, 99, 64)), keyboardSensor, 7);
    }

    /**
     * move paddle to specific x position.
     * create new block for paddle in the given location
     *
     * @param x position
     */
    public void setXPosition(int x) {
        this.block = new Block(new Rectangle(new Point(x, block.getCollisionRectangle().getUpperLeft().getY()),
                block.getCollisionRectangle().getWidth(), block.getCollisionRectangle().getHeight()),
                getBlock().getColor());
    }

    /**
     * set speed of paddle.
     *
     * @param s speed
     */
    public void setSpeed(int s) {
        this.speed = s;
    }

    /**
     * move paddle to the left by creating new block to the left of previous paddle.
     */
    public void moveLeft() {
        this.block = new Block(new Rectangle(new Point(block.getCollisionRectangle().getUpperLeft().getX() - speed,
                block.getCollisionRectangle().getUpperLeft().getY()), block.getCollisionRectangle().getWidth(),
                block.getCollisionRectangle().getHeight()), getBlock().getColor());
    }

    /**
     * move paddle to the right by creating new block to the right of previous paddle.
     */
    public void moveRight() {
        this.block = new Block(new Rectangle(new Point(block.getCollisionRectangle().getUpperLeft().getX() + speed,
                block.getCollisionRectangle().getUpperLeft().getY()), block.getCollisionRectangle().getWidth(),
                block.getCollisionRectangle().getHeight()), getBlock().getColor());
    }

    /**
     * draw paddle on draw surface, with black frame around.
     *
     * @param d draw surface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(getBlock().getColor());
        d.fillRectangle((int) getBlock().getCollisionRectangle().getUpperLeft().getX(),
                (int) getBlock().getCollisionRectangle().getUpperLeft().getY(),
                (int) getBlock().getCollisionRectangle().getWidth(),
                (int) getBlock().getCollisionRectangle().getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) getBlock().getCollisionRectangle().getUpperLeft().getX(),
                (int) getBlock().getCollisionRectangle().getUpperLeft().getY(),
                (int) getBlock().getCollisionRectangle().getWidth(),
                (int) getBlock().getCollisionRectangle().getHeight());
    }

    /**
     * if left key pressed move to the left.
     * if right key pressed move to the right.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.getCollisionRectangle().getUpperLine().start().getX() > GameLevel.FRAME_SIZE) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.getCollisionRectangle().getUpperLine().end().getX()
                < GameLevel.GUI_WIDTH - GameLevel.FRAME_SIZE) {
            moveRight();
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g game to add paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @return the rectangle of the block of paddle.
     */
    public Rectangle getCollisionRectangle() {
        return getBlock().getCollisionRectangle();
    }

    /**
     * split upper line of paddle to 5 lines, hit in any line has its own angle to return after hit.
     *
     * @param collisionPoint  the point of expected collision
     * @param currentVelocity of ball
     * @return new velocity after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        //current speed calculated from dx,dy by Pythagorean theorem
        double currentSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        double sizeOfRegion = this.getCollisionRectangle().getWidth() / 5;
        double leftMostX = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        //create 5 line
        Line firstLine = new Line(this.getCollisionRectangle().getUpperLeft(), new Point(leftMostX + sizeOfRegion, y));
        Line secondLine = new Line(firstLine.end(), new Point(leftMostX + sizeOfRegion * 2, y));
        Line thirdLine = new Line(secondLine.end(), new Point(leftMostX + sizeOfRegion * 3, y));
        Line fourthLine = new Line(thirdLine.end(), new Point(leftMostX + sizeOfRegion * 4, y));
        Line fifthLine = new Line(fourthLine.end(), new Point(leftMostX + sizeOfRegion * 5, y));
        //if hit in upper line
        if (getCollisionRectangle().getUpperLine().isOnLine(collisionPoint)) {
            //for each hit in any line return different angle, speed stay the same.
            if (firstLine.isOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(300, currentSpeed);
            } else if (secondLine.isOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(330, currentSpeed);
            } else if (thirdLine.isOnLine(collisionPoint)) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            } else if (fourthLine.isOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(30, currentSpeed);
            } else if (fifthLine.isOnLine(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(60, currentSpeed);
            }
        }
        //if hit not on upper line, return velocity by block hit method
        return this.block.hit(collisionPoint, currentVelocity);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        return hit(collisionPoint, currentVelocity);
    }

    /**
     * @return block.
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * set new color.
     *
     * @param color to set
     */
    public void setColor(Color color) {
        getBlock().setColor(color);
    }
}