package game.frame;

import game.controller.GameController;
import game.ball.BallModel;
import game.controller.KeyInputListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable {


    static Graphics g;
    public static BallModel ball;
    KeyInputListener keyInputListener;

    GamePanel() {
        initPanel();
    }

    public void initPanel() {
        GameFrame.GameIsRunning = true;
        this.setSize(GameFrame.width, GameFrame.height);
        this.setBackground(Color.BLACK);
        ball = GameController.newBall();
        keyInputListener = new KeyInputListener();
        this.addKeyListener(keyInputListener);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
    }


    public static void drawBall(Graphics g) {
        if (ball != null) {
            g.setColor(new Color(0x1C8F09));
            g.fillOval((int) ball.x, (int) ball.y, BallModel.ballRadius, BallModel.ballRadius);
        }

    }

}
