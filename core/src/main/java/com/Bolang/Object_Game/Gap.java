package com.Bolang.Object_Game;

public class Gap {
    private float gapX;
    private float gapY;
    private int gapWidth;
    private int gapHeight;

    public Gap(float gapX, float gapY, int gapWidth, int gapHeight) {
        this.gapX = gapX;
        this.gapY = gapY;
        this.gapWidth = gapWidth;
        this.gapHeight = gapHeight;
    }

    public Gap(int height, int width) {
        this.gapHeight = height;
        this.gapWidth = width;
    }

    public int getGapWidth() {
        return gapWidth;
    }
    public int getGapHeight() {
        return gapHeight;
    }
}
