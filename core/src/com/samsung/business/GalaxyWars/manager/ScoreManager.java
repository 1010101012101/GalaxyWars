package com.samsung.business.GalaxyWars.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreManager {

    private int score;

    public ScoreManager(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getValue() {
        return score;
    }

    public void reset() {
        score = 0;
    }
}
