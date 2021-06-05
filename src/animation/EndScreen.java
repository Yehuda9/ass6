package animation;

import biuoop.DrawSurface;

public class EndScreen implements Animation {
    private boolean WinOrLose;
    private int score;

    public EndScreen(boolean wnLs, int s) {
        this.WinOrLose = wnLs;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.WinOrLose) {
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
