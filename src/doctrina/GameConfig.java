package doctrina;

public class GameConfig {

    private static boolean debug;
    private static float musicVolume = 0.9f;

    public static boolean isDebugEnabled() {
        return debug;
    }

    public static void enableDebug() {
        debug = true;
    }

    public static void disableDebug() {
        debug = false;
    }

    public static void setMusicVolume(float volume) {
        musicVolume = volume;
    }

    public static float getMusicVolume() {
        return musicVolume;
    }
}
