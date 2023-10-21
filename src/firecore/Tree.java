package firecore;

import doctrina.Canvas;
import doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private BufferedImage spriteSheet;

    public Tree() {
        setDimension(64, 80);
        loadSpriteSheet();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(spriteSheet, x, y);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
