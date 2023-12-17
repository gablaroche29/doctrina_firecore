package utopia.entities.chest;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.GamePad;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;
import java.util.Random;

public class Chest extends StaticEntity {

    private Image close;
    private Image open;
    private boolean isOpen;
    private Player player;
    private final int potion;

    public Chest(int x, int y) {
        Random rnd = new Random();
        potion = rnd.nextInt(2) + 1;
        setDimension(32, 32);
        teleport(x, y);
        load();
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void update() {
        if (GameMouse.getInstance().isKeyPressed(GameMouse.rightClick)) {
            if (player.intersectWith(this) && !isOpen) {
                SoundEffect.INTERACTION.play();
                SoundEffect.BROKEN_CRATE.play();
                isOpen = true;
                player.addPotion(potion);
                GameMouse.getInstance().setKeyStateFalse(GameMouse.rightClick);
                Ui.openChest(potion);
            }
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (camera.intersectWith(this)) {
            Image sprite = (isOpen) ? open : close;
            canvas.drawImage(sprite, x - camera.getX(), y - camera.getY() - 32);
        }
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
