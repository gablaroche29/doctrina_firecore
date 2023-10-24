package firecore;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World extends StaticEntity {

    private static final String MAP_PATH = "images/backgrounds/island.png";
    //private static final String MAP_PATH = "images/bg_forest.png";
    private Image background;

    public World() {
        setDimension(3200, 3200);
        teleport(0, 0);
        //setDimension(896, 6624);
        load();
    }

    private void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());
    }
}
