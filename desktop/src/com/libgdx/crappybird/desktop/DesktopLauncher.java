package com.libgdx.crappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.libgdx.crappybird.crappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = crappyBird.HEIGHT;
        config.width = crappyBird.WIDTH;
        config.title = crappyBird.TITLE;

        new LwjglApplication(new crappyBird(), config);
	}
}
