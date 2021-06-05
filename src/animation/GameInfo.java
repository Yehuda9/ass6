package animation;

import biuoop.DrawSurface;
import game.GameLevel;
import observer.Counter;
import sprite.Block;
import sprite.Sprite;

import java.awt.Color;

public class GameInfo implements Sprite {
    private Block block;
    private Counter score;
    private Counter lives;
    private String levelName;

    public GameInfo(Block b, Counter s, Counter l, String name) {
        this.block = b;
        this.score = s;
        this.lives = l;
        this.levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
        d.setColor(Color.black);
        d.drawText(350, 23, "Score: " + score.getValue(), 20);
        d.drawText(100, 23, "Lives: " + this.lives.getValue(), 20);
        d.drawText(550, 23, "Level Name: " + this.levelName, 20);

    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
