package heavenly.menu;

import doctrina.MouseController;
import heavenly.state.GameContext;
import heavenly.state.GameState;
import heavenly.sounds.SoundEffect;

import java.awt.event.MouseEvent;

public class MenuPad extends MouseController {

    private final Button[] buttons;

    public MenuPad(Button[] buttons) {
        this.buttons = buttons;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Button button : buttons) {
            button.setActive(button.getBounds().contains(e.getX(), e.getY()));
            if (button.isActive() && button.getGameState() == GameState.INITIALIZE) {
                GameContext.INSTANCE.setCurrentState(GameState.INITIALIZE);
            }
        }
        SoundEffect.CLICK.play();
        System.out.println("X: " + e.getX());
        System.out.println("Y: " + e.getY());
    }

}
