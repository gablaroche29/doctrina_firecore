package heavenly;

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

    public PlayerAnimationHandler(Player player) {
        super(player);
        setDownAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_DOWN_MOVEMENT));
        setLeftAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_LEFT_MOVEMENT));
        setRightAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_RIGHT_MOVEMENT));
        setUpAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_UP_MOVEMENT));
        setDownAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_DOWN_SLASH));
        setUpAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_UP_SLASH));
        setRightAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_RIGHT_SLASH));
        setLeftAttackAnimation(new Animation(0, 64,
                player.getWidth(), player.getHeight(), 7, SPRITE_LEFT_SLASH));
    }
}
