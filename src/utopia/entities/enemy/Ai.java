package utopia.entities.enemy;

import doctrina.*;
import doctrina.Canvas;
import utopia.player.Player;
import utopia.sounds.SoundEffect;

import java.awt.*;

public class Ai extends MovableEntity {

    private final Player player;
    private Bounds triggerZone;
    private AiAnimationHandler animationHandler;
    private boolean moving;
    private boolean isAlive = true;
    private int attackCooldown = 0;

    public Ai(int x, int y, float speed, Player player) {
        super(2);
        teleport(x, y);
        setSpeed(speed);
        setDimension(32, 32);
        setDirection(Direction.DOWN);

        this.player = player;

        setTriggerZone();
        loadAnimationHandler();

        state = State.MOVE;

    }

    @Override
    public void update() {
        super.update();
        updateTriggerZone();

        attackCooldown--;
        if (attackCooldown <= 0) {
            attackCooldown = 0;
        }

        if (triggerZone.intersectsWith(player)) {
            moving = true;
        }

        if (moving) {
            moving();
        }

        if (player.hasAttacked()) {
            if (intersectWith(player.getAttackZone())) {
                isAlive = false;
            }
        }

        if (getAttackZone().intersectsWith(player) && attackCooldown == 0) {
            attackCooldown = 60;
            player.dropPv();
            SoundEffect.MONSTER_ATTACK.play();
        }

        animationHandler.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Image sprite = animationHandler.getDirectionFrame();
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());

        if (GameConfig.isDebugEnabled()) {
            drawCollisionDetector(canvas, camera);
            drawHitBox(canvas, camera);
            drawAttackZone(canvas, camera);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void moving() {
        int distanceX = (x + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
        int distanceY = (y + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
        Direction playerDirection = calculatePlayerDirection();

        if (distanceX != 0 || distanceY != 0) {
            Direction directionToGo;
            if (playerDirectionIsCorrupted(playerDirection)) {
                directionToGo = simulateNewDirection(distanceX, distanceY);
            } else {
                directionToGo = playerDirection;
            }
            move(directionToGo);
        }
    }

    private boolean playerDirectionIsCorrupted(Direction playerDirection) {
        int lastX = getX();
        int lastY = getY();
        move(playerDirection);
        if (!hitBoxIntersectsWithCollisions()) {
            teleport(lastX, lastY);
            return false;
        } else {
            teleport(lastX, lastY);
            return true;
        }
    }

    private boolean hitBoxIntersectsWithCollisions() {
        for (StaticEntity entity : CollidableRepository.getInstance()) {
            if (hitBoxIntersectWidth(entity)) {
                return true;
            }
        }
        return false;
    }

    private Direction simulateNewDirection(int distanceX, int distanceY) {
        if ((distanceX > 0 && distanceY > 0) || (distanceX > 0 && distanceY < 0)) {
            return Direction.LEFT;
        }
        if ((distanceX < 0 && distanceY > 0) || (distanceX < 0 && distanceY < 0)) {
            return Direction.RIGHT;
        }
        return Direction.UP;
    }

    private Direction calculatePlayerDirection() {
        if ((player.getY() + player.getHeight() / 2) < (y + getHeight() / 2)) {
            return Direction.UP;
        } else if ((player.getY() + player.getHeight() / 2) > (y + getHeight() / 2)) {
            return Direction.DOWN;
        } else if ((player.getX() + player.getWidth() / 2) < (x + getWidth() / 2)) {
            return Direction.LEFT;
        } else if ((player.getX() + player.getWidth() / 2) > (x + getWidth() / 2)) {
            return Direction.RIGHT;
        }
        return Direction.DOWN;
    }

    private void setTriggerZone() {
        final int diameter = 300;
        triggerZone = new Bounds(x + (width / 2) - (diameter / 2), y + (height / 2) - (diameter / 2),
                diameter, diameter);
    }

    private Bounds getTriggerZone() {
        return triggerZone;
    }

    private void updateTriggerZone() {
        triggerZone.setCoords(x + (width / 2) - (triggerZone.getWidth() / 2), y + (height / 2) - (triggerZone.getWidth() / 2));
    }

    private void loadAnimationHandler() {
        animationHandler = new AiAnimationHandler(this);
    }

}
