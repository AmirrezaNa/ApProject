package game.entity.enemy;

public class EnemyModel2 {
    public double x;
    public double y;
    public double dx = 2;
    public double dy = 2;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public double[] xAngles;
    public double[] yAngles;
    public int enemy2Size = 30;
    public double angle;

    public EnemyModel2(double x, double y, int enemyHealth) {
        xAngles = new double[]{x, (x + enemy2Size), (x + ((double) enemy2Size /2))};
        yAngles = new double[]{y, y, (y + (enemy2Size))};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.enemyHealth = enemyHealth;
        this.angle = 0;
    }
}
