package model.entity.enemy;

import javax.swing.*;
import java.awt.*;

import static model.entity.enemy.OmenoctModel.imageIconAdress;

public class BarricadosModel {
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
    public static int barricadosSize = 30;
    public double angle;
    public double dAngle;
    public static String imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\barricados.png";
    public static Image image = new ImageIcon(imageIconAdress).getImage();

    public BarricadosModel(double x, double y) {
        xAngles = new double[]{x, (x + barricadosSize), (x + barricadosSize), x};
        yAngles = new double[]{y, y, (y + barricadosSize), (y + barricadosSize)};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 15;
        this.angle = 0;
        this.dAngle = 0;
    }
}
