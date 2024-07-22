package model.entity;

public class Collectible {
    public double x;
    public double y;
    public static double collectibleSize = 10;
    public int collectibleHealth;
    public int xp;

    public Collectible(double x, double y, int xp) {
        this.x = x;
        this.y = y;
        this.collectibleHealth = 1;
        this.xp = xp;
    }
}
