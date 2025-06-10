package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class DmgScreen implements Screen {
    private static final int HEART_X =609;
    private static final int HEART_Y =308;

    private Bolang game;
    private int playerHp;


    private Texture background;
    private Texture heart;

    private BitmapFont font;
    private Music music;

    private int scoreGame;

    public DmgScreen(Bolang game,int playerHp, int scoreGame){
        this.game = game;
        this.playerHp = playerHp;
        this.scoreGame = scoreGame;

    }

    @Override
    public void show() {
        background = new Texture("background.png");
        heart = new Texture("Player/Heart_player.png");
        font = new BitmapFont(Gdx.files.internal("font/Dmg.fnt"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music4.mp3"));
        music.play();
        music.setLooping(true);
    }

    @Override
    public void render(float v) {
        game.batch.begin();
        game.batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(playerHp==2){
            game.batch.draw(heart,HEART_X,HEART_Y);
            game.batch.draw(heart,HEART_X+heart.getWidth(),HEART_Y);
        }else{
            game.batch.draw(heart,Bolang.GAME_WITDH/2 - heart.getWidth()/2,Bolang.GAME_HEIGHT/2 - heart.getHeight()/2);
        }
        if(Gdx.input.isTouched()){
            music.stop();
            this.dispose();
            game.setScreen(new PlayScreen(game,playerHp,scoreGame));
        }
        font.setColor(Color.BLACK);
        font.draw(game.batch,"You got "+ playerHp+ " heart left",505,907);
        font.draw(game.batch,"Click to continue",559,100);
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
        heart.dispose();
        font.dispose();
        music.dispose();
    }
}
