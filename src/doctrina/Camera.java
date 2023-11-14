package doctrina;

import java.awt.*;

public class Camera extends StaticEntity implements Runnable {

    private final ControllableEntity entity;
    private int entityX, entityY;
    private int destinationX, destinationY;
    private Thread cameraThread;

    public Camera(ControllableEntity entity, int width, int height) {
        this.entity = entity;
        this.width = width;
        this.height = height;

        startCameraThread();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x, y, width, height, new Color(255, 0, 0));
    }

    public void stopCameraThread() {
        cameraThread.interrupt();
    }

    public void update() {
        updateNewPositionPlayer();
        updateNewDestination();

        if (destinationX < 500) {
            destinationX = 500;
        }
        setPosition(destinationX, destinationY);
    }

    public boolean isItInArea(StaticEntity entity) {
        return intersectWith(entity);
    }

    private void startCameraThread() {
        cameraThread = new Thread(this);
        cameraThread.start();
    }

    private void updateNewDestination() {
        destinationX = entityX - (width / 2);
        destinationY = entityY - (height / 2);
    }

    private void updateNewPositionPlayer() {
        entityX = entity.getX() + (entity.getWidth() / 2);
        entityY = entity.getY() + (entity.getHeight() / 2);
    }

    private void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            update();
        }
    }
}
