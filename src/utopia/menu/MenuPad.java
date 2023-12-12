package utopia.menu;

import doctrina.MouseController;
import doctrina.GameContext;
import utopia.audio.SoundEffect;

import java.awt.event.MouseEvent;

public class MenuPad extends MouseController {

    private final Button[] buttons;

    public MenuPad(Button[] buttons) {
        this.buttons = buttons;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SoundEffect.CLICK.play();
        super.mouseClicked(e);
        for (Button button : buttons) {
            button.setActive(button.getBounds().contains(mouseCoords));
            if (button.isActive()) {
                GameContext.INSTANCE.setCurrentState(button.getGameState());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        for (Button button : buttons) {
            button.setHover(button.getBounds().contains(mouseCoords));
        }
    }
}
