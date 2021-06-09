package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Final four background.
 */
public class FinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(14, 157, 193));
        d.fillRectangle(0, 0, 800, 600);

        for (int i = 0; i < 10; i++) {
            d.setColor(new Color(205, 205, 205));
            d.drawLine(110 + i * 10, 400, 80 + i * 10, 600);
        }

        d.setColor(new Color(205, 205, 205));
        d.fillCircle(100, 400, 20);

        d.setColor(new Color(198, 197, 197));
        d.fillCircle(120, 420, 22);

        d.setColor(new Color(199, 189, 189));
        d.fillCircle(140, 390, 30);

        d.setColor(new Color(158, 155, 155));
        d.fillCircle(150, 420, 20);

        d.setColor(new Color(160, 156, 156));
        d.fillCircle(180, 400, 35);

        for (int i = 0; i < 10; i++) {
            d.setColor(new Color(205, 205, 205));
            d.drawLine(630 + i * 10, 500, 600 + i * 10, 600);
        }

        d.setColor(new Color(205, 205, 205));
        d.fillCircle(620, 500, 20);

        d.setColor(new Color(198, 197, 197));
        d.fillCircle(640, 520, 22);

        d.setColor(new Color(199, 189, 189));
        d.fillCircle(660, 490, 30);

        d.setColor(new Color(158, 155, 155));
        d.fillCircle(670, 520, 20);

        d.setColor(new Color(160, 156, 156));
        d.fillCircle(700, 500, 35);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
