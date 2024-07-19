package model.entity;

import controller.game.FrameOfObject;

public class BulletModel {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public int bulletHealth;
    public static int bulletSpeed = 20;
    public static int bulletSize = 10;
    public boolean bulletOut;
    public int bulletFrame;

    public BulletModel(double x, double y) {
        this.x = x;
        this.y = y;
        this.bulletOut = false;
        this.bulletHealth = 1;
        this.bulletFrame = FrameOfObject.getFrameOfBall();
    }
}
