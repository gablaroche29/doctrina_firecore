package doctrina;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class MouseController implements MouseListener, MouseMotionListener {

    private final HashMap<Integer, Boolean> pressedKeys;
    protected Point mouseCoords;
    private double proportionWidth;
    private double proportionHeight;

    public MouseController() {
        pressedKeys = new HashMap<>();
        mouseCoords = new Point();
        activate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        updateProportionScreen();
        setMouseCoords(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateProportionScreen();
        setMouseCoords(e);
    }

    public void dispose() {
        RenderingEngine.getInstance().removeMouseListener(this);
    }

    public void activate() {
        RenderingEngine.getInstance().addMouseListener(this);
    }

    private void setMouseCoords(MouseEvent e) {
        mouseCoords.setLocation(e.getX() * proportionWidth, e.getY() * proportionHeight);
    }

    private void updateProportionScreen() {
        // SET the screen basic width and height per default
        int widthFullscreen = RenderingEngine.getInstance().getScreen().getWidth();
        int heightFullscreen = RenderingEngine.getInstance().getScreen().getHeight();
        int width = 800;
        proportionWidth = (double) width / widthFullscreen;
        int height = 600;
        proportionHeight = (double) height / heightFullscreen;
    }

    protected void bindKey(int keyCode) {
        pressedKeys.put(keyCode, false);
    }

    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.containsKey(keyCode)
                && pressedKeys.get(keyCode);
    }

    public void setKeyStateFalse(int keyCode) {
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int keyCode = e.getButton();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int keyCode = e.getButton();
        if (pressedKeys.containsKey(keyCode)) {
            pressedKeys.put(keyCode, false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
