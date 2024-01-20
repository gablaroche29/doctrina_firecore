package utopia.menu;

import doctrina.Canvas;
import doctrina.GameState;
import doctrina.SpriteSheetSlicer;
import utopia.audio.Music;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Menu {

    private Image background;
    private Image title;
    private final Button[] buttons = new Button[3];
    private final MenuPad menuPad;

    private boolean isActive;

    public Menu() {
        buttons[0] = initializePlayButton();
        buttons[1] = initializeOptionsButton();
        buttons[2] = initializeQuitButton();

        menuPad = new MenuPad(this, buttons);
        load();
        Music.BG_MENU.play(Clip.LOOP_CONTINUOUSLY);
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background, 0, 0);
        canvas.drawImage(title, 220, 100);
        for (Button button : buttons) {
            button.draw(canvas);
        }
    }

    public void disable() {
        menuPad.dispose();
    }

    public void enable() {
        menuPad.activate();
    }

    private void load() {
        try {
            String bg_path = "image/menu/bg.png";
            String title_path = "image/menu/title_white_neo.png";
            background = ImageIO.read(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(bg_path)));
            title = SpriteSheetSlicer.getSprite(0, 0, 380, 109, title_path);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Button initializePlayButton() {
        return new Button(297, 250, 297, 172, 203, 83, GameState.INITIALIZE);
    }

    private Button initializeOptionsButton() {
        return new Button(297, 340, 297, 262, 203, 83, GameState.OPTIONS);
    }

    private Button initializeQuitButton() {
        return new Button(297, 430, 297, 352, 203, 83, GameState.QUIT);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
