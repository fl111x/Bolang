package com.Bolang.Object_Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Player extends ObjGame{
    private float velocity;
    private int healthPoint;

    private Sound jumpSound;
    private Sound deathSound;


    public Player(float x, float y, String path) {
        super(x, y, path);
        velocity = 1;
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("Player/sfx/jump1.mp3"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("Player/sfx/death1.mp3"));
    }

    public void playerJump(){
        velocity = 500;
        super.getRect().move(super.getX(), super.getY());
        jumpSound.play();
    }

    public void playerGravity(int gravity){
        velocity += gravity * Gdx.graphics.getDeltaTime();
        super.setY(super.getY() + velocity * Gdx.graphics.getDeltaTime());
        super.getRect().move(super.getX(), super.getY());

        if(velocity > 800){
            velocity = 1;
        }
    }

    public void gotDmg(){
        deathSound.play();
        healthPoint--;
    }

    public boolean isPlayerFall(Gap g){
        if (super.getY() < g.getGapHeight()){
            deathSound.play();
            healthPoint -= 3;
            return true;
        }else{
            return false;
        }

    }


    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public boolean isPlayerDeath(){
        return healthPoint <= 0;
    }




    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
