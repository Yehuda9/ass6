package observer;

import game.GameLevel;
import sprite.Ball;
import sprite.Block;

/**
 * @author Yehuda Schwartz 208994285
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel     the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        remainingBlocks = removedBlocks;
    }

    /*Blocks that are hit should be removed from the game, update counter of remaining blocks*/
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
