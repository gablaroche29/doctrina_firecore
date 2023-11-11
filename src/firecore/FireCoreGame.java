package firecore;

import doctrina.*;
import doctrina.Canvas;
import firecore.sounds.Music;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FireCoreGame extends Game {

    private GamePad gamePad;
    private Player player;

    private Pillar pillar;

    private List<MovableEntity> collidableEntities;
    private World world;
    private List<StaticEntity> renderingEntities;

    private Ia ia;
    private Ia ia2;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad, 864, 2368);
        world = new World(player);

        camera = new Camera(player, world, 800, 600);

        ia = new Ia(1150, 2518, 1.5f, player);
        ia2 = new Ia(1130, 2538, 1.5f, player);

        collidableEntities = new ArrayList<>();
        collidableEntities.add(player);
        collidableEntities.add(ia);
        collidableEntities.add(ia2);

        renderingEntities = new ArrayList<>();

        pillar = new Pillar();


        RenderingEngine.getInstance().getScreen().fullscreen();
        RenderingEngine.getInstance().getScreen().showCursor();

        startBackgroundMusic();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            camera.stopCameraThread();
            stop();
        }
        player.update();
        for (StaticEntity entity : renderingEntities) {
            entity.setRender(entity.isInCameraField(camera));
        }

        world.update(collidableEntities);

        ia.update();
        ia2.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas, camera);
        for (StaticEntity entity : renderingEntities) {
            if (entity.isRender()) {
                entity.draw(canvas, camera);
            }
        }
        player.draw(canvas, camera);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);

        pillar.draw(canvas, camera);

        ia.draw(canvas, camera);
        ia2.draw(canvas, camera);
    }

    private void startBackgroundMusic() {
        Music.BG_AMBIENT.play();
    }
}
