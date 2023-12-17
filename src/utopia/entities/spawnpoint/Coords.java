package utopia.entities.spawnpoint;

import java.awt.*;

public enum Coords {
    FIRST(864, 2368),
    SECOND(2752, 1888);

    private final Point point;

    Coords(int x, int y) {
        this.point = new Point(x, y);
    }

    public Point getCoords() {
        return point;
    }
}
