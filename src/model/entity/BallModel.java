package model.entity;

public class BallModel {

    private double x;
    private double y;
    private double dx = 2;
    private double dy = 2;
    private double ax = 0;
    private double ay = 0;
    private static double ballAcceleration;
    private static int ballRadius = 20;
    private int HP;

    public BallModel(double x, double y) {
        this.x = x;
        this.y = y;
        ballAcceleration = 3;
        this.HP = 100;
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

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getAx() {
        return ax;
    }

    public void setAx(double ax) {
        this.ax = ax;
    }

    public double getAy() {
        return ay;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public static double getBallAcceleration() {
        return ballAcceleration;
    }

    public static void setBallAcceleration(double ballAcceleration) {
        BallModel.ballAcceleration = ballAcceleration;
    }

    public static int getBallRadius() {
        return ballRadius;
    }

    public static void setBallRadius(int ballRadius) {
        BallModel.ballRadius = ballRadius;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
