package observer;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Block;
import sprite.Sprite;

import java.awt.Color;

/**
 * @author Yehuda Schwartz 208994285.
 */
public class ScoreIndicator implements Sprite {
    private Block block;
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param block the block
     * @param score the score
     */
    public ScoreIndicator(Block block , Counter score) {
        this.block = block;
        this.score = score;
    }

    /*draw block and then draw score text*/
    @Override
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
        d.setColor(Color.black);
        d.drawText(350 , 23 , "Score: " + score.getValue() , 20);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
