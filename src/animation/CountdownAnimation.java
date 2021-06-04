package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int numOfCounts;
    private SpriteCollection gameScreen;
    private boolean stop;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        numOfCounts = countFrom;
        this.stop = false;
    }

    public void doOneFrame(DrawSurface d) {
        if (this.countFrom == 0) {
            this.stop = true;
        }
        Sleeper sleeper = new Sleeper();
        gameScreen.drawAllOn(d);
        d.drawText(390, 280, String.valueOf(this.countFrom--), 50);
        sleeper.sleepFor((long) (numOfSeconds / numOfCounts));
    }

    public boolean shouldStop() {
        return this.stop;
    }
}

