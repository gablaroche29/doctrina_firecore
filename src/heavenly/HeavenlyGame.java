package heavenly;

import doctrina.*;
import doctrina.Canvas;
import doctrina.state.GameContext;
import doctrina.state.GameState;
import heavenly.menu.Menu;
import heavenly.sounds.Music;

import java.awt.*;


public class HeavenlyGame extends Game {

    private GameContext gameContext;
    private Menu menu;
    private GamePad gamePad;
    private Player player;
    private World world;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        RenderingEngine.getInstance().getScreen().fullscreen();

        gameContext = GameContext.INSTANCE;
        gameContext.setCurrentState(GameState.MENU);
        menu = new Menu();
    }

    @Override
    protected void update() {
        if (gameContext.getCurrentState() == GameState.MENU) {
            menu.update();
        }

        if (gameContext.getCurrentState() == GameState.QUIT) {
            stop();
        }

        if (gameContext.getCurrentState() == GameState.INITIALIZE) {
            Music.BG_MENU.stop();

            gamePad = new GamePad();
            player = new Player(gamePad, 864, 2368);
            world = new World(player);
            camera = new Camera(player, 800, 600);
            gameContext.setCurrentState(GameState.GAME);
            RenderingEngine.getInstance().getScreen().hideCursor();
        }

        if (gameContext.getCurrentState() == GameState.GAME) {
            if (gamePad.isQuitPressed()) {
                camera.stopCameraThread();
                stop();
            }
            player.update();
            world.update();
        }
    }


    @Override
    protected void draw(Canvas canvas) {
        if (gameContext.getCurrentState() == GameState.MENU) {
            menu.draw(canvas);
        }
        if (gameContext.getCurrentState() == GameState.GAME) {
            world.draw(canvas, camera);
            player.draw(canvas, camera);
            canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);
        }
    }
}
