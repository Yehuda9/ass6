package levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import sprite.Ball;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class WideEasy implements LevelInformation {
    public static final int GUI_WIDTH = 800;
    private static final int BLOCK_WIDTH = 52;
    private static final int BLOCK_HEIGHT = 30;
    private static final int GUI_HEIGHT = 600;
    private static final int BLOCK_X_START = 10;
    private static final int BLOCK_Y_START = 250;
    public static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 600;

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new LinkedList<>();
        for (int i = 5; i >= 1; i--) {
            Velocity velocity = Velocity.fromAngleAndSpeed(-10 * i, 8);
            lst.add(velocity);
        }
        for (int i = 1; i <= 5; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(10 * i, 8);
            lst.add(velocity);
        }
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> lst = new LinkedList<>();
        int blockX = BLOCK_X_START;
        for (int i = 0; i < 3; i++) {
            Block block1 = new Block(new Rectangle(new Point(blockX, BLOCK_Y_START), BLOCK_WIDTH, BLOCK_HEIGHT),
                    new Color(i * 70, 100, 50));
            Block block2 = new Block(
                    new Rectangle(new Point(block1.getCollisionRectangle().getUpperLine().end().getX(), BLOCK_Y_START),
                            BLOCK_WIDTH, BLOCK_HEIGHT), new Color(i * 70, 100, 50));
            lst.add(block2);
            lst.add(block1);
            blockX += BLOCK_WIDTH * 2;
        }
        for (int i = 0; i < 3; i++) {
            Block block1 = new Block(new Rectangle(new Point(blockX, BLOCK_Y_START), BLOCK_WIDTH, BLOCK_HEIGHT),
                    new Color(30, 69, 2));
            lst.add(block1);
            blockX += BLOCK_WIDTH;
        }
        for (int i = 0; i < 3; i++) {
            Block block1 = new Block(new Rectangle(new Point(blockX, BLOCK_Y_START), BLOCK_WIDTH, BLOCK_HEIGHT),
                    new Color(i * 70, 100, 50));
            Block block2 = new Block(
                    new Rectangle(new Point(block1.getCollisionRectangle().getUpperLine().end().getX(), BLOCK_Y_START),
                            BLOCK_WIDTH, BLOCK_HEIGHT), new Color(i * 70, 100, 50));
            lst.add(block2);
            lst.add(block1);
            blockX += BLOCK_WIDTH * 2;
        }
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
