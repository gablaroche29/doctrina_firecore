package doctrina;

import java.awt.*;
import java.util.Collection;

public class Blockade extends StaticEntity {

    public Blockade(int x, int y) {
        teleport(x, y);
        setDimension(32, 32);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public Blockade(int x, int y, int width, int height) {
        teleport(x, y);
        setDimension(width, height);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void update(Collection<MovableEntity> entities) {
        boolean collide = false;
        for (MovableEntity entity : entities) {
            if (intersectWith(entity.getCollisionDetector())) {
                collide = true;
                break;
            }
        }

        if (collide) {
            if (!CollidableRepository.getInstance().isItRegister(this)) {
                CollidableRepository.getInstance().registerEntity(this);
                setRender(true);
            }
        } else {
            CollidableRepository.getInstance().unregisterEntity(this);
            setRender(false);
        }
    }

    @Override
    public void update() {
        // TODO: 2023-12-05  
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (isRender()) {
            canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(255, 0, 0, 100));
        }
    }
}
