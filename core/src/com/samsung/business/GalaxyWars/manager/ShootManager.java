package com.samsung.business.GalaxyWars.manager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samsung.business.GalaxyWars.entity.EnemyShoot;
import com.samsung.business.GalaxyWars.entity.Invasion;
import com.samsung.business.GalaxyWars.entity.PlayerSpaceship;
import com.samsung.business.GalaxyWars.entity.Shoot;
import com.samsung.business.GalaxyWars.entity.PlayerShoot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShootManager {
    private List<Shoot> shoots;
    public final GraphicsManager graphicsManager;
    public final PlayerSpaceship player;
    public final Invasion invasion;
    private OnMissingShootListener onMissingShootListener;

    public ShootManager(GraphicsManager graphicsManager, PlayerSpaceship player, Invasion invasion) {
        this.graphicsManager = graphicsManager;
        this.player = player;
        this.invasion = invasion;
        this.shoots = new ArrayList<>();
    }

    public ShootManager addShot(Shoot shoot) {
        shoots.add(shoot);
        return this;
    }

    public void updateShots() {
        Iterator<Shoot> iter = shoots.iterator();
        while (iter.hasNext()) {
            Shoot shoot = iter.next();
            shoot.updateState();
            if (shoot.isOutsideScreen()) {
                iter.remove();

                notifyMissingShootListenerForPlayerShoots(shoot);
            } else {
                if (shoot instanceof EnemyShoot) {
                    player.isHit(shoot);
                } else {
                    invasion.checkEnemyHit(iter, shoot);
                }
            }
        }

    }

    private void notifyMissingShootListenerForPlayerShoots(Shoot shoot) {
        if(shoot instanceof PlayerShoot){
            onMissingShootListener.onMissingShoot();
        }
    }

    public void render(SpriteBatch batch, float animationTime) {
        for (Shoot p : shoots) {
            p.render(batch, animationTime);
        }
    }

    public void onMissed(OnMissingShootListener onMissingShootListener) {
        this.onMissingShootListener = onMissingShootListener;
    }

    public interface OnMissingShootListener{
        void onMissingShoot();
    }
}
