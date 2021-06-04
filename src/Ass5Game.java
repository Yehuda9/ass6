import levels.DirectHit;
import game.GameLevel;
import levels.WideEasy;

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
        GameLevel gameLevel = new GameLevel(new WideEasy());
        gameLevel.initialize();
        gameLevel.run();
    }
}
