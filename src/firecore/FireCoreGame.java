package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FireCoreGame extends Game {

    private GamePad gamePad;
    private Player player;
    private Monster monster;
    private List<MovableEntity> collidableEntities;
    private World world;
    private List<StaticEntity> renderingEntities;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(864, 2368);
        camera = new Camera(player, 800, 600);
        world = new World();

        monster = new Monster();


        collidableEntities = new ArrayList<>();
        collidableEntities.add(player);
        collidableEntities.add(monster);
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
//        for (StaticEntity entity : renderingEntities) {
//            entity.setRender(entity.isInCameraField(camera));
//        }

        if (player.hasMoved()) {
            camera.update(world);
        }

        world.updateCollisionWorld(collidableEntities);

        monster.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas, camera);
//        for (StaticEntity entity : renderingEntities) {
//            if (entity.getRender()) {
//                entity.draw(canvas, camera);
//            }
//        }
        //player.drawHitBox(canvas, camera);
        //player.drawCollisionDetector(canvas, camera);
        player.draw(canvas, camera);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);

        //monster.draw(canvas, camera);
        //world.drawTrees(canvas, camera);
        //camera.draw(canvas, camera);
    }
}
