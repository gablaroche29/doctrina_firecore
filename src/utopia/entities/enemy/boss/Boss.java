package utopia.entities.enemy.boss;

import doctrina.*;
import doctrina.Canvas;
import utopia.GameMouse;
import utopia.audio.Music;
import utopia.audio.SoundEffect;
import utopia.entities.enemy.type.necromancer.FireSpellLoader;
import utopia.player.Player;
import utopia.spell.Spell;
import utopia.spell.SpellLoader;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.Random;

public class Boss extends MovableEntity {

    private Player player;
    private final SpellLoader spellLoader;
    private Bounds triggerZone;
    protected AnimationHandler animationHandler;
    private boolean activate;
    private boolean spoke;
    private boolean ready;
    private Camera camera;

    private String[] dialogues;
    private int dialogueIndex = 0;
    private boolean finishTalking;
    private final FontLoader font;

    private boolean isAlive = true;
    private int attackCoolDown = 0;
    private int hurtCooldown = 0;
    private int deadCoolDown = 0;
    protected int maxPv = 3;
    private int pv = maxPv;
    private final int crystal;
    private boolean isHurt;
    private boolean isDead;
    private boolean hasAttacked;
    private final Point[] spawns;

    public Boss(int x, int y, float speed) {
        super(150);
        teleport(x, y);
        setSpeed(speed);
        setDimension(32, 32);
        setDirection(Direction.DOWN);
        state = State.IDLE;

        Random rnd = new Random();
        crystal = rnd.nextInt(150) + 1;
        setTriggerZone();
        spellLoader = new FireSpellLoader(this);
        setDialogues();
        font = new FontLoader("/font/perpetua/perpetua.ttf", 25.f);

        spawns = new Point[4];
        spawns[0] = new Point(352, 1184);
        spawns[1] = new Point(864, 1184);
        spawns[2] = new Point(384, 1504);
        spawns[3] = new Point(832, 1504);
    }

    @Override
    public void update() {
        super.update();
        updateTriggerZone();
        updateAttackCooldown();
        updateHurtCooldown();
        if (isDead) {
            updateDeadCoolDown();
        }

        if (triggerZone.intersectsWith(player) && !ready) {
            ready = true;
            SoundEffect.NECROMANCER_SCREAM.play();
            Music.BG_GAME.stop();
            Music.RAIN_AMBIANCE.stop();
            Music.WIND.play(Clip.LOOP_CONTINUOUSLY);
            camera.follow(this);
        }

        if (ready && !activate) {
            if (!spoke) {
                GameContext.INSTANCE.setCurrentState(GameState.INTERACTION);
                Ui.setDialogueText(speak(), font);
                spoke = true;
            }
            if (GameMouse.getInstance().isKeyPressed(GameMouse.RIGHT_CLICK)) {
                SoundEffect.INTERACTION.play();
                GameContext.INSTANCE.setCurrentState(GameState.INTERACTION);
                Ui.setDialogueText(speak(), font);
                GameMouse.getInstance().setKeyStateFalse(GameMouse.RIGHT_CLICK);
                if (isFinishTalking()) {
                    GameContext.INSTANCE.setCurrentState(GameState.GAME);
                    activate = true;
                    camera.follow(player);
                }
            }
        }


        if (activate && !isDead) {
            Music.BOSS_BATTLE.play(Clip.LOOP_CONTINUOUSLY);
        }

        if (activate && !hasAttacked && !isHurt && !isDead) {
            moving();
        }

        if (player.hasAttacked() && !isHurt && !isDead && activate) {
            if (intersectWith(player.getAttackZone())) {
                //SoundEffect.MONSTER_HIT.play();
                pv--;
                player.giveMana(10);
                isHurt();
                killed();
            }
        }

        for (Spell spell : spellLoader.getSpells()) {
            if (spell.getAttackZone().intersectsWith(player)) {
                player.dropPv();
                player.setHurt(true);
                spell.remove();
            }
        }

        for (Spell spell : player.getSpells()) {
            if (intersectWith(spell.getAttackZone()) && !isHurt && !isDead && activate) {
                //SoundEffect.MONSTER_HIT.play();
                pv -= 5;
                player.giveMana(10);
                spell.remove();
                isHurt();
                killed();
            }
        }

        if (getAttackZone().intersectsWith(player) && !hasAttacked && !isHurt && !isDead && activate) {
            hasAttacked = true;
            spellLoader.shoot();
            attackCoolDown = 102;
            SoundEffect.FIRE_BALL.play();
        }

        spellLoader.update();
        updateAnimationState();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        float prop = (float) pv / maxPv;
        float pvString = prop * 100;
        if (camera.intersectWith(this)) {
            canvas.drawImage(getAnimationFrame(), x - camera.getX(), y - camera.getY());
        }
        if (activate) {
            Ui.bossEventDetails(pvString, "Necromancer");
        }
        spellLoader.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            drawCollisionDetector(canvas, camera);
            drawHitBox(canvas, camera);
            drawAttackZone(canvas, camera);
        }
    }

    private void isHurt() {
        isHurt = true;
        hasAttacked = false;
        hurtCooldown = 35;
    }

    private void killed() {
        if (pv <= 0) {
            isHurt = false;
            player.addCrystal(crystal);
            Ui.enemyKilled(crystal);
            isDead = true;
            restartMusic();
            deadCoolDown = 48;
            SoundEffect.NECROMANCER_DEATH.play();
        }
    }

    private void restartMusic() {
        Music.BOSS_BATTLE.stop();
        Music.BG_GAME.play(Clip.LOOP_CONTINUOUSLY);
        Music.RAIN_AMBIANCE.play(Clip.LOOP_CONTINUOUSLY);
    }

    private Image getAnimationFrame() {
        if (hasAttacked) {
            return animationHandler.getAttackFrame();
        }
        if (hasMoved()) {
            return animationHandler.getDirectionFrame();
        }
        if (isHurt) {
            return animationHandler.getHurtFrame();
        }
        if (isDead) {
            return animationHandler.getDeadFrame();
        }
        return animationHandler.getIdleFrame();
    }

    private void updateHurtCooldown() {
        Random rnd = new Random();
        hurtCooldown--;
        if (hurtCooldown <= 0 && isHurt) {
            hurtCooldown = 0;
            isHurt = false;
            teleport(spawns[rnd.nextInt(4)]);
            SoundEffect.NECROMANCER_TELEPORT.play();
        }
    }

    private void updateAttackCooldown() {
        attackCoolDown--;
        if (attackCoolDown <= 0 && hasAttacked) {
            attackCoolDown = 0;
            hasAttacked = false;
        }
    }

    private void updateDeadCoolDown() {
        deadCoolDown--;
        if (deadCoolDown <= 0) {
            deadCoolDown = 0;
            isAlive = false;
        }
    }

    private void updateAnimationState() {
        State currentState = state;
        if (hasMoved()) {
            state = State.MOVE;
        } else if (hasAttacked) {
            state = State.ATTACK;
        } else if (isHurt) {
            state = State.HURT;
        } else if (isDead) {
            state = State.DEAD;
        } else {
            state = State.IDLE;
        }

        if (currentState != state) {
            animationHandler.reset();
        }
        animationHandler.update();
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void moving() {
        Direction playerDirection = calculatePlayerDirection();
        int distanceX = (x + getWidth() / 2) - (player.getX() + player.getWidth() / 2);
        int distanceY = (y + getHeight() / 2) - (player.getY() + player.getHeight() / 2);
        distanceX -= 100;
        distanceY -= 100;

        if (distanceX != 0 || distanceY != 0) {
            Direction directionToGo;
            if (playerDirectionIsCorrupted(playerDirection)) {
                directionToGo = simulateNewDirection(distanceX, distanceY);
            } else {
                directionToGo = playerDirection;
            }
            move(directionToGo);
        }
    }

    private boolean playerDirectionIsCorrupted(Direction playerDirection) {
        int lastX = getX();
        int lastY = getY();
        move(playerDirection);
        if (!hitBoxIntersectsWithCollisions()) {
            teleport(lastX, lastY);
            return false;
        } else {
            teleport(lastX, lastY);
            return true;
        }
    }

    private boolean hitBoxIntersectsWithCollisions() {
        for (StaticEntity entity : CollidableRepository.getInstance()) {
            if (hitBoxIntersectWith(entity)) {
                return true;
            }
        }
        return false;
    }

    private Direction simulateNewDirection(int distanceX, int distanceY) {
        if ((distanceX > 0 && distanceY > 0) || (distanceX > 0 && distanceY < 0)) {
            return Direction.LEFT;
        }
        if ((distanceX < 0 && distanceY > 0) || (distanceX < 0 && distanceY < 0)) {
            return Direction.RIGHT;
        }
        return Direction.UP;
    }

    private Direction calculatePlayerDirection() {
        if ((player.getY() + player.getHeight() / 2) < (y + height / 2)) {
            return Direction.UP;
        }
        if ((player.getY() + player.getHeight() / 2) > (y + height / 2)) {
            return Direction.DOWN;
        }
        if ((player.getX() + player.getWidth() / 2) < (x + width / 2)) {
            return Direction.LEFT;
        }
        if ((player.getX() + player.getWidth() / 2) > (x + width / 2)) {
            return Direction.RIGHT;
        }
        return Direction.DOWN;
    }

    private void setTriggerZone() {
        final int diameter = 500;
        triggerZone = new Bounds(x + (width / 2) - (diameter / 2), y + (height / 2) - (diameter / 2),
                diameter, diameter);
    }

    private void updateTriggerZone() {
        triggerZone.setCoords(x + (width / 2) - (triggerZone.getWidth() / 2), y + (height / 2) - (triggerZone.getWidth() / 2));
    }

    public void setPv(int pv) {
        maxPv = pv;
        this.pv = maxPv;
    }

    public void setValues(Player player) {
        this.player = player;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    private void setDialogues() {
        dialogues = new String[4];
        dialogues[0] = "Sous le ciel écarlate, je deviens l'incarnation de\nla fureur ancestrale.";
        dialogues[1] = "Mes pas sont des séismes, et ma volonté est le feu\nqui forge les légendes.";
        dialogues[2] = "Battons-nous, étranger...";
        dialogues[3] = "";
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
}