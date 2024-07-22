package model.entity.enemy.normalAndMiniBoss;

import javax.swing.*;
import java.awt.*;

public class NecropickModel {

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
    public static int necropickSize = 30;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\necropick.png";
    public static Image image = new ImageIcon(imageIcon).getImage();
    public int hidingTime;
    public boolean hide;
    public boolean allowShot;
    public boolean necropickAlert;

    public NecropickModel(double x, double y) {
        xAngles = new double[]{x, (x + necropickSize), (x + necropickSize), x};
        yAngles = new double[]{y, y, (y + necropickSize), (y + necropickSize)};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 10;
        this.hidingTime = 0;
        this.hide = false;
        this.allowShot = false;
        this.necropickAlert = false;
    }
}
