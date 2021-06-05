package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String k, Animation a) {
        this.keyboardSensor = sensor;
        this.key = k;
        this.animation = a;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

