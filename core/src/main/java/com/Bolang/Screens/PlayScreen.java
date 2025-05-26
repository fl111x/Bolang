package com.Bolang.Screens;

import com.Bolang.Bolang;
import com.Bolang.Object_Game.Gap;
import com.Bolang.Object_Game.Ground;
import com.Bolang.Object_Game.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlayScreen implements Screen {
    private Bolang game;
    private Texture background;
    private Player player;
    private Array<Ground> grounds;
    private final int GRAVITY = -700;
    private final Gap gap = new Gap(30,300);
    private int playerHp;
    int score = 0;
    public PlayScreen(Bolang game, int playerHp){
        this.game = game;
        this.playerHp = playerHp;
    }

    @Override
    public void show() {
        background = new Texture("background.png");

        grounds = new Array<Ground>();
        grounds.add(new Ground(0,0,"Grounds/Ground.png"));
        grounds.add(new Ground(grounds.first().getWidth()+gap.getGapWidth(),0,"Grounds/Ground.png"));
        grounds.add(new Ground(grounds.get(1).getX()+grounds.get(1).getWidth()+gap.getGapWidth(),0,"Grounds/Ground.png"));

        player = new Player(50,grounds.first().getHeight(),"Player/player.png");
        player.setHealthPoint(playerHp);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        ScreenUtils.clear(0,0,0,1);
        score += 1;
        player.playerGravity(GRAVITY);
        if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)){
            player.playerJump();
        }


        for(int i=0;i<grounds.size;i++){
            grounds.get(i).groundMove();

            if(grounds.get(i).getRect().collidesWith(player.getRect())){
                player.setY(grounds.get(i).getHeight());
            }

            if(grounds.get(i).mobCollision(player)){
                player.gotDmg();
                this.dispose();
                game.setScreen(new DmgScreen(game, player.getHealthPoint()));

            }

            int prevIndex = (i == 0) ? grounds.size - 1 : i - 1;
            grounds.get(i).groundPositionUpdate(grounds.get(prevIndex),gap);
            grounds.get(i).mobsMove();
        }

        if (player.isPlayerDeath()){
            this.dispose();
            game.setScreen(new RestartScreen(game));
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
            game.setScreen(new RestartScreen(game));
        }


        System.out.println(score);
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
