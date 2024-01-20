package utopia.menu.option;

import doctrina.GameContext;
import doctrina.MouseController;
import utopia.audio.SoundEffect;
import utopia.menu.Button;

import java.awt.event.MouseEvent;

public class OptionPad extends MouseController {

    private final Option option;
    private final Toggle[] toggles;
    private final OptionButton[] buttons;
    private final AudioControl[] audioControls;

    public OptionPad(Option option, Toggle[] toggles, OptionButton[] buttons, AudioControl[] audioControls) {
        this.option = option;
        this.toggles = toggles;
        this.buttons = buttons;
        this.audioControls = audioControls;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (option.isActive()) {
            SoundEffect.CLICK.play();
            for (Toggle toggle : toggles) {
                if (toggle.getBounds().contains(mouseCoords)) {
                    toggle.setActive(!toggle.isActive());
                    toggle.execute();
                }
            }

            for (OptionButton button : buttons) {
                button.setActive(button.getBounds().contains(mouseCoords));
                if (button.isActive()) {
                    button.execute();
                }
            }

            for (AudioControl audioControl : audioControls) {
                for (AudioLevelIcon audioLevelIcon : audioControl.getAudioLevelIcons()) {
                    if (audioLevelIcon.getDimension().contains(mouseCoords)) {
                        audioLevelIcon.onClick();
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        if (option.isActive()) {
            for (OptionButton button : buttons) {
                button.setHover(button.getBounds().contains(mouseCoords));
            }
        }
    }
}
