package utopia.entities;

import doctrina.Blockade;
import doctrina.XmlFileReader;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager implements XmlFileReader {

    private final List<Blockade> blockades;

    public CollisionManager() {
        blockades = new ArrayList<>();
        this.readXmlFile();
    }

    public List<Blockade> getBlockades() {
        return blockades;
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Collisions";
    }

    @Override
    public void instanceObject(String[] tiles) {
        int row = 0, col = 0;
        for (String tile : tiles) {
            if (tile.equals("833")) {
                blockades.add(new Blockade(row * 32, col * 32));
            }
            row++;
            if (row == 100) {
                col++;
                row = 0;
            }
        }
    }
}
