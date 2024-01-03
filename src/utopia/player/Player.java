package utopia.player;

import doctrina.*;
import doctrina.Canvas;
import doctrina.ControllableEntity;
import utopia.GameMouse;
import utopia.GamePad;
import utopia.audio.SoundEffect;
import utopia.entities.enemy.type.necromancer.Necromancer;
import utopia.entities.spawnpoint.SpawnPoint;
import utopia.player.spell.IceSpellLoader;
import utopia.spell.Spell;
import utopia.spell.SpellLoader;

import java.awt.*;
import java.util.List;

public class Player extends ControllableEntity {

    private final PlayerAnimationHandler animationHandler;
    private final SpellLoader spellLoader;
    private boolean hasAttacked;
    private int attackCoolDown = 0;
    private int pv = 5;
    private int crystal = 2;
    private int potion = 1;
    private boolean isHurt;
    private boolean isAlive = true;
    private SpawnPoint spawnPoint;

    public Player(MovementController controller, int x, int y) {
        super(controller, 5);
        teleport(x, y);
        controller.useWasdKeys();
        setDimension(32, 32);
        setSpeed(2);
        animationHandler = new PlayerAnimationHandler(this);
        setDirection(Direction.DOWN);
        spellLoader = new IceSpellLoader(this);
        state = State.IDLE;
    }

    @Override
    public void update() {
        super.update();
        if (!hasAttacked) {
            moveWithController();
        }

        updateAttackCooldown();
        if (GameMouse.getInstance().isKeyPressed(GameMouse.leftClick) && !hasAttacked) {
            hasAttacked = true;
            attackCoolDown = 65;
            SoundEffect.MELEE_SWORD.play();
            GameMouse.getInstance().setKeyStateFalse(GameMouse.leftClick);
        } else if (GamePad.getInstance().isQPressed() && !hasAttacked && crystal > 0) {
            hasAttacked = true;
            attackCoolDown = 65;
            spellLoader.shoot();
            SoundEffect.ICE_BALL.play();
            crystal--;
            GamePad.getInstance().setKeyStateFalse(GamePad.qKey);
        }

        if (GamePad.getInstance().isPPressed() && potion > 0) {
            SoundEffect.HEAL_SPELL.play();
            heal();
            potion--;
            GamePad.getInstance().setKeyStateFalse(GamePad.pKey);
        }

        if (pv <= 0) {
            isAlive = false;
        }
        spellLoader.update();
        updateAnimationState();
        System.out.println("State: " + state + ":"+animationHandler.currentAnimationFrame);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(getAnimationFrame(), x - camera.getX(), y - camera.getY());
        spellLoader.draw(canvas, camera);

        if (GameConfig.isDebugEnabled()) {
            drawHitBox(canvas, camera);
            drawCollisionDetector(canvas, camera);
            if (hasAttacked) {
                drawAttackZone(canvas, camera);
            }
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

    private void updateAttackCooldown() {
        attackCoolDown--;
        if (attackCoolDown <= 0) {
            attackCoolDown = 0;
            hasAttacked = false;
        }
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

    public int getPv() {
        return pv;
    }

    public void heal() {
        pv = 5;
        isAlive = true;
    }

    public void setSpawnPoint(SpawnPoint spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public SpawnPoint getSpawnPoint() {
        return spawnPoint;
    }

    public void addCrystal(int crystal) {
        this.crystal += crystal;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isHurt() {
        return isHurt;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public int getCrystal() {
        return crystal;
    }

    public void addPotion(int potion) {
        this.potion += potion;
    }

    public int getPotion() {
        return potion;
    }

    public void setHurt(boolean hurt) {
        isHurt = hurt;
    }

    public List<Spell> getSpells() {
        return spellLoader.getSpells();
    }

    public void dropPv() {
        SoundEffect.HIT_DAMAGE.play();
        pv--;
        if (pv <= 0 ) {
            pv = 0;
        }
    }
}
