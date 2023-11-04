package doctrina;

import java.awt.*;
import java.util.Collection;

public class Blockade extends StaticEntity {

    public Blockade(int x, int y) {
        teleport(x, y);
        setDimension(32, 32);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void update(Collection<MovableEntity> entities) {
        int collide = 0;

        for (MovableEntity entity : entities) {
            if (intersectWith(entity.getCollisionDetector())) {
                collide++;
            }
        }

        if (collide > 0) {
            CollidableRepository.getInstance().registerEntity(this);
            setRender(true);
        } else {
            CollidableRepository.getInstance().unregisterEntity(this);
            setRender(false);
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (isRender()) {
            canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(255, 0, 0, 100));
        }
    }
}
