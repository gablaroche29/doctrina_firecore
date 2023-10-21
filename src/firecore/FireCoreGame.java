package firecore;

import doctrina.Canvas;
import doctrina.Game;

public class FireCoreGame extends Game {

    private GamePad gamePad;
    private Player player;


    @Override
    protected void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(400, 300);
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        player.draw(canvas);
    }
}
