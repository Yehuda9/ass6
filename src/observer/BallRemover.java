package observer;

import game.GameLevel;
import sprite.Ball;
import sprite.Block;

/**
 * @author Yehuda Schwartz 208994285
 * in charge of removimg from the game balls that fell down, and also keeping count of remaining balls.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        remainingBalls = removedBalls;
    }

    /*remove the ball that fell out of screen, and decrease balls counter*/
    @Override
    public void hitEvent(Block beingHit , Ball hitter) {
        gameLevel.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}

