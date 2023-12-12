package utopia;

import doctrina.MovementController;
import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    public static final int quitKey = KeyEvent.VK_Q;
    public static final int enterKey = KeyEvent.VK_ENTER;

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
