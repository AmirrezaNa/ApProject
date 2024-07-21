package controller.game.objectsController.ball.enemies.normalAndMiniBoss;

import controller.game.GameController;
import model.entity.enemy.normalAndMiniBoss.BarricadosModel1;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;

public class BarricadosController1 {

    public static void setDirectionForBarricados1() {
        if (!barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < barricadosEnemies1.size(); i++) {
                if (barricadosEnemies1.get(i).enemyTimer > 0) {
                    barricadosEnemies1.get(i).dx = -((barricadosEnemies1.get(i).x - ball.x) / Math.sqrt(Math.pow((barricadosEnemies1.get(i).x - ball.x), 2) + Math.pow((barricadosEnemies1.get(i).y - ball.y), 2))) * BarricadosModel1.enemySpeed;
                    if (ball.y < barricadosEnemies1.get(i).y) {
                        barricadosEnemies1.get(i).dy = -Math.sqrt(Math.pow(BarricadosModel1.enemySpeed, 2) - Math.pow(barricadosEnemies1.get(i).dx, 2));
                    } else {
                        barricadosEnemies1.get(i).dy = Math.sqrt(Math.pow(BarricadosModel1.enemySpeed, 2) - Math.pow(barricadosEnemies1.get(i).dx, 2));
                    }
                }
            }
        }
    }

    public static void updateBarricados1() {
        setDirectionForBarricados1();
        if (!GameController.barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < barricadosEnemies1.size(); i++) {
                if (barricadosEnemies1.get(i).enemyTimer > 0) {
                    double epsilonDistance = Math.sqrt(Math.pow(Math.abs(barricadosEnemies1.get(i).x - ball.x), 2) + Math.pow(Math.abs(barricadosEnemies1.get(i).y - ball.y), 2));
                    if (epsilonDistance > 150) {
                        barricadosEnemies1.get(i).x += barricadosEnemies1.get(i).dx + barricadosEnemies1.get(i).ax;
                        barricadosEnemies1.get(i).y += barricadosEnemies1.get(i).dy + barricadosEnemies1.get(i).ay;
                    }

                    if (barricadosEnemies1.get(i).ax != 0) {
                        if (barricadosEnemies1.get(i).ax > 0) {
                            barricadosEnemies1.get(i).ax -= 0.05;
                        } else {
                            barricadosEnemies1.get(i).ax += 0.05;
                        }
                    }
                    if (barricadosEnemies1.get(i).ay != 0) {
                        if (barricadosEnemies1.get(i).ay > 0) {
                            barricadosEnemies1.get(i).ay -= 0.05;
                        } else {
                            barricadosEnemies1.get(i).ay += 0.05;
                        }
                    }
                }
            }
        }

    }


    public static void setTimerForBarricados1(BarricadosModel1 barricados1) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (barricados1.enemyTimer > 0) {
                    barricados1.enemyTimer--;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
