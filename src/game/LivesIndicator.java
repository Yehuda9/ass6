package game;

import biuoop.DrawSurface;
import observer.Counter;
import sprite.Sprite;

public class LivesIndicator implements Sprite {
    private Counter lives;

    public LivesIndicator(Counter l) {
        this.lives = l;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(500, 20, String.valueOf(this.lives.getValue()), 20);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
