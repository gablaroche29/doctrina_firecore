package utopia.entities.png;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;

public class BlackSmith extends MovableEntity {

    private final BlackSmithAnimationHandler animationHandler;
    private String[] dialogues;
    private int dialogueIndex = 0;
    private boolean finishTalking;
    private Player player;
    private Image rightClick;
    private final FontLoader font;

    public BlackSmith(int x, int y) {
        super(0);
        setDimension(32, 32);
        teleport(768, 2368);

        animationHandler = new BlackSmithAnimationHandler(this);
        load();
        font = new FontLoader("/font/perpetua/perpetua_bold.ttf", 15.f);
        setDialogues();
        setState(State.IDLE);
        setDirection(Direction.DOWN);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        if (GameMouse.getInstance().isKeyPressed(GameMouse.RIGHT_CLICK)) {
            if (player.intersectWith(this)) {
                SoundEffect.INTERACTION.play();
                GameContext.INSTANCE.setCurrentState(GameState.INTERACTION);
                Ui.setDialogueText(speak());
            }
            GameMouse.getInstance().setKeyStateFalse(GameMouse.RIGHT_CLICK);
            if (isFinishTalking()) {
                GameContext.INSTANCE.setCurrentState(GameState.GAME);
            }
        }
        animationHandler.update();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (camera.intersectWith(this)) {
            Image sprite = animationHandler.getIdleFrame();
            canvas.drawImage(sprite, x - camera.getX(), y - camera.getY());
            canvas.drawImage(rightClick, x + 7 - camera.getX(), y - 20 - camera.getY(), 20, 20);
        }
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void setDialogues() {
        dialogues = new String[5];
        dialogues[0] = "Salut, voyageuse...";
        dialogues[1] = "Cela faisait longtemps que j'en avais pas\nvu comme toi...";
        dialogues[2] = "À ta place, j'arrêterai tout ce que je fais\n, mais fais ce que tu veux...";
        dialogues[3] = "Après tout, je ne suis qu'un simple\nforgeron...";
        dialogues[4] = "";
    }

    private void load() {
        rightClick = SpriteSheetSlicer.getSprite(32, 0, 32, 32, "image/ui/keys.png");
    }
}
