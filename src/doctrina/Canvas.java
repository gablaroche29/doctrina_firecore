package doctrina;

import java.awt.*;

public class Canvas {

    private final Graphics2D graphics;

    public Canvas(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawRectangle(Rectangle rectangle, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void drawRectangle(StaticEntity entity, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(entity.x, entity.y, entity.width, entity.height);
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawString(String text, int x, int y, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public void drawImage(Image image, int x, int y, int width, int height) {
        graphics.drawImage(image, x, y, width, height, null);
    }
}
