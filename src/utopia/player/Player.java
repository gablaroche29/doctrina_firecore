package utopia.player;

import doctrina.*;
import doctrina.Canvas;
import doctrina.ControllableEntity;
import utopia.GameMouse;
import utopia.GamePad;
import utopia.Inventory;
import utopia.audio.SoundEffect;
import utopia.entities.spawnpoint.SpawnPoint;
import utopia.player.spell.IceSpellLoader;
import utopia.spell.Spell;
import utopia.spell.SpellLoader;

import java.awt.*;
import java.util.List;

public class Player extends ControllableEntity {

    private final PlayerAnimationHandler animationHandler;
    private final SpellLoader spellLoader;
    private final Inventory inventory;
    private boolean hasAttacked;
    private int attackCoolDown = 0;
    private int pv = 5;
    private int mana = 100;
    private int crystal = 2;
    private int potion = 1;
    private boolean isHurt;
    private boolean isAlive = true;
    private boolean isDashing;
    private int dashCooldown = 0;
    private float distance = 0;
    private SpawnPoint spawnPoint;
    private boolean iceSpellActive;

    public Player(MovementController controller, int x, int y) {
        super(controller, 5);
        teleport(x, y);
        controller.useWasdKeys();
        setDimension(32, 32);
        setSpeed(2f);
        animationHandler = new PlayerAnimationHandler(this);
        setDirection(Direction.DOWN);
        spellLoader = new IceSpellLoader(this);
        inventory = new Inventory(this);
        state = State.IDLE;
    }

    @Override
    public void update() {
        super.update();
        if (!hasAttacked) {
            moveWithController();
        }

        updateAttackCooldown();
        if (GameMouse.getInstance().isKeyPressed(GameMouse.LEFT_CLICK) && !hasAttacked && !isDashing) {
            hasAttacked = true;
            attackCoolDown = 65;
            SoundEffect.MELEE_SWORD.play();
            GameMouse.getInstance().setKeyStateFalse(GameMouse.LEFT_CLICK);
        } else if (GamePad.getInstance().isQPressed() && !hasAttacked && iceSpellActive && !isDashing && mana >= 30) {
            hasAttacked = true;
            attackCoolDown = 65;
            spellLoader.shoot();
            SoundEffect.ICE_BALL.play();
            mana -= 30;
            GamePad.getInstance().setKeyStateFalse(GamePad.qKey);
        }

        if (GamePad.getInstance().isOnePressed() && potion > 0) {
            SoundEffect.HEAL_SPELL.play();
            heal();
            potion--;
            GamePad.getInstance().setKeyStateFalse(GamePad.oneKey);
        }

        if (pv <= 0) {
            isAlive = false;
        }

        if (GamePad.getInstance().isSpacePressed() && !isDashing && dashCooldown == 0) {
            isDashing = true;
            dashCooldown = 50;
            setSpeed(getSpeed() * 2);
            GamePad.getInstance().setKeyStateFalse(GamePad.spaceKey);
        }

        if (isDashing) {
            move();
            moved = false;
            distance += getSpeed();
            if (distance == 40f) {
                distance = 0;
                isDashing = false;
                setSpeed(2f);
                state = State.IDLE;
            }
        }

        dashCooldown--;
        if (dashCooldown <= 0) {
            dashCooldown = 0;
        }

        spellLoader.update();
        updateAnimationState();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        canvas.drawImage(getAnimationFrame(), x - camera.getX(), y - camera.getY());
        int cooldownWidth = dashCooldown * width / 50;
        canvas.drawRectangle(x - camera.getX(), y - 5 - camera.getY(), cooldownWidth, 3, Color.RED);
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
        } else if (isDashing) {
            state = State.DASHING;
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
        if (isDashing) {
            return animationHandler.getDashFrame();
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

    public int getMana() {
        return mana;
    }

    public void giveMana(int mana) {
        this.mana += mana;
        if (this.mana >= 100) {
            this.mana = 100;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isIceSpellActive() {
        return iceSpellActive;
    }

    public void setIceSpellActive(boolean iceSpellActive) {
        crystal -= 30;
        this.iceSpellActive = iceSpellActive;
    }
}
