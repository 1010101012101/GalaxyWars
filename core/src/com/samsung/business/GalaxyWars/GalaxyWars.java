package com.samsung.business.GalaxyWars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samsung.business.GalaxyWars.manager.ScoreManager;
import com.samsung.business.GalaxyWars.screens.Level1;
import com.samsung.business.GalaxyWars.screens.Level2;
import com.samsung.business.GalaxyWars.screens.Level3;
import com.samsung.business.GalaxyWars.screens.Level4;
import com.samsung.business.GalaxyWars.screens.Level5;
import com.samsung.business.GalaxyWars.screens.MainMenuScreen;
import com.samsung.business.GalaxyWars.screens.Winscreen1;
import com.samsung.business.GalaxyWars.screens.Winscreen2;
import com.samsung.business.GalaxyWars.screens.Winscreen3;
import com.samsung.business.GalaxyWars.screens.Winscreen4;
import com.samsung.business.GalaxyWars.screens.Winscreen5;
import com.samsung.business.GalaxyWars.screens.GameOverScreen;

public class GalaxyWars extends Game {
    public SpriteBatch batch;
    private ScoreManager score;

    @Override
    public void create() {
        batch = new SpriteBatch();

        this.setScreen(new MainMenuScreen(this));
        score = new ScoreManager(0);
    }
    @Override
    public void render() {
        super.render(); //important!
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void gameOver() {
        this.setScreen(new GameOverScreen(this));
    }
    public void win1() {
        this.setScreen(new Winscreen1(this));
    }
    public void lvl1() {
        this.setScreen(new Level2(this));
    }
    public void win2() {
        this.setScreen(new Winscreen2(this));
    }
    public void lvl2() {
        this.setScreen(new Level3(this));

    }
    public void win3(){
        this.setScreen(new Winscreen3(this));
    }
    public void lvl3() {
        this.setScreen(new Level4(this));

    }
    public void win4() {
        this.setScreen(new Winscreen4(this));
    }
    public void lvl4(){
        this.setScreen(new Level5(this));
    }
    public void win5() {
        this.setScreen(new Winscreen5(this));
    }

    public ScoreManager getScore() {
        return score;
    }

    public void restart() {
        score.reset();
        com.samsung.business.GalaxyWars.entity.Invasion.ENEMY_ROWS_COUNT = 1;
        com.samsung.business.GalaxyWars.entity.Invasion.ENEMY_IN_ROW_COUNT = 2;
        com.samsung.business.GalaxyWars.entity.Invasion.ENEMY_HEIGHT = 30;
        com.samsung.business.GalaxyWars.entity.Invasion.ENEMY_WIDTH = 30;
        start();
    }

    public void start() {
        this.setScreen(new Level1(this));


    }
}
