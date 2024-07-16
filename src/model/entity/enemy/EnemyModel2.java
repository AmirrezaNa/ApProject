package model.entity.enemy;

public class EnemyModel2 {
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
    private static int enemy2Size = 30;
    private double angle;
    private double dAngle;

    public EnemyModel2(double x, double y) {
        xAngles = new double[]{x, (x + enemy2Size), (x + ((double) enemy2Size /2))};
        yAngles = new double[]{y, y, (y + (enemy2Size))};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 15;
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
        EnemyModel2.enemySpeed = enemySpeed;
    }

    public static double getEnemyAcceleration() {
        return enemyAcceleration;
    }

    public static void setEnemyAcceleration(double enemyAcceleration) {
        EnemyModel2.enemyAcceleration = enemyAcceleration;
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

    public static int getEnemy2Size() {
        return enemy2Size;
    }

    public static void setEnemy2Size(int enemy2Size) {
        EnemyModel2.enemy2Size = enemy2Size;
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
}
