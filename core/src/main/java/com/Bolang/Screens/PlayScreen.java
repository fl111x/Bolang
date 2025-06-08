package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.Bolang.Object_Game.Gap;
import com.Bolang.Object_Game.Ground;
import com.Bolang.Object_Game.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlayScreen implements Screen {
    private Bolang game;
    private Texture background;
    private Player player;
    private Texture smallHP;
    private BitmapFont font;
    private Music music1;
    private Music music2;
    private Array<Ground> grounds;
    private final int GRAVITY = -800;
    private final Gap gap = new Gap(250,350);
    private int playerHp;
    private int SCORE;
    private int CHANGE_MUSIC_SCORE = 5000;

    public PlayScreen(Bolang game, int playerHp, int SCORE) {
        this.game = game;
        this.playerHp = playerHp;
        this.SCORE = SCORE;
    }

    @Override
    public void show() {
        background = new Texture("background.png");

        grounds = new Array<Ground>();
        grounds.add(new Ground(0,0,"Grounds/Ground.png"));
        grounds.add(new Ground(grounds.first().getWidth()+gap.getGapWidth(),0,"Grounds/Ground.png"));
        grounds.add(new Ground(grounds.get(1).getX()+grounds.get(1).getWidth()+gap.getGapWidth(),0,"Grounds/Ground.png"));

        smallHP = new Texture("Player/Heart_small_player.png");

        font = new BitmapFont(Gdx.files.internal("font/Dmg.fnt"));

        player = new Player(50,grounds.first().getHeight(),"Player/player.png");
        player.setHealthPoint(playerHp);

        music1 = Gdx.audio.newMusic(Gdx.files.internal("music/music1.mp3"));
        music1.play();
        music1.setLooping(true);
        music1.setVolume(0.5f);
        music2 = Gdx.audio.newMusic(Gdx.files.internal("music/music3.mp3"));
        music2.setLooping(true);
        music2.setVolume(0.5f);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        ScreenUtils.clear(0,0,0,1);
        SCORE += 1;
        player.playerGravity(GRAVITY);

        if(SCORE > CHANGE_MUSIC_SCORE){
            music1.stop();
            music2.play();
        }

        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)){
            player.playerJump();
        }
        for(int i=0;i<grounds.size;i++){
            grounds.get(i).groundMove();

            if(grounds.get(i).getRect().collidesWith(player.getRect())){


                if(player.getY() >= gap.getGapHeight()){
                    player.setY(grounds.get(i).getHeight());
                }

            }

            if(grounds.get(i).mobCollision(player)){
                player.gotDmg();
                this.dispose();
                System.out.println("score pas dmg :"+SCORE);
                game.setScreen(new DmgScreen(game, player.getHealthPoint(),SCORE));
            }

            int prevIndex = (i == 0) ? grounds.size - 1 : i - 1;
            grounds.get(i).groundPositionUpdate(grounds.get(prevIndex),gap);
            grounds.get(i).mobsMove();
        }

        if (player.isPlayerDeath()){
            this.dispose();
            game.setScreen(new RestartScreen(game,SCORE));
        }

        game.batch.begin();
        game.batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.draw(player.getObjTexture(), player.getX(), player.getY());
        for (Ground ground : grounds) {
            game.batch.draw(ground.getObjTexture(), ground.getX(), ground.getY());
            ground.drawMobs(game.batch);
        }
        if(player.isPlayerFall(gap)){
            this.dispose();
            game.setScreen(new RestartScreen(game,SCORE));
        }
        game.batch.draw(smallHP,0,990);
        font.draw(game.batch,"SCORE :"+SCORE,1300,1060);
        font.draw(game.batch," "+player.getHealthPoint(),smallHP.getWidth(),1060);

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
        music1.stop();
        music2.stop();
        music1.dispose();
        music2.dispose();
        background.dispose();
        player.getObjTexture().dispose();
        font.dispose();
        smallHP.dispose();
    }
}
