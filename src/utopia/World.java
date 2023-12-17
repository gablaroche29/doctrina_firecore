package utopia;

import doctrina.*;
import doctrina.Canvas;
import utopia.entities.png.BlackSmith;
import utopia.entities.chest.ChestManager;
import utopia.entities.enemy.AiManager;
import utopia.entities.CollisionManager;
import utopia.entities.obstacle.ObstacleManager;
import utopia.audio.Music;
import utopia.player.Player;
import utopia.audio.SoundEffect;
import utopia.spawnpoint.SpawnPoint;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends StaticEntity {

    private static final String MAP_PATH = "image/background/heavenly/Map.png";
    private static final String FRONTVIEW_PATH = "image/background/heavenly/FrontView.png";
    private Image background;
    private Image frontview;

    private final GamePad gamePad;
    private final CollisionManager collisionManager;
    private final ChestManager chestManager;
    private final ObstacleManager obstacleManager;
    private List<MovableEntity> collidableEntities;
    private final Player player;
    private final AiManager aiManager;

    private final RainEffect rainEffect;

    private final BlackSmith blackSmith;

    public World(Player player, GamePad gamePad) {
        setDimension(3200, 3200);
        teleport(0, 0);
        load();

        this.player = player;
        this.gamePad = gamePad;
        collisionManager = new CollisionManager();
        chestManager = new ChestManager(player);
        obstacleManager = new ObstacleManager(player);
        aiManager = new AiManager(player);

        initializeCollidableEntities();

        rainEffect = new RainEffect(player);


        blackSmith = new BlackSmith(player);
        playBackgroundMusic();

        new GameMouse();
    }

    public void update() {
        aiManager.update();
        updateCollisionWorld();
        updateCollidableEntities();

        obstacleManager.update(collidableEntities);
        chestManager.update();
        rainEffect.update();
        blackSmith.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());
        obstacleManager.draw(canvas, camera);
        chestManager.draw(canvas, camera);

        blackSmith.draw(canvas, camera);
        aiManager.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            collisionManager.draw(canvas, camera);
        }
    }

    public void drawRain(Canvas canvas, Camera camera) {
        rainEffect.draw(canvas, camera);
    }

    public void drawFrontview(Canvas canvas, Camera camera) {
        canvas.drawImage(frontview, x - camera.getX(), y - camera.getY());
    }

    public void updateInteraction() {
        blackSmith.update();
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
        background = SpriteSheetSlicer.getSprite(0, 0, 3200, 3200, MAP_PATH);
        frontview = SpriteSheetSlicer.getSprite(0, 0, 3200, 3200, FRONTVIEW_PATH);
    }

    private void playBackgroundMusic() {
        if (!GameConfig.isDebugEnabled()) {
            Music.BG_GAME.play(Clip.LOOP_CONTINUOUSLY);
            Music.RAIN_AMBIANCE.play(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
