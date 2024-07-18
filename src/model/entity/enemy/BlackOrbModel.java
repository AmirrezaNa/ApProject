package model.entity.enemy;

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
    public double[] xAngles;
    public double[] yAngles;
    public static int blackOrbSize = 20;
    public double angle;
    public double dAngle;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\blackOrb.png";


    public BlackOrbModel(double x, double y) {
        xAngles = new double[]{x, (x + (blackOrbSize * Math.cos(Math.PI/5))), (x + (blackOrbSize * Math.cos(Math.PI/5))),
                (x + blackOrbSize + (blackOrbSize * Math.cos(2*Math.PI/5))), (x + (blackOrbSize * Math.cos(2*Math.PI/5)))};
        yAngles = new double[]{y, (y - (blackOrbSize * Math.sin(Math.PI/5))), y,
                (y + (blackOrbSize * Math.cos(2*Math.PI/5))), (y + (blackOrbSize * Math.cos(2*Math.PI/5)))};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 30;
        this.angle = 0;
        this.dAngle = 0;
    }
}
