package game.controller;

import game.entity.BallModel;
import game.entity.BulletModel;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;
import game.frame.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    static BallModel ball;
    static BulletModel bullet;
    static EnemyModel1 enemy1;
    static EnemyModel2 enemy2;
    static int enemyNumber;
    static int wave2Delay = 30;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    public static ArrayList<EnemyModel2> enemies2 = new ArrayList<>();


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


    public static EnemyModel1 setTimerForEnemy1() {   // this method creates an enemy every 5 seconds

        boolean enemyPermission = true;


        if (enemyPermission) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    newEnemy1();
                }

            };
            timer.scheduleAtFixedRate(task, 0, 10000);

        }
        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2() {   // this method creates an enemy every 5 seconds

        boolean enemyPermission = true;


        if (enemyPermission) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    newEnemy2();
                }

            };
            timer.scheduleAtFixedRate(task, 5000, 10000);

        }
        return enemy2;
    }

    public static void newEnemy1() {
        enemy1 = new EnemyModel1(20, (double) GameFrame.height / 2, 10);
        enemies1.add(0, enemy1);
    }

    public static void newEnemy2() {
            enemy2 = new EnemyModel2((double) GameFrame.width - 10, (double) GameFrame.height / 2, 10);
            enemies2.add(0, enemy2);
    }


    public static void setDirectionForEnemy1() {
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy : GameController.enemies1) {
                if (enemy.enemyHealth > 0) {
                    enemy.dx = -((enemy.x - (ball.x + (ball.ballRadius / 2))) / Math.sqrt(Math.pow((enemy.x - (ball.x + ((double) ball.ballRadius / 2))), 2) + Math.pow((enemy.y - (ball.y + ((double) ball.ballRadius / 2))), 2))) * EnemyModel1.enemySpeed;
                    if (ball.y < enemy.y) {
                        enemy.dy = -Math.sqrt(Math.pow(EnemyModel1.enemySpeed, 2) - Math.pow(enemy.dx, 2));
                    } else {
                        enemy.dy = Math.sqrt(Math.pow(EnemyModel1.enemySpeed, 2) - Math.pow(enemy.dx, 2));
                    }
                }
            }
        }
    }

    public static void setDirectionForEnemy2() {
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.enemyHealth > 0) {
                    enemy.dx = -((enemy.x - (ball.x + (ball.ballRadius / 2))) / Math.sqrt(Math.pow((enemy.x - (ball.x + ((double) ball.ballRadius / 2))), 2) + Math.pow((enemy.y - (ball.y + ((double) ball.ballRadius / 2))), 2))) * EnemyModel2.enemySpeed;
                    if (ball.y < enemy.y) {
                        enemy.dy = -Math.sqrt(Math.pow(EnemyModel2.enemySpeed, 2) - Math.pow(enemy.dx, 2));
                    } else {
                        enemy.dy = Math.sqrt(Math.pow(EnemyModel2.enemySpeed, 2) - Math.pow(enemy.dx, 2));
                    }
                }
            }
        }
    }


    public static void updateEnemy1() {
        setDirectionForEnemy1();
        //Rotation.enemy1Rotation();
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy : GameController.enemies1) {
                if (enemy.enemyHealth > 0) {
                    enemy.x += enemy.dx;
                    enemy.y += enemy.dy;
                    //enemy1.xAngles = new int[]{(int) enemy1.x, (int) (enemy1.x + enemy1.enemy1Size), (int) (enemy1.x + enemy1.enemy1Size), (int) enemy1.x};
                    //enemy1.yAngles = new int[]{(int) enemy1.y, (int) (enemy1.y), (int) (enemy1.y + enemy1.enemy1Size), (int) enemy1.y + enemy1.enemy1Size};
                }
            }
        }

    }

    public static void updateEnemy2() {
        setDirectionForEnemy2();
        //Rotation.enemy2Rotation();
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.enemyHealth > 0) {
                    enemy.x += enemy.dx;
                    enemy.y += enemy.dy;
                    //enemy2.xAngles = new int[]{(int) enemy2.x, (int) (enemy2.x + enemy2.enemy2Size), (int) (enemy2.x + (enemy2.enemy2Size / 2))};
                    //enemy2.yAngles = new int[]{(int) enemy2.y, (int) enemy2.y, (int) (enemy2.y + (enemy2.enemy2Size))};
                }
            }
        }

    }


    // =======================================================================================


    public static void checkCollisions() {
        Collision.checkBulletHitFrame();
        Collision.checkBallCollisionToFrame();
    }

}
