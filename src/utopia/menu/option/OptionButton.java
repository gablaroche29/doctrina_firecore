package utopia.menu.option;

import doctrina.Bounds;
import doctrina.Canvas;

import java.awt.*;

public class OptionButton {

    private boolean active;
    private boolean hover;
    private final Bounds bounds;
    private final ToggleBehavior behavior;
    private final Color bgColor = new Color(33, 95, 109);
    private final Color hoverColor = new Color(98, 152, 164);

    public OptionButton(int posX, int posY, int width, int height, ToggleBehavior behavior) {
        bounds = new Bounds(posX, posY, width, height);
        this.behavior = behavior;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void draw(Canvas canvas) {
        if (hover) {
            canvas.drawRoundRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), 10, 10, hoverColor);
        } else {
            canvas.drawRoundRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), 10, 10, bgColor);
        }
    }

    public void setActive(boolean value) {
        active = value;
    }
    public boolean isActive() {
        return active;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public void execute() {
        behavior.execute();
    }

}
