package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/characters/monsters.png";
    private AnimationHandler animationHandler;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
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
    public void draw(Canvas canvas) {
        Image sprite = animationHandler.getDirectionSprite(getDirection());
        canvas.drawImage(sprite, x, y);
    }

    private void loadAnimationHandler() {
        animationHandler = new AnimationHandler();
        animationHandler.setDownAnimation(new Animation(0, 0,
                32, 32, 3, SPRITE_PATH));
        animationHandler.setUpAnimation(new Animation(0, 96,
                32, 32, 3, SPRITE_PATH));
        animationHandler.setLeftAnimation(new Animation(0, 32,
                32, 32, 3, SPRITE_PATH));
        animationHandler.setRightAnimation(new Animation(0, 64,
                32, 32, 3, SPRITE_PATH));
    }
}
