package doctrina;

public abstract class Scene {

    protected abstract void initialize();
    protected abstract void update();
    protected abstract void draw(Canvas canvas, Camera camera);

}
