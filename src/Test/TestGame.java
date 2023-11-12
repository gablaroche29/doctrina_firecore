package Test;

import doctrina.Canvas;
import doctrina.Game;
import doctrina.RenderingEngine;

public class TestGame extends Game {

    private Menu menu;

    @Override
    protected void initialize() {
        menu = new Menu();

        //RenderingEngine.getInstance().getScreen().fullscreen();
        RenderingEngine.getInstance().getScreen().showCursor();
    }

    @Override
    protected void update() {
        menu.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        menu.draw(canvas);
    }
}
