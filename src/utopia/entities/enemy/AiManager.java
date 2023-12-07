package utopia.entities.enemy;

import doctrina.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utopia.entities.enemy.type.RedBat;
import utopia.entities.enemy.type.BlueBat;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.List;

public class AiManager extends Manager {

    private List<Ai> enemies;
    private final Player player;
    private final List<Ai> deadEnemies;

    public AiManager(Player player) {
        super();
        this.player = player;
        setPlayerForEnemies();
        //enemies.add(new Ai(1150, 2518, 1.5f, player));
        //enemies.add(new Ai(1130, 2538, 1.5f, player));

        deadEnemies = new ArrayList<>();
    }

    public void update() {
        deadEnemies.clear();
        for (Ai ai : enemies) {
            ai.update();
            if (!ai.isAlive()) {
                deadEnemies.add(ai);
            }
        }
        enemies.removeAll(deadEnemies);
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Ai ai : enemies) {
            ai.draw(canvas, camera);
        }
    }

    public List<Ai> getDeadEnemies() {
        return deadEnemies;
    }

    public List<Ai> getEnemies() {
        return enemies;
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Enemies";
    }

    @Override
    public void instanceObject(NodeList nodeList) {
        enemies = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            enemies.add(getEnemy(element));
        }
    }

    private Ai getEnemy(Element element) {
        return switch (element.getAttribute("name")) {
            case "Blue_Bat" -> new RedBat(
                    Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y")));
            case "Red_Bat" -> new BlueBat(
                    Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y")));
            default -> new RedBat(0, 0);
        };
    }

    private void setPlayerForEnemies() {
        for (Ai ai : enemies) {
            ai.setPlayer(player);
        }
    }
}
