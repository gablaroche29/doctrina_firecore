package firecore;

import doctrina.*;
import doctrina.Canvas;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FireCoreGame extends Game {

    private GamePad gamePad;
    private Player player;
    private Monster monster;
    private Monster monster2;

    private Pillar pillar;

    private List<MovableEntity> collidableEntities;
    private World world;
    private List<StaticEntity> renderingEntities;

    @Override
    protected void initialize() {
        GameConfig.disableDebug();
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(864, 2368);
        world = new World(player);

        camera = new Camera(player, world, 800, 600);
        monster = new Monster(player);
        monster.teleport(1152, 2528);
        monster2 = new Monster(player);
        monster2.teleport(1140, 2518);

        collidableEntities = new ArrayList<>();
        collidableEntities.add(player);
        collidableEntities.add(monster);
        collidableEntities.add(monster2);

        pillar = new Pillar();


        RenderingEngine.getInstance().getScreen().fullscreen();
        RenderingEngine.getInstance().getScreen().showCursor();

        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("music/bg/TheLoomingBattle-bg.wav"));
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            camera.stopCameraThread();
            stop();
        }
        player.update();
//        for (StaticEntity entity : renderingEntities) {
//            entity.setRender(entity.isInCameraField(camera));
//        }

        world.update(collidableEntities);
        monster.update();
        monster2.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas, camera);
//        for (StaticEntity entity : renderingEntities) {
//            if (entity.getRender()) {
//                entity.draw(canvas, camera);
//            }
//        }
        player.draw(canvas, camera);
        canvas.drawString("FPS " + GameTime.getCurrentFps(), 20, 20, Color.WHITE);

        monster.draw(canvas, camera);
        monster2.draw(canvas, camera);


        pillar.draw(canvas, camera);
        //world.drawTrees(canvas, camera);
    }
}
