package utopia;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.RenderingEngine;
import utopia.environment.Lighting;
import utopia.player.Player;

import javax.xml.catalog.CatalogManager;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class RainEffect {

    private final List<Raindrop> raindrops;
    private boolean stopRaining;
    private final Player player;
//    private Area darkness;
//    private RadialGradientPaint gPaint;

    public RainEffect(Player player) {
        this.player = player;
        raindrops = new ArrayList<>();
    }

    public void update() {
        for (Raindrop raindrop : raindrops) {
            raindrop.update();
            if (raindrop.getY() > RenderingEngine.getInstance().getScreen().getHeight()) {
                stopRaining = true;
                raindrop.teleport(raindrop.getX(), 0);
            }
        }

        if (!stopRaining) {
            for (int i = 0; i < 30; i++) {
                raindrops.add(new Raindrop());
            }
        }
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Raindrop raindrop : raindrops) {
            raindrop.draw(canvas, camera);
        }
        canvas.drawRectangle(0, 0, 800, 600, new Color(0, 0, 0, 0.50f));
    }

//    public void getDarkness(Canvas canvas, Camera camera) {
//        Color[] colors = new Color[10];
//        colors[0] = new Color(0, 0, 0, 0f);
//        colors[1] = new Color(0, 0, 0, 0.10f);
//        colors[2] = new Color(0, 0, 0, 0.18f);
//        colors[3] = new Color(0, 0, 0, 0.26f);
//        colors[4] = new Color(0, 0, 0, 0.34f);
//        colors[5] = new Color(0, 0, 0, 0.43f);
//        colors[6] = new Color(0, 0, 0, 0.52f);
//        colors[7] = new Color(0, 0, 0, 0.60f);
//        colors[8] = new Color(0, 0, 0, 0.68f);
//        colors[9] = new Color(0, 0, 0, 0.72f);
//
//        float[] fractions = new float[10];
//        fractions[0] = 0f;
//        fractions[1] = 0.10f;
//        fractions[2] = 0.18f;
//        fractions[3] = 0.26f;
//        fractions[4] = 0.34f;
//        fractions[5] = 0.43f;
//        fractions[6] = 0.52f;
//        fractions[7] = 0.60f;
//        fractions[8] = 0.68f;
//        fractions[9] = 0.76f;
//
//        gPaint = new RadialGradientPaint(player.getX() + 16 - camera.getX(), player.getY() + 16 - camera.getY(), 30, fractions, colors);
//
//        Lighting lighting = new Lighting(player, camera);
//        darkness = new Area(new Rectangle2D.Double(0, 0, 800, 600));
//        darkness.subtract(lighting.getLightArea());
//        Graphics2D g2d = canvas.getGraphics();
////        g2d.setColor(new Color(0, 0, 0, 0.6f));
//        g2d.setPaint(gPaint);
//        g2d.fill(lighting.getLightArea());
//        g2d.fill(darkness);
//    }

}
