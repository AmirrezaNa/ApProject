package controller.game.objectsController.ball.enemies.normalAndMiniBoss;

import controller.game.Rotation;
import model.entity.enemy.normalAndMiniBoss.EnemyModel1;

import static controller.game.GameController.*;
import static controller.game.GameController.blackOrbEnemies;

public class Enemy1Controller {



    public static void setDirectionForEnemy1() {
        if (!enemies1.isEmpty()) {
            for (int i = 0; i < enemies1.size(); i++) {
                if (enemies1.get(i).enemyHealth > 0) {
                    enemies1.get(i).dx = -((enemies1.get(i).x - ball.x) / Math.sqrt(Math.pow((enemies1.get(i).x - ball.x), 2) + Math.pow((enemies1.get(i).y - ball.y), 2))) * EnemyModel1.enemySpeed;
                    if (ball.y < enemies1.get(i).y) {
                        enemies1.get(i).dy = -Math.sqrt(Math.pow(EnemyModel1.enemySpeed, 2) - Math.pow(enemies1.get(i).dx, 2));
                    } else {
                        enemies1.get(i).dy = Math.sqrt(Math.pow(EnemyModel1.enemySpeed, 2) - Math.pow(enemies1.get(i).dx, 2));
                    }
                    if (ball.ballDismay) {
                        enemies1.get(i).dx = -enemies1.get(i).dx;
                        enemies1.get(i).dy = -enemies1.get(i).dy;
                    }
                }
            }
        }
    }


    public static void updateEnemy1() {
        setDirectionForEnemy1();
        if (!enemies1.isEmpty()) {
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
}
