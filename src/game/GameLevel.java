package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.GameInfo;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import observer.BallRemover;
import observer.BlockRemover;
import observer.Counter;
import observer.ScoreTrackingListener;
import sprite.Ball;
import sprite.Block;
import sprite.Collidable;
import sprite.Paddle;
import sprite.Sprite;
import geometry.Rectangle;
import geometry.Point;

import java.awt.Color;

/**
 * @author Yehuda Schwartz 208994285
 */
public class GameLevel implements Animation {
    public static final int GUI_WIDTH = 800;
    public static final int BALLS_RADIUS_OR_SPEED = 5;
    public static final int FRAME_SIZE = 10;
    private static final int GUI_HEIGHT = 600;
    private static final int PADDLE_SIZE = 20;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private Paddle paddle;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private Counter lives;


    /**
     * create new game object with new game.SpriteCollection and game.GameEnvironment.
     *
     * @param lvlInfo        holds level info reference
     * @param keyboardSensor keyboard sensor
     * @param ar             animation runner
     * @param l              lives counter
     * @param score          score counter
     */
    public GameLevel(LevelInformation lvlInfo, KeyboardSensor keyboardSensor, AnimationRunner ar, Counter score,
                     Counter l) {
        this.levelInformation = lvlInfo;
        this.keyboard = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(levelInformation.blocks().size());
        this.remainingBalls = new Counter(lvlInfo.numberOfBalls());
        this.currentScore = score;
        this.runner = ar;
        this.running = true;
        this.lives = l;
    }

    /**
     * getRemainingBlocks.
     *
     * @return remainingBlocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * get SpriteCollection.
     *
     * @return SpriteCollection
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Gets environment.
     *
     * @return GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }


    /**
     * add collidable to game environment.
     *
     * @param c collidable to add to game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite to sprite collection.
     *
     * @param s sprite to add to sprite collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprite.Ball (and sprite.Paddle) and add them to the game.
     */
    public void initialize() {
        levelInformation.getBackground().addToGame(this);
        initializePaddle();
        initializeBlocks();
        initializeBorders();
        initializeGameInfo();
    }

    /**
     * create game info block from block and counter.
     * game info hold reference to score, lives and level name and show them on the screen.
     */
    private void initializeGameInfo() {
        Block gameInfoBlock = new Block(new Rectangle(new Point(0, 0), GUI_WIDTH, FRAME_SIZE + 20), Color.white);
        GameInfo gameInfo =
                new GameInfo(gameInfoBlock, this.currentScore, this.lives, this.levelInformation.levelName());
        gameInfo.addToGame(this);
    }

    /**
     * create borders blocks for the gui and death block for the bottom border.
     */
    private void initializeBorders() {
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        Block block1 = new Block(new Rectangle(new Point(0, 30), GUI_WIDTH, FRAME_SIZE), Color.gray);
        Block block2 = new Block(new Rectangle(new Point(0, 0), FRAME_SIZE, GUI_HEIGHT + 50), Color.gray);
        Block block4 =
                new Block(new Rectangle(new Point(GUI_WIDTH - FRAME_SIZE, 0), FRAME_SIZE, GUI_HEIGHT + 50), Color.gray);
        block1.addToGame(this);
        block2.addToGame(this);
        block4.addToGame(this);
        Block deathRegion =
                new Block(new Rectangle(new Point(0, GUI_HEIGHT - FRAME_SIZE + 30), GUI_WIDTH, FRAME_SIZE), Color.gray);
        deathRegion.addHitListeners(ballRemover);
        deathRegion.addToGame(this);
    }

    /**
     * create paddle.
     */
    private void initializePaddle() {
        this.paddle = new Paddle(new Block(new Rectangle(
                new Point(400 - levelInformation.paddleWidth() / 2.0, GUI_HEIGHT - PADDLE_SIZE - FRAME_SIZE),
                levelInformation.paddleWidth(), PADDLE_SIZE), new Color(243, 182, 41)), this.keyboard,
                levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * iterate block list from level information add each block to the game and add listeners to the blocks.
     */
    private void initializeBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(currentScore);
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListeners(blockRemover);
            block.addHitListeners(scoreTrackingListener);
        }
    }

    /**
     * create balls from list of velocities given by level information.
     */
    private void createBallsOnTopOfPaddle() {
        this.remainingBalls.setCounter(levelInformation.numberOfBalls());
        //x position of ball is always center of paddle.
        int ballX = (int) this.paddle.getCollisionRectangle().getUpperLeft().getX()
                + (int) this.paddle.getCollisionRectangle().getUpperLine().length() / 2;
        for (int i = 0; i < this.remainingBalls.getValue(); i++) {
            Ball ball =
                    new Ball(ballX, this.paddle.getCollisionRectangle().getUpperLeft().getY() - BALLS_RADIUS_OR_SPEED,
                            BALLS_RADIUS_OR_SPEED, Color.white, levelInformation.initialBallVelocities().get(i),
                            this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * remove collidable from collidable collection.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        getEnvironment().getCollidableCollection().remove(c);
    }

    /**
     * remove sprite from sprite collection.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        getSprites().getSpritesCollection().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw all sprites
        this.sprites.drawAllOn(d);
        //make every element in the game to do its work
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY, new PauseScreen()));
        }
        //stop the level and increase 100 points if no remaining blocks
        if (remainingBlocks.getValue() == 0) {
            currentScore.increase(100);
            this.running = false;
        }
        //stop the level if no remaining balls
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * run the level.
     * set paddle to bottom center of screen, create balls, run countdown animation and finally run level animation.
     */
    public void run() {
        this.paddle.setXPosition(GUI_WIDTH / 2 - levelInformation.paddleWidth() / 2);
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2000, 3, getSprites()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
}