package game.entity;

public class EnemyModel1 {
    public double x;
    public double y;
    public double dx = 2;
    public double dy = 2;
    public int enemyHealth;
    public static double enemySpeed = 0.3;

    public EnemyModel1(double x, double y, int enemyHealth) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.enemyHealth = enemyHealth;
    }
}
