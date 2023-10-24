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
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x, y, 800, 600, new Color(255, 0, 0, 100));
    }

    public void update() {
        int entityX = entity.getX() + (entity.getWidth() / 2);
        int entityY = entity.getY() + (entity.getHeight() / 2);

        setDestination(entityX - (width / 2), entityY - (height / 2));
    }

    public boolean isItInArea(StaticEntity entity) {
        return intersectWith(entity);
    }

    private void setDestination(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
