package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Monster extends MovableEntity {

    private MonsterAnimationHandler animationHandler;
    private final Player player;
    private Direction directionToGo;

    public Monster(Player player) {
        setDimension(32, 32);
        setSpeed(1f);
        this.player = player;
        loadAnimationHandler();
    }

    @Override
    public void update() {
        super.update();
        int distanceX = (x + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
        int distanceY = (y + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
        Direction playerDirection = calculatePlayerDirection();

        if (distanceX != 0 || distanceY != 0) {
            if (playerDirectionIsCorrupted(playerDirection)) {
                directionToGo = simulateNewDirection(distanceX, distanceY);
            } else {
                System.out.println(Text.purple("Going to the player direction..."));
                directionToGo = playerDirection;
            }
            move(directionToGo);
        }
        animationHandler.update();
    }

    private boolean playerDirectionIsCorrupted(Direction playerDirection) {
        int lastX = getX();
        int lastY = getY();
        Direction currentDirection = getDirection();
        move(playerDirection);
        if (!hitBoxIntersectsWithCollisions()) {
            return false;
        } else {
            teleport(lastX, lastY);
            //move(currentDirection);
            System.out.println(Text.red("Correcting position to the ancient one"));
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

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Image sprite;
        if (hasMoved()) {
            sprite = animationHandler.getDirectionSprite(getDirection());
        } else {
            sprite = animationHandler.getIdleSprite();
        }
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
        if (GameConfig.isDebugEnabled()) {
            drawCollisionDetector(canvas, camera);
            drawHitBox(canvas, camera);
        }
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

    private void loadAnimationHandler() {
        animationHandler = new MonsterAnimationHandler(this);
    }
}
