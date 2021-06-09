package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import observer.Counter;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;


    /**
     * Instantiates a new Game flow.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.animationRunner = new AnimationRunner(gui, 60);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.score = new Counter(0);
        this.lives = new Counter(7);
    }

    /**
     * Run levels.
     * <p>
     * iterate levels from list and invoke each level run method till no more lives.
     * <p/>
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, this.lives);
            level.initialize();
            //while still remain lives and still remain blocks, run level.
            while (this.lives.getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
                //when one turn ends with blocks still on the screen, decrease lives.
                if (level.getRemainingBlocks().getValue() > 0) {
                    this.lives.decrease(1);
                }
            }
            //if no more lives remains break to end screen.
            if (this.lives.getValue() == 0) {
                break;
            }
        }
        //invoke end screen with information about win or lose.
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                new EndScreen(lives.getValue() != 0, score.getValue())));
        gui.close();
    }
}


