package utopia.environment;

import doctrina.Camera;
import doctrina.MovableEntity;
import doctrina.StaticEntity;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Lighting {

    private final MovableEntity entity;
    private final Camera camera;
    private Area lightArea;

    public Lighting(MovableEntity entity, Camera camera) {
        this.entity = entity;
        this.camera = camera;
        setupArea();
    }

    private void setupArea() {
        Shape shape = new Ellipse2D.Double(
                entity.getX() - 30 - camera.getX(),
                entity.getY() - 30 - camera.getY(),
                30 + 32 + 30,
                30 + 32 + 30);
        lightArea = new Area(shape);
    }

    public Area getLightArea() {
        return lightArea;
    }
}
