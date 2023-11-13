package heavenly.menu;

import doctrina.Bounds;
import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;
import heavenly.state.GameState;

import java.awt.*;

public class Button {

    private static final String BUTTONS_PATH = "images/menu/buttons.png";
    private Image image;
    private boolean active;
    private final Bounds bounds;
    private final GameState gameState;

    public Button(int posX, int posY, int xSheet, int ySheet, int width, int height, GameState gameState) {
        bounds = new Bounds(posX, posY, width, height);
        this.gameState = gameState;
        loadImage(xSheet, ySheet);
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void draw(Canvas canvas) {
        if (active) {
            canvas.drawRectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(), Color.red);
        } else {
            canvas.drawImage(image, bounds.getX(), bounds.getY());
        }
    }

    public void setActive(boolean value) {
        active = value;
    }
    public boolean isActive() {
        return active;
    }

    public GameState getGameState() {
        return gameState;
    }

    private void loadImage(int x, int y) {
        image = SpriteSheetSlicer.getSprite(x, y, bounds.getWidth(), bounds.getHeight(), BUTTONS_PATH);
    }
}
