package doctrina;

import java.awt.*;

public class Camera extends StaticEntity {

    private final ControllableEntity entity;

    public Camera(ControllableEntity entity, int width, int height) {
        this.entity = entity;
        this.width = width;
        this.height = height;
        update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(x, y, 800, 600, new Color(255, 0, 0, 100));
    }

    public void update() {
        int entityX = entity.getX() + (entity.getWidth() / 2);
        int entityY = entity.getY() + (entity.getHeight() / 2);

        setDestination(entityX - (width / 2), entityY - (height / 2));
    }

    private void setDestination(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    private Direction getDirection() {
//        return entity.getDirection();
//    }
//
//    private void destination(DestinationCalculator calculatorX, DestinationCalculator calculatorY) {
//        setDestination(calculatorX.calculate(), calculatorY.calculate());
//    }
//
//    private interface DestinationCalculator {
//        int calculate();
//    }
}
