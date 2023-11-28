package heavenly;

import doctrina.*;
import doctrina.Canvas;
import heavenly.enemy.Enemies;
import heavenly.sounds.Music;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class World extends StaticEntity {

    private static final String MAP_PATH = "image/background/heavenly/Map.png";
    private static final String COLLISIONS_PATH = "resources/collisions/collision_heavenly.txt";
    private Image background;

    private final CollisionRepository collisionRepository;
    private List<MovableEntity> collidableEntities;
    private final Player player;
    private final Enemies enemies;

    private final RainEffect rainEffect;

    public World(Player player) {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();
        collisionRepository = new CollisionRepository(COLLISIONS_PATH, 100, 100, 32, 833);
        this.player = player;

        enemies = new Enemies(this, player);

        initializeCollidableEntities();

        rainEffect = new RainEffect();
        playBackgroundMusic();
    }

    public void update() {
        enemies.update();
        updateCollisionWorld();

        rainEffect.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());
        enemies.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            for (Blockade blockade : collisionRepository.getCollisions()) {
                blockade.draw(canvas, camera);
            }
        }
    }

    public void drawRain(Canvas canvas, Camera camera) {
        rainEffect.draw(canvas, camera);
        canvas.drawRectangle(0, 0, 800, 600, new Color(0, 0, 0, 0.4f));
    }
    
    private void initializeCollidableEntities() {
        collidableEntities = new ArrayList<>();
        collidableEntities.add(player);
        collidableEntities.addAll(enemies.getEnemies());
    }

    private void updateCollisionWorld() {
        for (Blockade blockade : collisionRepository.getCollisions()) {
            blockade.update(collidableEntities);
        }
    }

    private void load() {
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH)));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void playBackgroundMusic() {
        if (!GameConfig.isDebugEnabled()) {
            Music.BG_GAME.play(Clip.LOOP_CONTINUOUSLY);
            Music.RAIN_AMBIANCE.play(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
