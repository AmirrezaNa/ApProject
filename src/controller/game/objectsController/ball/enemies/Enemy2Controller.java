package controller.game.objectsController.ball.enemies;

import controller.game.GameController;
import controller.game.Rotation;
import model.entity.BallModel;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;

import java.util.ArrayList;

public class Enemy2Controller {

    GameController gameController = new GameController();
    Rotation rotation = new Rotation();

    public void setDirectionForEnemy2() {
        if (!gameController.getEnemies2().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies2().size(); i++) {
                BallModel ball = gameController.getBall();
                EnemyModel2 enemy2 = gameController.getEnemies2().get(i);
                if (enemy2.getEnemyHealth() > 0) {
                    enemy2.setDx(-((enemy2.getX() - ball.getX()) / Math.sqrt(Math.pow(enemy2.getX() - ball.getX(), 2) + Math.pow(enemy2.getY() - ball.getY(), 2))) * EnemyModel2.getEnemySpeed());
                    if (ball.getY() < enemy2.getY()) {
                        enemy2.setDy(-Math.sqrt(Math.pow(EnemyModel2.getEnemySpeed(), 2) - Math.pow(enemy2.getDx(), 2)));
                    } else {
                        enemy2.setDy(Math.sqrt(Math.pow(EnemyModel2.getEnemySpeed(), 2) - Math.pow(enemy2.getDx(), 2)));
                    }
                }

                gameController.setBall(ball);
                gameController.getEnemies2().set(i, enemy2);
            }
        }
    }

    public void updateEnemy2() {
        setDirectionForEnemy2();
        if (!gameController.getEnemies2().isEmpty()) {
            BallModel ball = gameController.getBall();
            for (int i = 0; i < gameController.getEnemies2().size(); i++) {
                EnemyModel2 enemy2 = gameController.getEnemies2().get(i);
                if (enemy2.getEnemyHealth() > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs(enemy2.getX() - ball.getX()), 2) + Math.pow(Math.abs(enemy2.getY() - ball.getY()), 2));
                    if (epsilonDistance > 100) {
                        enemy2.setX(enemy2.getX() + (2 * (enemy2.getDx() + enemy2.getAx())));
                        enemy2.setY(enemy2.getY() + (2 * (enemy2.getDy() + enemy2.getAy())));

                    }
                    if (epsilonDistance <= 100) {
                        enemy2.setX(enemy2.getX() + (enemy2.getDx() + enemy2.getAx()));
                        enemy2.setY(enemy2.getY() + (enemy2.getDy() + enemy2.getAy()));
                    }

                    if (enemy2.getAx() != 0) {
                        if (enemy2.getAx() > 0) {
                            enemy2.setAx(enemy2.getAx() - 0.05);
                        } else {
                            enemy2.setAx(enemy2.getAx() + 0.05);
                        }
                    }
                    if (enemy2.getAy() != 0) {
                        if (enemy2.getAy() > 0) {
                            enemy2.setAy(enemy2.getAy() - 0.05);
                        } else {
                            enemy2.setAy(enemy2.getAy() + 0.05);
                        }
                    }
                    rotation.enemy2Rotation();
                }

                gameController.getEnemies2().set(i, enemy2);
            }
        }

    }
}
