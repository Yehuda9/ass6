package observer;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param number the number
     */
    public Counter(int number) {
        this.counter = number;
    }

    public void setCounter(int c) {
        this.counter = c;
    }

    /**
     * add number to current count.
     *
     * @param number number
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return counter;
    }
}
