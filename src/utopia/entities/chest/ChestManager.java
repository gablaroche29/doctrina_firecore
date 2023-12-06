package utopia.entities.chest;

import doctrina.Manager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ChestManager extends Manager {

    public ChestManager() {
        super();
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
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            entitiesList.add(new Chest(Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y"))));
        }
    }
}
