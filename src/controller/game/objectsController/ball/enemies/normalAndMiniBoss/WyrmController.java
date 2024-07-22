package controller.game.objectsController.ball.enemies.normalAndMiniBoss;

import controller.game.GameController;
import model.entity.enemy.normalAndMiniBoss.WyrmModel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;

public class WyrmController {

    public static void setDirectionForWyrm() {
        if (!wyrmEnemies.isEmpty()) {
            for (int i = 0; i < wyrmEnemies.size(); i++) {
                if (wyrmEnemies.get(i).enemyHealth > 0) {
                    wyrmEnemies.get(i).dx = -((wyrmEnemies.get(i).x - ball.x) / Math.sqrt(Math.pow((wyrmEnemies.get(i).x - ball.x), 2) + Math.pow((wyrmEnemies.get(i).y - ball.y), 2))) * WyrmModel.enemySpeed;
                    if (ball.y < wyrmEnemies.get(i).y) {
                        wyrmEnemies.get(i).dy = -Math.sqrt(Math.pow(WyrmModel.enemySpeed, 2) - Math.pow(wyrmEnemies.get(i).dx, 2));
                    } else {
                        wyrmEnemies.get(i).dy = Math.sqrt(Math.pow(WyrmModel.enemySpeed, 2) - Math.pow(wyrmEnemies.get(i).dx, 2));
                    }
                }
            }
        }
    }


    public static void updateWyrm() {
        setDirectionForWyrm();
        if (!wyrmEnemies.isEmpty()) {
            for (int i = 0; i < wyrmEnemies.size(); i++) {
                if (wyrmEnemies.get(i).enemyHealth > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs((wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize / 2)) - ball.x), 2) + Math.pow(Math.abs((wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize / 2)) - ball.y), 2));
                    if (epsilonDistance <= 200) {
                        if (ball.x > (wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize /2))
                                && ball.y > (wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize /2))) {
                            wyrmEnemies.get(i).x += 5;
                            wyrmEnemies.get(i).y += 5;
                        }
                        if (ball.x > (wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize /2))
                                && ball.y < (wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize /2))) {
                            wyrmEnemies.get(i).x -= 5;
                            wyrmEnemies.get(i).y += 5;
                        }
                        if (ball.x < (wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize /2))
                                && ball.y > (wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize /2))) {
                            wyrmEnemies.get(i).x -= 5;
                            wyrmEnemies.get(i).y -= 5;
                        }
                        if (ball.x < (wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize /2))
                                && ball.y < (wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize /2))) {
                            wyrmEnemies.get(i).x -= 5;
                            wyrmEnemies.get(i).y -= 5;
                        }
                    }
                    if (epsilonDistance > 200) {
                        wyrmEnemies.get(i).x += wyrmEnemies.get(i).dx + wyrmEnemies.get(i).ax;
                        wyrmEnemies.get(i).y += wyrmEnemies.get(i).dy + wyrmEnemies.get(i).ay;
                    }
                    if (wyrmEnemies.get(i).ax != 0) {
                        if (wyrmEnemies.get(i).ax > 0) {
                            wyrmEnemies.get(i).ax -= 0.05;
                        } else {
                            wyrmEnemies.get(i).ax += 0.05;
                        }
                    }
                    if (wyrmEnemies.get(i).ay != 0) {
                        if (wyrmEnemies.get(i).ay > 0) {
                            wyrmEnemies.get(i).ay -= 0.05;
                        } else {
                            wyrmEnemies.get(i).ay += 0.05;
                        }
                    }

                }
            }
        }

    }


    public static void shotBullet() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < wyrmEnemies.size(); i++) {
                    if (wyrmEnemies.get(i).enemyHealth > 0 && !GameController.pause) {
                        Point point = new Point();
                        point.setLocation(wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize /2),
                                wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize /2));
                        newOmenoctBullet(point);
                    }
                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }

}
