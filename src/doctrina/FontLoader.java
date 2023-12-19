package doctrina;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    private Font font;

    public FontLoader(String path, float size) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return font;
    }
}
