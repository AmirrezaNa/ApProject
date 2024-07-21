package view.gameLoop.phase2.finalBoss;

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
import view.gameLoop.phase2.normalAndMiniBossEnemies.GameFrame2;

import javax.swing.*;
import java.awt.*;

public class FinalBossPanel extends JPanel implements Runnable {



    public static BallModel ball;
    public static BallDirection ballDirection;
    public static BallAngle ballAngle;

    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;
    public static boolean finalBossOver;
    EpsilonFrame epsilonFrame;


    public FinalBossPanel() {
        finalBossOver = false;
        this.setBounds(0, 0, GameFrame2.width, GameFrame2.height);

        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

        epsilonFrame = new EpsilonFrame();
        ball = GameController.ball;
        BallController.getBallIntoFinalFrame();
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

        drawEpsilonFrame(g);

        drawBall(g);
        drawBallDirection(g);
        drawBallAngle(g);

        drawBullet(g);





        revalidate();
        repaint();
    }


    public void drawEpsilonFrame(Graphics g) {
        g.setColor(Color.black);
        int x = EpsilonFrame.epsilonFrame.x;
        int y = EpsilonFrame.epsilonFrame.y;
        int width = EpsilonFrame.epsilonFrame.width;
        int height = EpsilonFrame.epsilonFrame.height;
        g.fillRect(x, y, width, height);
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

    public void drawBullet(Graphics g) {
        if (!GameController.bullets.isEmpty()) {
            int size;
            if (GameController.bullets.size() > 15) {
                size = 15;
            }
            else {
                size = GameController.bullets.size();
            }
            for (int i = 0; i < size; i++) {
                if (GameController.bullets.get(i).bulletHealth > 0) {
                    if (BulletController.isBulletInAFrame(GameController.bullets.get(i))) {
                        g.setColor(new Color(0xEF8506));
                        g.fillOval((int) GameController.bullets.get(i).x,
                                (int) GameController.bullets.get(i).y,
                                BulletModel.bulletSize,
                                BulletModel.bulletSize);
                    }
                }
            }
        }
        repaint();
        repaint();
    }





}
