package com.samsung.business.GalaxyWars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.samsung.business.GalaxyWars.ui.DisplayInfo;
import com.samsung.business.GalaxyWars.ui.TouchInput;
import com.samsung.business.GalaxyWars.GalaxyWars;

public class MainMenuScreen extends AbstractScreen {
    private final GalaxyWars galaxyWars;
    private BitmapFont font;

    private OrthographicCamera camera;
    private TouchInput touchInput;

    private int height = DisplayInfo.getHeight();
	private int width = DisplayInfo.getWidth();

    public MainMenuScreen(GalaxyWars galaxyWars) {
        font = new BitmapFont();
        this.galaxyWars = galaxyWars;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DisplayInfo.getWidth(), DisplayInfo.getHeight());
        touchInput = new TouchInput(camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        galaxyWars.batch.setProjectionMatrix(camera.combined);
        galaxyWars.batch.begin();
        font.draw(galaxyWars.batch, "Galaxy Wars", 10, height/2);
        font.draw(galaxyWars.batch, "Touch screen to start", 10, height/2-50);

        galaxyWars.batch.end();
        if (touchInput.start()){
            galaxyWars.start();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
