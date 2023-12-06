package utopia.entities.chest;

import doctrina.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ChestManager extends Manager {

    private List<Chest> chests;

    public ChestManager() {
        super();
    }

    @Override
    public void update() {
        for (Chest chest : chests) {
            chest.update();
        }
    }

    @Override
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
    public void instanceObject(NodeList nodeList) {
        chests = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            chests.add(new Chest(Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y"))));
        }
    }
}
