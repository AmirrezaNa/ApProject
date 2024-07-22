package view.gameLoop.phase2.finalBoss;

import controller.game.GameController;
import controller.game.SmileyAttacksController;
import controller.game.collisions.bossFight.FrameCollisionBossFight;
import controller.game.collisions.bossFight.ObjectCollisionBossFight;
import controller.game.listener.KeyInputListener;
import controller.game.listener.MouseInputListener;
import controller.game.objectsController.ball.BallAngleController;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BallDirectionController;
import controller.game.objectsController.ball.BulletController;
import controller.game.objectsController.ball.finalBoss.LeftHandController;
import controller.game.objectsController.ball.finalBoss.PunchController;
import controller.game.objectsController.ball.finalBoss.RightHandController;
import controller.game.objectsController.ball.finalBoss.SmileyController;
import model.entity.BallAngle;
import model.entity.BallDirection;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.PunchModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.boss.SmileyModel;
import model.entity.enemy.normalAndMiniBoss.ArchmireModel;
import view.gameLoop.phase1.GamePanel;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GameFrame2;

import javax.swing.*;
import java.awt.*;

import static controller.game.GameController.punch;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2.phase2Over;

public class FinalBossPanel extends JPanel implements Runnable {

    public static SmileyModel smiley;
    public static RightHandModel rightHand;
    public static LeftHandModel leftHand;
    public static PunchModel punch;

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

        smiley = GameController.newSmiley(550, 0);
        rightHand = GameController.newRightHand(GameFrame2.width, (double) GameFrame2.height /2);
        leftHand = GameController.newLeftHand(0, (double) GameFrame2.height /2);




        SmileyAttacksController.startSmileyAttacks();
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

        while (!finalBossOver) {


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
        if (!GameController.pause) {

            FrameCollisionBossFight.checkBossFightFrameCollisions();

            BallController.updateTheBall();
            BallDirectionController.updateBallDirectionFinalBoss();
            BallAngleController.updateBallAngle();

            BulletController.updateBullet();
            if (!GameController.ball.ballSlumber) {
                BulletController.updateEnemyBullet();

                SmileyController.updateSmiley();
                RightHandController.updateRightHand();
                LeftHandController.updateLeftHand();
                PunchController.updatePunch();
            }

            ObjectCollisionBossFight.checkCollisionsPhase2();
            FrameCollisionBossFight.checkBossFightFrameCollisions();

            revalidate();
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawEpsilonFrame(g);
        drawArchmireTrace(g);

        GamePanel.drawBallCerberus(g);
        drawBall(g);
        drawBallDirection(g);
        drawBallAngle(g);

        drawSmiley(g);
        drawRightHand(g);
        drawLeftHand(g);
        drawPunch(g);


        drawBullet(g);
        drawEnemyBullet(g);





        revalidate();
        repaint();
    }


    public void drawEpsilonFrame(Graphics g) {
        super.paintComponent(g);
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
        super.paintComponent(g);
        if (ball != null) {
            g.setColor(new Color(0x1C8F09));
            g.fillOval((int) ball.x - BallModel.ballRadius, (int) ball.y - BallModel.ballRadius,
                    2 * BallModel.ballRadius, 2 * BallModel.ballRadius);
        }
        revalidate();
        repaint();
    }

    public void drawBallDirection(Graphics g) {
        super.paintComponent(g);
        if (ballDirection != null) {
            g.setColor(new Color(0x132F46));
            g.fillOval((int) (ballDirection.x - 5), (int) (ballDirection.y - 5), 10, 10);
        }
        revalidate();
        repaint();
    }

    public void drawBallAngle(Graphics g) {
        super.paintComponent(g);
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
                    g.setColor(new Color(0xEF8506));
                    g.fillOval((int) GameController.bullets.get(i).x,
                            (int) GameController.bullets.get(i).y,
                            BulletModel.bulletSize,
                            BulletModel.bulletSize);
                }
            }
        }
        repaint();
        repaint();
    }


    public void drawEnemyBullet(Graphics g) {
        if (!GameController.enemyBullets.isEmpty()) {
            int size = Math.min(GameController.enemyBullets.size(), 20);
            for (int i = 0; i < size; i++) {
                if (GameController.enemyBullets.get(i).bulletHealth > 0) {
                    g.setColor(new Color(0x8C0101));
                    g.fillOval((int) GameController.enemyBullets.get(i).x,
                            (int) GameController.enemyBullets.get(i).y,
                            BulletModel.bulletSize,
                            BulletModel.bulletSize);
                }
            }
        }
        repaint();
        repaint();
    }


    public void drawArchmireTrace(Graphics g) {
        int size = Math.min(30, GameController.archmirePoints.size());
        if (!GameController.archmirePoints.isEmpty()) {
            for (int i = 0; i < size; i++) {
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

    public void drawSmiley(Graphics g) {
        if (GameController.smiley != null) {
            g.drawImage(SmileyModel.image,
                    (int) (smiley.x - (SmileyModel.smileySize/2)),
                    (int) (smiley.y - (SmileyModel.smileySize/2)),
                    SmileyModel.smileySize,
                    SmileyModel.smileySize, null);
        }
        revalidate();
        repaint();
    }

    public void drawRightHand(Graphics g) {
        if (GameController.rightHand != null) {
            g.drawImage(RightHandModel.image,
                    (int) rightHand.x,
                    (int) (rightHand.y - (RightHandModel.rightHandSize/2)),
                    RightHandModel.rightHandSize,
                    RightHandModel.rightHandSize, null);
        }
        revalidate();
        repaint();
    }

    public void drawLeftHand(Graphics g) {
        if (GameController.leftHand != null) {
            g.drawImage(LeftHandModel.image,
                    (int) leftHand.x - LeftHandModel.leftHandSize,
                    (int) (leftHand.y - (LeftHandModel.leftHandSize/2)),
                    LeftHandModel.leftHandSize,
                    LeftHandModel.leftHandSize, null);
        }
        revalidate();
        repaint();
    }


    public void drawPunch(Graphics g) {
        if (GameController.smiley.punchExists && punch != null) {
            g.drawImage(PunchModel.image,
                    (int) (punch.x - (PunchModel.punchSize / 2)),
                    (int) (punch.y - PunchModel.punchSize),
                    PunchModel.punchSize,
                    PunchModel.punchSize, null);
        }
        revalidate();
        repaint();
    }





}
