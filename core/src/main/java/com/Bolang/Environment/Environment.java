package com.Bolang.Environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Environment {
    private float x;
    private float y;
    private final int ENV_SPEED = 100;
    private Texture obj;
    public Environment(float x, float y,String path) {
        this.x = x;
        this.y = y;
        this.obj = new Texture(path);
    }

    public void envMove(){
        this.x -= ENV_SPEED * Gdx.graphics.getDeltaTime();
    }

    public void envUpdatePosition(float newX){
        if (this.x < 0){
            this.x = newX;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public Texture getObj() {
        return obj;
    }

    public void setObj(Texture obj) {
        this.obj = obj;
    }
}
