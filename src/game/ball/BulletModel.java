package game.ball;

public class BulletModel {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public int bulletHealth = 1;
    public static int bulletSpeed = 20;
    public static int bulletSize = 10;

    public BulletModel(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
