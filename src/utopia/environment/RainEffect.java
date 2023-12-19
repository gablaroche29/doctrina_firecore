package utopia.environment;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.RenderingEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RainEffect {

    private final List<Raindrop> raindrops;
    private boolean stopRaining;

    public RainEffect() {
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

}
