package utopia.menu.option;

import doctrina.Canvas;
import doctrina.FontLoader;
import doctrina.RenderingEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Option {

    private Image background;
    private final OptionPad optionPad;
    private final Toggle[] toggles = new Toggle[1];
    private final FontLoader fontLoader;
    private final Toggle fullScreen;

    private boolean isActive;

    private final Color bgColor = new Color(33, 95, 109);


    public Option() {
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 30.f);
        load();
        fullScreen = new Toggle(500, 135, 80, 35,
                () -> RenderingEngine.getInstance().getScreen().toggleScreen());
        toggles[0] = fullScreen;

        optionPad = new OptionPad(this, toggles);
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
    }

    public void disable() {
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
