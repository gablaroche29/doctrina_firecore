package utopia;

import doctrina.MovementController;
import doctrina.RenderingEngine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePad extends MovementController {

    private static GamePad instance;
    public static final int quitKey = KeyEvent.VK_ESCAPE;
    public static final int enterKey = KeyEvent.VK_ENTER;
    public static final int pKey = KeyEvent.VK_P;
    public static final int spaceKey = KeyEvent.VK_SPACE;
    public static final int qKey = KeyEvent.VK_Q;

    public static GamePad getInstance() {
        if (instance == null) {
            instance = new GamePad();
        }
        return instance;
    }

    public GamePad() {
        bindKey(quitKey);
        bindKey(enterKey);
        bindKey(pKey);
        bindKey(spaceKey);
        bindKey(qKey);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }
    public boolean isEnterPressed() {
        return isKeyPressed(enterKey);
    }
    public boolean isPPressed() {
        return isKeyPressed(pKey);
    }
    public boolean isSpacePressed() {
        return isKeyPressed(spaceKey);
    }
    public boolean isQPressed() {
        return isKeyPressed(qKey);
    }

}
