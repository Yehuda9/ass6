package levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Green3 implements LevelInformation {
    public static final int GUI_WIDTH = 800;
    private static final int BLOCK_WIDTH = 52;
    private static final int BLOCK_HEIGHT = 30;
    private static final int GUI_HEIGHT = 600;
    private static final int BLOCK_X_START = 322;
    private static final int BLOCK_Y_START = 150;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        lst.add(Velocity.fromAngleAndSpeed(-45, 8));
        lst.add(Velocity.fromAngleAndSpeed(45, 8));
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> lst = new ArrayList<>();
        int blockX = BLOCK_X_START;
        int blockY = BLOCK_Y_START;
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Block block = new Block(new Rectangle(new Point(blockX, blockY), BLOCK_WIDTH, BLOCK_HEIGHT),
                        new Color(i * 30, 100, 50));
                lst.add(block);
                blockX += BLOCK_WIDTH;
            }
            blockX = BLOCK_WIDTH * i + BLOCK_X_START;
            blockY += BLOCK_HEIGHT;
        }
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
