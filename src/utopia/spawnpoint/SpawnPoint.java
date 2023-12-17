package utopia.spawnpoint;

import java.awt.*;

public enum SpawnPoint {
    FIRST(864, 2368),
    SECOND(2752, 1888);

    private final Point point;

    SpawnPoint(int x, int y) {
        this.point = new Point(x, y);
    }

    public Point getCoords() {
        return point;
    }
}
