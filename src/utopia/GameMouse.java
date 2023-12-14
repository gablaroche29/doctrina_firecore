package utopia;

import doctrina.MouseController;

import java.awt.event.MouseEvent;

public class GameMouse extends MouseController {

    private static GameMouse instance;

    public static final int leftClick = MouseEvent.BUTTON1;
    public static final int rightClick = MouseEvent.BUTTON3;

    public static GameMouse getInstance() {
        if (instance == null) {
            instance = new GameMouse();
        }
        return instance;
    }

    public GameMouse() {
        bindKey(leftClick);
        bindKey(rightClick);
    }

}
