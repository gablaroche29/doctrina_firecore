package utopia.entities.png;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.audio.SoundEffect;
import utopia.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackSmith extends MovableEntity {

    private final BlackSmithAnimationHandler animationHandler;
    private List<String> dialogues;
    private int dialogueIndex = 0;
    private boolean finishTalking;
    private Player player;
    private Image rightClick;
    private final FontLoader font;

    public BlackSmith(int x, int y) {
        super(0);
        setDimension(32, 32);
        teleport(x, y);

        animationHandler = new BlackSmithAnimationHandler(this);
        load();
        font = new FontLoader("/font/perpetua/perpetua_bold.ttf", 25.f);
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
                GameMouse.getInstance().setKeyStateFalse(GameMouse.RIGHT_CLICK);
                if (player.getCrystal() >= 30) {
                    player.setIceSpellActive(true);
                    dialogues.clear();
                    dialogues.add("Voici la vraie puissance.");
                    dialogues.add("Concentre ton mana pour projeter\nune puissance glaciale sur\nles monstres.");
                    dialogues.add("Bonne chance pour la suite...");
                    dialogues.add("");
                }
                Ui.setDialogueText(speak(), font);
            }
            if (isFinishTalking()) {
                GameContext.INSTANCE.setCurrentState(GameState.GAME);
                GameMouse.getInstance().setKeyStateFalse(GameMouse.RIGHT_CLICK);
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
        String text = dialogues.get(dialogueIndex);
        dialogueIndex++;
        if (dialogueIndex >= dialogues.size()) {
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
        dialogues = new ArrayList<>();
        dialogues.add("Salut, voyageuse...");
        dialogues.add("Cela faisait longtemps que j'en avais pas\nvu comme toi...");
        dialogues.add("À ta place, j'arrêterai tout ce que je fais\n, mais fais ce que tu veux...");
        dialogues.add("Cependant, ramène moi 30 crystaux et je\nte forgerais une arme puissante...");
        dialogues.add("");
    }

    private void load() {
        rightClick = SpriteSheetSlicer.getSprite(32, 0, 32, 32, "image/ui/keys.png");
    }
}
