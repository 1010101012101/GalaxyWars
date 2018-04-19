package com.samsung.business.GalaxyWars.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class GraphicsManager {
    public Map<String, Graphics> graphics = new HashMap<>();

    public static GraphicsManager loadGraphics(){
        GraphicsManager graphicsManager = new GraphicsManager();
        graphicsManager.addGraphics("player", Graphics.loadFromFile(Gdx.files.internal("player.png"), 8, 2));
        graphicsManager.addGraphics("enemy", Graphics.loadFromFile(Gdx.files.internal("enemy.png"), 4, 2));
        graphicsManager.addGraphics("playerlaser", Graphics.loadFromFile(Gdx.files.internal("playerlaser.png"), 1, 1));
        graphicsManager.addGraphics("enemylaser", Graphics.loadFromFile(Gdx.files.internal("enemylaser.png"), 1, 1));
        graphicsManager.addGraphics("button", Graphics.loadFromFile(Gdx.files.internal("button.png"), 1, 1));
        graphicsManager.addGraphics("stick", Graphics.loadFromFile(Gdx.files.internal("stick.png"), 1, 1));
        graphicsManager.addGraphics("stickIndicator", Graphics.loadFromFile(Gdx.files.internal("stick_indicator.png"), 1, 1));

        return graphicsManager;
    }

    public Graphics find(String name){
        return graphics.get(name);
    }

    private void addGraphics(String spaceship, Graphics graphics) {
        this.graphics.put(spaceship, graphics);
    }

    public static class Graphics {
        private static final boolean LOOPED = true;
        private Texture texture;
        private Animation<TextureRegion> animation;

        public Graphics(Texture texture, int columnFrames, int rowFrames) {
            this.texture = texture;
            this.animation = prepareAnimation(texture, columnFrames, rowFrames);
        }

        public static Graphics loadFromFile(FileHandle textureFile, int columnFrames, int rowFrames){
            Texture texture = new Texture(textureFile);
            return new Graphics(texture, columnFrames, rowFrames);
        }

        public TextureRegion frameToRender(float animationTime){
            return animation.getKeyFrame(animationTime, LOOPED);
        }

        private Animation<TextureRegion> prepareAnimation(Texture texture, int columnFrames, int rowFrames){
            TextureRegion[][] tmp = TextureRegion.split(texture,
                    texture.getWidth()/columnFrames,
                    texture.getHeight()/rowFrames);
            TextureRegion[] textureFrames = new TextureRegion[rowFrames * columnFrames];
            int indeks = 0;
            for (int i = 0; i < rowFrames; i++){
                for (int j = 0; j < columnFrames; j++){
                    textureFrames[indeks++] = tmp[i][j];
                }
            }
            return new Animation<TextureRegion>(0.025f, textureFrames);
        }
    }
}
