package utopia.menu.option;

import doctrina.MouseController;
import utopia.audio.SoundEffect;

import java.awt.event.MouseEvent;

public class OptionPad extends MouseController {

    private final Toggle[] toggles;
    private final Option option;

    public OptionPad( Option option, Toggle[] toggles) {
        this.option = option;
        this.toggles = toggles;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (option.isActive()) {
            SoundEffect.CLICK.play();
            super.mouseClicked(e);
            for (Toggle toggle : toggles) {
                if (toggle.getBounds().contains(mouseCoords)) {
                    toggle.setActive(!toggle.isActive());
                    toggle.execute();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (option.isActive()) {
            super.mouseMoved(e);
        }
    }
}
