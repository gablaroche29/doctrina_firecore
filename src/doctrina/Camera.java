package doctrina;

import utopia.World;

import javax.sound.sampled.Clip;
import java.awt.*;

public class Camera extends StaticEntity {

    private final World world;
    private final CameraAnimation animation;
    private int entityX, entityY;
    private int destinationX, destinationY;
    private static StaticEntity entityToFollow;

    public Camera(World world, ControllableEntity entity, int width, int height) {
        this.world = world;
        this.width = width;
        this.height = height;
        entityToFollow = entity;
        animation = new CameraAnimation(this);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(0.9f, 0, 0, 0.40f));
    }

    public void update() {
        if (animation.isRunning()){
            animation.update();
        } else {
            updateNewPositionEntity();
            updateNewDestination();
            setPosition();
        }
    }

    public void follow(StaticEntity entity) {
        animation.slideTo(entity, 100000);
        entityToFollow = entity;
    }

    private void updateNewPositionEntity() {
        entityX = entityToFollow.getX() + (entityToFollow.getWidth() / 2);
        entityY = entityToFollow.getY() + (entityToFollow.getHeight() / 2);
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
        } else if (destinationY < 928) {
            destinationY = 928;
        }
    }

    private void setPosition() {
        this.x = destinationX;
        this.y = destinationY;
    }

    public boolean isAnimationFinish() {
        return animation.isRunning();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
