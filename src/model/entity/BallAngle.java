package model.entity;

public class BallAngle {
    private double x;
    private double y;
    private final static int ballAngleRadius = 5;
    private boolean angleExists;
    private double angle;

    public BallAngle(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.angleExists = false;
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

    public boolean isAngleExists() {
        return angleExists;
    }

    public void setAngleExists(boolean angleExists) {
        this.angleExists = angleExists;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public static int getBallAngleRadius() {
        return ballAngleRadius;
    }
}
