package heavenly;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class HeavenlyGame extends Game {

    private GamePad gamePad;
    private Player player;
    private World world;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad, 864, 2368);
        world = new World(player);
        camera = new Camera(player, 800, 600);

        RenderingEngine.getInstance().getScreen().fullscreen();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            camera.stopCameraThread();
            stop();
        }
        player.update();
        world.update();
    }


    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas, camera);
        player.draw(canvas, camera);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);
    }
}
