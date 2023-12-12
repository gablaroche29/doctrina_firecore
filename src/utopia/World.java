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

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends StaticEntity {

    private static final String MAP_PATH = "image/background/heavenly/Map.png";
    private Image background;

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
        chestManager = new ChestManager();
        obstacleManager = new ObstacleManager(player);
        aiManager = new AiManager(player);

        initializeCollidableEntities();

        rainEffect = new RainEffect(player);


        blackSmith = new BlackSmith();
        playBackgroundMusic();
    }

    public void update() {
        aiManager.update();
        updateCollisionWorld();
        updateCollidableEntities();

        obstacleManager.update(collidableEntities);
        rainEffect.update();
        blackSmith.update();
        updateInteraction();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(background, x - camera.getX(), y - camera.getY());
        aiManager.draw(canvas, camera);

        obstacleManager.draw(canvas, camera);
        chestManager.draw(canvas, camera);

        blackSmith.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            collisionManager.draw(canvas, camera);
        }
    }

    public void drawRain(Canvas canvas, Camera camera) {
        rainEffect.draw(canvas, camera);
        canvas.drawRectangle(0, 0, 800, 600, new Color(0, 0, 0, 0.4f));
    }

    private void updateInteraction() {
        if (gamePad.isEnterPressed()) {
            SoundEffect.INTERACTION.play();
            if (player.intersectWith(blackSmith)) {
                GameContext.INSTANCE.setCurrentState(GameState.DIALOGUE);
                Ui.setTexts(blackSmith.speak());
            }
            gamePad.setKeyStateFalse(GamePad.enterKey);
            if (blackSmith.isFinishTalking()) {
                GameContext.INSTANCE.setCurrentState(GameState.GAME);
            }
        }
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
    }

    private void playBackgroundMusic() {
        if (!GameConfig.isDebugEnabled()) {
            Music.BG_GAME.play(Clip.LOOP_CONTINUOUSLY);
            Music.RAIN_AMBIANCE.play(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
