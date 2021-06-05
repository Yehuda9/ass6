import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import levels.DirectHit;
import game.GameLevel;
import levels.Green3;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

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
        GUI gui = new GUI("Arkanoid", 800, 600);
        List<LevelInformation> levelInformations = new ArrayList<>();
        levelInformations.add(new DirectHit());
        levelInformations.add(new WideEasy());
        levelInformations.add(new Green3());
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui.getKeyboardSensor());
        gameFlow.runLevels(levelInformations);
    }
}
