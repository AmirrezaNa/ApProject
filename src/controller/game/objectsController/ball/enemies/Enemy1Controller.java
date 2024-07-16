package controller.game.objectsController.ball.enemies;

import controller.game.GameController;
import controller.game.Rotation;
import model.entity.BallModel;
import model.entity.enemy.EnemyModel1;

import java.util.ArrayList;

public class Enemy1Controller {
    GameController gameController = new GameController();
    Rotation rotation = new Rotation();


    public void setDirectionForEnemy1() {
        if (!gameController.getEnemies1().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies1().size(); i++) {
                BallModel ball = gameController.getBall();
                EnemyModel1 enemy1 = gameController.getEnemies1().get(i);
                if (enemy1.getEnemyHealth() > 0) {
                    enemy1.setDx(-((enemy1.getX() - ball.getX()) / Math.sqrt(Math.pow(enemy1.getX() - ball.getX(), 2) + Math.pow(enemy1.getY() - ball.getY(), 2))) * EnemyModel1.getEnemySpeed());
                    if (ball.getY() < enemy1.getY()) {
                        enemy1.setDy(-Math.sqrt(Math.pow(EnemyModel1.getEnemySpeed(), 2) - Math.pow(enemy1.getDx(), 2)));
                    } else {
                        enemy1.setDy(Math.sqrt(Math.pow(EnemyModel1.getEnemySpeed(), 2) - Math.pow(enemy1.getDx(), 2)));
                    }
                }

                gameController.setBall(ball);
                gameController.getEnemies1().set(i, enemy1);
            }
        }
    }


    public void updateEnemy1() {
        setDirectionForEnemy1();
        if (!gameController.getEnemies1().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies1().size(); i++) {
                EnemyModel1 enemy1 = gameController.getEnemies1().get(i);
                if (enemy1.getEnemyHealth() > 0) {
                    if (enemy1.isDash()) {
                        enemy1.setX(enemy1.getX() + (2*(enemy1.getDx() + enemy1.getAx())));
                        enemy1.setY(enemy1.getY() + (2*(enemy1.getDy() + enemy1.getAy())));
                    }
                    if (!enemy1.isDash()) {
                        enemy1.setX(enemy1.getX() + (enemy1.getDx() + enemy1.getAx()));
                        enemy1.setY(enemy1.getY() + (enemy1.getDy() + enemy1.getAy()));
                    }

                    if (enemy1.getAx() != 0) {
                        if (enemy1.getAx() > 0) {
                            enemy1.setAx(enemy1.getAx() - 0.05);
                        } else {
                            enemy1.setAx(enemy1.getAx() + 0.05);
                        }
                    }
                    if (enemy1.getAy() != 0) {
                        if (enemy1.getAy() > 0) {
                            enemy1.setAy(enemy1.getAy() - 0.05);
                        } else {
                            enemy1.setAy(enemy1.getAy() + 0.05);
                        }
                    }
                    rotation.enemy1Rotation();
                }

                gameController.getEnemies1().set(i, enemy1);
            }
        }

    }
}
