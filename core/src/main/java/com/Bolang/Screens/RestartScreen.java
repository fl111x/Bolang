package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.Bolang.Environment.Environment;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class RestartScreen implements Screen {
    public static final int RESTART_BUTTON_WITDH = 694;
    public static final int RESTART_BUTTON_HEIGHT = 379;
    public static final int RESTART_BUTTON_X = Bolang.GAME_WITDH/2 -  RESTART_BUTTON_WITDH /2;
    public static final int RESTART_BUTTON_Y = Bolang.GAME_HEIGHT / 2 - RESTART_BUTTON_HEIGHT /2;

    Bolang game;
    Texture background;
    Texture restartButtonActive;
    Texture restartButtonInactive;
    Music music;
    Sound sound;
    Environment eagle;
    int tes;

    public RestartScreen(Bolang game) {
        this.game = game;

    }


    @Override
    public void show() {
        background = new Texture("background.png");
        restartButtonActive = new Texture("restartButtonActive.png");
        restartButtonInactive = new Texture("restartButtonInactive.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music4.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("music/sfx/coin2.mp3"));
    }

    @Override
    public void render(float v) {
        music.play();
        music.setLooping(true);


        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(Gdx.input.getX() < RESTART_BUTTON_X +  RESTART_BUTTON_WITDH && Gdx.input.getX() > RESTART_BUTTON_X && Bolang.GAME_HEIGHT - Gdx.input.getY() < RESTART_BUTTON_HEIGHT + RESTART_BUTTON_Y && Bolang.GAME_HEIGHT - Gdx.input.getY() > RESTART_BUTTON_HEIGHT) {
            game.batch.draw(restartButtonActive, RESTART_BUTTON_X, RESTART_BUTTON_Y);
            if (Gdx.input.isTouched()) {
                sound.play();
                music.stop();
                this.dispose();
                game.setScreen(new PlayScreen(game,3));

            }
        }else {
            game.batch.draw(restartButtonInactive, RESTART_BUTTON_X, RESTART_BUTTON_Y);
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

    }
}
