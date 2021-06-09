package animation;

import biuoop.DrawSurface;
import game.SpriteCollection;

/**
 * The type Countdown animation.
 * <p>
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 * <p/>
 */
public class CountdownAnimation implements Animation {
    private double startTime;
    private double numOfSeconds;
    private int countFrom;
    private int numOfCounts;
    private SpriteCollection gameScreen;
    private boolean stop;
    private boolean first;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfCounts = countFrom;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.first = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.first) {
            this.startTime = System.currentTimeMillis();
            this.first = false;
        }
        gameScreen.drawAllOn(d);
        d.drawText((d.getWidth() / 2) - 13, (d.getHeight() / 2) - 20, String.valueOf(this.countFrom), 50);
        double currentTime = System.currentTimeMillis();
        if (currentTime - this.startTime > numOfSeconds / numOfCounts) {
            countFrom--;
            this.first = true;
        }
        if (this.countFrom <= 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

