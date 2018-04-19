package com.samsung.business.GalaxyWars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.samsung.business.GalaxyWars.GalaxyWars;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Galaxy Wars";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new GalaxyWars(), config);
	}
}