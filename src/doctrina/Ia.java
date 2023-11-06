package doctrina;

import firecore.Player;

import java.awt.*;

public class Ia extends MovableEntity {

    private Player player;
    private Image sprite;
    private Rectangle triggerZone;

    public Ia(int x, int y, float speed, Image sprite, Player player) {
        this.x = x;
        this.y = y;
        setSpeed(speed);
        setDimension(32, 32);
        this.sprite = sprite;

        this.player = player;

        setTriggerZone();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawRectangle(triggerZone.x - camera.getX(),
                triggerZone.y - camera.getY(),
                triggerZone.width, triggerZone.height, new Color(0, 0, 255, 200));
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
    }

    private void setTriggerZone() {
        final int diameter = 300;
        triggerZone = new Rectangle(x + (width / 2) - (diameter / 2), y + (height / 2) - (diameter / 2),
                diameter, diameter);
    }

    private Rectangle getTriggerZone() {
        return triggerZone;
    }
}
