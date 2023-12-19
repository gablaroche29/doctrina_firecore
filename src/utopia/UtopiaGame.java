package utopia;

import doctrina.*;
import utopia.menu.Menu;
import utopia.player.Player;


public class UtopiaGame extends Game {

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
            case DEAD_PLAYER -> updateDeadPlayer();
            case INTERACTION -> updateDialogue();
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        switch (gameContext.getCurrentState()) {
            case MENU -> drawMenu(canvas);
            case GAME, DEAD_PLAYER, INTERACTION -> drawGame(canvas);
        }
        if (ui != null) {
            ui.draw(canvas, gameContext.getCurrentState());
        }
    }

    private void initializeGame() {
        menu.quit();
        gamePad = new GamePad();
        player = new Player(gamePad, 864, 2368);
//        player = new Player(gamePad, 344, 1430);

        world = new World(player);
        camera = new Camera(world, player, 800, 600);
        ui = new Ui(player);
        gameContext.setCurrentState(GameState.GAME);
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    private void updateGame() {
        if (gameContext.getCurrentState() == GameState.GAME) {
            if (gamePad.isQuitPressed()) {
                camera.stopCameraThread();
                gameContext.setCurrentState(GameState.QUIT);
                return;
            }
            player.update();
            world.update();
            if (!player.isAlive()) {
                gameContext.setCurrentState(GameState.DEAD_PLAYER);
            }
        } else {
            world.update();
        }
    }

    private void updateDialogue() {
        world.updateInteraction();
    }

    private void updateDeadPlayer() {
        Ui.death(true);
        if (GamePad.getInstance().isEnterPressed()) {
            player.teleport(player.getSpawnPoint());
            player.heal();
            Ui.death(false);
            gameContext.setCurrentState(GameState.GAME);
        }
    }

    private void drawMenu(Canvas canvas) {
        menu.draw(canvas);
    }

    private void drawGame(Canvas canvas) {
        world.draw(canvas, camera);
        player.draw(canvas, camera);
        world.drawFrontview(canvas, camera);
        world.drawRain(canvas, camera);
    }
}
