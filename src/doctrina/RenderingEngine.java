package doctrina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    private static RenderingEngine instance;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;

    public static RenderingEngine getInstance() {
        if (instance == null) {
            instance = new RenderingEngine();
        }
        return instance;
    }

    public void start() {
        frame.setVisible(true);
    }

    public void stop() {
        frame.setVisible(false);
        frame.dispose();
    }

    public void addKeyListener(KeyListener keyListener) {
        panel.addKeyListener(keyListener);
    }

    public Canvas buildCanvas() {
        bufferedImage = new BufferedImage(800, 600,
                BufferedImage.TYPE_INT_RGB);
        buffer = bufferedImage.createGraphics();
        buffer.setRenderingHints(buildRenderingHints());
        return new Canvas(buffer);
    }

    public void drawOnScreen(Camera camera) {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        graphics.drawImage(bufferedImage, -camera.getX(), -camera.getY(), panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel);
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        frame.setTitle("Doctrina Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setState(JFrame.NORMAL);
        frame.setUndecorated(true);
    }

    private RenderingHints buildRenderingHints() {
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        return hints;
    }

    private RenderingEngine() {
        initializeFrame();
        initializePanel();
    }
}
