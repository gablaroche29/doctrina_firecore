package firecore;

import doctrina.Blockade;
import doctrina.Camera;
import doctrina.Canvas;
import doctrina.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World extends StaticEntity {

    private static final String MAP_PATH = "images/backgrounds/island.png";
    private static final String COLLISIONS_PATH = "resources/collisions/collisions.txt";
    private Image background;
    private CollisionRepository collisionRepository;

    public World() {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();

        collisionRepository = new CollisionRepository(COLLISIONS_PATH, 100, 100, 32, 763);
    }

    public void updateCollisionWorld(Player player) {
        for (Blockade blockade : collisionRepository.getCollisions()) {
            blockade.update(player);
        }
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

        for (Blockade blockade : collisionRepository.getCollisions()) {
            blockade.draw(canvas, camera);
        }
    }
}
