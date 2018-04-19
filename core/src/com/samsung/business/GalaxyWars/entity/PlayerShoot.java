package com.samsung.business.GalaxyWars.entity;

import com.badlogic.gdx.Gdx;
import com.samsung.business.GalaxyWars.manager.GraphicsManager;
import com.samsung.business.GalaxyWars.ui.DisplayInfo;

public class PlayerShoot extends Shoot {
    private static final int HEIGHT = DisplayInfo.getHeight();

    public PlayerShoot(GraphicsManager.Graphics graphics, float originX, float originY) {
        super(graphics, originX, originY);
    }

    @Override
    public void updateState() {
        this.position.y += 200 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public boolean isOutsideScreen() {
        return this.position.y - 10 > HEIGHT;
    }

}
