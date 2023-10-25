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
    private Tree tree;
    private World world;
    private List<StaticEntity> renderingEntities;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(1000, 1000);
        tree = new Tree(200, 200);
        tree.teleport(200, 200);
        camera = new Camera(player, 800, 600);
        world = new World();

        monster = new Monster();

        renderingEntities = new ArrayList<>();
        renderingEntities.add(tree);


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
        for (StaticEntity entity : renderingEntities) {
            entity.setRender(entity.isInCameraField(camera));
        }

        if (player.hasMoved()) {
            camera.update();
        }

        world.updateCollisionWorld(collidableEntities);

        monster.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas, camera);
        for (StaticEntity entity : renderingEntities) {
            if (entity.getRender()) {
                entity.draw(canvas, camera);
            }
        }
        player.drawHitBox(canvas, camera);
        player.drawCollisionDetector(canvas, camera);
        player.draw(canvas, camera);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);

        monster.draw(canvas, camera);
        //camera.draw(canvas, camera);
    }
}
