package doctrina;

import firecore.CollisionRepository;
import firecore.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class Scene {

    private static final String MAP_PATH = "images/backgrounds/heavenly/Map.png";
    private static final String PROP_PATH = "images/props/Props.png";
    private static final String COLLISIONS_PATH = "resources/collisions/collision_heavenly.txt";
    private Image background;
    private final Bounds bounds;
    private final CollisionRepository collisions;
    private final Player player;

    public Scene(int x, int y, int width, int height, Player player) {
        bounds = new Bounds(x, y, width, height);
        load();
        collisions = new CollisionRepository(COLLISIONS_PATH, 100, 100, 32, 833);

        this.player = player;
    }

    public void update(Collection<MovableEntity> entities) {
        updateCollisionWorld(entities);
        updateWorldLimit();
    }

    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, bounds.getX() - camera.getX(), bounds.getY() - camera.getY());

        if (GameConfig.isDebugEnabled()) {
            for (Blockade blockade : collisions.getCollisions()) {
                blockade.draw(canvas, camera);
            }
        }
    }

    private void updateCollisionWorld(Collection<MovableEntity> entities) {
        for (Blockade blockade : collisions.getCollisions()) {
            blockade.update(entities);
        }
    }

    private void updateWorldLimit() {

    }

    private void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
