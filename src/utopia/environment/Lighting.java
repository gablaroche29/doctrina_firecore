package utopia.environment;

import doctrina.MovableEntity;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Lighting {

    private final MovableEntity entity;
    private Area lightArea;

    public Lighting(MovableEntity entity) {
        this.entity = entity;
        setupArea();
    }

    private void setupArea() {
        Shape shape = new Ellipse2D.Double(
                350,
                250,
                100,
                100);
        lightArea = new Area(shape);
    }

    public Area getLightArea() {
        return lightArea;
    }
}
