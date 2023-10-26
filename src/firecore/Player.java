package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/characters/boy.png";
    private AnimationHandler animationHandler;

    public Player(MovementController controller) {
        super(controller);
        setDimension(16, 16);
        setSpeed(3);
        loadAnimationHandler();
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        if (hasMoved()) {
            animationHandler.update();

        } else {
            animationHandler.reset();
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Image sprite = animationHandler.getDirectionSprite(getDirection());
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
    }

    private void loadAnimationHandler() {
        animationHandler = new AnimationHandler();
        animationHandler.setDownAnimation(new Animation(0, 0,
                16, 16, 3, SPRITE_PATH));
        animationHandler.setUpAnimation(new Animation(0, 96,
                16, 16, 3, SPRITE_PATH));
        animationHandler.setLeftAnimation(new Animation(0, 32,
                16, 16, 3, SPRITE_PATH));
        animationHandler.setRightAnimation(new Animation(0, 64,
                16, 16, 3, SPRITE_PATH));
    }
}
