package view.phase2;

import controller.game.FrameOfObject;
import controller.game.GameController;
import controller.game.WaveController;
import controller.game.collisions.phase2.FrameCollisions2;
import controller.game.collisions.phase2.ObjectCollisions2;
import controller.game.listener.KeyInputListener;
import controller.game.listener.MouseInputListener;
import controller.game.objectsController.ball.BallAngleController;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BallDirectionController;
import controller.game.objectsController.ball.BulletController;
import controller.game.objectsController.ball.enemies.ArchmireController;
import controller.game.objectsController.ball.enemies.NecropickController;
import controller.game.objectsController.ball.enemies.OmenoctController;
import controller.game.objectsController.ball.enemies.WyrmController;
import model.entity.*;
import model.entity.enemy.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel2 extends JPanel implements Runnable {

    public static BallModel ball;
    public static BallDirection ballDirection;
    public static BallAngle ballAngle;
    public static ArchmireModel archmire;
    public static BarricadosModel barricados;
    public static BlackOrbModel blackOrb;
    public static OmenoctModel omenoct;
    public static WyrmModel wyrm;
    public static NecropickModel necropick;
    KeyInputListener keyInputListener;
    MouseInputListener mouseInputListener;
    public static boolean pause;

    GameInternalFrame gameInternalFrame;

    public static boolean framesCreated;
    public static boolean ballBetweenFrames;
    public static int mainFrame;//shows the frame of the ball
    public GamePanel2() {
        this.setBounds(0, 0, GameFrame2.width, GameFrame2.height);

        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

        gameInternalFrame = new GameInternalFrame();

        ball = GameController.ball;
        BallController.getBallIntoFrame2();
        BallController.checkIfBallInArchmire();
        BallController.checkIfBallInArchmireTrace();
        ballDirection = GameController.createBallDirection();
        ballAngle = GameController.createBallAngle();
        omenoct = WaveController.setTimerForOmenoct();
        necropick = WaveController.setTimerForNecropick();
        archmire = WaveController.setTimerForArchmire();
        wyrm = WaveController.setTimerForWyrm();





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
        mainFrame = FrameOfObject.getFrameOfBall();
        BallController.updateTheBall();
        BallDirectionController.updateBallDirectionPanel2();
        BallAngleController.updateBallAngle();
        BulletController.updateBullet();
        BulletController.updateEnemyBullet();
        OmenoctController.updateOmenoct();
        NecropickController.update();
        ArchmireController.updateArchmire();
        WyrmController.updateWyrm();
        FrameOfObject.FrameOfBullet();
        FrameCollisions2.checkFramesCollisions2();
        ObjectCollisions2.checkCollisionsPhase2();
        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrames(g);
        drawArchmireTrace(g);
        drawBall(g);
        drawBallAngle(g);
        drawBallDirection(g);
        drawBullet(g);
        drawEnemyBullet(g);
        drawOmenoct(g);
        drawNecropick(g);
        drawArchmire(g);
        drawWyrm(g);
        drawCollectible(g);
        revalidate();
        repaint();
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
        if (BallController.checkIfBallIsInFrame() || ballBetweenFrames) {
            if (ball != null) {
                g.setColor(new Color(0x1C8F09));
                g.fillOval((int) ball.x - BallModel.ballRadius, (int) ball.y - BallModel.ballRadius,
                        2 * BallModel.ballRadius, 2 * BallModel.ballRadius);
            }
            revalidate();
            repaint();
        }
    }

    public void drawBallDirection(Graphics g) {
        if (BallController.checkIfBallIsInFrame() || ballBetweenFrames) {
            if (ballDirection != null) {
                g.setColor(new Color(0x132F46));
                g.fillOval((int) (ballDirection.x - 5), (int) (ballDirection.y - 5), 10, 10);
            }
            revalidate();
            repaint();
        }
    }

    public void drawBallAngle(Graphics g) {
        if (BallController.checkIfBallIsInFrame() || ballBetweenFrames) {
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
    }

    public void drawBullet(Graphics g) {
        if (!GameController.bullets.isEmpty()) {
            for (int i = 0; i < GameController.bullets.size(); i++) {
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

    public void drawEnemyBullet(Graphics g) {
        if (!GameController.enemyBullets.isEmpty()) {
            for (int i = 0; i < GameController.enemyBullets.size(); i++) {
                if (GameController.enemyBullets.get(i).bulletHealth > 0) {
                    if (BulletController.isBulletInAFrame(GameController.enemyBullets.get(i))) {
                        g.setColor(new Color(0x8C0101));
                        g.fillOval((int) GameController.enemyBullets.get(i).x,
                                (int) GameController.enemyBullets.get(i).y,
                                BulletModel.bulletSize,
                                BulletModel.bulletSize);
                    }
                }
            }
        }
        repaint();
        repaint();
    }




    public void drawOmenoct(Graphics g) {
        if (!GameController.omenoctEnemies.isEmpty()) {
            for (int i = 0; i < GameController.omenoctEnemies.size(); i++) {
                if (GameController.omenoctEnemies.get(i).enemyHealth > 0) {
                    super.paintComponent(g);
                    g.drawImage(OmenoctModel.image,
                            (int)GameController.omenoctEnemies.get(i).x - OmenoctModel.distanceToCenter,
                            (int)GameController.omenoctEnemies.get(i).y,
                            2*OmenoctModel.distanceToCenter,
                            2*OmenoctModel.distanceToCenter,
                            null);
                }
            }
        }
        repaint();
        repaint();
    }

    public void drawNecropick(Graphics g) {
        if (!GameController.necropickEnemies.isEmpty()) {
            for (int i = 0; i < GameController.necropickEnemies.size(); i++) {
                if (GameController.necropickEnemies.get(i).enemyHealth > 0 &&
                        !GameController.necropickEnemies.get(i).hide) {
                    super.paintComponent(g);
                    g.drawImage(NecropickModel.image,
                            (int)GameController.necropickEnemies.get(i).x,
                            (int)GameController.necropickEnemies.get(i).y,
                            NecropickModel.necropickSize,
                            NecropickModel.necropickSize,
                            null);
                }
            }
        }
        repaint();
        repaint();
    }


    public void drawArchmire(Graphics g) {
        if (!GameController.archmireEnemies.isEmpty()) {
            for (int i = 0; i < GameController.archmireEnemies.size(); i++) {
                if (GameController.archmireEnemies.get(i).enemyHealth > 0) {
                    super.paintComponent(g);
                    g.drawImage(ArchmireModel.image,
                            (int)GameController.archmireEnemies.get(i).x,
                            (int)GameController.archmireEnemies.get(i).y,
                            ArchmireModel.archmireSize,
                            ArchmireModel.archmireSize,
                            null);
                }
            }
        }
        repaint();
        repaint();
    }

    public void drawArchmireTrace(Graphics g) {
        if (!GameController.archmirePoints.isEmpty()) {
            for (int i = 0; i < GameController.archmirePoints.size(); i++) {
                if (GameController.archmirePoints.get(i).archmirePointTimer > 0) {
                    super.paintComponent(g);
                    g.setColor(new Color(0x4B2828));
                    g.fillOval((int)GameController.archmirePoints.get(i).x - (ArchmireModel.archmireSize/2),
                            (int)GameController.archmirePoints.get(i).y - (ArchmireModel.archmireSize/2),
                            ArchmireModel.archmireSize,
                            ArchmireModel.archmireSize);
                }
            }
        }
        repaint();
        repaint();
    }

    public void drawWyrm(Graphics g) {

    }

}
