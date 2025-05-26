package com.Bolang;

import com.Bolang.Screens.StartScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bolang extends Game {
    public static final int GAME_WITDH = 1920;
    public static final int GAME_HEIGHT = 1080;

    public SpriteBatch batch;

    @Override
    public void create() {
        this.render();
        batch = new SpriteBatch();
        this.setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
