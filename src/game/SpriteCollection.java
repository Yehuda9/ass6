package game;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yehuda Schwartz
 */
public class SpriteCollection {
    private List<Sprite> spritesCollection;

    /**
     * create new sprites collection.
     */
    public SpriteCollection() {
        this.spritesCollection = new LinkedList<>();
    }

    /**
     * @return spritesCollection
     */
    public List<Sprite> getSpritesCollection() {
        return spritesCollection;
    }

    /**
     * add new sprite to list.
     *
     * @param s sprite to add to sprites collection
     */
    public void addSprite(Sprite s) {
        spritesCollection.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new LinkedList<>(getSpritesCollection());
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d draw surface to draw sprites on it.
     */
    public void drawAllOn(DrawSurface d) {
        d.setColor(new Color(24, 47, 78));
        d.fillRectangle(0, 0, 800, 600);
        for (Sprite sprite : this.spritesCollection) {
            sprite.drawOn(d);
        }
    }
}