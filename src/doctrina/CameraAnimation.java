package doctrina;

public class CameraAnimation{

    private final Camera camera;
    private int cameraDestinationX;
    private int cameraDestinationY;
    public float animationDuration;
    private boolean isRunning;

    public CameraAnimation(Camera camera){
        this.camera = camera;
    }

    public void slideTo(StaticEntity entity, int timeMillis) {
        isRunning = true;
        setNewDestination(entity);
        animationDuration = (float) timeMillis / 1000;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void update() {
        if (animationDuration > 0) {
            updateAnimation();
            return;
        }
        isRunning = false;
    }

    private void updateAnimation() {
        int distanceX = ((int) (((cameraDestinationX - camera.getX())) / animationDuration) );
        int distanceY = ((int) (((cameraDestinationY - camera.getY())) / animationDuration) );
        camera.setPosition(camera.getX() + distanceX, camera.getY() + distanceY);
        animationDuration -= 1;
    }

    private void setNewDestination(StaticEntity entity) {
        cameraDestinationX = entity.getX() + (entity.getWidth() / 2) - 400;
        cameraDestinationY = entity.getY() + (entity.getHeight() / 2) - 300;
    }
}