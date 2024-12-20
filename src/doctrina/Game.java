package doctrina;

public abstract class Game {

    private final RenderingEngine renderingEngine;
    private boolean playing = true;
    protected Camera camera;

    protected abstract void initialize();
    protected abstract void update();
    protected abstract void draw(Canvas canvas);

    public Game() {
        renderingEngine = RenderingEngine.getInstance();
    }

    public final void start() {
        initialize();
        run();
        // conclude();
    }

    public final void stop() {
        playing = false;
    }

    private void run() {
        renderingEngine.start();
        GameTime gameTime = new GameTime();
        while (playing) {
            gameTime.synchronize();
            update();
            draw(renderingEngine.buildCanvas());
            renderingEngine.drawOnScreen();
        }
        renderingEngine.stop();
    }

}
