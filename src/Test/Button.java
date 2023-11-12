package Test;

import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;

import java.awt.*;

public class Button {

    private static final String BUTTONS_PATH = "images/menu/buttons.png";
    private Image image;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private boolean active;

    public Button(int posX, int posY, int xSheet, int ySheet, int width, int height) {
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.height = height;
        loadImage(xSheet, ySheet);
    }

    public void draw(Canvas canvas) {
        if (active) {
            canvas.drawRectangle(x, y, width, height, Color.red);
        } else {
            canvas.drawImage(image, x, y);
        }
    }

    public void setActive() {
        active = true;
    }

    private void loadImage(int x, int y) {
        image = SpriteSheetSlicer.getSprite(x, y, width, height, BUTTONS_PATH);
    }
}
