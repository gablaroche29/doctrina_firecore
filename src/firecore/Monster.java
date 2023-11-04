package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Monster extends MovableEntity {

    private MonsterAnimationHandler animationHandler;
    private final Player player;

    public Monster(Player player) {
        setDimension(32, 32);
        setSpeed(1.5f);
        this.player = player;
        loadAnimationHandler();
    }

    @Override
    public void update() {
        super.update();
        int distanceX = (x + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
        int distanceY = (y + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
        if (distanceX != 0 || distanceY != 0) {
            move(calculatePlayerDirection());
        }

        animationHandler.update();
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
