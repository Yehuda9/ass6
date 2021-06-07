package levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.*;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;

public class FinalFour implements LevelInformation {
    public static final int GUI_WIDTH = 800;
    public static final int PADDLE_SPEED = 12;
    private static final int BLOCK_WIDTH = 52;
    private static final int BLOCK_HEIGHT = 30;
    private static final int GUI_HEIGHT = 600;
    private static final int BLOCK_X_START = 10;
    private static final int BLOCK_Y_START = 100;
    private static final int PADDLE_WIDTH = 100;

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(-40 + 40 * i, 8);
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> lst = new LinkedList<>();
        int blockX = BLOCK_X_START;
        int blockY = BLOCK_Y_START;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Rectangle(new Point(blockX, blockY), BLOCK_WIDTH, BLOCK_HEIGHT),
                        new Color(30 * i, 48, 48));
                lst.add(block);
                blockX += BLOCK_WIDTH;
            }
            blockY += BLOCK_HEIGHT;
            blockX = BLOCK_X_START;
        }
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
