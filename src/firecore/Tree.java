package firecore;

import doctrina.Blockade;
import doctrina.Camera;
import doctrina.Canvas;
import doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private BufferedImage spriteSheet;
    private Blockade blockade;

    public Tree(int x, int y) {
        teleport(x, y);
        setDimension(64, 80);
        loadSpriteSheet();
        blockade = new Blockade(x + 16, y + 64);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(spriteSheet, x - camera.getX(), y - camera.getY());
        blockade.draw(canvas, camera);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
