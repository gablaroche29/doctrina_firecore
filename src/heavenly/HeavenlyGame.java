package heavenly;

import doctrina.*;
import doctrina.Canvas;
import heavenly.ennemy.Ennemies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HeavenlyGame extends Game {

    private GamePad gamePad;
    private Player player;

    private Pillar pillar;

    private List<MovableEntity> collidableEntities;
    private World world;
    private List<StaticEntity> renderingEntities;
    private Ennemies ennemies;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad, 864, 2368);
        world = new World(player);

        camera = new Camera(player, 800, 600);

        ennemies = new Ennemies(world, player);


        collidableEntities = new ArrayList<>();
        collidableEntities.add(player);
        collidableEntities.addAll(ennemies.getEnnemies());

//        renderingEntities = new ArrayList<>();

//        pillar = new Pillar();



        RenderingEngine.getInstance().getScreen().fullscreen();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            camera.stopCameraThread();
            stop();
        }
        player.update();

//        for (StaticEntity entity : renderingEntities) {
//            entity.setRender(entity.isInCameraField(camera));
//        }

        world.update(collidableEntities);
        ennemies.update();
    }


    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas, camera);
//        for (StaticEntity entity : renderingEntities) {
//            if (entity.isRender()) {
//                entity.draw(canvas, camera);
//            }
//        }
        player.draw(canvas, camera);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);

        ennemies.draw(canvas, camera);
//        pillar.draw(canvas, camera);
    }
}
