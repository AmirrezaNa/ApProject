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

    GameController gameController = new GameController();
    Impact impact = new Impact();


    public void checkFrameCollisions() {
        if (!GamePanel.pause) {
            checkBulletHitFrame();
            checkBallCollisionToFrame();
            checkEnemy1CollisionToFrame();
            checkEnemy2CollisionToFrame();
        }
    }


    public  void checkBulletHitFrame() {
        if (!gameController.getBullets().isEmpty()) {
            for (int i = 0; i < gameController.getBullets().size(); i++) {
                BulletModel bullet = gameController.getBullets().get(i);
                if (bullet.getBulletHealth() > 0) {

                    if (bullet.getX() > GameFrame.width) {
                        bullet.setDx(0);
                        bullet.setDy(0);
                        bullet.setBulletHealth(0);
                        if (!GameFrame.countDown) {
                            GameFrame.width += 20;
                            GameFrame.x += 5;
                            impact.turnOnImpact(bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2));
                        }

                    } else if (bullet.getY() > GameFrame.height) {
                        bullet.setDx(0);
                        bullet.setDy(0);
                        bullet.setBulletHealth(0);
                        if (!GameFrame.countDown) {
                            GameFrame.height += 20;
                            GameFrame.y += 5;
                            impact.turnOnImpact(bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2));
                        }

                    } else if (bullet.getX() < 0) {
                        bullet.setDx(0);
                        bullet.setDy(0);
                        bullet.setBulletHealth(0);
                        if (!GameFrame.countDown) {
                            GameFrame.x -= 20;
                            GameFrame.width += 10;
                            impact.turnOnImpact(bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2));
                        }

                    } else if (bullet.getY() < 0) {
                        bullet.setDx(0);
                        bullet.setDy(0);
                        bullet.setBulletHealth(0);
                        if (!GameFrame.countDown) {
                            GameFrame.y -= 20;
                            GameFrame.height += 10;
                            impact.turnOnImpact(bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                    bullet.getY() + ((double) BulletModel.getBulletSize() / 2));
                        }

                    }

                }

                gameController.getBullets().set(i, bullet);
            }
        }
    }

    public void checkBallCollisionToFrame() {
        if (gameController.getBall() != null) {

            BallModel ball = gameController.getBall();

            if (ball.getX() + BallModel.getBallRadius() >= GameFrame.width) {
                impact.turnOnImpact(ball.getX() + BallModel.getBallRadius(),
                        ball.getY(),
                        ball.getX() + BallModel.getBallRadius(),
                        ball.getY());
            }
            if (ball.getX() - BallModel.getBallRadius() <= 0) {
                impact.turnOnImpact(ball.getX() - BallModel.getBallRadius(),
                        ball.getY(),
                        ball.getX() - BallModel.getBallRadius(),
                        ball.getY());
            }
            if (ball.getY() + BallModel.getBallRadius() >= GameFrame.height) {
                impact.turnOnImpact(ball.getX(),
                        ball.getY() + BallModel.getBallRadius(),
                        ball.getX(),
                        ball.getY() + BallModel.getBallRadius());
            }
            if (ball.getY() - BallModel.getBallRadius() <= 0) {
                impact.turnOnImpact(ball.getX(),
                        ball.getY() - BallModel.getBallRadius(),
                        ball.getX(),
                        ball.getY() - BallModel.getBallRadius());
            }
        }
    }


    public void checkEnemy1CollisionToFrame() {
        if (!gameController.getEnemies1().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies1().size(); i++) {
                EnemyModel1 enemy1 = gameController.getEnemies1().get(i);
                if (enemy1.getEnemyHealth() > 0) {

                    double xMin1 = enemy1.getxAngles()[0];
                    double xMax1 = enemy1.getxAngles()[0];
                    double yMin1 = enemy1.getyAngles()[0];
                    double yMax1 = enemy1.getyAngles()[0];
                    for (int j = 0; j < 4; j++) {
                        if (enemy1.getxAngles()[j] < xMin1) {
                            xMin1 = enemy1.getxAngles()[j];
                        }
                        if (enemy1.getxAngles()[j] > xMax1) {
                            xMax1 = enemy1.getxAngles()[j];
                        }
                        if (enemy1.getyAngles()[j] < yMin1) {
                            yMin1 = enemy1.getyAngles()[j];
                        }
                        if (enemy1.getyAngles()[j] > yMax1) {
                            yMax1 = enemy1.getyAngles()[j];
                        }
                    }
                    if (xMax1 >= GameFrame.width) {
                        impact.turnOnImpact(enemy1.getX() + EnemyModel1.getEnemy1Size(),
                                enemy1.getY(),
                                enemy1.getX() + EnemyModel1.getEnemy1Size(),
                                enemy1.getY());
                    }
                    if (xMin1 <= 0) {
                        impact.turnOnImpact(enemy1.getX() - EnemyModel1.getEnemy1Size(),
                                enemy1.getY(),
                                enemy1.getX() - EnemyModel1.getEnemy1Size(),
                                enemy1.getY());
                    }
                    if (yMax1 >= GameFrame.height) {
                        impact.turnOnImpact(enemy1.getX(),
                                enemy1.getY() + EnemyModel1.getEnemy1Size(),
                                enemy1.getX(),
                                enemy1.getY() + EnemyModel1.getEnemy1Size());
                    }
                    if (yMin1 <= 0) {
                        impact.turnOnImpact(enemy1.getX(),
                                enemy1.getY() - EnemyModel1.getEnemy1Size(),
                                enemy1.getX(),
                                enemy1.getY() - EnemyModel1.getEnemy1Size());
                    }
                }
            }
        }
    }


    public void checkEnemy2CollisionToFrame() {
        if (!gameController.getEnemies2().isEmpty()) {
            for (int k = 0; k < gameController.getEnemies2().size(); k++) {
                EnemyModel2 enemy2 = gameController.getEnemies2().get(k);
                if (enemy2.getEnemyHealth() > 0) {

                    double xMin2 = enemy2.getxAngles()[0];
                    double xMax2 = enemy2.getxAngles()[0];
                    double yMin2 = enemy2.getyAngles()[0];
                    double yMax2 = enemy2.getyAngles()[0];
                    for (int i = 0; i < 3; i++) {
                        if (enemy2.getxAngles()[i] < xMin2) {
                            xMin2 = enemy2.getxAngles()[i];
                        }
                        if (enemy2.getxAngles()[i] > xMax2) {
                            xMax2 = enemy2.getxAngles()[i];
                        }
                        if (enemy2.getyAngles()[i] < yMin2) {
                            yMin2 = enemy2.getyAngles()[i];
                        }
                        if (enemy2.getyAngles()[i] > yMax2) {
                            yMax2 = enemy2.getyAngles()[i];
                        }
                    }
                    if (xMax2 >= GameFrame.width) {
                        impact.turnOnImpact(enemy2.getX() + EnemyModel2.getEnemy2Size(),
                                enemy2.getY(),
                                enemy2.getX() + EnemyModel2.getEnemy2Size(),
                                enemy2.getY());
                    }
                    if (xMin2 <= 0) {
                        impact.turnOnImpact(enemy2.getX() - EnemyModel2.getEnemy2Size(),
                                enemy2.getY(),
                                enemy2.getX() - EnemyModel2.getEnemy2Size(),
                                enemy2.getY());
                    }
                    if (yMax2 >= GameFrame.height) {
                        impact.turnOnImpact(enemy2.getX(),
                                enemy2.getY() + EnemyModel2.getEnemy2Size(),
                                enemy2.getX(),
                                enemy2.getY() + EnemyModel2.getEnemy2Size());
                    }
                    if (yMin2 <= 0) {
                        impact.turnOnImpact(enemy2.getX(),
                                enemy2.getY() - EnemyModel2.getEnemy2Size(),
                                enemy2.getX(),
                                enemy2.getY() - EnemyModel2.getEnemy2Size());
                    }
                }
            }
        }
    }


}
