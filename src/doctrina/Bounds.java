package doctrina;

import java.awt.*;

public class Bounds {

    private Point coords;
    private int width, height;

    public Bounds(int x, int y, int width, int height) {
        this.coords = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    public boolean intersectsWith(StaticEntity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();
        return doRectanglesOverlap(coords.x, coords.y, width, height, entityX, entityY, entityWidth, entityHeight);
    }

    public boolean intersectsWith(Rectangle rectangle) {
        return doRectanglesOverlap(coords.x, coords.y, width, height, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean contains(int x, int y) {
        return (x >= coords.x && x <= coords.x + width &&
                y >= coords.y && y <= coords.y + height);
    }

    public void setCoords(int x, int y) {
        coords.setLocation(x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return (int) coords.getX();
    }

    public int getY() {
        return (int) coords.getY();
    }

    private boolean doRectanglesOverlap(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        int rightX1 = x1 + width1;
        int bottomY1 = y1 + height1;
        int rightX2 = x2 + width2;
        int bottomY2 = y2 + height2;

        boolean isLeft = rightX2 < x1;
        boolean isRight = x2 > rightX1;
        boolean isAbove = bottomY2 < y1;
        boolean isBelow = y2 > bottomY1;

        return !(isLeft || isRight || isAbove || isBelow);
    }
}
