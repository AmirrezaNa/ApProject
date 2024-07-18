package controller.game;

import controller.Constants;
import controller.data.controller.SoundEffects;
import controller.game.objectsController.ball.CollectibleController;
import model.entity.*;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;
import view.phase1.GameFrame;
import view.phase1.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class GameController {

    public static BallModel ball;
    static BulletModel bullet;
    static EnemyModel1 enemy1;
    static EnemyModel2 enemy2;
    static Collectible collectible;
    static BallDirection ballDirection;
    public static BallAngle ballAngle;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    public static ArrayList<EnemyModel2> enemies2 = new ArrayList<>();
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


    // creating the enemies ================================================

    public static void newEnemy1() {
        if (!GamePanel.phase1over) {

            if (enemies1.size() % 2 == 0) {
                SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
                enemy1 = new EnemyModel1(50, (double) GameFrame.height / 2);
                enemies1.add(0, enemy1);
            } else {
                SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
                enemy1 = new EnemyModel1((double) GameFrame.width / 2, 50);
                enemy1.dash = true;
                enemies1.add(0, enemy1);
            }
        }
    }

    public static void newEnemy2() {
        if (!GamePanel.phase1over) {

            if (enemies2.size() % 2 == 0) {
                SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
                enemy2 = new EnemyModel2((double) GameFrame.width - 60, (double) GameFrame.height / 2);
                enemies2.add(0, enemy2);
            } else {
                SoundEffects.playSound(Constants.ENEMY_ENTER_SOUND_PATH);
                enemy2 = new EnemyModel2(((double) GameFrame.width / 2), GameFrame.height - 60);
                enemies2.add(0, enemy2);
            }

        }
    }


    // this part is for collectibles ====================================================

    public static void newCollectible(double x, double y) {
        collectible = new Collectible(x, y);
        collectibles.add(collectible);

        CollectibleController.countDownCollectible(10, collectible);//collectible will disappear in 10 seconds
    }




}
