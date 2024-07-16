package model.entity.enemy;

public class EnemyModel1 {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double ax;
    private double ay;
    private int enemyHealth;
    private static double enemySpeed = 0.3;
    private static double enemyAcceleration;
    private double[] xAngles;
    private double[] yAngles;
    private static int enemy1Size = 30;
    private double angle;
    private double dAngle;
    private boolean dash;


    public EnemyModel1(double x, double y) {
        xAngles = new double[]{x, (x + enemy1Size), (x + enemy1Size), x};
        yAngles = new double[]{y, y, (y + enemy1Size), (y + enemy1Size)};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        this.dash = false;
        enemyAcceleration = 3;
        this.enemyHealth = 10;
        this.angle = 0;
        this.dAngle = 0;
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

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public static double getEnemySpeed() {
        return enemySpeed;
    }

    public static void setEnemySpeed(double enemySpeed) {
        EnemyModel1.enemySpeed = enemySpeed;
    }

    public static double getEnemyAcceleration() {
        return enemyAcceleration;
    }

    public static void setEnemyAcceleration(double enemyAcceleration) {
        EnemyModel1.enemyAcceleration = enemyAcceleration;
    }

    public double[] getxAngles() {
        return xAngles;
    }

    public void setxAngles(double[] xAngles) {
        this.xAngles = xAngles;
    }

    public double[] getyAngles() {
        return yAngles;
    }

    public void setyAngles(double[] yAngles) {
        this.yAngles = yAngles;
    }

    public static int getEnemy1Size() {
        return enemy1Size;
    }

    public static void setEnemy1Size(int enemy1Size) {
        EnemyModel1.enemy1Size = enemy1Size;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getdAngle() {
        return dAngle;
    }

    public void setdAngle(double dAngle) {
        this.dAngle = dAngle;
    }

    public boolean isDash() {
        return dash;
    }

    public void setDash(boolean dash) {
        this.dash = dash;
    }

}
