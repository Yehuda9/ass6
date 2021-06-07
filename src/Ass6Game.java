import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import levels.DirectHit;
import game.GameLevel;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        Ass6Game ass6Game = new Ass6Game();
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(ass6Game.argsToLevels(args));
    }

    public List<LevelInformation> argsToLevels(String[] args) {
        List<LevelInformation> result = new LinkedList<>();
        Map<String, LevelInformation> levelInformations = new HashMap<>();
        levelInformations.put("1", new DirectHit());
        levelInformations.put("2", new WideEasy());
        levelInformations.put("3", new Green3());
        levelInformations.put("4", new FinalFour());
        for (String s : args) {
            if (levelInformations.containsKey(s)) {
                result.add(levelInformations.get(s));
            } else {
                System.out.println("not a level");
            }
        }
        if (result.isEmpty()) {
            args = new String[]{"1", "2", "3", "4"};
            return argsToLevels(args);
        }
        return result;
    }
}
