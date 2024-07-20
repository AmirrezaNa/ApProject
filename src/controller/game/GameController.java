package controller.game;

import controller.Constants;
import controller.data.controller.SoundEffects;
import controller.game.objectsController.ball.CollectibleController;
import controller.game.objectsController.ball.enemies.ArchmireController;
import controller.game.objectsController.ball.enemies.OmenoctController;
import controller.game.objectsController.ball.enemies.WyrmController;
import model.entity.*;
import model.entity.enemy.*;
import view.phase1.GameFrame;
import view.phase1.GamePanel;

import java.awt.*;
import java.util.ArrayList;

import static view.phase2.GameInternalFrame.createdFrames;

public class GameController {

    public static BallModel ball;
    static BulletModel bullet;
    static EnemyModel1 enemy1;
    static EnemyModel2 enemy2;
    static ArchmireModel archmire;
    static BarricadosModel barricados;
    static BlackOrbModel blackOrb;
    static OmenoctModel omenoct;
    static WyrmModel wyrm;
    static NecropickModel necropick;
    static Collectible collectible;
    static BallDirection ballDirection;
    public static BallAngle ballAngle;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<BulletModel> enemyBullets = new ArrayList<>();
    public static ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    public static ArrayList<EnemyModel2> enemies2 = new ArrayList<>();
    public static ArrayList<ArchmireModel> archmireEnemies = new ArrayList<>();
    public static ArrayList<ArchmirePoints> archmirePoints = new ArrayList<>();
    public static ArrayList<BarricadosModel> barricadosEnemies = new ArrayList<>();
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
        if (!gameOver) {
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
        if (!gameOver) {
            bullet = new BulletModel(point.x, point.y);
            bullet.dx = -((point.x - (ball.x)) / Math.sqrt(Math.pow((point.x - (ball.x)), 2) + Math.pow((point.y - (ball.y)), 2))) * BulletModel.bulletSpeed;
            if (ball.y > point.y) {
                bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
            } else {
                bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
            }
            enemyBullets.add(0, bullet);
            return bullet;
        }
        return null;
    }

    public static BulletModel newNecropickBullet(Point point, Point goal) {
        if (!gameOver) {
            bullet = new BulletModel(point.x, point.y);
            bullet.dx = -((point.x - (goal.x)) / Math.sqrt(Math.pow((point.x - (goal.x)), 2) + Math.pow((point.y - (goal.y)), 2))) * BulletModel.bulletSpeed;
            if (goal.y > point.y) {
                bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
            } else {
                bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
            }
            enemyBullets.add(0, bullet);
            return bullet;
        }
        return null;
    }



    // creating the enemies ================================================

    public static void newEnemy1() {
        if (!GamePanel.phase1over) {
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
        if (!GamePanel.phase1over) {
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
        for (int i = 0; i < 4; i++) {
            ArchmireModel archmireModel = new ArchmireModel(createdFrames[i].x + (double) (createdFrames[i].width / 2),
                    createdFrames[i].y + (double) (createdFrames[i].height / 2));
            ArchmireController.setTrace(archmireModel);
            archmireEnemies.add(archmireModel);
        }
        SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);

    }

    public static void newBarricados() {

    }


    public static void newBlackOrb() {

    }


    public static void newOmenoct() {
        if (GamePanel.phase1over) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[FrameOfObject.getFrameOfBall()].x + createdFrames[FrameOfObject.getFrameOfBall()].width - (ArchmireModel.archmireSize / 2);
            int y = createdFrames[FrameOfObject.getFrameOfBall()].y + createdFrames[FrameOfObject.getFrameOfBall()].height/2;
            omenoct = new OmenoctModel(x, y);
            if (omenoctEnemies.isEmpty()) {
                OmenoctController.shotBullet();
            }
            omenoctEnemies.add(omenoct);
        }
    }


    public static void newWyrm() {
        if (GamePanel.phase1over) {
            SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
            int x = createdFrames[3].x + (createdFrames[3].width/2);
            int y = createdFrames[3].y + createdFrames[3].height - (WyrmModel.wyrmSize/2);
            wyrm = new WyrmModel(x, y);
            if (wyrmEnemies.isEmpty()) {
                WyrmController.shotBullet();
            }
            wyrmEnemies.add(wyrm);
        }
    }

    public static void newNecropick() {
        if (GamePanel.phase1over) {
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




}
