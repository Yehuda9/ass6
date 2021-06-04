package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.*;

public class WideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
