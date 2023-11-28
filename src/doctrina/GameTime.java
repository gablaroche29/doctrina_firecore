package doctrina;

import java.util.concurrent.TimeUnit;

public class GameTime {

    private static final int FPS_TARGET = 60;

    private static int currentFps;
    private static int fpsCount;
    private static long fpsTimeDelta;
    private static long gameStartTime;
    private long lastFrameTime = 0;
    private final long startTime = System.nanoTime();

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static int getCurrentFps() {
        return (currentFps > 0) ? currentFps : fpsCount;
    }

    public static long getElapsedTime() {
        return System.currentTimeMillis() - gameStartTime;
    }

    public static String getElapsedFormattedTime() {
        long time = getElapsedTime();
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        time -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        time -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    protected GameTime() {
        gameStartTime = System.currentTimeMillis();
        fpsTimeDelta = 0;
        currentFps = 0;
    }

    protected void synchronize() {
        sleep();
        update();
    }

    private void update() {
        fpsCount++;
        long currentSecond = TimeUnit.MILLISECONDS.toSeconds(getElapsedTime());
        if (fpsTimeDelta != currentSecond) {
            currentFps = fpsCount;
            fpsCount = 0;
        }
        fpsTimeDelta = currentSecond;
    }

    private void sleep(long nanoSeconds) throws InterruptedException {
        // selon le modèle de Samuel
        long endTime = System.nanoTime() + nanoSeconds;
        long timeLeft = nanoSeconds;
        long precision = TimeUnit.MILLISECONDS.toNanos(2);
        while (timeLeft > 0) {
            if (timeLeft > precision) {
                Thread.sleep(1);
            } else {
                Thread.sleep(0);
            }
            timeLeft = endTime - System.nanoTime();
        }
    }

    public void sleep() {
        // selon le modèle de Samuel
        double targetFrameTime = 1_000_000_000.0 / 60;
        long waitTime = (long) (targetFrameTime - ((System.nanoTime() - startTime) - lastFrameTime));
        if (waitTime > 0 && waitTime <= targetFrameTime) {
            try {
                sleep(waitTime); // l'autre methode sleep
            } catch (InterruptedException e) {
                throw new RuntimeException("Could not sleep current thread: " + e.getMessage(), e);
            }
        }
        lastFrameTime = System.nanoTime() - startTime;
    }

}
