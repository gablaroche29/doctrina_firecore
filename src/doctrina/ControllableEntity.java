package doctrina;

import doctrina.MovableEntity;
import doctrina.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private final MovementController controller;

    public ControllableEntity(MovementController controller, int attackZone) {
        super(attackZone);
        this.controller = controller;
    }

    public void moveWithController() {
        if (controller.isMoving()) {
            move(controller.getDirection());
        }
    }
}
