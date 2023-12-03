package heavenly;

import doctrina.*;
import heavenly.menu.Menu;
import heavenly.player.Player;


public class HeavenlyGame extends Game {

    private GameContext gameContext;
    private Menu menu;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Ui ui;

    @Override
    protected void initialize() {
//        .contains("win")
//        System.out.println(System.getProperty("os.name").toLowerCase());
        GameConfig.disableDebug();
        RenderingEngine.getInstance().getScreen().fullscreen();

        gameContext = GameContext.INSTANCE;
        gameContext.setCurrentState(GameState.MENU);
        menu = new Menu();
    }

    @Override
    protected void update() {
        switch (gameContext.getCurrentState()) {
            case QUIT -> stop();
            case INITIALIZE -> initializeGame();
            case GAME -> updateGame();
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        switch (gameContext.getCurrentState()) {
            case MENU -> drawMenu(canvas);
            case GAME -> drawGame(canvas);
        }
    }

    private void initializeGame() {
        menu.quit();
        gamePad = new GamePad();
        player = new Player(gamePad, 864, 2368);
        world = new World(player);
        camera = new Camera(player, 800, 600);
        ui = new Ui(player);
        gameContext.setCurrentState(GameState.GAME);
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    private void updateGame() {
        if (gamePad.isQuitPressed()) {
            camera.stopCameraThread();
            gameContext.setCurrentState(GameState.QUIT);
            return;
        }
        player.update();
        world.update();
    }

    private void drawMenu(Canvas canvas) {
        menu.draw(canvas);
    }

    private void drawGame(Canvas canvas) {
        world.draw(canvas, camera);
        player.draw(canvas, camera);
        world.drawRain(canvas, camera);
        ui.draw(canvas, camera);
    }
}
