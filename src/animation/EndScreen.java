package animation;

import biuoop.DrawSurface;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean winOrLose;
    private int score;

    /**
     * Instantiates a new End screen.
     *
     * @param wnLs win or lose
     * @param s    score
     */
    public EndScreen(boolean wnLs, int s) {
        this.winOrLose = wnLs;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.winOrLose) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
