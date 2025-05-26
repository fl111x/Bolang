package com.Bolang.Object_Game;

import com.Bolang.Bolang;
import com.Bolang.Screens.RestartScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Ground extends ObjGame{
    private float groundSpeed;
    private final int maxSpeed = 600;
    private ArrayList<Mob> Mobs;

    public Ground(float x, float y, String path) {
        super(x, y, path);
        groundSpeed = 100;
        Mobs = new ArrayList<>();
        createMobs();
    }

    private void createMobs(){
        for (int i = 0;i < 2;i++){
            Mobs.add(new Mob(-100,super.getHeight(),"Mob/test_"+(i+1)+".png"));
        }
        mobsPosition();
    }

    private int mobSpawn(){
        Random rand = new Random();
        return rand.nextInt(2);
    }


    public void mobsPosition(){
        Random rand = new Random();
        float maxX = super.getX() + super.getWidth() - 200;
        float minX = super.getX() + 400;
        float x1 = rand.nextFloat(maxX-minX+1)+minX;

        Mobs.get(mobSpawn()).setX(x1);

    }

    public void mobsMove(){
        for (Mob mob : Mobs){
            mob.mobMove();
        }
    }

    public void drawMobs(SpriteBatch batch){
        Mobs.forEach(mob -> {
            mob.mobPrint(batch);
        });
    }

    public void speedIncrement(){
        if (groundSpeed > maxSpeed){
            groundSpeed = maxSpeed;
        }else{
            groundSpeed += 10;
        }

        for (Mob mob : Mobs){
            mob.setMobSpeed(groundSpeed);
        }
    }

    public void groundMove(){
        speedIncrement();
        super.setX(super.getX() - (groundSpeed * Gdx.graphics.getDeltaTime()));
        super.getRect().move(super.getX(), super.getY());

    }

    public void groundPositionUpdate(Ground prevGround, Gap gap){
        if(super.isOutOfScreen()){
            float newX = prevGround.getX() + prevGround.getWidth() + gap.getGapWidth();
            super.setX(newX);
            super.getRect().move(super.getX(), super.getY());
            mobsPosition();
        }
    }

    public boolean mobCollision(Player player){
        for (Mob mob : Mobs){
            if(mob.getRect().collidesWith(player.getRect())){
                return true;
            }
        }

        return false;
    }

}
