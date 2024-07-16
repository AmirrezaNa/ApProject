package view.game;

import controller.game.WaveController;
import controller.game.collisions.FrameCollision;
import controller.game.collisions.ObjectCollision;
import controller.game.objectsController.ball.BallAngleController;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BallDirectionController;
import controller.game.objectsController.ball.BulletController;
import controller.game.objectsController.ball.enemies.Enemy1Controller;
import controller.game.objectsController.ball.enemies.Enemy2Controller;
import model.entity.*;
import controller.game.GameController;
import controller.game.listener.KeyInputListener;
import controller.game.listener.MouseInputListener;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable {
    GameController gameController = new GameController();
    WaveController waveController = new WaveController();


    public static BallModel ball;
    public static BallDirection ballDirection;
    public static BallAngle ballAngle;
    public static EnemyModel1 enemy1;
    public static EnemyModel2 enemy2;
    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;
    public static boolean pause;

    GamePanel() {
        initPanel();
    }

    public void initPanel() {
        GameFrame.GameIsRunning = true;
        //changeGamePanelSize();
        this.setBackground(Color.BLACK);
        ball = gameController.newBall();
        ballDirection = gameController.createBallDirection();
        ballAngle = gameController.createBallAngle();
        waveController.setTimerForEnemy1();
        waveController.setTimerForEnemy2();

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


            draw();


            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    BallController ballController = new BallController();
    BallDirectionController ballDirectionController = new BallDirectionController();
    BallAngleController ballAngleController = new BallAngleController();
    BulletController bulletController = new BulletController();
    ObjectCollision objectCollision = new ObjectCollision();
    FrameCollision frameCollision = new FrameCollision();
    Enemy1Controller enemy1Controller = new Enemy1Controller();
    Enemy2Controller enemy2Controller = new Enemy2Controller();


    public void update() {
        if (!pause) {
            ballController.updateTheBall();
            ballDirectionController.updateBallDirection();
            ballAngleController.updateBallAngle();
            bulletController.updateBullet();
            objectCollision.checkObjectsCollisions();
            frameCollision.checkFrameCollisions();
            enemy1Controller.updateEnemy1();
            enemy2Controller.updateEnemy2();
        }
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
        drawBallAngle(g);
        drawBallDirection(g);
        drawBullet(g);
        drawEnemy1(g);
        drawEnemy2(g);
        drawCollectible(g);
    }


    public void drawBall(Graphics g) {
        if (gameController.getBall() != null) {
            g.setColor(new Color(0x1C8F09));
            g.fillOval((int) gameController.getBall().getX() - BallModel.getBallRadius(), (int) gameController.getBall().getY() - BallModel.getBallRadius(),
                    2 * BallModel.getBallRadius(), 2 * BallModel.getBallRadius());
        }

    }

    public void drawBallDirection(Graphics g) {
        if (gameController.getBallDirection() != null) {
            g.setColor(new Color(0x132F46));
            g.fillOval((int) (gameController.getBallDirection().getX() - 5), (int) (gameController.getBallDirection().getY() - 5), 10, 10);
        }
    }

    public void drawBallAngle(Graphics g) {
        if (gameController.getBallAngle() != null) {
            if (ballAngle.isAngleExists()) {
                g.setColor(new Color(0xE5E5E5));
                g.fillOval((int) (gameController.getBallAngle().getX() - BallAngle.getBallAngleRadius()),
                        (int) (gameController.getBallAngle().getY() - BallAngle.getBallAngleRadius()),
                        2 * BallAngle.getBallAngleRadius(),
                        2 * BallAngle.getBallAngleRadius());
            }
        }
    }

    public void drawBullet(Graphics g) {
        if (!gameController.getBullets().isEmpty()) {
            for (int i = 0; i < gameController.getBullets().size(); i++) {
                if (gameController.getBullets().get(i).getBulletHealth() > 0) {
                    g.setColor(new Color(0xEF8506));
                    g.fillOval((int) gameController.getBullets().get(i).getX(),
                            (int) gameController.getBullets().get(i).getY(),
                            BulletModel.getBulletSize(),
                            BulletModel.getBulletSize());
                }
            }
        }
    }

    public void drawEnemy1(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!gameController.getEnemies1().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies1().size(); i++) {
                EnemyModel1 enemy = gameController.getEnemies1().get(i);
                if (enemy.getEnemyHealth() > 0) {
                    Polygon polygon = new Polygon(
                            new int[]{(int) enemy.getxAngles()[0],
                                    (int) enemy.getxAngles()[1],
                                    (int) enemy.getxAngles()[2],
                                    (int) enemy.getxAngles()[3]},
                            new int[]{(int) enemy.getyAngles()[0],
                                    (int) enemy.getyAngles()[1],
                                    (int) enemy.getyAngles()[2],
                                    (int) enemy.getyAngles()[3]},
                            4);
                    g2d.setColor(new Color(0xD71111));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawPolygon(polygon);
                }
            }
        }
    }

    public void drawEnemy2(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!gameController.getEnemies2().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies2().size(); i++) {
                EnemyModel2 enemy = gameController.getEnemies2().get(i);
                if (enemy.getEnemyHealth() > 0) {
                    Polygon polygon = new Polygon(
                            new int[]{(int) enemy.getxAngles()[0],
                                    (int) enemy.getxAngles()[1],
                                    (int) enemy.getxAngles()[2]},
                            new int[]{(int) enemy.getyAngles()[0],
                                    (int) enemy.getyAngles()[1],
                                    (int) enemy.getyAngles()[2]},
                            3);
                    g2d.setColor(new Color(0x0271D7));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawPolygon(polygon);

                }
            }
        }
    }

    public void drawCollectible(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!gameController.getCollectibles().isEmpty()) {
            for (int i = 0; i < gameController.getCollectibles().size(); i++) {
                Collectible collectible = gameController.getCollectibles().get(i);
                if (collectible.getCollectibleHealth() > 0) {
                    g2d.setColor(new Color(0xFFCF0F));
                    g2d.fillOval((int) collectible.getX(),
                            (int) collectible.getY(),
                            (int) Collectible.getCollectibleSize(),
                            (int) Collectible.getCollectibleSize());
                }
            }
        }
    }

}
