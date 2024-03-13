package utopia.entities.png;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.Manager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PngManager extends Manager {


    private final Player player;
    private List<BlackSmith> pngs;

    public PngManager(Player player) {
        super();
        this.player = player;
        setPlayerForChest();
    }

    @Override
    public void update() {
        for (BlackSmith blackSmith : pngs) {
            blackSmith.update();
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        for (BlackSmith blackSmith : pngs) {
            blackSmith.draw(canvas, camera);
        }
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Pngs";
    }

    @Override
    public void instanceObject(NodeList nodeList) {
        pngs = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            pngs.add(new BlackSmith(Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y"))));
        }
    }

    private void setPlayerForChest() {
        for (BlackSmith blackSmith : pngs) {
            blackSmith.setPlayer(player);
        }
    }
}
