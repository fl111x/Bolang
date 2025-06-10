package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class StartScreen implements Screen {
    public static final int START_BUTTON_WITDH = 694;
    public static final int START_BUTTON_HEIGHT = 379;
    public static final int START_BUTTON_X = Bolang.GAME_WITDH/2 -  START_BUTTON_WITDH /2;
    public static final int START_BUTTON_Y = Bolang.GAME_HEIGHT / 2 - START_BUTTON_HEIGHT /2;

    Bolang game;
    Texture background;
    Texture playButtonActive;
    Texture playButtonInactive;
    Music music;
    Sound sound;

    final int playerHp = 3;
    final int score = 0;

    public StartScreen(Bolang game) {
        this.game = game;

    }

    @Override
    public void show() {
        background = new Texture("background.png");
        playButtonActive = new Texture("playButtonActive.png");
        playButtonInactive = new Texture("playButtonInactive.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music4.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("music/sfx/coin2.mp3"));
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        music.play();
        music.setLooping(true);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(Gdx.input.getX() < START_BUTTON_X +  START_BUTTON_WITDH && Gdx.input.getX() > START_BUTTON_X && Bolang.GAME_HEIGHT - Gdx.input.getY() < START_BUTTON_HEIGHT + START_BUTTON_Y && Bolang.GAME_HEIGHT - Gdx.input.getY() > START_BUTTON_HEIGHT) {
            game.batch.draw(playButtonActive, START_BUTTON_X, START_BUTTON_Y);
            if (Gdx.input.isTouched()) {
                sound.play();
                this.dispose();
                game.setScreen(new PlayScreen(game,playerHp,score));
                music.stop();

            }
        }else {
            game.batch.draw(playButtonInactive, START_BUTTON_X, START_BUTTON_Y);
        }
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        playButtonActive.dispose();
        playButtonInactive.dispose();
        music.dispose();
    }
}
