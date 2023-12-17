package utopia.entities.spawnpoint;

import doctrina.*;
import doctrina.Canvas;
import utopia.GamePad;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;

public class SpawnPoint extends StaticEntity {

    private Image sprite;
    private boolean isActive;

    public SpawnPoint(int x, int y) {
        teleport(x, y);
        setDimension(32, 32);
        load();
    }

    public void update(Player player) {
        if (player.intersectWith(this) && GamePad.getInstance().isSpacePressed()) {
            SoundEffect.SPAWN.play();
            isActive = true;
            GamePad.getInstance().setKeyStateFalse(GamePad.spaceKey);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (isActive && camera.intersectWith(this)) {
            canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private void load() {
        sprite = SpriteSheetSlicer.getSprite(0, 0, 32, 32, "image/props/spawn_point.png");
    }
}
