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
        ball = new BallModel((double) (GameFrame.width /2) - 20, (double) (GameFrame.height /2) - 20);
        return ball;
    }

    public static void updateTheBall() {
        ball.x += ball.ax;
        ball.y += ball.ay;

        if (ball.ax != 0) {
            if (ball.ax > 0) {
                ball.ax -= 0.05;
            }
            else {
                ball.ax += 0.05;
            }
        }
        if (ball.ay != 0) {
            if (ball.ay > 0) {
                ball.ay -= 0.05;
            }
            else {
                ball.ay += 0.05;
            }
        }
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
            timer.scheduleAtFixedRate(task, 10000, 5000);

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
            timer.scheduleAtFixedRate(task, 12500, 5000);

        }
        return enemy2;
    }

    public static void newEnemy1() {
        enemy1 = new EnemyModel1(50, (double) GameFrame.height / 2, 10);
        enemies1.add(0, enemy1);
    }

    public static void newEnemy2() {
            enemy2 = new EnemyModel2((double) GameFrame.width - 60, (double) GameFrame.height / 2, 10);
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
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy : GameController.enemies1) {
                if (enemy.enemyHealth > 0) {
                    enemy.x += enemy.dx + enemy.ax;
                    enemy.y += enemy.dy + enemy.ay;
                    if (enemy.ax != 0) {
                        if (enemy.ax > 0) {
                            enemy.ax -= 0.05;
                        }
                        else {
                            enemy.ax += 0.05;
                        }
                    }
                    if (enemy.ay != 0) {
                        if (enemy.ay > 0) {
                            enemy.ay -= 0.05;
                        }
                        else {
                            enemy.ay += 0.05;
                        }
                    }
                    enemy.xAngles = new double[]{enemy.x, (enemy.x + enemy.enemy1Size), (enemy.x + enemy.enemy1Size), enemy.x};
                    enemy.yAngles = new double[]{enemy.y, (enemy.y), (enemy.y + enemy.enemy1Size), enemy.y + enemy.enemy1Size};
                }
            }
        }

    }

    public static void updateEnemy2() {
        setDirectionForEnemy2();
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.enemyHealth > 0) {

                    enemy.x += enemy.dx + enemy.ax;
                    enemy.y += enemy.dy + enemy.ay;
                    if (enemy.ax != 0) {
                        if (enemy.ax > 0) {
                            enemy.ax -= 0.05;
                        }
                        else {
                            enemy.ax += 0.05;
                        }
                    }
                    if (enemy.ay != 0) {
                        if (enemy.ay > 0) {
                            enemy.ay -= 0.05;
                        }
                        else {
                            enemy.ay += 0.05;
                        }
                    }
                    enemy.xAngles = new double[]{enemy.x, (enemy.x + enemy.enemy2Size), (enemy.x + ((double) enemy.enemy2Size / 2))};
                    enemy.yAngles = new double[]{enemy.y, enemy.y, (enemy.y + (enemy.enemy2Size))};
                }
            }
        }

    }


    // =======================================================================================


    public static void checkCollisions() {
        Collision.checkBulletHitFrame();
        Collision.checkBallCollisionToFrame();
        Collision.checkCollisionEnemy1Enemy2();
        Collision.checkCollisionEnemy1Enemy1();
        Collision.checkCollisionEnemy2Enemy2();
        Collision.checkCollisionBallEnemy1();
        Collision.checkCollisionBallEnemy2();
    }

}
