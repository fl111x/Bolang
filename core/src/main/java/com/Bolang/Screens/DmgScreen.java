package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class DmgScreen implements Screen {
    private Bolang game;
    private int playerHp;


    Texture background;
    Texture heart;

    BitmapFont font;

    Music music;

    int scoreGame;

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
    }

    @Override
    public void render(float v) {
        music.play();
        game.batch.begin();
        game.batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(playerHp==2){
            game.batch.draw(heart,609,308);
            game.batch.draw(heart,609+heart.getWidth(),308);
        }else{
            game.batch.draw(heart,Bolang.GAME_WITDH/2 - heart.getWidth()/2,Bolang.GAME_HEIGHT/2 - heart.getHeight()/2);
        }
        if(Gdx.input.isTouched()){
            music.stop();
            this.dispose();
            game.setScreen(new PlayScreen(game,playerHp,scoreGame));
        }
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

    }
}
