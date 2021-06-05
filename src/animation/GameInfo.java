package animation;

import biuoop.DrawSurface;
import game.GameLevel;
import observer.Counter;
import sprite.Sprite;

import java.awt.*;

public class GameInfo implements Sprite {
    private Counter lives;

    public GameInfo(Counter l) {
        this.lives = l;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(500, 20, String.valueOf(this.lives.getValue()), 20);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
