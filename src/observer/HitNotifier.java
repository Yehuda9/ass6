package observer;

/**
 * @author Yehuda Schwartz 208994285
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener to remove to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener to remove
     */
    void removeHitListener(HitListener hl);
}