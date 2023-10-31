package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "images/characters/characters.png";
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
    public void draw(Canvas canvas, Camera camera) {
        Image sprite = animationHandler.getDirectionSprite(getDirection());
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());

        if (GameConfig.isDebugEnabled()) {
            drawHitBox(canvas, camera);
            drawCollisionDetector(canvas, camera);
        }
    }

    private void loadAnimationHandler() {
        animationHandler = new AnimationHandler();
        animationHandler.setDownAnimation(new Animation(192, 0,
                32, 32, 3, SPRITE_PATH));
        animationHandler.setLeftAnimation(new Animation(192, 32,
                32, 32, 3, SPRITE_PATH));
        animationHandler.setRightAnimation(new Animation(192, 64,
                32, 32, 3, SPRITE_PATH));
        animationHandler.setUpAnimation(new Animation(192, 96,
                32, 32, 3, SPRITE_PATH));
    }
}
