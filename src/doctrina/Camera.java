package doctrina;

import firecore.World;

import java.awt.*;

public class Camera extends StaticEntity implements Runnable {

    private final ControllableEntity entity;
    private final World world;

    public Camera(ControllableEntity entity, World world, int width, int height) {
        this.entity = entity;
        this.world = world;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(0, 0, width, height, new Color(255, 0, 0, 100));
    }

    public void update() {
        int entityX = entity.getX() + (entity.getWidth() / 2);
        int entityY = entity.getY() + (entity.getHeight() / 2);
        int tempX = entityX - (width / 2);
        int tempY = entityY - (height / 2);

        if (tempX < world.getLimitX()) {
            tempX = world.getLimitX();
        }

        if (tempY > world.getLimitY()) {
            tempY = world.getLimitY();
        }

        setDestination(tempX, tempY);
    }

    public boolean isItInArea(StaticEntity entity) {
        return intersectWith(entity);
    }

    private void setDestination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (true) {
            update();
        }
    }
}
