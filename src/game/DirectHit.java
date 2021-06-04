package game;

import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        lst.add(Velocity.fromAngleAndSpeed(0, 7));
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(24, 47, 78));
    }

    @Override
    public List<Block> blocks() {
        List<Block> lst = new LinkedList<>();
        lst.add(new Block(new Rectangle(new Point(400, 400), 50, 50), Color.black));
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
