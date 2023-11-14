package heavenly.menu;

import doctrina.Bounds;
import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;
import doctrina.GameState;

import java.awt.*;

public class Button {

    private static final String BUTTONS_PATH = "image/menu/buttons.png";
    private static final String BUTTONS_HOVER_PATH = "image/menu/buttons_hover.png";
    private Image image;
    private Image img_hover;
    private boolean active;
    private boolean hover;
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
        if (hover) {
            canvas.drawImage(img_hover, bounds.getX(), bounds.getY());
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

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public GameState getGameState() {
        return gameState;
    }

    private void loadImage(int x, int y) {
        image = SpriteSheetSlicer.getSprite(x, y, bounds.getWidth(), bounds.getHeight(), BUTTONS_PATH);
        img_hover = SpriteSheetSlicer.getSprite(x, y, bounds.getWidth(), bounds.getHeight(), BUTTONS_HOVER_PATH);
    }
}
