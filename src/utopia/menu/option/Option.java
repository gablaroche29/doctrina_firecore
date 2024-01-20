package utopia.menu.option;

import doctrina.*;
import doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Option {

    private Image background;
    private final OptionPad optionPad;
    private final Toggle[] toggles = new Toggle[1];
    private final OptionButton[] buttons = new OptionButton[1];
    private final AudioControl musicControl = new AudioControl(470, 220, 50, 50, true);
    private final AudioControl soundControl = new AudioControl(470, 290, 50, 50, false);

    private final FontLoader fontLoader;

    private boolean isActive;

    private final Color bgColor = new Color(33, 95, 109);

    public Option() {
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 30.f);
        load();
        Toggle fullScreen = new Toggle(500, 135, 80, 35,
                () -> RenderingEngine.getInstance().getScreen().toggleScreen());
        OptionButton menuButton = new OptionButton(430, 430, 150, 45,
                () -> GameContext.INSTANCE.setCurrentState(GameState.MENU));
        toggles[0] = fullScreen;
        buttons[0] = menuButton;

        AudioControl[] audioControls = new AudioControl[2];
        audioControls[0] = musicControl;
        audioControls[1] = soundControl;

        optionPad = new OptionPad(this, toggles, buttons, audioControls);
        optionPad.dispose();

    }

    public void draw(Canvas canvas) {
        canvas.drawImage(background, 0, 0);
        canvas.drawRoundRectangle(197, 97, 406, 406, 35, 35, Color.BLACK);
        canvas.drawRoundRectangle(200, 100, 400, 400, 35, 35, bgColor);
        canvas.drawString("Plein écran", 220, 165, Color.WHITE, fontLoader.getFont());
        for (Toggle toggle : toggles) {
            toggle.draw(canvas);
        }

        for (OptionButton button : buttons) {
            button.draw(canvas);
            canvas.drawString("Menu", 465, 460, Color.WHITE, fontLoader.getFont());
        }

        musicControl.draw(canvas);
        canvas.drawString("Music", 220, 250, Color.WHITE, fontLoader.getFont());

        soundControl.draw(canvas);
        canvas.drawString("Sound", 220, 320, Color.WHITE, fontLoader.getFont());

    }

    public void disable() {
        isActive = false;
        optionPad.dispose();
    }

    public void enable() {
        optionPad.activate();
    }

    private void load() {
        try {
            String bg_path = "image/menu/bg.png";
            background = ImageIO.read(
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(bg_path)));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
