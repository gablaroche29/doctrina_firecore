package doctrina;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Canvas {

    private final Graphics2D graphics;

    public Canvas(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
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

    public void drawString(String text, int x, int y, Paint paint, Font font) {
        graphics.setPaint(paint);
        graphics.setFont(font);
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

    public void drawImage(BufferedImage image, int x, int y, int angle) {
        double rotationRequired = Math.toRadians(angle);
        double locationX = (double) image.getWidth() / 2;
        double locationY = (double) image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        graphics.drawImage(op.filter(image, null), x, y, null);
    }

    public FontMetrics getFontMetrics() {
        return graphics.getFontMetrics();
    }

}
