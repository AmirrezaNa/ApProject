package game.controller;

import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;

import java.util.Timer;
import java.util.TimerTask;

public class Rotation {

    public static void enemy1Rotation() {
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy : GameController.enemies1) {
                if (enemy.dAngle >= 0) {
                    enemy.dAngle -= Math.PI/200;
                }
                enemy.angle += enemy.dAngle/80;
                double angleBetween = (2 * Math.PI)/4;
                enemy.xAngles[0] = (int) (enemy.x - (enemy.enemy1Size * Math.sin(enemy.angle)));
                enemy.xAngles[1] = (int) (enemy.x - (enemy.enemy1Size * Math.sin(enemy.angle + angleBetween)));
                enemy.xAngles[2] = (int) (enemy.x - (enemy.enemy1Size * Math.sin(enemy.angle + (2 * angleBetween))));
                enemy.xAngles[3] = (int) (enemy.x - (enemy.enemy1Size * Math.sin(enemy.angle + (3 * angleBetween))));
                enemy.yAngles[0] = (int) (enemy.y + (enemy.enemy1Size * Math.cos(enemy.angle)));
                enemy.yAngles[1] = (int) (enemy.y + (enemy.enemy1Size * Math.cos(enemy.angle + (angleBetween))));
                enemy.yAngles[2] = (int) (enemy.y + (enemy.enemy1Size * Math.cos(enemy.angle + (2 * angleBetween))));
                enemy.yAngles[3] = (int) (enemy.y + (enemy.enemy1Size * Math.cos(enemy.angle + (3 * angleBetween))));
                if (enemy.angle >= 2 * Math.PI) {
                    enemy.angle = 0;
                }
            }

        }
    }

    public static void enemy2Rotation() {
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy : GameController.enemies2) {
                if (enemy.dAngle >= 0) {
                    enemy.dAngle -= Math.PI/200;
                }
                enemy.angle += enemy.dAngle/80;
                double angleBetween = (2 * Math.PI)/3;
                enemy.xAngles[0] = (int) (enemy.x - (enemy.enemy2Size * Math.sin(enemy.angle)));
                enemy.xAngles[1] = (int) (enemy.x - (enemy.enemy2Size * Math.sin(enemy.angle + angleBetween)));
                enemy.xAngles[2] = (int) (enemy.x - (enemy.enemy2Size * Math.sin(enemy.angle + (2 * angleBetween))));
                enemy.yAngles[0] = (int) (enemy.y + (enemy.enemy2Size * Math.cos(enemy.angle)));
                enemy.yAngles[1] = (int) (enemy.y + (enemy.enemy2Size * Math.cos(enemy.angle + angleBetween)));
                enemy.yAngles[2] = (int) (enemy.y + (enemy.enemy2Size * Math.cos(enemy.angle + (2 * angleBetween))));
                if (enemy.angle >= 2 * Math.PI) {
                    enemy.angle = 0;
                }
            }

        }
    }

}
