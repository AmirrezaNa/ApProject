package controller.game;

import controller.Constants;
import controller.data.controller.SoundEffects;
import controller.game.objectsController.ball.CollectibleController;
import controller.game.objectsController.ball.enemies.normalAndMiniBoss.*;
import model.entity.*;
import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.PunchModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.boss.SmileyModel;
import model.entity.enemy.normalAndMiniBoss.*;
import view.gameLoop.phase1.GameFrame;
import view.gameLoop.phase1.GamePanel;
import view.gameLoop.phase2.finalBoss.FinalBossPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame.createdFrames;

public class GameController {
    public static SmileyModel smiley;
    public static RightHandModel rightHand;
    public static LeftHandModel leftHand;
    public static PunchModel punch;

    public static BallModel ball;
    static BallDirection ballDirection;
    public static BallAngle ballAngle;
    static BulletModel bullet;
    static EnemyModel1 enemy1;
    static EnemyModel2 enemy2;
    static ArchmireModel archmire;
    static BarricadosModel1 barricados1;
    static BarricadosModel2 barricados2;
    static BlackOrbModel blackOrb;
    static OmenoctModel omenoct;
    static WyrmModel wyrm;
    static NecropickModel necropick;
    static Collectible collectible;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<BulletModel> enemyBullets = new ArrayList<>();
    public static ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    public static ArrayList<EnemyModel2> enemies2 = new ArrayList<>();
    public static ArrayList<ArchmireModel> archmireEnemies = new ArrayList<>();
    public static ArrayList<ArchmirePoints> archmirePoints = new ArrayList<>();
    public static ArrayList<BarricadosModel1> barricadosEnemies1 = new ArrayList<>();
    public static ArrayList<BarricadosModel2> barricadosEnemies2 = new ArrayList<>();
    public static ArrayList<BlackOrbModel> blackOrbEnemies = new ArrayList<>();
    public static ArrayList<OmenoctModel> omenoctEnemies = new ArrayList<>();
    public static ArrayList<WyrmModel> wyrmEnemies = new ArrayList<>();
    public static ArrayList<NecropickModel> necropickEnemies = new ArrayList<>();
    public static ArrayList<Collectible> collectibles = new ArrayList<>();
    public static int wave = 1;
    public static int Banish = 0;
    public static int Empower = 0;
    public static boolean bulletAres;
    public static boolean gameOver;
    public static boolean pause;

    public static int elapsedTime;
    public static int numberOfBullets;

    // ===============================================================================


    // creating and updating the ball ================================================

    public synchronized static BallModel newBall() {
        ball = BallModel.getInstance((double) (GameFrame.width / 2) - 20, (double) (GameFrame.height / 2) - 20);
        return ball;
    }


    public static BallDirection createBallDirection() {
        ballDirection = new BallDirection(ball.x, ball.y);
        return ballDirection;
    }

    public static BallAngle createBallAngle() {
        ballAngle = new BallAngle(ball.x, ball.y);
        return ballAngle;
    }



    // creating the bullets ================================================

    public static boolean empowerBullet;


    public static BulletModel newBullet(Point point) {
        if ((!gameOver || !FinalBossPanel.finalBossOver) && !GameController.pause) {
            bullet = new BulletModel(ball.x, ball.y);
            bullet.dx = ((point.x - (ball.x)) / Math.sqrt(Math.pow((point.x - (ball.x)), 2) + Math.pow((point.y - (ball.y)), 2))) * BulletModel.bulletSpeed;
            if (bullet.y < point.y) {
                bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
            } else {
                bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
            }
            bullets.add(0, bullet);
            return bullet;
        }
        return null;
    }

    public static BulletModel newOmenoctBullet(Point point) {
        if ((!gameOver || !FinalBossPanel.finalBossOver)  && !GameController.pause && !ball.ballSlumber) {
            BulletModel.bulletSpeed = 3;
            bullet = new BulletModel(point.x, point.y);
            bullet.dx = -((point.x - (ball.x + 20)) / Math.sqrt(Math.pow((point.x - (ball.x + 20)), 2) + Math.pow((point.y - (ball.y + 20)), 2))) * BulletModel.bulletSpeed;
            if (ball.y > point.y) {
                bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
            } else {
                bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
            }
            enemyBullets.add(0, bullet);
            BulletModel.bulletSpeed = 15;
            return bullet;
        }
        return null;
    }

    public static BulletModel newNecropickBullet(Point point, Point goal) {
        if ((!gameOver || !FinalBossPanel.finalBossOver)  && !GameController.pause && !ball.ballSlumber) {
            BulletModel.bulletSpeed = 3;
            bullet = new BulletModel(point.x, point.y);
            bullet.dx = -((point.x - (goal.x)) / Math.sqrt(Math.pow((point.x - (goal.x)), 2) + Math.pow((point.y - (goal.y)), 2))) * BulletModel.bulletSpeed;
            if (goal.y > point.y) {
                bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
            } else {
                bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
            }
            enemyBullets.add(0, bullet);
            BulletModel.bulletSpeed = 15;
            return bullet;
        }
        return null;
    }



    // creating the enemies ================================================

    public static void newEnemy1() {
        if (!GamePanel.phase1over && !GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            if (enemies1.size() % 2 == 0) {
                enemy1 = new EnemyModel1(50, (double) GameFrame.height / 2);
                enemies1.add(0, enemy1);
            } else {
                enemy1 = new EnemyModel1((double) GameFrame.width / 2, 50);
                enemy1.dash = true;
                enemies1.add(0, enemy1);
            }
        }
    }

    public static void newEnemy2() {
        if (!GamePanel.phase1over && !GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            if (enemies2.size() % 2 == 0) {
                enemy2 = new EnemyModel2((double) GameFrame.width - 60, (double) GameFrame.height / 2);
                enemies2.add(0, enemy2);
            } else {
                enemy2 = new EnemyModel2(((double) GameFrame.width / 2), GameFrame.height - 60);
                enemies2.add(0, enemy2);
            }

        }
    }


    public static void newArchmire() {
        if (!GameController.pause && !ball.ballSlumber)
        for (int i = 1; i < 3; i++) {
            ArchmireModel archmireModel = new ArchmireModel(createdFrames[i].x + (double) (createdFrames[i].width / 2),
                    createdFrames[i].y + (double) (createdFrames[i].height / 2));
            ArchmireController.setTrace(archmireModel);
            archmireEnemies.add(archmireModel);
        }
        SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);

    }

    public static void newBarricados1() {
        if (!GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[2].x + (createdFrames[2].width/2);
            int y = createdFrames[2].y + createdFrames[2].height - (BarricadosModel1.barricadosSize/2);
            barricados1 = new BarricadosModel1(x, y);
            barricadosEnemies1.add(barricados1);
            BarricadosController1.setTimerForBarricados1(barricados1);
        }
    }

    public static void newBarricados2() {
        if (!GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[1].x + (createdFrames[1].width/2);
            int y = createdFrames[1].y + createdFrames[1].height - (BarricadosModel2.barricadosSize/2);
            barricados2 = new BarricadosModel2(x, y);
            barricadosEnemies2.add(barricados2);
            BarricadosController2.setTimerForBarricados2(barricados2);
        }
    }


    public static void newBlackOrb() {
        if (!GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[3].x + (createdFrames[3].width/2);
            int y = createdFrames[3].y + (createdFrames[3].height/2);
            blackOrb = new BlackOrbModel(x, y);
            blackOrbEnemies.add(blackOrb);
            BlackOrbController.setTimerForCreatingBlackOrb(blackOrb);
        }
    }


    public static void newOmenoct() {
        if (!GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[FrameOfObject.getFrameOfBall()].x + createdFrames[FrameOfObject.getFrameOfBall()].width - (ArchmireModel.archmireSize / 2);
            int y = createdFrames[FrameOfObject.getFrameOfBall()].y + createdFrames[FrameOfObject.getFrameOfBall()].height/2;
            omenoct = new OmenoctModel(x, y);
            omenoctEnemies.add(omenoct);
            if (omenoctEnemies.size() == 1) {
                OmenoctController.shotBullet();
            }
        }
    }


    public static void newWyrm() {
        if (!GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[3].x + (createdFrames[3].width/2);
            int y = createdFrames[3].y + createdFrames[3].height - (WyrmModel.wyrmSize/2);
            wyrm = new WyrmModel(x, y);
            wyrmEnemies.add(wyrm);
            if (wyrmEnemies.size() == 1) {
                WyrmController.shotBullet();
            }
        }
    }

    public static void newNecropick() {
        if (!GameController.pause && !ball.ballSlumber) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            necropick = new NecropickModel((int) (ball.x - 200), (int) ball.y);
            necropickEnemies.add(necropick);
        }
    }


    // this part is for collectibles ====================================================

    public static void newCollectible(double x, double y) {
        collectible = new Collectible(x, y);
        collectibles.add(collectible);
        CollectibleController.countDownCollectible(10, collectible);//collectible will disappear in 10 seconds
    }



    // this part is for boss-fight ======================================================

    public static SmileyModel newSmiley(double x, double y) {
        smiley = new SmileyModel(x, y);
        return smiley;
    }

    public static RightHandModel newRightHand(double x, double y) {
        rightHand = new RightHandModel(x, y);
        return rightHand;
    }

    public static LeftHandModel newLeftHand(double x, double y) {
        leftHand = new LeftHandModel(x, y);
        return leftHand;
    }

    public static PunchModel newPunch(double x, double y) {
        punch = new PunchModel(x, y);
        return punch;
    }


    public static void gameTime() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!GameController.pause) {
                    elapsedTime++;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


}
