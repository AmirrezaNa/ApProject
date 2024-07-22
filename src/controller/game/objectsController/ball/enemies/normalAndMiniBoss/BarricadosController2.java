package controller.game.objectsController.ball.enemies.normalAndMiniBoss;

import controller.game.GameController;
import model.entity.enemy.normalAndMiniBoss.BarricadosModel2;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame.createdFrames;

public class BarricadosController2 {

    public static void setDirectionForBarricados2() {
        if (!barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < barricadosEnemies2.size(); i++) {
                if (barricadosEnemies2.get(i).enemyTimer > 0) {
                    barricadosEnemies2.get(i).dx = -((barricadosEnemies2.get(i).x - ball.x) / Math.sqrt(Math.pow((barricadosEnemies2.get(i).x - ball.x), 2) + Math.pow((barricadosEnemies2.get(i).y - ball.y), 2))) * BarricadosModel2.enemySpeed;
                    if (ball.y < barricadosEnemies2.get(i).y) {
                        barricadosEnemies2.get(i).dy = -Math.sqrt(Math.pow(BarricadosModel2.enemySpeed, 2) - Math.pow(barricadosEnemies2.get(i).dx, 2));
                    } else {
                        barricadosEnemies2.get(i).dy = Math.sqrt(Math.pow(BarricadosModel2.enemySpeed, 2) - Math.pow(barricadosEnemies2.get(i).dx, 2));
                    }
                }
            }
        }
    }

    public static void updateBarricados2() {
        setDirectionForBarricados2();
        if (!GameController.barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < barricadosEnemies2.size(); i++) {
                if (barricadosEnemies2.get(i).enemyTimer > 0) {
                    barricadosEnemies2.get(i).x += barricadosEnemies2.get(i).dx + barricadosEnemies2.get(i).ax;
                    barricadosEnemies2.get(i).y += barricadosEnemies2.get(i).dy + barricadosEnemies2.get(i).ay;

                    if (barricadosEnemies2.get(i).ax != 0) {
                        if (barricadosEnemies2.get(i).ax > 0) {
                            barricadosEnemies2.get(i).ax -= 0.05;
                        } else {
                            barricadosEnemies2.get(i).ax += 0.05;
                        }
                    }
                    if (barricadosEnemies2.get(i).ay != 0) {
                        if (barricadosEnemies2.get(i).ay > 0) {
                            barricadosEnemies2.get(i).ay -= 0.05;
                        } else {
                            barricadosEnemies2.get(i).ay += 0.05;
                        }
                    }
                }
            }
        }

    }

    public static void setTimerForBarricados2(BarricadosModel2 barricados2) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (barricados2.enemyTimer > 0 && !GameController.pause) {
                    barricados2.enemyTimer--;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public static boolean isBarricados2InFrame(int k) {
        if (!barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < barricadosEnemies2.size(); i++) {
                if (barricadosEnemies2.get(i).x >= createdFrames[i].x &&
                        barricadosEnemies2.get(i).x <= createdFrames[i].x + createdFrames[i].width &&
                        barricadosEnemies2.get(i).y >= createdFrames[i].y &&
                        barricadosEnemies2.get(i).y <= createdFrames[i].y + createdFrames[i].height) {
                    return true;
                }
            }
        }
        return false;
    }
}
