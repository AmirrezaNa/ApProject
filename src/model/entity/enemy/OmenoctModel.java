package model.entity.enemy;

import javax.swing.*;
import java.awt.*;

public class OmenoctModel {
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
    public static int omenoctSize = 20;
    public static int distanceToCenter = (int) ((omenoctSize/2)/Math.sin(Math.PI/8));
    public double angle;
    public double dAngle;
    public static String imageIconAdress = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\omenoct.png";
    public static Image image = new ImageIcon(imageIconAdress).getImage();


    // the position of omenoct is based on the top angle

    public OmenoctModel(double x, double y) {
        xAngles = new double[]{x, (x + (omenoctSize*Math.cos(Math.PI/8)))
                , (x + (distanceToCenter*Math.cos(Math.PI/8))), (x + (omenoctSize*Math.cos(Math.PI/8)))
                , x, (x - (omenoctSize*Math.cos(Math.PI/8)))
                , (x - (distanceToCenter*Math.cos(Math.PI/8))), (x - (omenoctSize*Math.cos(Math.PI/8)))};
        yAngles = new double[]{y, (y + (omenoctSize*Math.sin(Math.PI/8)))
                , (y + distanceToCenter - (distanceToCenter*Math.sin(Math.PI/8))), (y + distanceToCenter + (omenoctSize*Math.sin(Math.PI/8)))
                , y + (2*distanceToCenter), (y + distanceToCenter + (omenoctSize*Math.sin(Math.PI/8)))
                , (y + distanceToCenter - (distanceToCenter*Math.sin(Math.PI/8))), (y + (omenoctSize*Math.sin(Math.PI/8)))};
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        enemyAcceleration = 3;
        this.enemyHealth = 20;
        this.angle = 0;
        this.dAngle = 0;
    }
}
