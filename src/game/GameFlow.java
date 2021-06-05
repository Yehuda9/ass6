package game;

import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import observer.Counter;

import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score);
            System.out.println(level.getLevelInformation().levelName());
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() == 0) {
                break;
            }

        }
    }
}


