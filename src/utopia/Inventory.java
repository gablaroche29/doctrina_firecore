package utopia;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.MovableEntity;
import utopia.player.Player;

import java.awt.*;

public class Inventory extends MovableEntity {

    private Player player;
    private int potion;
    private final Color bg = new Color(0, 0, 0, 125);

    public Inventory(int attackRadius, Player player) {
        super(attackRadius);
        this.player = player;
    }

    public void update() {

    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        teleport(500, 50);
        canvas.drawRectangle(x, y, 250, 450, bg);
    }

    public void addPotion(int potion) {
        this.potion += potion;
    }
}
