package utopia.menu.option;

import doctrina.Bounds;
import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;

import java.awt.*;

public class Toggle {

    private Image inactiveImg;
    private Image activeImg;
    private final ToggleBehavior behavior;
    private boolean active;
    private final Bounds bounds;

    public Toggle(int posX, int posY, int width, int height, boolean active, ToggleBehavior behavior) {
        bounds = new Bounds(posX, posY, width, height);
        this.active = active;
        this.behavior = behavior;
        load();
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void execute() {
        behavior.execute();
    }

    public void draw(Canvas canvas) {
        if (active) {
            canvas.drawImage(activeImg, bounds.getX(), bounds.getY());
        } else {
            canvas.drawImage(inactiveImg, bounds.getX(), bounds.getY());
        }
    }

    public void setActive(boolean value) {
        active = value;
    }
    public boolean isActive() {
        return active;
    }

    private void load() {
        inactiveImg = SpriteSheetSlicer.getSprite(0, 0, 80, 40, "image/option/toggle.png");
        activeImg = SpriteSheetSlicer.getSprite(80, 0, 80, 40, "image/option/toggle.png");
    }

}
