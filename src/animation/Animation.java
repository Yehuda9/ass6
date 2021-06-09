package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     * <p>
     * every animation with its frames to draw on draw surface.
     * </p>
     *
     * @param d draw surface to draw on it
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return whether the animation should stop.
     */
    boolean shouldStop();
}
