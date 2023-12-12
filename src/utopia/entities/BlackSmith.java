package utopia.entities;

import doctrina.Camera;
import doctrina.Canvas;
import doctrina.SpriteSheetSlicer;
import doctrina.StaticEntity;

import java.awt.*;

public class BlackSmith extends StaticEntity {

    private static final String PATH = "image/characters/BlackSmith/blacksmith.png";
    private Image sprite;

    private String[] dialogues;
    private int dialogueIndex = 0;
    private boolean finishTalking;

    public BlackSmith() {
        setDimension(32, 32);
        teleport(832, 2432);
        load();

        setDialogues();
    }

    @Override
    public void update() {
        // TODO: 2023-12-12
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
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

    private void load() {
        sprite = SpriteSheetSlicer.getSprite(0, 0, 32, 32, PATH);
    }

    private void setDialogues() {
        dialogues = new String[4];
        dialogues[0] = "Salut chère amie!";
        dialogues[1] = "Cela fait longtemps qu'on ce n'est pas vue...";
        dialogues[2] = "Je te souhaite bonne chance dans ta quête!";
        dialogues[3] = "";
    }
}
