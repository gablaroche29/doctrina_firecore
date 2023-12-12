package utopia.entities.png;

import doctrina.*;
import doctrina.Canvas;

import java.awt.*;

public class BlackSmith extends MovableEntity {

    private Image sprite;

    private BlackSmithAnimationHandler animationHandler;
    private String[] dialogues;
    private int dialogueIndex = 0;
    private boolean finishTalking;

    public BlackSmith() {
        super(0);
        setDimension(32, 32);
        teleport(832, 2432);

        animationHandler = new BlackSmithAnimationHandler(this);
        setDialogues();
        setState(State.IDLE);
        setDirection(Direction.DOWN);
    }

    @Override
    public void update() {
        animationHandler.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        sprite = animationHandler.getIdleFrame();
        canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
    }

    public String speak() {
        finishTalking = false;
        String text = dialogues[dialogueIndex];
        dialogueIndex++;
        if (dialogueIndex >= dialogues.length) {
            finishTalking = true;
            dialogueIndex = 0;
        }
        return text;
    }

    public boolean isFinishTalking() {
        return finishTalking;
    }

    private void setDialogues() {
        dialogues = new String[4];
        dialogues[0] = "Salut chère amie!";
        dialogues[1] = "Cela fait longtemps qu'on ce n'est pas vue...";
        dialogues[2] = "Je te souhaite bonne chance dans ta quête!";
        dialogues[3] = "";
    }
}
