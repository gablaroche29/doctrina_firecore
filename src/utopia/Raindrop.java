package utopia;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;
import java.util.Random;

public class Raindrop extends StaticEntity {

    private final int length;
    private final int speed;
    private final Color color = new Color(128, 145, 172);

    public Raindrop() {
        Random random = new Random();
        x = random.nextInt(RenderingEngine.getInstance().getScreen().getWidth());
        y = 0;
        length = random.nextInt(5) + 5;
        speed = random.nextInt(5) + 5;
    }

    public void update() {
        y += speed;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x, y, 1, length, color);
    }

}
