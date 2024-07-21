package model.entity.enemy.normalAndMiniBoss;

public class EnemyModel2 {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public double ax;
    public double ay;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public double[] xAngles;
    public double[] yAngles;
    public static int enemy2Size = 30;
    public double angle;
    public double dAngle;

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
}
