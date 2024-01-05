package utopia;

import doctrina.*;
import doctrina.Canvas;
import utopia.entities.chest.ChestManager;
import utopia.entities.enemy.ai.AiManager;
import utopia.entities.CollisionManager;
import utopia.entities.enemy.boss.BossManager;
import utopia.entities.obstacle.ObstacleManager;
import utopia.audio.Music;
import utopia.entities.png.PngManager;
import utopia.entities.sign.SignManager;
import utopia.entities.spawnpoint.SpawnPointManager;
import utopia.environment.RainEffect;
import utopia.player.Player;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends StaticEntity {

    private static final String MAP_PATH = "image/background/heavenly/Map.png";
    private static final String FRONT_VIEW_PATH = "image/background/heavenly/FrontView.png";
    private Image background;
    private Image frontView;

    private Camera camera;

    private final CollisionManager collisionManager;
    private final ChestManager chestManager;
    private final ObstacleManager obstacleManager;
    private final SpawnPointManager spawnPointManager;
    private final SignManager signManager;
    private List<MovableEntity> collidableEntities;
    private final Player player;
    private final AiManager aiManager;
    private final PngManager pngManager;
    private final BossManager bossManager;

    private final RainEffect rainEffect;

    public World(Player player) {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();

        this.player = player;
        collisionManager = new CollisionManager();
        chestManager = new ChestManager(player);
        obstacleManager = new ObstacleManager(player);
        aiManager = new AiManager(player);
        spawnPointManager = new SpawnPointManager(player);
        signManager = new SignManager(player);
        pngManager = new PngManager(player);
        bossManager = new BossManager(player);
        rainEffect = new RainEffect();
        initializeCollidableEntities();
        playBackgroundMusic();
        new GameMouse();
    }

    public void update() {
        aiManager.update();
        obstacleManager.update(collidableEntities);
        chestManager.update();
        spawnPointManager.update();
        signManager.update();
        rainEffect.update();
        pngManager.update();
        bossManager.update();
        updateCollisionWorld();
        updateCollidableEntities();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());
        obstacleManager.draw(canvas, camera);
        chestManager.draw(canvas, camera);
        spawnPointManager.draw(canvas, camera);
        signManager.draw(canvas, camera);
        pngManager.draw(canvas, camera);
        aiManager.draw(canvas, camera);
        bossManager.draw(canvas, camera);
        if (GameConfig.isDebugEnabled()) {
            collisionManager.draw(canvas, camera);
        }
    }

    public void drawRain(Canvas canvas, Camera camera) {
        rainEffect.draw(canvas, camera);
    }

    public void drawFrontview(Canvas canvas, Camera camera) {
        canvas.drawImage(frontView, x - camera.getX(), y - camera.getY());
    }

    public void updateInteraction() {
        pngManager.update();
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
        collidableEntities.removeAll(bossManager.getDeadEnemies());
    }

    private void load() {
        background = SpriteSheetSlicer.getSprite(0, 0, 3200, 3200, MAP_PATH);
        frontView = SpriteSheetSlicer.getSprite(0, 0, 3200, 3200, FRONT_VIEW_PATH);
    }

    private void playBackgroundMusic() {
        if (!GameConfig.isDebugEnabled()) {
            Music.BG_GAME.play(Clip.LOOP_CONTINUOUSLY);
            Music.RAIN_AMBIANCE.play(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
        bossManager.setCamera(camera);
    }
}
