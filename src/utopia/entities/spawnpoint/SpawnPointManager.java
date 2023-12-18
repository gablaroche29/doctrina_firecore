package utopia.entities.spawnpoint;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.Manager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnPointManager extends Manager {

    private List<SpawnPoint> spawnPoints;
    private final Player player;
    private SpawnPoint currentSpawnPoint;

    public SpawnPointManager(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void update() {
        for (SpawnPoint spawnPoint : spawnPoints) {
            if (currentSpawnPoint != spawnPoint) {
                spawnPoint.setActive(false);
            }
            spawnPoint.update(player);
            if (spawnPoint.isActive()) {
                currentSpawnPoint = spawnPoint;
            }
        }
        if (currentSpawnPoint == null) {
            currentSpawnPoint = spawnPoints.get(0);
        }
        player.setSpawnPoint(currentSpawnPoint);
    }

    public void draw(Canvas canvas, Camera camera) {
        for (SpawnPoint spawnPoint : spawnPoints) {
            spawnPoint.draw(canvas, camera);
        }
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "SpawnPoints";
    }

    @Override
    public void instanceObject(NodeList nodeList) {
        spawnPoints = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            spawnPoints.add(new SpawnPoint(Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y"))));
        }
    }
}
