package game.entity;

public class Collectible {
    public double x;
    public double y;
    public static double collectibleSize = 10;
    public int collectibleHealth;

    public Collectible(double x, double y) {
        this.x = x;
        this.y = y;
        this.collectibleHealth = 1;
    }
}
