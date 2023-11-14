package doctrina;

public class GameConfig {

    private static boolean debug;
    private static double musicVolume;

    public static boolean isDebugEnabled() {
        return debug;
    }

    public static void enableDebug() {
        debug = true;
    }

    public static void disableDebug() {
        debug = false;
    }

    public static void setMusicVolume(double volume) {
        musicVolume = volume;
    }

    public static double getMusicVolume() {
        return musicVolume;
    }
}
