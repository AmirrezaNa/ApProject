package controller.game;

import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;

public class Rotation {
    GameController gameController = new GameController();

    public void enemy1Rotation() {
        if (!gameController.getEnemies1().isEmpty()) {
            for (EnemyModel1 enemy : gameController.getEnemies1()) {
                if (enemy.getdAngle() >= 0) {
                    enemy.setdAngle(enemy.getdAngle() - (Math.PI/200));
                }
                enemy.setAngle(enemy.getAngle() + (enemy.getdAngle()/80));
                double angleBetween = (2 * Math.PI)/4;
                enemy.getxAngles()[0] = (int) (enemy.getX() - (EnemyModel1.getEnemy1Size() * Math.sin(enemy.getAngle())));
                enemy.getxAngles()[1] = (int) (enemy.getX() - (EnemyModel1.getEnemy1Size() * Math.sin(enemy.getAngle() + angleBetween)));
                enemy.getxAngles()[2] = (int) (enemy.getX() - (EnemyModel1.getEnemy1Size() * Math.sin(enemy.getAngle() + (2 * angleBetween))));
                enemy.getxAngles()[3] = (int) (enemy.getX() - (EnemyModel1.getEnemy1Size() * Math.sin(enemy.getAngle() + (3 * angleBetween))));
                enemy.getyAngles()[0] = (int) (enemy.getY() + (EnemyModel1.getEnemy1Size() * Math.cos(enemy.getAngle())));
                enemy.getyAngles()[1] = (int) (enemy.getY() + (EnemyModel1.getEnemy1Size() * Math.cos(enemy.getAngle() + (angleBetween))));
                enemy.getyAngles()[2] = (int) (enemy.getY() + (EnemyModel1.getEnemy1Size() * Math.cos(enemy.getAngle() + (2 * angleBetween))));
                enemy.getyAngles()[3] = (int) (enemy.getY() + (EnemyModel1.getEnemy1Size() * Math.cos(enemy.getAngle() + (3 * angleBetween))));
                if (enemy.getAngle() >= 2 * Math.PI) {
                    enemy.setAngle(0);
                }
            }

        }
    }

    public void enemy2Rotation() {
        if (!gameController.getEnemies2().isEmpty()) {
            for (EnemyModel2 enemy : gameController.getEnemies2()) {
                if (enemy.getdAngle() >= 0) {
                    enemy.setdAngle(enemy.getdAngle() - (Math.PI/200));
                }
                enemy.setAngle(enemy.getAngle() + (enemy.getdAngle()/80));
                double angleBetween = (2 * Math.PI)/3;
                enemy.getxAngles()[0] = (int) (enemy.getX() - (EnemyModel2.getEnemy2Size() * Math.sin(enemy.getAngle())));
                enemy.getxAngles()[1] = (int) (enemy.getX() - (EnemyModel2.getEnemy2Size() * Math.sin(enemy.getAngle() + angleBetween)));
                enemy.getxAngles()[2] = (int) (enemy.getX() - (EnemyModel2.getEnemy2Size() * Math.sin(enemy.getAngle() + (2 * angleBetween))));
                enemy.getyAngles()[0] = (int) (enemy.getY() + (EnemyModel2.getEnemy2Size() * Math.cos(enemy.getAngle())));
                enemy.getyAngles()[1] = (int) (enemy.getY() + (EnemyModel2.getEnemy2Size() * Math.cos(enemy.getAngle() + angleBetween)));
                enemy.getyAngles()[2] = (int) (enemy.getY() + (EnemyModel2.getEnemy2Size() * Math.cos(enemy.getAngle() + (2 * angleBetween))));
                if (enemy.getAngle() >= 2 * Math.PI) {
                    enemy.setAngle(0);
                }
            }

        }
    }

}
