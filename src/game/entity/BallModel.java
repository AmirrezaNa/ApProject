package game.entity;

public class BallModel {

    public double x;
    public double y;
    public double dx = 2;
    public double dy = 2;
    public double ax = 0;
    public double ay = 0;
    public static double ballAcceleration;
    public static int ballRadius = 20;
    public int HP;

    public BallModel(double x, double y) {
        this.x = x;
        this.y = y;
        ballAcceleration = 3;
        this.HP = 100;
    }
}
