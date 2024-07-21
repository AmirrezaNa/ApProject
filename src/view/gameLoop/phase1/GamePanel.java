package view.gameLoop.phase1;

import controller.game.WaveController;
import controller.game.collisions.phase1.FrameCollision;
import controller.game.collisions.phase1.ObjectCollision;
import controller.game.objectsController.ball.BallAngleController;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BallDirectionController;
import controller.game.objectsController.ball.BulletController;
import controller.game.objectsController.ball.enemies.normalAndMiniBoss.Enemy1Controller;
import controller.game.objectsController.ball.enemies.normalAndMiniBoss.Enemy2Controller;
import model.entity.*;
import controller.game.GameController;
import controller.game.listener.KeyInputListener;
import controller.game.listener.MouseInputListener;
import model.entity.enemy.normalAndMiniBoss.EnemyModel1;
import model.entity.enemy.normalAndMiniBoss.EnemyModel2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable {


    public static BallModel ball;
    public static BallDirection ballDirection;
    public static BallAngle ballAngle;
    public static EnemyModel1 enemy1;
    public static EnemyModel2 enemy2;
    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;


    public static boolean phase1over;

    GamePanel() {
        initPanel();
    }

    public void initPanel() {
        GameFrame.GameIsRunning = true;
        //changeGamePanelSize();
        this.setBackground(Color.BLACK);
        ball = GameController.newBall();
        ballDirection = GameController.createBallDirection();
        ballAngle = GameController.createBallAngle();
        enemy1 = WaveController.setTimerForEnemy1();
        enemy2 = WaveController.setTimerForEnemy2();

        changeGamePanelSize();
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

        while (GameFrame.GameIsRunning) {


            update();




            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void update() {
        if (!phase1over) {
            BallController.updateTheBall();
            BallDirectionController.updateBallDirection();
            BallAngleController.updateBallAngle();
            BulletController.updateBullet();
            FrameCollision.checkFrameCollisions();
            ObjectCollision.checkObjectsCollisions();
            Enemy1Controller.updateEnemy1();
            Enemy2Controller.updateEnemy2();
        }
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
        drawBallAngle(g);
        drawBallDirection(g);
        drawBullet(g);
        drawEnemy1(g);
        drawEnemy2(g);
        drawCollectible(g);
    }


    public static void drawBall(Graphics g) {
        if (ball != null) {
            g.setColor(new Color(0x1C8F09));
            g.fillOval((int) ball.x - BallModel.ballRadius, (int) ball.y - BallModel.ballRadius,
                    2 * BallModel.ballRadius, 2 * BallModel.ballRadius);
        }

    }

    public static void drawBallDirection(Graphics g) {
        if (ballDirection != null) {
            g.setColor(new Color(0x132F46));
            g.fillOval((int) (ballDirection.x - 5), (int) (ballDirection.y - 5), 10, 10);
        }
    }

    public static void drawBallAngle(Graphics g) {
        if (ballAngle != null) {
            if (ballAngle.angleExists) {
                g.setColor(new Color(0xE5E5E5));
                g.fillOval((int) (ballAngle.x - BallAngle.ballAngleRadius),
                        (int) (ballAngle.y - BallAngle.ballAngleRadius),
                        2 * BallAngle.ballAngleRadius,
                        2 * BallAngle.ballAngleRadius);
            }
        }
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

    public static void drawEnemy1(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!GameController.enemies1.isEmpty()) {
            for (int i = 0; i < GameController.enemies1.size(); i++) {
                if (GameController.enemies1.get(i).enemyHealth > 0) {
                    Polygon polygon = new Polygon(
                            new int[]{(int) GameController.enemies1.get(i).xAngles[0],
                                    (int) GameController.enemies1.get(i).xAngles[1],
                                    (int) GameController.enemies1.get(i).xAngles[2],
                                    (int) GameController.enemies1.get(i).xAngles[3]},
                            new int[]{(int) GameController.enemies1.get(i).yAngles[0],
                                    (int) GameController.enemies1.get(i).yAngles[1],
                                    (int) GameController.enemies1.get(i).yAngles[2],
                                    (int) GameController.enemies1.get(i).yAngles[3]},
                            4);
                    g2d.setColor(new Color(0xD71111));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawPolygon(polygon);
                }
            }
        }
    }

    public static void drawEnemy2(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!GameController.enemies2.isEmpty()) {
            for (int i = 0; i < GameController.enemies2.size(); i++) {
                if (GameController.enemies2.get(i).enemyHealth > 0) {
                    Polygon polygon = new Polygon(
                            new int[]{(int) GameController.enemies2.get(i).xAngles[0],
                                    (int) GameController.enemies2.get(i).xAngles[1],
                                    (int) GameController.enemies2.get(i).xAngles[2]},
                            new int[]{(int) GameController.enemies2.get(i).yAngles[0],
                                    (int) GameController.enemies2.get(i).yAngles[1],
                                    (int) GameController.enemies2.get(i).yAngles[2]},
                            3);
                    g2d.setColor(new Color(0x0271D7));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawPolygon(polygon);

                }
            }
        }
    }

    public static void drawCollectible(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!GameController.collectibles.isEmpty()) {
            for (int i = 0; i < GameController.collectibles.size(); i++) {
                if (GameController.collectibles.get(i).collectibleHealth > 0) {
                    g2d.setColor(new Color(0xFFCF0F));
                    g2d.fillOval((int) GameController.collectibles.get(i).x,
                            (int) GameController.collectibles.get(i).y,
                            (int) Collectible.collectibleSize,
                            (int) Collectible.collectibleSize);
                }
            }
        }
    }

}
