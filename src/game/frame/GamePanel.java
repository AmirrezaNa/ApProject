package game.frame;

import game.entity.BulletModel;
import game.controller.GameController;
import game.entity.BallModel;
import game.controller.KeyInputListener;
import game.controller.MouseInputListener;
import game.entity.EnemyModel1;
import game.entity.EnemyModel2;

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
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy : GameController.enemies1) {
                if (enemy.enemyHealth > 0) {
                    g.setColor(new Color(0xEF8506));
                    g.fillOval((int) enemy.x, (int) enemy.y, BulletModel.bulletSize, BulletModel.bulletSize);
                }
            }
        }
    }

    public static void drawEnemy2(Graphics g) {
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.enemyHealth > 0) {
                    g.setColor(new Color(0xEF8506));
                    g.fillOval((int) enemy.x, (int) enemy.y, BulletModel.bulletSize, BulletModel.bulletSize);
                }
            }
        }
    }

}
