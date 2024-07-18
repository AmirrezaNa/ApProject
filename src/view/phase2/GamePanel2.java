package view.phase2;

import controller.game.GameController;
import controller.game.listener.KeyInputListener;
import controller.game.listener.MouseInputListener;
import controller.game.objectsController.ball.BallAngleController;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BallDirectionController;
import controller.game.objectsController.ball.BulletController;
import model.entity.BallAngle;
import model.entity.BallDirection;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;
import view.phase1.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GamePanel2 extends JPanel implements Runnable {

    public static BallModel ball;
    public static BallDirection ballDirection;
    public static BallAngle ballAngle;
    public static EnemyModel1 enemy1;
    public static EnemyModel2 enemy2;
    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;
    public static boolean pause;

    GameInternalFrame gameInternalFrame;

    public static boolean framesCreated;
    public GamePanel2() {
        this.setBounds(0, 0, GameFrame2.width, GameFrame2.height);

        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

        gameInternalFrame = new GameInternalFrame();

        ball = GameController.ball;
        BallController.getBallIntoFrame2();
        ballDirection = GameController.createBallDirection();
        ballAngle = GameController.createBallAngle();





        this.setOpaque(false);
        keyInputListener = new KeyInputListener();
        this.addKeyListener(keyInputListener);
        mouseInputListener = new MouseInputListener();
        this.addMouseListener(mouseInputListener);
        this.addMouseMotionListener(mouseInputListener);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    @Override
    public void run() {

        while (true) {


            update();




            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public void update() {
        BallController.updateTheBall();
        BallDirectionController.updateBallDirectionPanel2();
        BallAngleController.updateBallAngle();
        BulletController.updateBullet();
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrames(g);
        drawBall(g);
        drawBallAngle(g);
        drawBallDirection(g);
        drawBullet(g);
//        drawEnemy1(g);
//        drawEnemy2(g);
//        drawCollectible(g);
        revalidate();
        repaint();
    }


    public void drawFrames(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            int x = GameInternalFrame.createdFrames[i].x;
            int y = GameInternalFrame.createdFrames[i].y;
            int width = GameInternalFrame.createdFrames[i].width;
            int height = GameInternalFrame.createdFrames[i].height;
            g.fillRect(x, y, width, height);
        }
        revalidate();
        repaint();

    }

    public void drawBall(Graphics g) {
        if (ball != null) {
            g.setColor(new Color(0x1C8F09));
            g.fillOval((int) ball.x - BallModel.ballRadius, (int) ball.y - BallModel.ballRadius,
                    2 * BallModel.ballRadius, 2 * BallModel.ballRadius);
        }
        revalidate();
        repaint();

    }

    public void drawBallDirection(Graphics g) {
        if (ballDirection != null) {
            g.setColor(new Color(0x132F46));
            g.fillOval((int) (ballDirection.x - 5), (int) (ballDirection.y - 5), 10, 10);
        }
        revalidate();
        repaint();
    }

    public void drawBallAngle(Graphics g) {
        if (ballAngle != null) {
            if (ballAngle.angleExists) {
                g.setColor(new Color(0xE5E5E5));
                g.fillOval((int) (ballAngle.x - BallAngle.ballAngleRadius),
                        (int) (ballAngle.y - BallAngle.ballAngleRadius),
                        2 * BallAngle.ballAngleRadius,
                        2 * BallAngle.ballAngleRadius);
            }
        }
        revalidate();
        repaint();
    }

    public static void drawBullet(Graphics g) {
        if (!GameController.bullets.isEmpty()) {
            for (int i = 0; i < GameController.bullets.size(); i++) {
                if (GameController.bullets.get(i).bulletHealth > 0) {
                    g.setColor(new Color(0xEF8506));
                    g.fillOval((int) GameController.bullets.get(i).x,
                            (int) GameController.bullets.get(i).y,
                            BulletModel.bulletSize,
                            BulletModel.bulletSize);
                }
            }
        }
    }

}
