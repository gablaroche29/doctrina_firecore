package doctrina;

import heavenly.MonsterAnimationHandler;
import heavenly.Player;

import java.awt.*;

public class Ia extends MovableEntity {

    private Player player;
    private Bounds triggerZone;
    private MonsterAnimationHandler animationHandler;
    private Direction directionToGo;
    private boolean moving;

    public Ia(int x, int y, float speed, Player player) {
        this.x = x;
        this.y = y;
        setSpeed(speed);
        setDimension(32, 32);

        this.player = player;

        setTriggerZone();
        loadAnimationHandler();
    }

    @Override
    public void update() {
        super.update();
        updateTriggerZone();

        if (triggerZone.intersectsWith(player)) {
            moving = true;
        }

        if (moving) {
            moving();
        }
        animationHandler.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
//        canvas.drawRectangle(triggerZone.x - camera.getX(),
//                triggerZone.y - camera.getY(),
//                triggerZone.width, triggerZone.height, new Color(0, 0, 255, 200));
        Image sprite;
//        if (hasMoved()) {
//            sprite = animationHandler.getDirectionSprite(getDirection());
//        } else {
//            sprite = animationHandler.getIdleSprite();
//        }
        sprite = animationHandler.getDirectionSprite();
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
        if (GameConfig.isDebugEnabled()) {
            drawCollisionDetector(canvas, camera);
            drawHitBox(canvas, camera);
        }
    }

    private void moving() {
        int distanceX = (x + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
        int distanceY = (y + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
        Direction playerDirection = calculatePlayerDirection();

        if (distanceX != 0 || distanceY != 0) {
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
        animationHandler = new MonsterAnimationHandler(this);
    }

}
