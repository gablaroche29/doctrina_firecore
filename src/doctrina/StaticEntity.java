package doctrina;

import utopia.entities.spawnpoint.SpawnPoint;

import java.awt.*;

public abstract class StaticEntity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean render;
    protected State state;

    public abstract void update();
    public abstract void draw(Canvas canvas, Camera camera);

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void teleport(SpawnPoint point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean intersectWith(StaticEntity other) {
        return getBounds().intersects(other.getBounds());
    }

    public boolean intersectWith(Rectangle rectangle) {
        return getBounds().intersects(rectangle);
    }

    public boolean intersectWith(Bounds bounds) {
        return bounds.intersectsWith(getBounds());
    }

    public Rectangle getBounds() {
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

    public boolean isRender() {
        return render;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
