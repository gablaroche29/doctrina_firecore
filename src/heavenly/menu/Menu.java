package heavenly.menu;

import doctrina.Canvas;
import doctrina.GameState;
import heavenly.sounds.Music;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Menu {

    private final String BG_PATH = "image/menu/menu.png";
    private Image background;
    private final Button[] buttons = new Button[3];
    private final MenuPad menuPad;

    public Menu() {
        buttons[0] = initializePlayButton();
        buttons[1] = initializeOptionsButton();
        buttons[2] = initializeQuitButton();

        menuPad = new MenuPad(buttons);
        load();
        Music.BG_MENU.play(Clip.LOOP_CONTINUOUSLY);
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background, 0, 0);
        for (Button button : buttons) {
            button.draw(canvas);
        }
    }

    public void quit() {
        menuPad.dispose();
        Music.BG_MENU.stop();
    }

    private void load() {
        try {
            background = ImageIO.read(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(BG_PATH)));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Button initializePlayButton() {
        return new Button(224, 64, 224, 64, 352, 160, GameState.INITIALIZE);
    }

    private Button initializeOptionsButton() {
        return new Button(224, 224, 224, 224, 352, 160, GameState.OPTIONS);
    }

    private Button initializeQuitButton() {
        return new Button(224, 384, 224, 384, 352, 160, GameState.QUIT);
    }
}
