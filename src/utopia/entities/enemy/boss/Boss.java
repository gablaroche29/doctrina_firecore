package utopia.entities.enemy.boss;

import doctrina.*;
import doctrina.Canvas;
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
    private SpellLoader spellLoader;
    private Bounds triggerZone;
    protected AnimationHandler animationHandler;
    private boolean activate;

    private boolean isAlive = true;
    private int attackCoolDown = 0;
    protected int maxPv = 3;
    private int pv = maxPv;
    private final int crystal;
    private boolean isKnockback;
    private boolean hasAttacked;

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
    }

    @Override
    public void update() {
        super.update();
        updateTriggerZone();
        updateAttackCooldown();


        if (triggerZone.intersectsWith(player) && !activate) {
            activate = true;
            Music.BG_GAME.stop();
            Music.RAIN_AMBIANCE.stop();
            Music.BOSS_BATTLE.play(Clip.LOOP_CONTINUOUSLY);
        }

        if (activate && !hasAttacked) {
            moving();
        }

        if (player.hasAttacked() && !isKnockback) {
            if (intersectWith(player.getAttackZone())) {
                SoundEffect.MONSTER_HIT.play();
                isKnockback = true;
                pv--;
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
            if (intersectWith(spell.getAttackZone())) {
                SoundEffect.MONSTER_HIT.play();
                pv -= 5;
                spell.remove();
                isKnockback = true;
                killed();
            }
        }

        if (isKnockback) {
            setSpeed(getSpeed() + 1.5f);
            move(getOpposateDirection());
            setDirection(getOpposateDirection());
            if (getSpeed() > 20f) {
                setSpeed(1.5f);
                isKnockback = false;
            }
        }

        if (getAttackZone().intersectsWith(player) && !hasAttacked) {
            hasAttacked = true;
            spellLoader.shoot();
            attackCoolDown = 102;
            SoundEffect.FIRE_BALL.play();
            state = State.ATTACK;
        }

        spellLoader.update();
        updateAnimationState();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        float prop = (float) pv / maxPv;
        float pvString = prop * width;
        if (camera.intersectWith(this)) {
            canvas.drawImage(getAnimationFrame(), x - camera.getX(), y - camera.getY());
            canvas.drawRectangle(x - camera.getX(), y - 5 - camera.getY(), (int) pvString, 2, Color.RED);
        }
        spellLoader.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            drawCollisionDetector(canvas, camera);
            drawHitBox(canvas, camera);
            drawAttackZone(canvas, camera);
        }
    }

    private void killed() {
        if (pv <= 0) {
            SoundEffect.MONSTER_DEAD.play();
            player.addCrystal(crystal);
            Ui.enemyKilled(crystal);
            isAlive = false;
            restartMusic();
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
        return animationHandler.getIdleFrame();
    }

    private void updateAttackCooldown() {
        attackCoolDown--;
        if (attackCoolDown <= 0) {
            attackCoolDown = 0;
            hasAttacked = false;
        }
    }

    private void updateAnimationState() {
        State currentState = state;
        if (hasMoved()) {
            state = State.MOVE;
        } else if (hasAttacked) {
            state = State.ATTACK;
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}
