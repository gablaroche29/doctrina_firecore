package utopia;

import doctrina.MovementController;
import doctrina.RenderingEngine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePad extends MovementController {

    private static GamePad instance;
    public static final int quitKey = KeyEvent.VK_Q;
    public static final int enterKey = KeyEvent.VK_ENTER;

    public static GamePad getInstance() {
        if (instance == null) {
            instance = new GamePad();
        }
        return instance;
    }

    public GamePad() {
        bindKey(quitKey);
        bindKey(enterKey);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }
    public boolean isEnterPressed() {
        return isKeyPressed(enterKey);
    }
}
