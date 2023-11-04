package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Player extends ControllableEntity {

    private PlayerAnimationHandler animationHandler;

    public Player(MovementController controller) {
        super(controller);
        controller.useWasdKeys();
        setDimension(32, 32);
        setSpeed(2);
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
        animationHandler = new PlayerAnimationHandler(this);
    }
}
