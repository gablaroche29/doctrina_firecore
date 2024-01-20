package doctrina;

import javax.swing.*;
import java.awt.*;

public enum DisplayMode {
    WINDOW {
        @Override
        public void enable(JFrame frame) {
            GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
            if (device.getFullScreenWindow() != null) {
                device.setFullScreenWindow(null);
            }
            frame.setBounds(0, 0, 800, 600);
            frame.setLocationRelativeTo(null);
            if (!frame.isVisible()) {
                frame.setVisible(true);
            }
        }
    }, MAXIMIZED {
        @Override
        public void enable(JFrame frame) {
            GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
            java.awt.DisplayMode displayMode = device.getDisplayMode();
            if (device.getFullScreenWindow() != null) {
                device.setFullScreenWindow(null);
            }
            frame.setBounds(0, 0, displayMode.getWidth(), displayMode.getHeight());
            frame.setLocationRelativeTo(null);
            if (!frame.isVisible()) {
                frame.setVisible(true);
            }
        }
    }, FULLSCREEN {
        @Override
        public void enable(JFrame frame) {
            GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
            if (!device.isFullScreenSupported()) {
                return;
            }
            if (frame.isVisible()) {
                frame.setVisible(false);
            }
            device.setFullScreenWindow(frame);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    };

    public abstract void enable(JFrame frame);
}