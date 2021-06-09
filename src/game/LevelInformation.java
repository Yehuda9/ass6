package game;

import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls.
     *
     * @return amount of ball in level.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return list with the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     *
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return Paddle width
     */
    int paddleWidth();

    /**
     * Level name.
     * the level name will be displayed at the top of the screen.
     *
     * @return level name
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * Blocks list.
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return list of blocks
     */

    List<Block> blocks();

    /**
     * Number of blocks to remove.
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return Number of blocks to remove
     */
    int numberOfBlocksToRemove();
}

