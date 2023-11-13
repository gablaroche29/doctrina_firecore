package heavenly;

import doctrina.*;
import doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class World extends StaticEntity {

    private static final String MAP_PATH = "image/background/heavenly/Map.png";
    private static final String PROP_PATH = "image/props/Props.png";
    private static final String COLLISIONS_PATH = "resources/collisions/collision_heavenly.txt";
    private Image background;

    private int limitLeft, limitDown, limitRight, limitUp;

    private CollisionRepository collisionRepository;
    private Player player;

    public World(Player player) {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();
        limitLeft = 640;
        limitDown = 2752;
        collisionRepository = new CollisionRepository(COLLISIONS_PATH, 100, 100, 32, 833);

        this.player = player;
    }

    public int getLimitLeft() {
        return limitLeft;
    }

    public int getLimitDown() {
        return limitDown;
    }

    private void updateCollisionWorld(Collection<MovableEntity> entities) {
        for (Blockade blockade : collisionRepository.getCollisions()) {
            blockade.update(entities);
        }
    }

    public void update(Collection<MovableEntity> entities) {
        updateCollisionWorld(entities);
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

    private void load() {
        try {
            background = ImageIO.read(
                    this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}