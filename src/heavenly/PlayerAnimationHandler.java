package heavenly;

import doctrina.Animation;
import doctrina.AnimationHandler;

public class PlayerAnimationHandler extends AnimationHandler {

    private static final String SPRITE_PATH_DOWN_MOVEMENT = "images/characters/RedHood/Front_Movement.png";
    private static final String SPRITE_PATH_LEFT_MOVEMENT = "images/characters/RedHood/Left_Movement.png";
    private static final String SPRITE_PATH_RIGHT_MOVEMENT = "images/characters/RedHood/Right_Movement.png";
    private static final String SPRITE_PATH_UP_MOVEMENT = "images/characters/RedHood/Back_Dash.png";

    public PlayerAnimationHandler(Player player) {
        setDownAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_DOWN_MOVEMENT));
        setLeftAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_LEFT_MOVEMENT));
        setRightAnimation(new Animation(0, 32,
                player.getWidth(), player.getHeight(), 6, SPRITE_PATH_RIGHT_MOVEMENT));
        setUpAnimation(new Animation(0, 0,
                player.getWidth(), player.getHeight(), 4, SPRITE_PATH_UP_MOVEMENT));
    }
}
