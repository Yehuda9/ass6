package sprite;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * @author Yehuda Schwartz
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d draw surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add sprite to game.
     *
     * @param gameLevel to add sprite to
     */
    void addToGame(GameLevel gameLevel);
}