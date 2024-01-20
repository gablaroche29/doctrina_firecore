package utopia.menu.option;

import doctrina.Bounds;
import doctrina.Canvas;
import doctrina.GameState;

import java.awt.*;

public class Toggle {

    private final ToggleBehavior behavior;
    private boolean active;
    private final Bounds bounds;

    private final Color bgColor = new Color(98, 152, 164);

    public Toggle(int posX, int posY, int width, int height, ToggleBehavior behavior) {
        bounds = new Bounds(posX, posY, width, height);
        this.behavior = behavior;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void execute() {
        behavior.execute();
    }

    public void draw(Canvas canvas) {
        if (active) {
            canvas.drawRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), Color.BLACK);
            canvas.drawRectangle(bounds.getX() + 2, bounds.getY() + 2, bounds.getWidth() - 4, bounds.getHeight() - 4, bgColor);
            canvas.drawRectangle(bounds.getX() + 4, bounds.getY() + 4, bounds.getWidth() - 8, bounds.getHeight() - 8, Color.CYAN);
            canvas.drawRectangle(bounds.getX() + 8, bounds.getY() + 8, bounds.getWidth() - 16, bounds.getHeight() - 16, Color.BLACK);

            canvas.drawRectangle(bounds.getX() + 5 + (bounds.getWidth() - 35), bounds.getY() + 5, 25, 25, bgColor);
            canvas.drawRectangle(bounds.getX() + 7 + (bounds.getWidth() - 35), bounds.getY() + 7, 21, 21, Color.CYAN);
            canvas.drawRectangle(bounds.getX() + 9 + (bounds.getWidth() - 35), bounds.getY() + 9, 17, 17, bgColor);
        } else {
            canvas.drawRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), Color.BLACK);
            canvas.drawRectangle(bounds.getX() + 2, bounds.getY() + 2, bounds.getWidth() - 4, bounds.getHeight() - 4, bgColor);
            canvas.drawRectangle(bounds.getX() + 4, bounds.getY() + 4, bounds.getWidth() - 8, bounds.getHeight() - 8, Color.BLACK);

            canvas.drawRectangle(bounds.getX() + 5, bounds.getY() + 5, 25, 25, bgColor);
            canvas.drawRectangle(bounds.getX() + 7, bounds.getY() + 7, 21, 21, Color.BLACK);
            canvas.drawRectangle(bounds.getX() + 9, bounds.getY() + 9, 17, 17, bgColor);
        }
    }

    public void setActive(boolean value) {
        active = value;
    }
    public boolean isActive() {
        return active;
    }

}
