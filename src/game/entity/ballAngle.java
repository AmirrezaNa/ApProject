package game.entity;

public class ballAngle {
    public double x;
    public double y;
    public final static int ballAngleSize = 5;
    public boolean angleExists;

    public ballAngle(double x, double y) {
        this.x = x;
        this.y = y;
        this.angleExists = false;
    }
}
