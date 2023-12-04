package utopia.entities;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChestManager implements XmlFileReader {

    private final List<Chest> chests;

    public ChestManager() {
        chests = new ArrayList<>();
        this.readXmlFile();
    }

    public void update() {
        for (Chest chest : chests) {
            chest.update();
        }
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Chest chest : chests) {
            chest.draw(canvas, camera);
        }
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Chests";
    }

    @Override
    public void instanceObject(String[] tiles) {
        int row = 0, col = 0;
        for (String tile : tiles) {
            if (tile.equals("340")) {
                chests.add(new Chest(row * 32, col * 32));
            }
            row++;
            if (row == 100) {
                col++;
                row = 0;
            }
        }
    }


    private static class Chest extends StaticEntity {

        public Chest(int x, int y) {
            setDimension(32, 32);
            teleport(x, y);
        }

        public void update() {
            // TODO: 2023-12-04
        }

        @Override
        public void draw(Canvas canvas, Camera camera) {
            canvas.drawRectangle(x - camera.getX(), y - camera.getY(), width, height, Color.CYAN);
        }
    }
}
