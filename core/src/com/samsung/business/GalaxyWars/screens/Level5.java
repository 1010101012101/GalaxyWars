package com.samsung.business.GalaxyWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.samsung.business.GalaxyWars.entity.PlayerSpaceship;
import com.samsung.business.GalaxyWars.entity.Spaceship;
import com.samsung.business.GalaxyWars.ui.DisplayInfo;
import com.samsung.business.GalaxyWars.ui.TouchInput;
import com.samsung.business.GalaxyWars.ui.components.ScoreGuiLabel;
import com.samsung.business.GalaxyWars.GalaxyWars;
import com.samsung.business.GalaxyWars.entity.EnemySpaceship;
import com.samsung.business.GalaxyWars.entity.Invasion;
import com.samsung.business.GalaxyWars.manager.GraphicsManager;
import com.samsung.business.GalaxyWars.manager.ShootManager;

public class Level5 extends AbstractScreen {
    private final GalaxyWars galaxyWars;
    private final BitmapFont font;
    private OrthographicCamera camera;
    private ShootManager shootManager;
    private GraphicsManager graphicsManager;
    private PlayerSpaceship player;
    private Invasion invasion;
    private ScoreGuiLabel scoreGuiLabel;
    private TouchInput touchInput;
    private float animationTime;
    private int height = DisplayInfo.getHeight();


    public Level5(GalaxyWars galaxyWars) {
        this.galaxyWars = galaxyWars;
        font = new BitmapFont();
        scoreGuiLabel = new ScoreGuiLabel();
        create();
    }

    public void create() {
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DisplayInfo.getWidth(), DisplayInfo.getHeight());

        touchInput = new TouchInput(camera);

        //zaladuj tekstury
        graphicsManager = GraphicsManager.loadGraphics();

        //utworz rakiete gracza
        player = new PlayerSpaceship(graphicsManager.find("player"), camera);
        player.registerOnSpaceshipHit(new Spaceship.OnSpaceshipHit() {
            @Override
            public void onSpaceshipHit() {
                galaxyWars.gameOver();
                dispose();
            }
        });

        //przygotuj raid wroga
        invasion = Invasion.raid(graphicsManager);

        invasion.listenOnDestroyed(new Invasion.OnEnemyDestroyed() {
            @Override
            public void onEnemyDestroyed(EnemySpaceship enemy) {
                galaxyWars.getScore().addScore(100);
            }
        });

        invasion.listenOnInvasionDestroyed(new Invasion.OnInvasionDestroyed() {
            @Override
            public void onInvasionDestroyed() {
                galaxyWars.win5();
                dispose();
            }
        });

        //zaladuj system zarzadzania pociskami
        shootManager = new ShootManager(graphicsManager, player, invasion);
        shootManager.onMissed(()->{
            galaxyWars.getScore().addScore(-10);
        });
    }

    private void updatGameState() {
        //zaktualizuj stan i polozenie gracza i wrogow
        player.update(camera, shootManager);
        invasion.update(camera, shootManager);

        //zaktualizuj stan pociskow
        shootManager.updateShots();


        scoreGuiLabel.setScore(galaxyWars.getScore().getValue());
    }

    @Override
    public void render(float delta) {
        animationTime+= delta;
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        galaxyWars.batch.setProjectionMatrix(camera.combined);
        galaxyWars.batch.begin();
        font.draw(galaxyWars.batch, "Level 5", 10,height-10);
        player.render(galaxyWars.batch, animationTime);
        shootManager.render(galaxyWars.batch, animationTime);
        invasion.render(galaxyWars.batch, animationTime);
        scoreGuiLabel.render(galaxyWars.batch, animationTime);
        galaxyWars.batch.end();

        updatGameState();
        if (touchInput.exit()){
            galaxyWars.setScreen(new MainMenuScreen(galaxyWars));
            galaxyWars.getScore().reset();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
    }

    @Override
    public void dispose() {
    }
}