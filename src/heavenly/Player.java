package heavenly;

import doctrina.*;
import doctrina.Canvas;
import doctrina.ControllableEntity;

import java.awt.*;

public class Player extends ControllableEntity {

    private PlayerAnimationHandler animationHandler;
    private boolean hasAttacked;
    private int attackCoolDown = 0;

    public Player(MovementController controller, int x, int y) {
        super(controller);
        teleport(x, y);
        controller.useWasdKeys();
        setDimension(32, 32);
        setSpeed(2);
        loadAnimationHandler();
        setDirection(Direction.DOWN);

        state = State.IDLE;
    }

    @Override
    public void update() {
        super.update();
        if (!hasAttacked) {
            moveWithController();
        }

        attackCoolDown--;
        if (attackCoolDown <= 0) {
            attackCoolDown = 0;
            hasAttacked = false;
        }

        if (getController().isSpacePressed()) {
            hasAttacked = true;
            attackCoolDown = 40;
        }

        if (hasMoved()) {
            state = State.MOVE;
            animationHandler.update();
        } else if (hasAttacked) {
            state = State.ATTACK;
            animationHandler.update();
        } else {
            state = State.IDLE;
            animationHandler.reset();
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Image sprite;
        if (hasAttacked) {
            sprite = animationHandler.getAttackFrame();
        } else {
            sprite = animationHandler.getDirectionSprite();
        }
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
