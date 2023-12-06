package utopia.entities.obstacle;

import doctrina.*;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ObstacleManager implements XmlFileReaderTest {

    private final List<Obstacle> obstacles;
    private final List<Obstacle> destructObstacles;

    private final Player player;

    public ObstacleManager(Player player) {
        obstacles = new ArrayList<>();
        destructObstacles = new ArrayList<>();
        this.player = player;
        this.readXmlFile();
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

    public void draw(Canvas canvas, Camera camera) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas, camera);
        }
    }

    @Override
    public String getFileName() {
        return "resources/xml/collisions.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Obstacles";
    }

    @Override
    public void instanceObject(String[] tiles) {
        int row = 0, col = 0, id;
        for (String tile : tiles) {
            if (tile.equals("342") || tile.equals("374")) {
                id = (tile.equals("342") ? 1 : 2);
                obstacles.add(new Obstacle(row * 32, col * 32, id));
            }
            row++;
            if (row == 100) {
                col++;
                row = 0;
            }
        }
    }
}
