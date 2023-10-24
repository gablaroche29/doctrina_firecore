package doctrina;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade(int x, int y) {
        teleport(x, y);
        setDimension(32, 32);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(255, 0, 0, 100));
    }
}
