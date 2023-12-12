package utopia;

import doctrina.*;
import doctrina.Canvas;
import utopia.entities.chest.ChestManager;
import utopia.entities.enemy.AiManager;
import utopia.entities.CollisionManager;
import utopia.entities.obstacle.ObstacleManager;
import utopia.sounds.Music;
import utopia.player.Player;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class World extends StaticEntity {

    private static final String MAP_PATH = "image/background/heavenly/Map.png";
    private Image background;

    private final CollisionManager collisionManager;
    private final ChestManager chestManager;
    private final ObstacleManager obstacleManager;
    private List<MovableEntity> collidableEntities;
    private final Player player;
    private final AiManager aiManager;

    private final RainEffect rainEffect;

    public World(Player player) {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();

        this.player = player;
        collisionManager = new CollisionManager();
        chestManager = new ChestManager();
        obstacleManager = new ObstacleManager(player);
        aiManager = new AiManager(player);

        initializeCollidableEntities();

        rainEffect = new RainEffect(player);
        playBackgroundMusic();
    }

    public void update() {
        aiManager.update();
        updateCollisionWorld();
        updateCollidableEntities();

        obstacleManager.update(collidableEntities);
        rainEffect.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());
        aiManager.draw(canvas, camera);

        obstacleManager.draw(canvas, camera);
        chestManager.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            collisionManager.draw(canvas, camera);
        }
    }

    public void drawRain(Canvas canvas, Camera camera) {
        rainEffect.draw(canvas, camera);
        canvas.drawRectangle(0, 0, 800, 600, new Color(0, 0, 0, 0.4f));
    }
    
    private void initializeCollidableEntities() {
        collidableEntities = new ArrayList<>();
        collidableEntities.add(player);
        collidableEntities.addAll(aiManager.getEnemies());
    }

    private void updateCollisionWorld() {
        for (Blockade blockade : collisionManager.getBlockades()) {
            blockade.update(collidableEntities);
        }
    }

    private void updateCollidableEntities() {
        collidableEntities.removeAll(aiManager.getDeadEnemies());
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
