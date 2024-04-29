package game.frame;

import game.entity.BulletModel;
import game.controller.GameController;
import game.entity.BallModel;
import game.controller.KeyInputListener;
import game.controller.MouseInputListener;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable {


    public static BallModel ball;
    public static EnemyModel1 enemy1;
    public static EnemyModel2 enemy2;
    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;

    GamePanel() {
        initPanel();
    }

    public void initPanel() {
        GameFrame.GameIsRunning = true;
        //changeGamePanelSize();
        this.setBackground(Color.BLACK);
        ball = GameController.newBall();
        enemy1 = GameController.setTimerForEnemy1();
        enemy2 = GameController.setTimerForEnemy2();
        changeGamePanelSize();
        keyInputListener = new KeyInputListener();
        this.addKeyListener(keyInputListener);
        mouseInputListener = new MouseInputListener();
        this.addMouseListener(mouseInputListener);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    @Override
    public void run() {

        while (GameFrame.GameIsRunning) {

            update();


            draw();


            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void update() {
        GameController.updateTheBall();
        GameController.updateBullet();
        GameController.checkCollisions();
        GameController.updateEnemy1();
        GameController.updateEnemy2();
    }


    public static void draw() {

    }


    // a method to close all windows at the start =====================================

    public static void closeAllWindows() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_WINDOWS);


            // after minimizing all windows it waits for 1 second to open the game frame
            Thread.sleep(1000);

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeGamePanelSize() {


        // this timer reduces the frame size ========================================
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(GameFrame.width, GameFrame.height);
            }
        });
        timer.start();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
        drawBullet(g);
        drawEnemy1(g);
        drawEnemy2(g);
    }


    public static void drawBall(Graphics g) {
        if (ball != null) {
            g.setColor(new Color(0x1C8F09));
            g.fillOval((int) ball.x, (int) ball.y, BallModel.ballRadius, BallModel.ballRadius);
        }

    }

    public static void drawBullet(Graphics g) {
        if (!GameController.bullets.isEmpty()) {
            for (BulletModel bullet : GameController.bullets) {
                if (bullet.bulletHealth > 0) {
                    g.setColor(new Color(0xEF8506));
                    g.fillOval((int) bullet.x, (int) bullet.y, BulletModel.bulletSize, BulletModel.bulletSize);
                }
            }
        }
    }

    public static void drawEnemy1(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy1 : GameController.enemies1) {
                if (enemy1.enemyHealth > 0) {
                    Polygon polygon = new Polygon(new int[]{(int)enemy1.xAngles[0], (int) enemy1.xAngles[1], (int) enemy1.xAngles[2], (int) enemy1.xAngles[3]},
                            new int[]{(int) enemy1.yAngles[0], (int) enemy1.yAngles[1], (int) enemy1.yAngles[2], (int) enemy1.yAngles[3]}, 4);
                    g2d.setColor(new Color(0xEF8506));
                    g2d.setStroke(new BasicStroke(4));
                    g2d.drawPolygon(polygon);
                }
            }
        }
    }

    public static void drawEnemy2(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy2 : GameController.enemies2) {
                if (enemy2.enemyHealth > 0) {
                    Polygon polygon = new Polygon(new int[]{(int) enemy2.xAngles[0], (int) enemy2.xAngles[1], (int) enemy2.xAngles[2]},
                            new int[]{(int) enemy2.yAngles[0], (int) enemy2.yAngles[1], (int) enemy2.yAngles[2]}, 3);
                    g2d.setColor(new Color(0xEF8506));
                    g2d.setStroke(new BasicStroke(4));
                    g2d.drawPolygon(polygon);

                }
            }
        }
    }

}
