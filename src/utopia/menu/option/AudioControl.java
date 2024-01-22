package utopia.menu.option;

import doctrina.Bounds;
import doctrina.Canvas;
import doctrina.GameConfig;
import doctrina.SpriteSheetSlicer;

import java.awt.*;

public class AudioControl {

    private Image audioControl;
    private float audioLevel;
    private final Bounds dimension;
    private final AudioLevelIcon plus;
    private final AudioLevelIcon minus;

    private final boolean music;

    private final AudioLevelIcon[] audioLevelIcons = new AudioLevelIcon[2];

    private final Color activeColor = new Color(98, 152, 164);

    public AudioControl(int x, int y, int width, int height, boolean music) {
        this.dimension = new Bounds(x, y, width, height);
        minus = new AudioLevelIcon(x - 60, y + 10, 30, 30, this, false);
        plus = new AudioLevelIcon(x + width + 30, y + 10, 30, 30, this, true);
        audioLevelIcons[0] = minus;
        audioLevelIcons[1] = plus;

        this.music = music;
        if (music) {
            audioLevel = GameConfig.getMusicVolume();
        } else {
            audioLevel = GameConfig.getSoundVolume();
        }

        load();
    }

    public void draw(Canvas canvas) {
        canvas.drawImage(audioControl, dimension.getX(), dimension.getY());
        canvas.drawString(formatInPourcentage(audioLevel), dimension.getX() + 6, dimension.getY() + 35, Color.WHITE);
        minus.draw(canvas);
        plus.draw(canvas);
    }


    public void setAudioLevel(float audioLevel) {
        this.audioLevel = audioLevel;
        if (music) {
            GameConfig.setMusicVolume(audioLevel);
        } else {
            GameConfig.setSoundVolume(audioLevel);
        }
    }

    public float getAudioLevel() {
        return audioLevel;
    }

    public AudioLevelIcon[] getAudioLevelIcons() {
        return audioLevelIcons;
    }

    public String formatInPourcentage(float valeur) {
        int pourcentage = (int) (valeur * 100);
        return Integer.toString(pourcentage);
    }

    private void load() {
        audioControl = SpriteSheetSlicer.getSprite(0, 0, 50, 50, "image/option/audio_control.png");
    }
}
