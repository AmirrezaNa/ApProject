package game.controller;

import game.entity.*;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;
import game.frame.GameFrame;
import game.frame.GamePanel;
import startPage.EnterNamePage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    public static BallModel ball;
    static BulletModel bullet;
    static EnemyModel1 enemy1;
    static EnemyModel2 enemy2;
    static Collectible collectible;
    static BallDirection ballDirection;
    static BallAngle ballAngle;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();
    public static ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    public static ArrayList<EnemyModel2> enemies2 = new ArrayList<>();
    public static ArrayList<Collectible> collectibles = new ArrayList<>();
    public static int wave = 1;
    public static int Banish = 0;
    public static int Empower = 0;
    public static boolean bulletAres;

    // ===============================================================================


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

    public static BallDirection createBallDirection() {
        ballDirection = new BallDirection(ball.x, ball.y);
        return ballDirection;
    }

    public static BallAngle createBallAngle() {
        ballAngle = new BallAngle(ball.x, ball.y);
        return ballAngle;
    }

    public static void updateBallAngle() {
        if (ballAngle.angleExists) {

            double x1 = ball.x;
            double y1 = ball.y;
            double x2 = MouseInputListener.x;
            double y2 = MouseInputListener.y;
            double deltaX = x2 - x1;
            double deltaY = y2 - y1;

            // Calculate the angle in radians
            ballAngle.angle = Math.atan2(deltaY, deltaX);
            ballAngle.x = ball.x + (BallModel.ballRadius * Math.cos(ballAngle.angle));
            ballAngle.y = ball.y + (BallModel.ballRadius * Math.sin(ballAngle.angle));
        }
    }

    public static void updateBallDirection() {

        double x1 = ball.x;
        double y1 = ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        ballDirection.angle = Math.atan2(deltaY, deltaX);
        ballDirection.x = ball.x + (10 * Math.cos(ballDirection.angle));
        ballDirection.y = ball.y + (10 * Math.sin(ballDirection.angle));
    }

    public static void turnOnWritOfProteus() {
        if (EnterNamePage.player.writOfProteus) {
            if (!GamePanel.pause) {
                ballAngle.angleExists = true;
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            EnterNamePage.player.writOfProteus = false;
                        } else {
                            ballAngle.angleExists = false;
                            EnterNamePage.player.writOfProteus = true;
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }


    // ==================================================================================



    // ==================================================================================


    public static void turnOnWritOfAceso() {
        if (EnterNamePage.player.writOfAceso) {
            if (!GamePanel.pause) {
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            ball.HP++;
                            EnterNamePage.player.writOfProteus = false;
                        } else {
                            EnterNamePage.player.writOfAceso = true;
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }



    // ==================================================================================


    // creating and updating the bullets ================================================

    public static boolean empowerBullet;


    public static BulletModel newBullet(Point point) {
        if (!GamePanel.pause) {
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

    public static void updateBullet() {
        if (Empower == 1) {
            empowerBullet();
            Empower--;
        }
        if (!GameController.bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).bulletHealth > 0) {
                    bullets.get(i).x += bullets.get(i).dx;
                    bullets.get(i).y += bullets.get(i).dy;
                }
            }
        }
    }


    public static void empowerBullet() {
        if (!GamePanel.pause) {
            empowerBullet = true;
            Timer timer = new Timer();
            int[] countDownEmpower = {10};
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (countDownEmpower[0] > 0) {
                        countDownEmpower[0]--;
                        System.out.println("hi");
                    } else {
                        empowerBullet = false;
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }

    public static void turnOnWritOfAres() {
        if (EnterNamePage.player.writOfAres) {
            if (!GamePanel.pause) {
                bulletAres = true;
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            EnterNamePage.player.writOfAres = false;
                        } else {
                            bulletAres = false;
                            EnterNamePage.player.writOfProteus = true;
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
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
                } else {
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
                } else {
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
                } else {
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
                } else {
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
        if (!GamePanel.pause) {

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
        if (!GamePanel.pause) {

            if (enemies2.size() % 2 == 0) {
                enemy2 = new EnemyModel2((double) GameFrame.width - 60, (double) GameFrame.height / 2);
                enemies2.add(0, enemy2);
            } else {
                enemy2 = new EnemyModel2(((double) GameFrame.width / 2), GameFrame.height - 60);
                enemies2.add(0, enemy2);
            }

        }
    }


    public static void setDirectionForEnemy1() {
        if (!GameController.enemies1.isEmpty()) {
            for (int i = 0; i < enemies1.size(); i++) {
                if (enemies1.get(i).enemyHealth > 0) {
                    enemies1.get(i).dx = -((enemies1.get(i).x - ball.x) / Math.sqrt(Math.pow((enemies1.get(i).x - ball.x), 2) + Math.pow((enemies1.get(i).y - ball.y), 2))) * EnemyModel1.enemySpeed;
                    if (ball.y < enemies1.get(i).y) {
                        enemies1.get(i).dy = -Math.sqrt(Math.pow(EnemyModel1.enemySpeed, 2) - Math.pow(enemies1.get(i).dx, 2));
                    } else {
                        enemies1.get(i).dy = Math.sqrt(Math.pow(EnemyModel1.enemySpeed, 2) - Math.pow(enemies1.get(i).dx, 2));
                    }
                }
            }
        }
    }

    public static void setDirectionForEnemy2() {
        if (!GameController.enemies2.isEmpty()) {
            for (int i = 0; i < enemies2.size(); i++) {
                if (enemies2.get(i).enemyHealth > 0) {
                    enemies2.get(i).dx = -((enemies2.get(i).x - ball.x) / Math.sqrt(Math.pow((enemies2.get(i).x - ball.x), 2) + Math.pow((enemies2.get(i).y - ball.y), 2))) * EnemyModel2.enemySpeed;
                    if (ball.y < enemies2.get(i).y) {
                        enemies2.get(i).dy = -Math.sqrt(Math.pow(EnemyModel2.enemySpeed, 2) - Math.pow(enemies2.get(i).dx, 2));
                    } else {
                        enemies2.get(i).dy = Math.sqrt(Math.pow(EnemyModel2.enemySpeed, 2) - Math.pow(enemies2.get(i).dx, 2));
                    }
                }
            }
        }
    }


    public static void updateEnemy1() {
        setDirectionForEnemy1();
        if (!GameController.enemies1.isEmpty()) {
            for (int i = 0; i < enemies1.size(); i++) {
                if (enemies1.get(i).enemyHealth > 0) {
                    if (enemies1.get(i).dash) {
                        enemies1.get(i).x += 2 * (enemies1.get(i).dx + enemies1.get(i).ax);
                        enemies1.get(i).y += 2 * (enemies1.get(i).dy + enemies1.get(i).ay);
                    }
                    if (!enemies1.get(i).dash) {
                        enemies1.get(i).x += enemies1.get(i).dx + enemies1.get(i).ax;
                        enemies1.get(i).y += enemies1.get(i).dy + enemies1.get(i).ay;
                    }

                    if (enemies1.get(i).ax != 0) {
                        if (enemies1.get(i).ax > 0) {
                            enemies1.get(i).ax -= 0.05;
                        } else {
                            enemies1.get(i).ax += 0.05;
                        }
                    }
                    if (enemies1.get(i).ay != 0) {
                        if (enemies1.get(i).ay > 0) {
                            enemies1.get(i).ay -= 0.05;
                        } else {
                            enemies1.get(i).ay += 0.05;
                        }
                    }
                    Rotation.enemy1Rotation();
                }
            }
        }

    }

    public static void updateEnemy2() {
        setDirectionForEnemy2();
        if (!GameController.enemies2.isEmpty()) {
            for (int i = 0; i < enemies2.size(); i++) {
                if (enemies2.get(i).enemyHealth > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs(enemies2.get(i).x - ball.x), 2) + Math.pow(Math.abs(enemies2.get(i).y - ball.y), 2));
                    if (epsilonDistance > 100) {
                        enemies2.get(i).x += 2 * (enemies2.get(i).dx + enemies2.get(i).ax);
                        enemies2.get(i).y += 2 * (enemies2.get(i).dy + enemies2.get(i).ay);
                    }
                    if (epsilonDistance <= 100) {
                        enemies2.get(i).x += enemies2.get(i).dx + enemies2.get(i).ax;
                        enemies2.get(i).y += enemies2.get(i).dy + enemies2.get(i).ay;
                    }

                    if (enemies2.get(i).ax != 0) {
                        if (enemies2.get(i).ax > 0) {
                            enemies2.get(i).ax -= 0.05;
                        } else {
                            enemies2.get(i).ax += 0.05;
                        }
                    }
                    if (enemies2.get(i).ay != 0) {
                        if (enemies2.get(i).ay > 0) {
                            enemies2.get(i).ay -= 0.05;
                        } else {
                            enemies2.get(i).ay += 0.05;
                        }
                    }
                    Rotation.enemy2Rotation();
                }
            }
        }

    }


    // =======================================================================================


    public static void checkCollisions() {
        if (!GamePanel.pause) {
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
            Collision.checkCollisionBallAngleEnemy1();
            Collision.checkCollisionBallAngleEnemy2();
        }

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

    // =============================================================================

    // restarting the game

    public static void restartGame() {
        bullets.clear();
        enemies2.clear();
        enemies1.clear();
        bullets.clear();
        collectibles.clear();
        ball.HP = 100;
    }


}
