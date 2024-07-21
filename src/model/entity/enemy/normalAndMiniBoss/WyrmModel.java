package model.entity.enemy.normalAndMiniBoss;

import javax.swing.*;
import java.awt.*;

public class WyrmModel {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public double ax;
    public double ay;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public static int wyrmSize = 50;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\wyrm.png";
    public static Image image = new ImageIcon(imageIcon).getImage();


    public WyrmModel(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 12;

    }
}
