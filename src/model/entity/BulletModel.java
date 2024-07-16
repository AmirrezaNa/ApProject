package model.entity;

public class BulletModel {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private int bulletHealth;
    private static int bulletSpeed = 20;
    private static int bulletSize = 10;
    private boolean bulletOut;

    public BulletModel(double x, double y) {
        this.x = x;
        this.y = y;
        this.bulletOut = false;
        this.bulletHealth = 1;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getBulletHealth() {
        return bulletHealth;
    }

    public void setBulletHealth(int bulletHealth) {
        this.bulletHealth = bulletHealth;
    }

    public static int getBulletSpeed() {
        return bulletSpeed;
    }

    public static void setBulletSpeed(int bulletSpeed) {
        BulletModel.bulletSpeed = bulletSpeed;
    }

    public static int getBulletSize() {
        return bulletSize;
    }

    public static void setBulletSize(int bulletSize) {
        BulletModel.bulletSize = bulletSize;
    }

    public boolean isBulletOut() {
        return bulletOut;
    }

    public void setBulletOut(boolean bulletOut) {
        this.bulletOut = bulletOut;
    }
}
