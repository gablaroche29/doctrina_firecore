package utopia.entities.chest;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.StaticEntity;

import java.awt.*;

public class Chest extends StaticEntity {

    public Chest(int x, int y) {
        setDimension(32, 32);
        teleport(x, y);
    }

    public void update() {
        // TODO: 2023-12-04
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, Color.CYAN);
    }
}
