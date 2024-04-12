package game.ball;

public class BallModel {

    public double x;
    public double y;
    public double dx = 2;
    public double dy = 2;
    public final static int ballRadius = 40;

    public BallModel(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
