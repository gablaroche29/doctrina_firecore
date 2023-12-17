package utopia.event;

import doctrina.Canvas;
import doctrina.FontLoader;

public abstract class GameEvent {

    protected String[] text;
    protected boolean isActive;
    protected int cooldown;
    protected final FontLoader fontLoader;
    public abstract void draw(Canvas canvas);

    protected GameEvent(int cooldown) {
        this.cooldown = cooldown;
        fontLoader = new FontLoader("/font/perpetua/perpetua_bold.ttf", 25.0f);
    }

    protected void update() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 50;
            setActive(false);
        }
    }

    public void setText(String text) {
        this.text = text.split("\n");;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }
}
