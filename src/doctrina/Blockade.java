package doctrina;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(255, 0, 0, 100));
    }
}
