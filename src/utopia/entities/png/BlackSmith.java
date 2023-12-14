package utopia.entities.png;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.GamePad;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;

public class BlackSmith extends MovableEntity {

    private final BlackSmithAnimationHandler animationHandler;
    private String[] dialogues;
    private int dialogueIndex = 0;
    private boolean finishTalking;
    private final Player player;

    public BlackSmith(Player player) {
        super(0);
        this.player = player;
        setDimension(32, 32);
        teleport(832, 2432);

        animationHandler = new BlackSmithAnimationHandler(this);
        setDialogues();
        setState(State.IDLE);
        setDirection(Direction.DOWN);
    }

    @Override
    public void update() {
        if (GameMouse.getInstance().isKeyPressed(GameMouse.rightClick)) {
            if (player.intersectWith(this)) {
                SoundEffect.INTERACTION.play();
                GameContext.INSTANCE.setCurrentState(GameState.DIALOGUE);
                Ui.setTexts(speak());
            }
            GameMouse.getInstance().setKeyStateFalse(GameMouse.rightClick);
            if (isFinishTalking()) {
                GameContext.INSTANCE.setCurrentState(GameState.GAME);
            }
        }
        animationHandler.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Image sprite = animationHandler.getIdleFrame();
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
        dialogues[1] = "Cela fait longtemps qu'on \nce n'est pas vue...";
        dialogues[2] = "Je te souhaite bonne chance dans \nta quête!";
        dialogues[3] = "";
    }
}
