package doctrina;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {

    protected Point mouseCoords;
    private int widthFullscreen;
    private int heightFullscreen;
    private final int width = 800;
    private final int height = 600;

    public MouseController() {
        mouseCoords = new Point();
        RenderingEngine.getInstance().addMouseListener(this);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        widthFullscreen = RenderingEngine.getInstance().getScreen().getWidth();
        heightFullscreen = RenderingEngine.getInstance().getScreen().getHeight();

        double proportionWidth = (double) width / widthFullscreen;
        double proportionHeight = (double) height / heightFullscreen;
        mouseCoords.setLocation(e.getX() * proportionWidth, e.getY() * proportionHeight);

        System.out.println("X mouse: " + mouseCoords.x);
        System.out.println("Y mouse: " + mouseCoords.y);
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
        widthFullscreen = RenderingEngine.getInstance().getScreen().getWidth();
        heightFullscreen = RenderingEngine.getInstance().getScreen().getHeight();

        double proportionWidth = (double) width / widthFullscreen;
        double proportionHeight = (double) height / heightFullscreen;
        mouseCoords.setLocation(e.getX() * proportionWidth, e.getY() * proportionHeight);

        System.out.println("X mouse: " + mouseCoords.x);
        System.out.println("Y mouse: " + mouseCoords.y);
    }
}
