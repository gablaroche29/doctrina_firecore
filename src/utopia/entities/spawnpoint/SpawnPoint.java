package utopia.entities.spawnpoint;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.GamePad;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;

public class SpawnPoint extends StaticEntity {

    private Image sprite;
    private Image rightClick;
    private final FontLoader font;
    private boolean isActive;

    public SpawnPoint(int x, int y) {
        teleport(x, y);
        setDimension(32, 32);
        load();
        font = new FontLoader("/font/perpetua/perpetua_bold.ttf", 15.f);
    }

    public void update(Player player) {
        if (player.intersectWith(this) && GameMouse.getInstance().isKeyPressed(GameMouse.RIGHT_CLICK)) {
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
        if (camera.intersectWith(this)) {
            if (isActive) {
                canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
            } else {
                canvas.drawString("Activer", x - 20 - camera.getX(), y - camera.getY(), new Color(255, 255, 255), font.getFont());
                canvas.drawImage(rightClick, x + 30 - camera.getX(), y - 15 - camera.getY(), 20, 20);
            }
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
        rightClick = SpriteSheetSlicer.getSprite(32, 0, 32, 32, "image/ui/keys.png");
    }
}
