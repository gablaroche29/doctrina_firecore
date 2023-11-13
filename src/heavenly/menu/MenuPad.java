package heavenly.menu;

import doctrina.MouseController;
import doctrina.state.GameContext;
import doctrina.state.GameState;
import heavenly.sounds.SoundEffect;

import java.awt.event.MouseEvent;

public class MenuPad extends MouseController {

    private final Button[] buttons;
    private MouseEvent event;
    private boolean mousePressed;

    public MenuPad(Button[] buttons) {
        this.buttons = buttons;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.event = e;
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    public MouseEvent getMouseEvent() {
        return event;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
}
