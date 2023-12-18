package utopia.entities.sign;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.Manager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SignManager extends Manager {

    private List<Sign> signs;
    private final Player player;

    public SignManager(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void update() {
        for (Sign sign : signs) {
            sign.update(player);
        }
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Sign sign : signs) {
            sign.draw(canvas, camera);
        }
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Signs";
    }

    @Override
    public void instanceObject(NodeList nodeList) {
        signs = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            NodeList properties = element.getElementsByTagName("property");
            Node property = properties.item(0);
            Element orientation = (Element) property;
            signs.add(new Sign(
                    Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y")),
                    element.getAttribute("name"),
                    orientation.getAttribute("value")));
        }
    }
}
