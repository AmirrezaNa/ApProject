package model.entity.enemy;

import javax.swing.*;
import java.awt.*;

public class BarricadosModel2 {
    public double x;
    public double y;
    public double dx;
    public double dy;
    public double ax;
    public double ay;
    public int enemyTimer;
    public static double enemySpeed = 0.3;
    public static double enemyAcceleration;
    public static int barricadosSize = 60;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\barricados.png";
    public static Image image = new ImageIcon(imageIcon).getImage();

    public BarricadosModel2(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyTimer = 120;
    }
}
