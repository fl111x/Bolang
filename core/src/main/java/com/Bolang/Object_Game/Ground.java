package com.Bolang.Object_Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Ground extends ObjGame{
    private static final int MAX_SPEED = 600;
    private static final int MAX_X_MOB = 200;
    private static final int MIN_X_MOB = 400;
    private static final int TOTAL_MOBS = 2;
    private static final int SPEED_INCREMENT = 10;
    private static final int START_SPEED = 100;
    private float groundSpeed;
    private ArrayList<Mob> Mobs;

    public Ground(float x, float y, String path) {
        super(x, y, path);
        groundSpeed = START_SPEED;
        Mobs = new ArrayList<>();
        createMobs();
    }

    private void createMobs(){
        for (int i = 0;i < TOTAL_MOBS;i++){
            Mobs.add(new Mob(-100,super.getHeight(),"Mob/test_"+(i+1)+".png"));
        }
        mobsPosition();
    }

    private int mobSpawn(){
        Random rand = new Random();
        return rand.nextInt(TOTAL_MOBS);
    }


    public void mobsPosition(){
        Random rand = new Random();
        float maxX = super.getX() + super.getWidth() - MAX_X_MOB;
        float minX = super.getX() + MIN_X_MOB;
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
        if (groundSpeed > MAX_SPEED){
            groundSpeed = MAX_SPEED;
        }else{
            groundSpeed += SPEED_INCREMENT;
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
