package doctrina;

import java.util.Collection;

public abstract class Scene {

    protected abstract void initialize();
    protected abstract void update();
    protected abstract void update(Collection<MovableEntity> entities);
    protected abstract void draw(Canvas canvas, Camera camera);

}
