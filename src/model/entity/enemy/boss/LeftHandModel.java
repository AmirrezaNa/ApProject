package model.entity.enemy.boss;

import javax.swing.*;
import java.awt.*;

public class LeftHandModel {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public double ax;
    public double ay;
    public int enemyHealth;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public static int leftHandSize = 150;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\LeftHand.png";
    public static Image image = new ImageIcon(imageIcon).getImage();
    public boolean leftHandExists;


    public LeftHandModel(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = 1;
        this.dy = 1;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 100;
        this.leftHandExists = true;
    }
}
