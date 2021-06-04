import levels.DirectHit;
import game.GameLevel;
import levels.Green3;
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
        GameLevel gameLevel = new GameLevel(new Green3());
        gameLevel.initialize();
        gameLevel.run();
    }
}
