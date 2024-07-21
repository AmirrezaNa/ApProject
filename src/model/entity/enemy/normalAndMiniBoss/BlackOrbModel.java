package model.entity.enemy.normalAndMiniBoss;

public class BlackOrbModel {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public double ax;
    public double ay;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public static int blackOrbSize = 30;

    public boolean draw1;
    public boolean draw2;
    public boolean draw3;
    public boolean draw4;
    public boolean draw5;
    public int blackOrbTimer;



    public BlackOrbModel(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 150;
        this.draw1 = false;
        this.draw2 = false;
        this.draw3 = false;
        this.draw4 = false;
        this.draw5 = false;
        blackOrbTimer = 25;

    }
}
