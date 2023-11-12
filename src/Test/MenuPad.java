package Test;

import doctrina.Controller;

import java.awt.event.KeyEvent;

public class MenuPad extends Controller {

    private final int upKey = KeyEvent.VK_UP;
    private final int downKey = KeyEvent.VK_DOWN;
    private final int enterKey = KeyEvent.VK_ENTER;

    public MenuPad() {
        bindKey(upKey);
        bindKey(downKey);
        bindKey(enterKey);
    }

    public boolean isUpKeyPressed() {
        return isKeyPressed(upKey);
    }

    public boolean isDownKeyPressed() {
        return isKeyPressed(downKey);
    }

    public boolean isEnterKeyPressed() {
        return isKeyPressed(enterKey);
    }
}
