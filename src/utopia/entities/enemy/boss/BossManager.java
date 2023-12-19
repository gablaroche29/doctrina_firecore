package utopia.entities.enemy.boss;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.Manager;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utopia.entities.enemy.type.necromancer.Necromancer;
import utopia.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BossManager extends Manager {

    private List<Boss> enemies;
    private final Player player;
    private final List<Boss> deadEnemies;

    public BossManager(Player player) {
        this.player = player;
        setPlayerForEnemies();
        deadEnemies = new ArrayList<>();
    }

    public void update() {
        deadEnemies.clear();
        for (Boss boss : enemies) {
            boss.update();
            if (!boss.isAlive()) {
                deadEnemies.add(boss);
            }
        }
        enemies.removeAll(deadEnemies);
    }

    public void draw(Canvas canvas, Camera camera) {
        for (Boss boss : enemies) {
            boss.draw(canvas, camera);
        }
    }

    public List<Boss> getDeadEnemies() {
        return deadEnemies;
    }

    public List<Boss> getEnemies() {
        return enemies;
    }

    @Override
    public String getFileName() {
        return "resources/xml/entities.xml";
    }

    @Override
    public String getNameAttribute() {
        return "Boss";
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

    private Boss getEnemy(Element element) {
        if (element.getAttribute("name").equals("Necromancer")) {
            return new Necromancer(Integer.parseInt(element.getAttribute("x")),
                    Integer.parseInt(element.getAttribute("y")));
        }
        return null;
    }

    private void setPlayerForEnemies() {
        for (Boss boss : enemies) {
            boss.setPlayer(player);
        }
    }
}
