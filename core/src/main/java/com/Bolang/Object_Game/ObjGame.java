package com.Bolang.Object_Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ObjGame {
    private float x,y;
    private int width;
    private int height;
    private Texture objTexture;
    private CollisionRect rect;

    public ObjGame(String path) {
        this.objTexture = new Texture(Gdx.files.internal(path));
        this.width = objTexture.getWidth();
        this.height = objTexture.getHeight();
    }

    public ObjGame(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ObjGame(float x, float y, String path){
        this.x=x;
        this.y=y;
        objTexture=new Texture(path);
        width=objTexture.getWidth();
        height=objTexture.getHeight();
        rect=new CollisionRect(x,y,width,height);
    }

    public ObjGame(float x,float y,int width,int height,String path){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

        this.objTexture=new Texture(path);

    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean isOutOfScreen(){
        return this.getX() + this.getWidth() < 0;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Texture getObjTexture() {
        return objTexture;
    }

    public void setObjTexture(Texture objTexture) {
        this.objTexture = objTexture;
    }
}
