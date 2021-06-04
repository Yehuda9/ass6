import game.DirectHit;
import game.GameLevel;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Ass5Game {
    /**
     * main function to initialize and run game.
     *
     * @param args commend line arguments
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(new DirectHit());
        gameLevel.initialize();
        gameLevel.run();
    }
}
