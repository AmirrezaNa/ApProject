package controller.game.collisions;

import controller.game.GameController;
import controller.game.Impact;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;
import view.game.GamePanel;
import model.entity.BallModel;
import model.entity.BulletModel;
import view.game.GameFrame;

public class FrameCollision {



    public static void checkFrameCollisions() {
        if (!GamePanel.pause) {
            checkBulletHitFrame();
            checkBallCollisionToFrame();
            checkEnemy1CollisionToFrame();
            checkEnemy2CollisionToFrame();
        }
    }


    public static void checkBulletHitFrame() {
        if (!GameController.bullets.isEmpty()) {
            for (int i = 0; i < GameController.bullets.size(); i++) {
                if (GameController.bullets.get(i).bulletHealth > 0) {

                    if (GameController.bullets.get(i).x > GameFrame.width) {
                        GameController.bullets.get(i).dx = 0;
                        GameController.bullets.get(i).dy = 0;
                        GameController.bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.width += 20;
                            GameFrame.x += 5;
                            Impact.turnOnImpact(GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (GameController.bullets.get(i).y > GameFrame.height) {
                        GameController.bullets.get(i).dx = 0;
                        GameController.bullets.get(i).dy = 0;
                        GameController.bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.height += 20;
                            GameFrame.y += 5;
                            Impact.turnOnImpact(GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (GameController.bullets.get(i).x < 0) {
                        GameController.bullets.get(i).dx = 0;
                        GameController.bullets.get(i).dy = 0;
                        GameController.bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.x -= 20;
                            GameFrame.width += 10;
                            Impact.turnOnImpact(GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (GameController.bullets.get(i).y < 0) {
                        GameController.bullets.get(i).dx = 0;
                        GameController.bullets.get(i).dy = 0;
                        GameController.bullets.get(i).bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.y -= 20;
                            GameFrame.height += 10;
                            Impact.turnOnImpact(GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                    GameController.bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                        }

                    }

                }
            }
        }
    }

    public static void checkBallCollisionToFrame() {
        if (GameController.ball.x + BallModel.ballRadius >= GameFrame.width) {
            Impact.turnOnImpact(GameController.ball.x + BallModel.ballRadius,
                    GameController.ball.y,
                    GameController.ball.x + BallModel.ballRadius,
                    GameController.ball.y);
        }
        if (GameController.ball.x - BallModel.ballRadius <= 0) {
            Impact.turnOnImpact(GameController.ball.x - BallModel.ballRadius,
                    GameController.ball.y,
                    GameController.ball.x - BallModel.ballRadius,
                    GameController.ball.y);
        }
        if (GameController.ball.y + BallModel.ballRadius >= GameFrame.height) {
            Impact.turnOnImpact(GameController.ball.x,
                    GameController.ball.y + BallModel.ballRadius,
                    GameController.ball.x,
                    GameController.ball.y + BallModel.ballRadius);
        }
        if (GameController.ball.y - BallModel.ballRadius <= 0) {
            Impact.turnOnImpact(GameController.ball.x,
                    GameController.ball.y - BallModel.ballRadius,
                    GameController.ball.x,
                    GameController.ball.y - BallModel.ballRadius);
        }
    }


    public static void checkEnemy1CollisionToFrame() {
        if (!GameController.enemies1.isEmpty()) {
            for (int i = 0; i < GameController.enemies1.size(); i++) {
                if (GameController.enemies1.get(i).enemyHealth > 0) {

                    double xMin1 = GameController.enemies1.get(i).xAngles[0];
                    double xMax1 = GameController.enemies1.get(i).xAngles[0];
                    double yMin1 = GameController.enemies1.get(i).yAngles[0];
                    double yMax1 = GameController.enemies1.get(i).yAngles[0];
                    for (int j = 0; j < 4; j++) {
                        if (GameController.enemies1.get(i).xAngles[j] < xMin1) {
                            xMin1 = GameController.enemies1.get(i).xAngles[j];
                        }
                        if (GameController.enemies1.get(i).xAngles[j] > xMax1) {
                            xMax1 = GameController.enemies1.get(i).xAngles[j];
                        }
                        if (GameController.enemies1.get(i).yAngles[j] < yMin1) {
                            yMin1 = GameController.enemies1.get(i).yAngles[j];
                        }
                        if (GameController.enemies1.get(i).yAngles[j] > yMax1) {
                            yMax1 = GameController.enemies1.get(i).yAngles[j];
                        }
                    }
                    if (xMax1 >= GameFrame.width) {
                        Impact.turnOnImpact(GameController.enemies1.get(i).x + GameController.enemies1.get(i).enemy1Size,
                                GameController.enemies1.get(i).y,
                                GameController.enemies1.get(i).x + GameController.enemies1.get(i).enemy1Size,
                                GameController.enemies1.get(i).y);
                    }
                    if (xMin1 <= 0) {
                        Impact.turnOnImpact(GameController.enemies1.get(i).x - GameController.enemies1.get(i).enemy1Size,
                                GameController.enemies1.get(i).y,
                                GameController.enemies1.get(i).x - GameController.enemies1.get(i).enemy1Size,
                                GameController.enemies1.get(i).y);
                    }
                    if (yMax1 >= GameFrame.height) {
                        Impact.turnOnImpact(GameController.enemies1.get(i).x,
                                GameController.enemies1.get(i).y + GameController.enemies1.get(i).enemy1Size,
                                GameController.enemies1.get(i).x,
                                GameController.enemies1.get(i).y + GameController.enemies1.get(i).enemy1Size);
                    }
                    if (yMin1 <= 0) {
                        Impact.turnOnImpact(GameController.enemies1.get(i).x,
                                GameController.enemies1.get(i).y - GameController.enemies1.get(i).enemy1Size,
                                GameController.enemies1.get(i).x,
                                GameController.enemies1.get(i).y - GameController.enemies1.get(i).enemy1Size);
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
