package utopia.entities.sign;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;

public class Sign extends StaticEntity {

    private Image sprite;
    private Image rightClick;
    private final String value;
    private final FontLoader font;
    private boolean isActive;
    private int valueCooldown = 0;

    public Sign(int x, int y, String value, String orientation) {
        teleport(x, y);
        setDimension(32, 32);
        this.value = value;
        load(orientation);
        font = new FontLoader("/font/perpetua/perpetua_bold.ttf", 15.f);
    }

    public void update(Player player) {
        if (player.intersectWith(this) && GameMouse.getInstance().isKeyPressed(GameMouse.rightClick)) {
            SoundEffect.INTERACTION.play();
            valueCooldown = 60;
            GameMouse.getInstance().setKeyStateFalse(GameMouse.rightClick);
        }
        valueCooldown--;
        if (valueCooldown <= 0) {
            valueCooldown = 0;
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (camera.intersectWith(this)) {
            canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
            if (valueCooldown == 0){
                canvas.drawString("Interagir", x - 25 - camera.getX(), y - camera.getY(), new Color(255, 255, 255), font.getFont());
                canvas.drawImage(rightClick, x + 30 - camera.getX(), y - 15 - camera.getY(), 20, 20);
            } else {
                canvas.drawString(value, x - 40 - camera.getX(), y - camera.getY(), new Color(255, 255, 255), font.getFont());
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private void load(String orientation) {
        if (orientation.equals("Right")) {
            sprite = SpriteSheetSlicer.getSprite(0, 0, 32, 32, "image/props/sign.png");
        } else {
            sprite = SpriteSheetSlicer.getSprite(32, 0, 32, 32, "image/props/sign.png");
        }
        rightClick = SpriteSheetSlicer.getSprite(32, 0, 32, 32, "image/ui/keys.png");
    }
}
