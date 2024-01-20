package utopia;

import doctrina.*;
import utopia.audio.Music;
import utopia.menu.Menu;
import utopia.menu.option.Option;
import utopia.player.Player;

import javax.sound.sampled.Clip;


public class UtopiaGame extends Game {

    private GameContext gameContext;
    private Menu menu;
    private Option option;
    private GamePad gamePad;
    private Player player;
    private World world;
    private Ui ui;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        //RenderingEngine.getInstance().getScreen().fullscreen();

        gameContext = GameContext.INSTANCE;
        gameContext.setCurrentState(GameState.MENU);
        menu = new Menu();
        option = new Option();
    }

    @Override
    protected void update() {
        switch (gameContext.getCurrentState()) {
            case QUIT -> stop();
            case INITIALIZE -> initializeGame();
            case GAME -> updateGame();
            case DEAD_PLAYER -> updateDeadPlayer();
            case INTERACTION -> updateDialogue();
            case OPTIONS -> updateOptions();
            case MENU -> updateMenu();
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        switch (gameContext.getCurrentState()) {
            case MENU -> drawMenu(canvas);
            case OPTIONS -> drawOptions(canvas);
            case GAME, DEAD_PLAYER, INTERACTION -> drawGame(canvas);
        }
        if (ui != null) {
            ui.draw(canvas, gameContext.getCurrentState());
        }
    }

    private void initializeGame() {
        menu.disable();
        Music.BG_MENU.stop();
        gamePad = new GamePad();
//        player = new Player(gamePad, 864, 2368);
        player = new Player(gamePad, 544, 1600);
        world = new World(player);
        camera = new Camera(world, player, 800, 600);
        world.setCamera(camera);
        ui = new Ui(player);
        gameContext.setCurrentState(GameState.GAME);
        RenderingEngine.getInstance().getScreen().hideCursor();
    }

    private void updateGame() {
        if (gameContext.getCurrentState() == GameState.GAME) {
            if (gamePad.isQuitPressed()) {
                gameContext.setCurrentState(GameState.QUIT);
                return;
            }
            player.update();
            world.update();
            if (!player.isAlive()) {
                gameContext.setCurrentState(GameState.DEAD_PLAYER);
            }
            camera.update();
        } else {
            world.update();
        }
    }

    private void updateDialogue() {
        camera.update();
        world.updateInteraction();
    }

    private void updateOptions() {
        if (!option.isActive()) {
            System.out.println("Disable menu");
            menu.disable();
            option.enable();
            option.setActive(true);
            menu.setActive(false);
        }
    }

    private void updateMenu() {
        if (!menu.isActive()) {
            System.out.println("Disable options");
            option.disable();
            menu.enable();
            menu.setActive(true);
            option.setActive(false);
        }
    }

    private void updateDeadPlayer() {
        Ui.death(true);
        if (GamePad.getInstance().isEnterPressed()) {
            player.teleport(player.getSpawnPoint());
            player.heal();
            Ui.death(false);
            gameContext.setCurrentState(GameState.GAME);
            Music.BOSS_BATTLE.stop();
            Music.BG_GAME.play(Clip.LOOP_CONTINUOUSLY);
            Music.RAIN_AMBIANCE.play(Clip.LOOP_CONTINUOUSLY);
        }
    }

    private void drawMenu(Canvas canvas) {
        menu.draw(canvas);
    }

    private void drawOptions(Canvas canvas) {
        option.draw(canvas);
    }

    private void drawGame(Canvas canvas) {
        world.draw(canvas, camera);
        player.draw(canvas, camera);
        world.drawFrontview(canvas, camera);
        world.drawRain(canvas, camera);
    }
}
