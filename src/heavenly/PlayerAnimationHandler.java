package heavenly;

import doctrina.Animation;
import doctrina.AnimationHandler;

public class PlayerAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH_DOWN_MOVEMENT = "image/characters/RedHood/Front_Movement.png";
    private static final String SPRITE_PATH_LEFT_MOVEMENT = "image/characters/RedHood/Left_Movement.png";
    private static final String SPRITE_PATH_RIGHT_MOVEMENT = "image/characters/RedHood/Right_Movement.png";
    private static final String SPRITE_PATH_UP_MOVEMENT = "image/characters/RedHood/Back_Dash.png";
    private static final String SPRITE_FRONT_SLASH = "image/characters/RedHood/Front_Slash.png";

    public PlayerAnimationHandler(Player player) {
        super(player);
        setDownAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_DOWN_MOVEMENT));
        setLeftAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_LEFT_MOVEMENT));
        setRightAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_RIGHT_MOVEMENT));
        setUpAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 4, SPRITE_PATH_UP_MOVEMENT));
        setFrontAttackAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 9, SPRITE_FRONT_SLASH));
    }
}
