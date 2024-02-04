package utopia.menu.option;

import doctrina.*;
import doctrina.Canvas;
import utopia.UtopiaGame;

import java.awt.*;

public class Option {

    private Image background2;
    private final OptionPad optionPad;
    private final Toggle[] toggles = new Toggle[1];
    private final OptionButton[] buttons = new OptionButton[2];
    private final OptionButton menuButton;
    private final OptionButton gameButton;
    private final AudioControl musicControl = new AudioControl(470, 220, 50, 50, true);
    private final AudioControl soundControl = new AudioControl(470, 290, 50, 50, false);

    private final FontLoader fontLoader;

    private boolean isActive;

    public Option() {
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 30.f);
        load();
        Toggle fullScreen = new Toggle(500, 135, 80, 40,
                () -> RenderingEngine.getInstance().getScreen().toggleScreen());
        menuButton = new OptionButton(430, 430, 150, 45,
                () -> GameContext.INSTANCE.setCurrentState(GameState.MENU));
        gameButton = new OptionButton(230, 430, 150, 45,
                () -> GameContext.INSTANCE.setCurrentState(GameState.GAME));
        toggles[0] = fullScreen;

        buttons[0] = menuButton;
        buttons[1] = gameButton;

        AudioControl[] audioControls = new AudioControl[2];
        audioControls[0] = musicControl;
        audioControls[1] = soundControl;

        optionPad = new OptionPad(this, toggles, buttons, audioControls);
        optionPad.dispose();

    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRectangle(190, 90, 420, 420, 35, 35, Color.BLACK);
        canvas.drawImage(background2, 200, 100);
        canvas.drawString("Plein écran", 220, 165, Color.WHITE, fontLoader.getFont());
        for (Toggle toggle : toggles) {
            toggle.draw(canvas);
        }

        if (UtopiaGame.isPlaying) {
            gameButton.draw(canvas);
            canvas.drawString("Retour", 260, 460, Color.WHITE, fontLoader.getFont());
        }

        menuButton.draw(canvas);
        canvas.drawString("Menu", 465, 460, Color.WHITE, fontLoader.getFont());

        musicControl.draw(canvas);
        canvas.drawString("Musique", 220, 250, Color.WHITE, fontLoader.getFont());

        soundControl.draw(canvas);
        canvas.drawString("Effet sonore", 220, 320, Color.WHITE, fontLoader.getFont());

    }

    public void disable() {
        isActive = false;
        optionPad.dispose();
    }

    public void enable() {
        optionPad.activate();
    }

    private void load() {
        background2 = SpriteSheetSlicer.getSprite(0, 0, 400, 400, "image/option/bg_2.png");
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
