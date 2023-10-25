package firecore;

import doctrina.*;
import doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

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

//    public void updateCollisionWorld(MovableEntity movableEntity) {
//        for (Blockade blockade : collisionRepository.getCollisions()) {
//            blockade.update(movableEntity);
//        }
//    }

    public void updateCollisionWorld(Collection<MovableEntity> entities) {
        for (Blockade blockade : collisionRepository.getCollisions()) {
            blockade.update(entities);
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
