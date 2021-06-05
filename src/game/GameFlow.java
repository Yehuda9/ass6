package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import observer.Counter;

import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;


    public GameFlow() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.animationRunner = new AnimationRunner(gui, 60);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.score = new Counter(0);
        this.lives = new Counter(2);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, this.lives);
            level.initialize();

            while (this.lives.getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
                if (level.getRemainingBlocks().getValue() > 0) {
                    this.lives.decrease(1);
                }
            }

            if (this.lives.getValue() == 0) {
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new EndScreen(lives.getValue() != 0, score.getValue())));
        gui.close();
    }
}


