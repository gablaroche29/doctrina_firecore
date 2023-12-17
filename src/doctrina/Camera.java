package doctrina;

import utopia.World;

import java.awt.*;

public class Camera extends StaticEntity implements Runnable {

    private final World world;
    private final ControllableEntity entity;
    private int entityX, entityY;
    private int destinationX, destinationY;
    private Thread cameraThread;

    public Camera(World world, ControllableEntity entity, int width, int height) {
        this.world = world;
        this.entity = entity;
        this.width = width;
        this.height = height;

        startCameraThread();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(0.9f, 0, 0, 0.40f));
    }

    public void stopCameraThread() {
        cameraThread.interrupt();
    }

    public void update() {
        updateNewPositionPlayer();
        updateNewDestination();
        setPosition();
    }

    private void startCameraThread() {
        cameraThread = new Thread(this);
        cameraThread.start();
    }

    private void updateNewDestination() {
        destinationX = entityX - (width / 2);
        destinationY = entityY - (height / 2);
        verifyOutOfBounds();
    }

    private void verifyOutOfBounds() {
        if (destinationX < 0) {
            destinationX = 0;
        } else if (destinationX + width > world.getWidth()) {
            destinationX = world.getWidth() - width;
        }
        if (destinationY < 0) {
            destinationY = 0;
        } else if (destinationY + height > world.getHeight()) {
            destinationY = world.getHeight() - height;
        }
    }

    private void updateNewPositionPlayer() {
        entityX = entity.getX() + (entity.getWidth() / 2);
        entityY = entity.getY() + (entity.getHeight() / 2);
    }

    private void setPosition() {
        this.x = destinationX;
        this.y = destinationY;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            update();
        }
    }
}
