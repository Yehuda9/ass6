package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Direct hit background.
 */
public class DirectHitBackground implements Sprite {
    public static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
        d.setColor(Color.BLUE);
        d.drawCircle(GUI_WIDTH / 2, 160, 55);
        d.drawCircle(GUI_WIDTH / 2, 160, 90);
        d.drawCircle(GUI_WIDTH / 2, 160, 120);
        d.drawLine(270, 160, 530, 160);
        d.drawLine(GUI_WIDTH / 2, 30, GUI_WIDTH / 2, 290);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
