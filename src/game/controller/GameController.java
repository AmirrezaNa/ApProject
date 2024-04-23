package game.controller;

import game.entity.BallModel;
import game.entity.BulletModel;
import game.entity.EnemyModel;
import game.frame.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    static BallModel ball;
    static BulletModel bullet;
    static EnemyModel enemy;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<EnemyModel> enemies = new ArrayList<>();


    // creating and updating the ball ================================================

    public static BallModel newBall() {
        ball = new BallModel(140, 140);
        return ball;
    }

    public static void updateTheBall() {

    }

    // ==================================================================================


    // creating and updating the bullets ================================================


    public static BulletModel newBullet(Point point) {
        bullet = new BulletModel((ball.x + ((double) ball.ballRadius / 2)), (ball.y + ((double) ball.ballRadius / 2)));
        bullet.dx = ((point.x - (ball.x + (ball.ballRadius / 2))) / Math.sqrt(Math.pow((point.x - (ball.x + ((double) ball.ballRadius / 2))), 2) + Math.pow((point.y - (ball.y + ((double) ball.ballRadius / 2))), 2))) * BulletModel.bulletSpeed;
        if (bullet.y < point.y) {
            bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
        } else {
            bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
        }

        bullets.add(0, bullet);
        return bullet;
    }

    public static void updateBullet() {
        if (!GameController.bullets.isEmpty()) {
            for (BulletModel bullet : GameController.bullets) {
                if (bullet.bulletHealth > 0) {
                    bullet.x += bullet.dx;
                    bullet.y += bullet.dy;
                }
            }

        }
    }


    // ==================================================================================


    // creating and updating the enemies ================================================


    public static EnemyModel setTimerForEnemy() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                newEnemy();
            }

        };
        timer.scheduleAtFixedRate(task, 0, 5000);
        return enemy;
    }

    public static void newEnemy() {
        int enemyNumber = enemies.size();
        if (enemyNumber % 4 == 0) {
            enemy = new EnemyModel((double) GameFrame.width /2, GameFrame.y, 10);
        } else if (enemyNumber % 4 == 1) {
            enemy = new EnemyModel(GameFrame.x, (double) GameFrame.height /2, 10);
        } else if (enemyNumber % 4 == 2) {
            enemy = new EnemyModel((double) GameFrame.width /2, GameFrame.height, 10);
        } else {
            enemy = new EnemyModel(GameFrame.width, (double) GameFrame.height /2, 10);
        }
        enemies.add(0,enemy);
    }

    public static void updateEnemy() {
        if (!GameController.enemies.isEmpty()) {
            for (EnemyModel enemy : GameController.enemies) {
                if (enemy.enemyHealth > 0) {
                    enemy.dx = -((enemy.x - (ball.x + (ball.ballRadius / 2))) / Math.sqrt(Math.pow((enemy.x - (ball.x + ((double) ball.ballRadius / 2))), 2) + Math.pow((enemy.y - (ball.y + ((double) ball.ballRadius / 2))), 2))) * EnemyModel.enemySpeed;
                    if (ball.y < enemy.y) {
                        enemy.dy = -Math.sqrt(Math.pow(EnemyModel.enemySpeed, 2) - Math.pow(enemy.dx, 2));
                    } else {
                        enemy.dy = Math.sqrt(Math.pow(EnemyModel.enemySpeed, 2) - Math.pow(enemy.dx, 2));
                    }
                }
            }
        }
    }



    // =======================================================================================


    public static void checkCollisions() {
        Collision.checkBulletHitFrame();
        //Collision.checkBallCollisionToFrame();
    }

}
