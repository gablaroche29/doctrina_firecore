package utopia.entities.obstacle;

import doctrina.*;
import doctrina.Canvas;
import utopia.player.Player;
import utopia.audio.SoundEffect;

import java.awt.*;
import java.util.Collection;

public class Obstacle extends Blockade {

    private boolean isDestruct;
    private final String id;
    private Image sprite;

    public Obstacle(int x, int y, String id) {
        super(x, y);
        this.id = id;
        load();
    }

    public void update(Player player, Collection<MovableEntity> entities) {
        if (player.hasAttacked()) {
            if (intersectWith(player.getAttackZone())) {
                SoundEffect.BROKEN_CRATE.play();
                isDestruct = true;
            }
        }

        if (!isDestruct) {
            super.update(entities);
        } else {
            CollidableRepository.getInstance().unregisterEntity(this);
            setRender(false);
        }

    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (camera.intersectWith(this)) {
            canvas.drawImage(sprite, x - camera.getX(), y - camera.getY() - 32);
        }
    }

    public boolean isDestruct() {
        return isDestruct;
    }

    private void load() {
        sprite = (id.equals("Big_Box")) ?
                SpriteSheetSlicer.getSprite(0, 0, 32, 64, "image/props/obstacles.png")
                : SpriteSheetSlicer.getSprite(32, 0, 32, 64, "image/props/obstacles.png");
    }
}
