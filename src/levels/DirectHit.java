package levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectHit implements LevelInformation {
    public static final int GUI_WIDTH = 800;
    public static final int PADDLE_SPEED = 7;
    private static final int GUI_HEIGHT = 600;
    private static final int PADDLE_WIDTH = 150;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 50;


    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        lst.add(Velocity.fromAngleAndSpeed(0, 7));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> lst = new LinkedList<>();
        lst.add(new Block(new Rectangle(new Point(GUI_WIDTH / 2.0 - BLOCK_WIDTH / 2.0, 135), BLOCK_WIDTH, BLOCK_HEIGHT),
                Color.red));
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
