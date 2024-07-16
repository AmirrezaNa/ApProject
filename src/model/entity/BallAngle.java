package model.entity;

public class BallAngle {
    public double x;
    public double y;
    public final static int ballAngleRadius = 5;
    public boolean angleExists;
    public double angle;

    public BallAngle(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.angleExists = false;
    }
}
