package doctrina;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {

    protected Point mouseCoords;
    private double proportionWidth;
    private double proportionHeight;

    public MouseController() {
        mouseCoords = new Point();
        RenderingEngine.getInstance().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        updateProportionScreen();
        setMouseCoords(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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

    @Override
    public void mouseMoved(MouseEvent e) {
        updateProportionScreen();
        setMouseCoords(e);
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
}
