package firecore;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FireCoreGame extends Game {

    private GamePad gamePad;
    private Player player;
    private Tree tree;
    private Camera camera;
    private List<StaticEntity> renderingEntities;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(400, 300);
        tree = new Tree();
        tree.teleport(200, 200);
        camera = new Camera(player, 800, 600);

        renderingEntities = new ArrayList<>();
        renderingEntities.add(tree);
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();

        if (player.hasMoved()) {
            camera.update();
            for (StaticEntity entity : renderingEntities) {
                entity.setRender(entity.isInCameraField(camera));
            }
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        player.draw(canvas);
        player.drawHitBox(canvas);
        camera.draw(canvas);

        for (StaticEntity entity : renderingEntities) {
            if (entity.getRender()) {
                entity.draw(canvas);
            }
        }

        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);
    }
}
