package game.controller;

import game.entity.BallModel;
import game.entity.BulletModel;
import game.entity.EnemyModel1;
import game.entity.EnemyModel2;
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
                    newEnemy();
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
                    newEnemy();
                }

            };
            timer.scheduleAtFixedRate(task, 0, 10000);

        }
        return enemy2;
    }

    public static void newEnemy() {
        enemyNumber = enemies1.size();
        if (enemyNumber % 2 == 0) {
            if (enemyNumber % 4 == 0) {
                enemy1 = new EnemyModel1((double) GameFrame.width /2, (double) GameFrame.y /2, 10);
            } else if (enemyNumber % 4 == 1) {
                enemy1 = new EnemyModel1((double) GameFrame.x /2, (double) GameFrame.height /2, 10);
            } else if (enemyNumber % 4 == 2) {
                enemy1 = new EnemyModel1((double) GameFrame.width /2, (double) GameFrame.height /2, 10);
            } else {
                enemy1 = new EnemyModel1((double) GameFrame.width /2, (double) GameFrame.height /2, 10);
            }
            enemies1.add(0,enemy1);
        }

        if (enemyNumber % 2 == 1) {
            if (enemyNumber % 4 == 0) {
                enemy2 = new EnemyModel2((double) GameFrame.width /2, (double) GameFrame.y /2, 10);
            } else if (enemyNumber % 4 == 1) {
                enemy2 = new EnemyModel2((double) GameFrame.x /2, (double) GameFrame.height /2, 10);
            } else if (enemyNumber % 4 == 2) {
                enemy2 = new EnemyModel2((double) GameFrame.width /2, (double) GameFrame.height /2, 10);
            } else {
                enemy2 = new EnemyModel2((double) GameFrame.width /2, (double) GameFrame.height /2, 10);
            }
            enemies2.add(0,enemy2);
        }


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
                    enemy.x += enemy.dx;
                    enemy.y += enemy.dy;
                }
            }
        }

    }

    public static void updateEnemy2() {
        setDirectionForEnemy2();
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.enemyHealth > 0) {
                    enemy.x += enemy.dx;
                    enemy.y += enemy.dy;
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
