package utopia;

import doctrina.MouseController;

import java.awt.event.MouseEvent;

public class GameMouse extends MouseController {

    private static GameMouse instance;

    public static final int LEFT_CLICK = MouseEvent.BUTTON1;
    public static final int RIGHT_CLICK = MouseEvent.BUTTON3;

    public static GameMouse getInstance() {
        if (instance == null) {
            instance = new GameMouse();
        }
        return instance;
    }

    public GameMouse() {
        bindKey(LEFT_CLICK);
        bindKey(RIGHT_CLICK);
    }

}
