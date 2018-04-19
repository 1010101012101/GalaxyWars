package com.samsung.business.GalaxyWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.samsung.business.GalaxyWars.ui.DisplayInfo;
import com.samsung.business.GalaxyWars.ui.TouchInput;
import com.samsung.business.GalaxyWars.GalaxyWars;

public class Winscreen5 extends AbstractScreen {
    private final GalaxyWars galaxyWars;
    private OrthographicCamera camera;
    private BitmapFont font;
    private TouchInput touchInput;

    public Winscreen5(GalaxyWars galaxyWars) {
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DisplayInfo.getWidth(), DisplayInfo.getHeight());
        this.galaxyWars = galaxyWars;
        this.touchInput = new TouchInput(camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        galaxyWars.batch.setProjectionMatrix(camera.combined);
        galaxyWars.batch.begin();
        font.draw(galaxyWars.batch, "You Won The Whole Game! " + galaxyWars.getScore().getValue(), 10, Gdx.graphics.getHeight()/2);
        font.draw(galaxyWars.batch, "Touch screen to restart", 10, (Gdx.graphics.getHeight()/2)-50);

        galaxyWars.batch.end();
        if (touchInput.start()){
            galaxyWars.restart();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}