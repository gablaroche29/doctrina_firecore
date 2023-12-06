package utopia.entities.obstacle;

import doctrina.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utopia.entities.chest.Chest;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ObstacleManager extends Manager {

    private List<Obstacle> obstacles;
    private final List<Obstacle> destructObstacles;
    private final Player player;

    public ObstacleManager(Player player) {
        super();
        destructObstacles = new ArrayList<>();
        this.player = player;
    }

    public void update(Collection<MovableEntity> entities) {
        for (Obstacle obstacle : obstacles) {
            obstacle.update(player, entities);
            if (obstacle.isDestruct()) {
                destructObstacles.add(obstacle);
            }
        }
        obstacles.removeAll(destructObstacles);
    }

    @Override
    public void update() {
        // TODO: 2023-12-05
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas, camera);
        }
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Obstacles";
    }

    @Override
    public void instanceObject(NodeList nodeList) {
        obstacles = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            obstacles.add(new Obstacle(Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y")),
                    element.getAttribute("name")));
        }
    }
}
