package model.entity;

public class Collectible {
    private double x;
    private double y;
    private static double collectibleSize = 10;
    private int collectibleHealth;

    public Collectible(double x, double y) {
        this.x = x;
        this.y = y;
        this.collectibleHealth = 1;
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

    public int getCollectibleHealth() {
        return collectibleHealth;
    }

    public void setCollectibleHealth(int collectibleHealth) {
        this.collectibleHealth = collectibleHealth;
    }

    public static double getCollectibleSize() {
        return collectibleSize;
    }

    public static void setCollectibleSize(double collectibleSize) {
        Collectible.collectibleSize = collectibleSize;
    }
}
