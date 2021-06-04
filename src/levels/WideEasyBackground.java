package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import sprite.Sprite;

import java.awt.*;

public class WideEasyBackground implements Sprite {
    private static final int BLOCK_Y_START = 250;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 233, 167));
        d.fillCircle(120, 120, 60);
        for (int i = 700; i > 0; i -= 6) {
            d.drawLine(120, 120, i, BLOCK_Y_START);
        }
        d.setColor(new Color(246, 205, 25));
        d.fillCircle(120, 120, 50);
        d.setColor(new Color(250, 220, 88));
        d.fillCircle(120, 120, 40);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
