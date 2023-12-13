package utopia.entities.chest;

import doctrina.*;
import doctrina.Canvas;
import utopia.GamePad;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;

public class Chest extends StaticEntity {

    private Image close;
    private Image open;
    private boolean isOpen;
    private Player player;

    public Chest(int x, int y) {
        setDimension(32, 32);
        teleport(x, y);
        load();
    }

    public void update() {
        if (GamePad.getInstance().isEnterPressed()) {
            if (player.intersectWith(this)) {
                SoundEffect.BROKEN_CRATE.play();
                isOpen = true;
            }
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Image sprite = (isOpen) ? open : close;
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY() - 32);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void load() {
        String PATH = "image/props/chest.png";
        close = SpriteSheetSlicer.getSprite(0, 0, 32, 64, PATH);
        open = SpriteSheetSlicer.getSprite(32, 0, 32, 64, PATH);
    }
}
