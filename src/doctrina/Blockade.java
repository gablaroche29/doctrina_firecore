package doctrina;

import firecore.Player;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade(int x, int y) {
        teleport(x, y);
        setDimension(32, 32);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void update(Player player) {
        if (!intersectWith(player.getCollisionDetector())) {
            CollidableRepository.getInstance().unregisterEntity(this);
            setRender(false);
        } else {
            CollidableRepository.getInstance().registerEntity(this);
            setRender(true);
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (getRender()) {
            canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, new Color(255, 0, 0, 100));

        }
    }
}
