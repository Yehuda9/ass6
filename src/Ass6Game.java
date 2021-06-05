import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import levels.DirectHit;
import game.GameLevel;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yehuda Schwartz 208994285
 */
public class Ass6Game {
    /**
     * main function to initialize and run game.
     *
     * @param args commend line arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levelInformations = new ArrayList<>();
        levelInformations.add(new FinalFour());
        levelInformations.add(new DirectHit());
        levelInformations.add(new WideEasy());
        levelInformations.add(new Green3());
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levelInformations);
    }
}
