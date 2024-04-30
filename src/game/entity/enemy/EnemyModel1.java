package game.entity.enemy;

public class EnemyModel1 {
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
    public int enemy1Size = 30;
    public double angle;
    public EnemyModel1(double x, double y) {
        xAngles = new double[]{x, (x + enemy1Size), (x + enemy1Size), x};
        yAngles = new double[]{y, y, (y + enemy1Size), (y + enemy1Size)};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 10;
        this.angle = 0;
    }
}
