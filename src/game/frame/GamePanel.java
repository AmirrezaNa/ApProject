package game.frame;

import game.ball.BulletModel;
import game.controller.GameController;
import game.ball.BallModel;
import game.controller.KeyInputListener;
import game.controller.MouseInputListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable {


    static Graphics g;
    public static BallModel ball;
    public static BulletModel bullet;
    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;

    GamePanel() {
        initPanel();
    }

    public void initPanel() {
        GameFrame.GameIsRunning = true;
        //changeGamePanelSize();
        this.setBackground(Color.BLACK);
        this.setSize(GameFrame.width, GameFrame.height);
        ball = GameController.newBall();
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

}
