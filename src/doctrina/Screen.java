package doctrina;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.DisplayMode;

public class Screen {

    private JFrame frame;
    private Cursor invisibleCursor;
    private GraphicsDevice device;
    private DisplayMode fullscreenDisplayMode;
    private boolean isFullscreenMode;

    public Screen() {
        initializeFrame();
        initializeInvisibleCursor();
        initializeDevice();
    }

    public void start() {
        frame.setVisible(true);
    }

    public void stop() {
        frame.setVisible(false);
        frame.dispose();
    }

    public void hideCursor() {
        frame.setCursor(invisibleCursor);
    }

    public void showCursor() {
        frame.setCursor(Cursor.getDefaultCursor());
    }

    public void setSize(int width, int height) {
        boolean frameIsVisible = frame.isVisible();
        if (frameIsVisible) {
            frame.setVisible(false);
        }
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        if (frameIsVisible) {
            frame.setVisible(true);
        }

        //fullscreenDisplayMode = findClosestDisplayMode(width, height);
        System.out.println("Fullscreen Mode:" + fullscreenDisplayMode.getWidth() + "x" + fullscreenDisplayMode.getHeight());
    }

    public void setPanel(JPanel panel) {
        frame.add(panel);
    }

    public int getWidth() {
        return isFullscreenMode ? fullscreenDisplayMode.getWidth() : frame.getWidth();
    }

    public int getHeight() {
        return isFullscreenMode ? fullscreenDisplayMode.getHeight() : frame.getHeight();
    }

    public void fullscreen() {
        doctrina.DisplayMode.FULLSCREEN.enable(frame);
        isFullscreenMode = true;
    }

    public void window() {
        doctrina.DisplayMode.WINDOW.enable(frame);
        isFullscreenMode = false;
    }

    public void toggleScreen() {
        if (isFullscreenMode) {
            window();
        } else {
            fullscreen();
        }
    }

    private DisplayMode findClosestDisplayMode(int width, int height) {
        DisplayMode[] displayModes = device.getDisplayModes();
        int desiredResolution = width * height;
        int[] availableResolution = new int[displayModes.length];
        for (int i = 0; i < availableResolution.length; i++) {
            availableResolution[i] = displayModes[i].getWidth() * displayModes[i].getHeight();
        }
        return displayModes[closestIndexOfValue(desiredResolution, availableResolution)];
    }

    private int closestIndexOfValue(int value, int[] list) {
        int closestIndex = -1;
        for (int i = 0, min = Integer.MAX_VALUE; i < list.length; i++) {
            final int difference = Math.abs(list[i] - value);
            if (difference < min) {
                min = difference;
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Heavenly Game");
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setState(JFrame.NORMAL);
        frame.setUndecorated(false);
        frame.setBackground(Color.BLACK);
    }

    private void initializeInvisibleCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point hotSpot = new Point(0, 0);
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
    }

    private void initializeDevice() {
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        fullscreenDisplayMode = device.getDisplayMode();
        // windowedDisplayMode = device.getDisplayMode();
    }
}
