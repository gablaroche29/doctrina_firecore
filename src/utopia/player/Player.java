package utopia.player;

import doctrina.*;
import doctrina.Canvas;
import doctrina.ControllableEntity;
import utopia.GamePad;
import utopia.audio.SoundEffect;

import java.awt.*;

public class Player extends ControllableEntity {

    private PlayerAnimationHandler animationHandler;
    private boolean hasAttacked;
    private int attackCoolDown = 0;

    private int pv = 5;
    private int crystal = 0;

    public Player(MovementController controller, int x, int y) {
        super(controller, 5);
        teleport(x, y);
        controller.useWasdKeys();
        setDimension(32, 32);
        setSpeed(2);
        animationHandler = new PlayerAnimationHandler(this);
        setDirection(Direction.DOWN);

        state = State.IDLE;
    }

    @Override
    public void update() {
        super.update();
        if (!hasAttacked) {
            moveWithController();
        }

        updateAttackCooldown();
        if (GamePad.getInstance().isSpacePressed()) {
            hasAttacked = true;
            attackCoolDown = 40;
            SoundEffect.MELEE_SWORD.play();
        }

        if (GamePad.getInstance().isPPressed()) {
            pv = 5;
        }
        updateAnimationState();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(getAnimationFrame(), x - camera.getX(), y - camera.getY());

        if (GameConfig.isDebugEnabled()) {
            drawHitBox(canvas, camera);
            drawCollisionDetector(canvas, camera);
            if (hasAttacked) {
                drawAttackZone(canvas, camera);
            }
        }
    }

    public int getPv() {
        return pv;
    }

    public void addCrystal(int crystal) {
        this.crystal += crystal;
    }

    public int getCrystal() {
        return crystal;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void dropPv() {
        pv--;
        if (pv <= 0 ) {
            pv = 0;
        }
    }

    private void updateAnimationState() {
        State currentState = state;
        if (hasMoved()) {
            state = State.MOVE;
            animationHandler.update();
        } else if (hasAttacked) {
            state = State.ATTACK;
            animationHandler.update();
        } else {
            state = State.IDLE;
            animationHandler.update();
        }

        if (currentState != state) {
            animationHandler.reset();
        }
    }

    private void updateAttackCooldown() {
        attackCoolDown--;
        if (attackCoolDown <= 0) {
            attackCoolDown = 0;
            hasAttacked = false;
        }
    }

    private Image getAnimationFrame() {
        if (hasAttacked) {
            return animationHandler.getAttackFrame();
        }
        if (hasMoved()) {
            return animationHandler.getDirectionFrame();
        }
        return animationHandler.getIdleFrame();
    }

}
