package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.Bolang.Environment.Environment;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class RestartScreen implements Screen {
    public static final int RESTART_BUTTON_WITDH = 694;
    public static final int RESTART_BUTTON_HEIGHT = 379;
    public static final int RESTART_BUTTON_X = Bolang.GAME_WITDH/2 -  RESTART_BUTTON_WITDH /2;
    public static final int RESTART_BUTTON_Y = Bolang.GAME_HEIGHT / 2 - RESTART_BUTTON_HEIGHT /2;
    private int endScore;

    Bolang game;
    Texture background;
    Texture restartButtonActive;
    Texture restartButtonInactive;
    Music music;
    Sound sound;
    Environment eagle;

    final int newPlayerHp = 3;
    final int newScore = 0;

    BitmapFont font;

    public RestartScreen(Bolang game,int endScore) {
        this.game = game;
        this.endScore = endScore;
    }


    @Override
    public void show() {
        background = new Texture("background.png");
        restartButtonActive = new Texture("restartButtonActive.png");
        restartButtonInactive = new Texture("restartButtonInactive.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music4.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("music/sfx/coin2.mp3"));
        font = new BitmapFont(Gdx.files.internal("font/Dmg.fnt"));
    }

    @Override
    public void render(float v) {
        music.play();
        music.setLooping(true);

//        saveHighScore(endScore);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(Gdx.input.getX() < RESTART_BUTTON_X +  RESTART_BUTTON_WITDH && Gdx.input.getX() > RESTART_BUTTON_X && Bolang.GAME_HEIGHT - Gdx.input.getY() < RESTART_BUTTON_HEIGHT + RESTART_BUTTON_Y && Bolang.GAME_HEIGHT - Gdx.input.getY() > RESTART_BUTTON_HEIGHT) {
            game.batch.draw(restartButtonActive, RESTART_BUTTON_X, RESTART_BUTTON_Y);
            if (Gdx.input.isTouched()) {
                sound.play();
                music.stop();
                this.dispose();
                game.setScreen(new PlayScreen(game,newPlayerHp,newScore));

            }
        }else {
            game.batch.draw(restartButtonInactive, RESTART_BUTTON_X, RESTART_BUTTON_Y);
        }
        font.draw(game.batch,"Score:"+endScore,750,270);
        game.batch.end();



    }

//    public void saveHighScore(int score){
//        Preferences prefs = Gdx.app.getPreferences("dataHighScore");
//        int highScore = prefs.getInteger("highScore",0);
//
//        if (score > highScore) {
//            prefs.putInteger("highscore", score);
//            prefs.flush();
//        }
//    }

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
