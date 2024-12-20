package utopia.player;

import doctrina.Animation;
import doctrina.AnimationHandler;

public class PlayerAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH_DOWN_MOVEMENT = "image/characters/RedHood/Front_Movement.png";
    private static final String SPRITE_PATH_LEFT_MOVEMENT = "image/characters/RedHood/Left_Movement.png";
    private static final String SPRITE_PATH_RIGHT_MOVEMENT = "image/characters/RedHood/Right_Movement.png";
    private static final String SPRITE_PATH_UP_MOVEMENT = "image/characters/RedHood/Back_Movement.png";
    private static final String SPRITE_DOWN_SLASH = "image/characters/RedHood/Front_Slash.png";
    private static final String SPRITE_UP_SLASH = "image/characters/RedHood/Back_Slash.png";
    private static final String SPRITE_RIGHT_SLASH = "image/characters/RedHood/Right_Slash.png";
    private static final String SPRITE_LEFT_SLASH = "image/characters/RedHood/Left_Slash.png";

    private static final String SPRITE_DOWN_DASH = "image/characters/RedHood/Front_Dash.png";
    private static final String SPRITE_UP_DASH = "image/characters/RedHood/Back_Dash.png";
    private static final String SPRITE_RIGHT_DASH = "image/characters/RedHood/Right_Dash.png";
    private static final String SPRITE_LEFT_DASH = "image/characters/RedHood/Left_Dash.png";

    public PlayerAnimationHandler(Player player) {
        super(player);
        setAnimationSpeed(10);
        setDownMovementAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_DOWN_MOVEMENT));
        setLeftMovementAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_LEFT_MOVEMENT));
        setRightMovementAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_RIGHT_MOVEMENT));
        setUpMovementAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_UP_MOVEMENT));

        setDownIdleAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_DOWN_MOVEMENT));
        setLeftIdleAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_LEFT_MOVEMENT));
        setRightIdleAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_RIGHT_MOVEMENT));
        setUpIdleAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_UP_MOVEMENT));

        setDownAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_DOWN_SLASH));
        setUpAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_UP_SLASH));
        setRightAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_RIGHT_SLASH));
        setLeftAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_LEFT_SLASH));

        setDownDashAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 4, SPRITE_DOWN_DASH));
        setUpDashAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 4, SPRITE_UP_DASH));
        setLeftDashAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 4, SPRITE_LEFT_DASH));
        setRightDashAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 4, SPRITE_RIGHT_DASH));
    }
}
