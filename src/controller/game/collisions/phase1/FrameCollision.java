package controller.game.collisions.phase1;

import controller.game.GameController;
import controller.game.Impact;
import view.gameLoop.phase1.GamePanel;
import model.entity.BallModel;
import model.entity.BulletModel;
import view.gameLoop.phase1.GameFrame;

import static controller.game.GameController.*;

public class FrameCollision {


    public static void checkFrameCollisions() {

        checkBulletHitFrame();
        checkBallCollisionToFrame();
        checkEnemy1CollisionToFrame();
        checkEnemy2CollisionToFrame();

    }


    public static void checkBulletHitFrame() {
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).bulletHealth > 0) {

                    if (bullets.get(i).x > GameFrame.width) {
                        bullets.get(i).dx = 0;
                        bullets.get(i).dy = 0;
                        bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.width += 20;
                            GameFrame.x += 5;
                            Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (bullets.get(i).y > GameFrame.height) {
                        bullets.get(i).dx = 0;
                        bullets.get(i).dy = 0;
                        bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.height += 20;
                            GameFrame.y += 5;
                            Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (bullets.get(i).x < 0) {
                        bullets.get(i).dx = 0;
                        bullets.get(i).dy = 0;
                        bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.x -= 20;
                            GameFrame.width += 10;
                            Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (bullets.get(i).y < 0) {
                        bullets.get(i).dx = 0;
                        bullets.get(i).dy = 0;
                        bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.y -= 20;
                            GameFrame.height += 10;
                            Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    }

                }
            }
        }
    }

    public static void checkBallCollisionToFrame() {
        if (ball.x + BallModel.ballRadius >= GameFrame.width) {
            Impact.turnOnImpact(ball.x + BallModel.ballRadius,
                    ball.y,
                    ball.x + BallModel.ballRadius,
                    ball.y);
        }
        if (ball.x - BallModel.ballRadius <= 0) {
            Impact.turnOnImpact(ball.x - BallModel.ballRadius,
                    ball.y,
                    ball.x - BallModel.ballRadius,
                    ball.y);
        }
        if (ball.y + BallModel.ballRadius >= GameFrame.height) {
            Impact.turnOnImpact(ball.x,
                    ball.y + BallModel.ballRadius,
                    ball.x,
                    ball.y + BallModel.ballRadius);
        }
        if (ball.y - BallModel.ballRadius <= 0) {
            Impact.turnOnImpact(ball.x,
                    ball.y - BallModel.ballRadius,
                    ball.x,
                    ball.y - BallModel.ballRadius);
        }
    }


    public static void checkEnemy1CollisionToFrame() {
        if (!enemies1.isEmpty()) {
            for (int i = 0; i < enemies1.size(); i++) {
                if (enemies1.get(i).enemyHealth > 0) {

                    double xMin1 = enemies1.get(i).xAngles[0];
                    double xMax1 = enemies1.get(i).xAngles[0];
                    double yMin1 = enemies1.get(i).yAngles[0];
                    double yMax1 = enemies1.get(i).yAngles[0];
                    for (int j = 0; j < 4; j++) {
                        if (enemies1.get(i).xAngles[j] < xMin1) {
                            xMin1 = enemies1.get(i).xAngles[j];
                        }
                        if (enemies1.get(i).xAngles[j] > xMax1) {
                            xMax1 = enemies1.get(i).xAngles[j];
                        }
                        if (enemies1.get(i).yAngles[j] < yMin1) {
                            yMin1 = enemies1.get(i).yAngles[j];
                        }
                        if (enemies1.get(i).yAngles[j] > yMax1) {
                            yMax1 = enemies1.get(i).yAngles[j];
                        }
                    }
                    if (xMax1 >= GameFrame.width) {
                        Impact.turnOnImpact(enemies1.get(i).x + enemies1.get(i).enemy1Size,
                                enemies1.get(i).y,
                                enemies1.get(i).x + enemies1.get(i).enemy1Size,
                                enemies1.get(i).y);
                    }
                    if (xMin1 <= 0) {
                        Impact.turnOnImpact(enemies1.get(i).x - enemies1.get(i).enemy1Size,
                                enemies1.get(i).y,
                                enemies1.get(i).x - enemies1.get(i).enemy1Size,
                                enemies1.get(i).y);
                    }
                    if (yMax1 >= GameFrame.height) {
                        Impact.turnOnImpact(enemies1.get(i).x,
                                enemies1.get(i).y + enemies1.get(i).enemy1Size,
                                enemies1.get(i).x,
                                enemies1.get(i).y + enemies1.get(i).enemy1Size);
                    }
                    if (yMin1 <= 0) {
                        Impact.turnOnImpact(enemies1.get(i).x,
                                enemies1.get(i).y - enemies1.get(i).enemy1Size,
                                enemies1.get(i).x,
                                enemies1.get(i).y - enemies1.get(i).enemy1Size);
                    }
                }
            }
        }
    }


    public static void checkEnemy2CollisionToFrame() {
        if (!GameController.enemies2.isEmpty()) {
            for (int k = 0; k < GameController.enemies2.size(); k++) {
                if (GameController.enemies2.get(k).enemyHealth > 0) {

                    double xMin2 = GameController.enemies2.get(k).xAngles[0];
                    double xMax2 = GameController.enemies2.get(k).xAngles[0];
                    double yMin2 = GameController.enemies2.get(k).yAngles[0];
                    double yMax2 = GameController.enemies2.get(k).yAngles[0];
                    for (int i = 0; i < 3; i++) {
                        if (GameController.enemies2.get(k).xAngles[i] < xMin2) {
                            xMin2 = GameController.enemies2.get(k).xAngles[i];
                        }
                        if (GameController.enemies2.get(k).xAngles[i] > xMax2) {
                            xMax2 = GameController.enemies2.get(k).xAngles[i];
                        }
                        if (GameController.enemies2.get(k).yAngles[i] < yMin2) {
                            yMin2 = GameController.enemies2.get(k).yAngles[i];
                        }
                        if (GameController.enemies2.get(k).yAngles[i] > yMax2) {
                            yMax2 = GameController.enemies2.get(k).yAngles[i];
                        }
                    }
                    if (xMax2 >= GameFrame.width) {
                        Impact.turnOnImpact(GameController.enemies2.get(k).x + GameController.enemies2.get(k).enemy2Size,
                                GameController.enemies2.get(k).y,
                                GameController.enemies2.get(k).x + GameController.enemies2.get(k).enemy2Size,
                                GameController.enemies2.get(k).y);
                    }
                    if (xMin2 <= 0) {
                        Impact.turnOnImpact(GameController.enemies2.get(k).x - GameController.enemies2.get(k).enemy2Size,
                                GameController.enemies2.get(k).y,
                                GameController.enemies2.get(k).x - GameController.enemies2.get(k).enemy2Size,
                                GameController.enemies2.get(k).y);
                    }
                    if (yMax2 >= GameFrame.height) {
                        Impact.turnOnImpact(GameController.enemies2.get(k).x,
                                GameController.enemies2.get(k).y + GameController.enemies2.get(k).enemy2Size,
                                GameController.enemies2.get(k).x,
                                GameController.enemies2.get(k).y + GameController.enemies2.get(k).enemy2Size);
                    }
                    if (yMin2 <= 0) {
                        Impact.turnOnImpact(GameController.enemies2.get(k).x,
                                GameController.enemies2.get(k).y - GameController.enemies2.get(k).enemy2Size,
                                GameController.enemies2.get(k).x,
                                GameController.enemies2.get(k).y - GameController.enemies2.get(k).enemy2Size);
                    }
                }
            }
        }
    }


}
