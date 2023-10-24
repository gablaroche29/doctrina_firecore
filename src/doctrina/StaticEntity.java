package doctrina;

import java.awt.*;

public abstract class StaticEntity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean render;

    public abstract void draw(Canvas canvas, Camera camera);

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean intersectWidth(StaticEntity other) {
        return getBounds().intersects(other.getBounds());
    }

    protected Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean getRender() {
        return render;
    }

    public boolean isInCameraField(Camera camera) {
        return camera.isItInArea(this);
    }
}
