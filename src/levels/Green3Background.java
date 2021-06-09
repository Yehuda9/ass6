package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Green 3 background.
 */
public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(43, 109, 24));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.fillRectangle(50, 450, 100, 150);
        d.setColor(new Color(33, 33, 33));
        d.fillRectangle(50, 450, 100, 10);
        d.fillRectangle(50, 450, 10, 150);
        d.fillRectangle(150, 450, 10, 150);
        int y = 480;
        int x = 75;
        for (int i = 0; i < 4; i++) {
            d.fillRectangle(50, y, 100, 8);
            y += 30;
            d.fillRectangle(x, 450, 8, 150);
            x += 25;
        }
        d.setColor(new Color(47, 47, 47));
        d.fillRectangle(89, 400, 30, 50);
        d.setColor(new Color(56, 56, 56));
        d.fillRectangle(99, 200, 10, 200);
        d.setColor(new Color(132, 114, 114));
        d.fillCircle(104, 215, 15);
        d.setColor(new Color(128, 85, 85));
        d.fillCircle(104, 215, 10);
        d.setColor(new Color(161, 143, 143));
        d.fillCircle(104, 215, 5);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
