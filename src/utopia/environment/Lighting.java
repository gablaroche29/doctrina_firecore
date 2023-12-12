package utopia.environment;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.MovableEntity;
import doctrina.RenderingEngine;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {

    private final BufferedImage darkness;

    public Lighting(MovableEntity entity, int circleSize) {
        int width = RenderingEngine.getInstance().getScreen().getWidth();
        int height = RenderingEngine.getInstance().getScreen().getHeight();
        darkness = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = (Graphics2D) darkness.getGraphics();
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, width, height));
        int centerX = entity.getX() + (entity.getWidth() / 2);
        int centerY = entity.getY() + (entity.getHeight() / 2);
        double x = centerX - ((double) circleSize / 2);
        double y = centerY - ((double) circleSize / 2);

        Shape circlShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
        Area lightArea = new Area(circlShape);
        screenArea.subtract(lightArea);
        graphics2D.setColor(new Color(0, 0, 0, 0.95f));
        graphics2D.fill(screenArea);
        graphics2D.dispose();
    }

    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(darkness, camera.getX(), camera.getY());
    }
}
