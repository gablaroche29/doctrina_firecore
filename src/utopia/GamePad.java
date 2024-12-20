package utopia;

import doctrina.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private static GamePad instance;
    public static final int quitKey = KeyEvent.VK_ESCAPE;
    public static final int enterKey = KeyEvent.VK_ENTER;
    public static final int eKey = KeyEvent.VK_E;
    public static final int oneKey = KeyEvent.VK_1;
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
        bindKey(eKey);
        bindKey(oneKey);
        bindKey(spaceKey);
        bindKey(qKey);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }
    public boolean isEnterPressed() {
        return isKeyPressed(enterKey);
    }
    public boolean isOnePressed() {
        return isKeyPressed(oneKey);
    }
    public boolean isSpacePressed() {
        return isKeyPressed(spaceKey);
    }
    public boolean isQPressed() {
        return isKeyPressed(qKey);
    }
    public boolean isEPressed() {
        return isKeyPressed(eKey);
    }

}
