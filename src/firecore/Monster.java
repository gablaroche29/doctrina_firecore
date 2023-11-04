package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class Monster extends MovableEntity {

    private MonsterAnimationHandler animationHandler;
    private Player player;
    private Direction direction;
    private int coolDown = 5;

    public Monster(Player player) {
        setDimension(32, 32);
        setSpeed(3);
        this.player = player;
        loadAnimationHandler();
    }

    @Override
    public void update() {
        super.update();
        coolDown--;
        int distanceX = x - player.getX();
        int distanceY = y - player.getY();
        System.out.println("Distance X: " + distanceX + " / Distance Y: " + distanceY);

        if (distanceX != 0 && distanceY != 0) {
            if (player.getY() < y) {
                direction = Direction.UP;
            } else if (player.getY() > y + height) {
                direction = Direction.DOWN;
            } else if (player.getX() < x) {
                direction = Direction.LEFT;
            } else if (player.getX() > x) {
                direction = Direction.RIGHT;
            }
            move(direction);
        }

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
            drawCollisionDetector(canvas, camera);
            drawHitBox(canvas, camera);
        }
    }

    private void loadAnimationHandler() {
        animationHandler = new MonsterAnimationHandler(this);
    }
}
