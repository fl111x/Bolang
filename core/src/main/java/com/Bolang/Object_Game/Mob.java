package com.Bolang.Object_Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mob extends ObjGame{
    private float mobSpeed = 300;

    public Mob(float x, float y, String path) {
        super(x, y, path);
    }

    public void mobMove(){
        super.setX(super.getX() - mobSpeed * Gdx.graphics.getDeltaTime());
        super.getRect().move(super.getX(),super.getY());
    }

    public void mobPrint(SpriteBatch batch){
        batch.draw(super.getObjTexture(),super.getX(),super.getY());

    }

    public void setMobSpeed(float mobSpeed) {
        this.mobSpeed = mobSpeed;
    }


}
