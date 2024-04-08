package game.ball;

public class BallModel {

    public double x;
    public double y;
    public double dx = 1;
    public double dy = 1;
    public double ax = 0.1;
    public double ay = 0.1;
    public final static int ballRadius = 40;

    public BallModel(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
