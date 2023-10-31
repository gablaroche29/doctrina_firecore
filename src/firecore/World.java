package firecore;

import doctrina.*;
import doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class World extends StaticEntity {

    private static final String MAP_PATH = "images/backgrounds/heavenly/Map.png";
    private static final String TREES_PATH = "images/backgrounds/trees.png";

    private static final String COLLISIONS_PATH = "resources/collisions/collision_heavenly.txt";
    private Image background;
    private Image trees;

    private int limitX;
    private int limitY;

    private CollisionRepository collisionRepository;

    public World() {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();
        limitX = 640;
        limitY = 2752;
        collisionRepository = new CollisionRepository(COLLISIONS_PATH, 100, 100, 32, 833);
    }

//    public void updateCollisionWorld(MovableEntity movableEntity) {
//        for (Blockade blockade : collisionRepository.getCollisions()) {
//            blockade.update(movableEntity);
//        }
//    }

    public int getLimitX() {
        return limitX;
    }

    public int getLimitY() {
        return limitY;
    }

    public void updateCollisionWorld(Collection<MovableEntity> entities) {
        for (Blockade blockade : collisionRepository.getCollisions()) {
            blockade.update(entities);
        }
    }

    private void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
            trees = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(TREES_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());

        if (GameConfig.isDebugEnabled()) {
            for (Blockade blockade : collisionRepository.getCollisions()) {
                blockade.draw(canvas, camera);
            }
        }
    }

    public void drawTrees(Canvas canvas, Camera camera) {
        canvas.drawImage(trees, x - camera.getX(), y - camera.getY());
    }
}
