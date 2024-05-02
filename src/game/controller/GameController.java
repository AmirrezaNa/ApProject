package game.controller;

import game.entity.BallModel;
import game.entity.BulletModel;
import game.entity.Collectible;
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
    static Collectible collectible;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    public static ArrayList<EnemyModel2> enemies2 = new ArrayList<>();
    public static ArrayList<Collectible> collectibles = new ArrayList<>();
    static int wave = 1;


    // creating and updating the ball ================================================

    public static BallModel newBall() {
        ball = new BallModel((double) (GameFrame.width / 2) - 20, (double) (GameFrame.height / 2) - 20);
        return ball;
    }

    public static void updateTheBall() {
        ball.x += ball.ax;
        ball.y += ball.ay;

        if (ball.ax != 0) {
            if (ball.ax > 0) {
                ball.ax -= 0.05;
            } else {
                ball.ax += 0.05;
            }
        }
        if (ball.ay != 0) {
            if (ball.ay > 0) {
                ball.ay -= 0.05;
            } else {
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

    // ========================== creating wave1 enemies ================================



    public static EnemyModel1 setTimerForEnemy1() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies2.size() + enemies1.size() <= 10) {
                    newEnemy1();
                }
                else {
                    timer.cancel();
                    wave++;
                    setTimerForEnemy1wave2();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 10000, 5000);

        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies1.size() + enemies2.size() <= 10) {
                    newEnemy2();
                }
                else {
                    timer.cancel();
                    setTimerForEnemy2wave2();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 12500, 5000);

        return enemy2;
    }



    // =========================== creating wave2 enemies =======================================

    public static EnemyModel1 setTimerForEnemy1wave2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies2.size() + enemies1.size() <= 25) {
                    GameFrame.countDown = false;
                    newEnemy1();
                }
                else {
                    timer.cancel();
                    wave++;
                    setTimerForEnemy1wave3();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2wave2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies2.size() + enemies1.size() <= 25) {
                    GameFrame.countDown = false;
                    newEnemy2();
                }
                else {
                    timer.cancel();
                    setTimerForEnemy2wave3();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return enemy2;
    }

    // =========================== creating wave3 enemies =======================================

    public static EnemyModel1 setTimerForEnemy1wave3() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies1.size() + enemies2.size() <= 45) {
                    GameFrame.countDown = false;
                    newEnemy1();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2wave3() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies1.size() + enemies2.size() <= 45) {
                    GameFrame.countDown = false;
                    newEnemy2();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return enemy2;
    }


    // =========================================================================================


    public static void newEnemy1() {
        if (enemies1.size() % 2 == 0) {
            enemy1 = new EnemyModel1(50, (double) GameFrame.height / 2);
            enemies1.add(0, enemy1);
        }
        else {
            enemy1 = new EnemyModel1((double) GameFrame.width /2, 50);
            enemy1.dash = true;
            enemies1.add(0, enemy1);
        }

    }

    public static void newEnemy2() {
        if (enemies2.size() % 2 == 0) {
            enemy2 = new EnemyModel2((double) GameFrame.width - 60, (double) GameFrame.height / 2);
            enemies2.add(0, enemy2);
        }
        else{
            enemy2 = new EnemyModel2( ((double) GameFrame.width /2), GameFrame.height - 60);
            enemies2.add(0, enemy2);
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
                    if (enemy.dash) {
                        enemy.x += 2 * (enemy.dx + enemy.ax);
                        enemy.y += 2 * (enemy.dy + enemy.ay);
                    }
                    if (!enemy.dash){
                        enemy.x += enemy.dx + enemy.ax;
                        enemy.y += enemy.dy + enemy.ay;
                    }

                    if (enemy.ax != 0) {
                        if (enemy.ax > 0) {
                            enemy.ax -= 0.05;
                        } else {
                            enemy.ax += 0.05;
                        }
                    }
                    if (enemy.ay != 0) {
                        if (enemy.ay > 0) {
                            enemy.ay -= 0.05;
                        } else {
                            enemy.ay += 0.05;
                        }
                    }
                    Rotation.enemy1Rotation();
//                    enemy.xAngles = new double[]{enemy.x - ((double) enemy.enemy1Size /2),
//                            (enemy.x + ((double) enemy.enemy1Size /2)),
//                            (enemy.x + ((double) enemy.enemy1Size /2)),
//                            enemy.x - ((double) enemy.enemy1Size /2)};
//                    enemy.yAngles = new double[]{enemy.x - ((double) enemy.enemy1Size /2),
//                            enemy.x - ((double) enemy.enemy1Size /2),
//                            enemy.x + ((double) enemy.enemy1Size /2),
//                            enemy.x + ((double) enemy.enemy1Size /2)};
                }
            }
        }

    }

    public static void updateEnemy2() {
        setDirectionForEnemy2();
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.enemyHealth > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs(enemy.x - ball.x), 2) + Math.pow(Math.abs(enemy.y - ball.y), 2));
                    if (epsilonDistance > 100) {
                        enemy.x += 2 * (enemy.dx + enemy.ax);
                        enemy.y += 2 * (enemy.dy + enemy.ay);
                    }
                    if (epsilonDistance <= 100) {
                        enemy.x += enemy.dx + enemy.ax;
                        enemy.y += enemy.dy + enemy.ay;
                    }

                    if (enemy.ax != 0) {
                        if (enemy.ax > 0) {
                            enemy.ax -= 0.05;
                        } else {
                            enemy.ax += 0.05;
                        }
                    }
                    if (enemy.ay != 0) {
                        if (enemy.ay > 0) {
                            enemy.ay -= 0.05;
                        } else {
                            enemy.ay += 0.05;
                        }
                    }
                    Rotation.enemy2Rotation();
//                    enemy.xAngles = new double[]{enemy.x - ((double) enemy.enemy2Size /2),
//                            (enemy.x + enemy.enemy2Size),
//                            (enemy.x + ((double) enemy.enemy2Size / 2))};
//                    enemy.yAngles = new double[]{enemy.y,
//                            enemy.y,
//                            (enemy.y + (enemy.enemy2Size))};
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
        Collision.checkCollisionBulletEnemy1();
        Collision.checkCollisionBulletEnemy2();
        Collision.checkCollisionBallCollectible();
        Collision.checkEnemy1CollisionToFrame();
        Collision.checkEnemy2CollisionToFrame();
    }





// ==================================================================================

// this part is for collectibles ====================================================

    public static void newCollectible(double x, double y) {
        collectible = new Collectible(x, y);
        collectibles.add(collectible);
        countDownCollectible(10, collectible);//collectible will disappear in 10 seconds
    }

    public static void countDownCollectible(int seconds, Collectible collectible1) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = seconds;

            @Override
            public void run() {
                if (remainingSeconds > 0) {
                    remainingSeconds--;
                } else {
                    collectible1.collectibleHealth = 0;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }


}
