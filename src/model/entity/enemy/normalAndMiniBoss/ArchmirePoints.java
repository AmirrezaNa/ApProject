package model.entity.enemy.normalAndMiniBoss;

public class ArchmirePoints {
    public double x;
    public double y;
    public int archmirePointTimer;
    public static double radius = 20;

    public ArchmirePoints(double x, double y) {
        this.x = x;
        this.y = y;
        this.archmirePointTimer = 5;
    }
}
