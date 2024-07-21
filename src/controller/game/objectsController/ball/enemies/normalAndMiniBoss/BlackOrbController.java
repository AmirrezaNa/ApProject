package controller.game.objectsController.ball.enemies.normalAndMiniBoss;

import model.entity.enemy.normalAndMiniBoss.BlackOrbModel;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.ball;
import static controller.game.GameController.blackOrbEnemies;

public class BlackOrbController {


    public static void setDirectionForBlackOrb() {
        if (!blackOrbEnemies.isEmpty()) {
            for (int i = 0; i < blackOrbEnemies.size(); i++) {
                if (blackOrbEnemies.get(i).enemyHealth > 0) {
                    blackOrbEnemies.get(i).dx = -((blackOrbEnemies.get(i).x - ball.x) / Math.sqrt(Math.pow((blackOrbEnemies.get(i).x - ball.x), 2) + Math.pow((blackOrbEnemies.get(i).y - ball.y), 2))) * BlackOrbModel.enemySpeed;
                    if (ball.y < blackOrbEnemies.get(i).y) {
                        blackOrbEnemies.get(i).dy = -Math.sqrt(Math.pow(BlackOrbModel.enemySpeed, 2) - Math.pow(blackOrbEnemies.get(i).dx, 2));
                    } else {
                        blackOrbEnemies.get(i).dy = Math.sqrt(Math.pow(BlackOrbModel.enemySpeed, 2) - Math.pow(blackOrbEnemies.get(i).dx, 2));
                    }
                }
            }
        }
    }


    public static void updateBlackOrb() {
        setDirectionForBlackOrb();
        if (!blackOrbEnemies.isEmpty()) {
            for (int i = 0; i < blackOrbEnemies.size(); i++) {
                if (blackOrbEnemies.get(i).enemyHealth > 0) {
                    if (blackOrbEnemies.get(i).draw5) {
                        blackOrbEnemies.get(i).x += blackOrbEnemies.get(i).dx + blackOrbEnemies.get(i).ax;
                        blackOrbEnemies.get(i).y += blackOrbEnemies.get(i).dy + blackOrbEnemies.get(i).ay;

                        if (blackOrbEnemies.get(i).ax != 0) {
                            if (blackOrbEnemies.get(i).ax > 0) {
                                blackOrbEnemies.get(i).ax -= 0.05;
                            } else {
                                blackOrbEnemies.get(i).ax += 0.05;
                            }
                        }
                        if (blackOrbEnemies.get(i).ay != 0) {
                            if (blackOrbEnemies.get(i).ay > 0) {
                                blackOrbEnemies.get(i).ay -= 0.05;
                            } else {
                                blackOrbEnemies.get(i).ay += 0.05;
                            }
                        }
                    }
                }
            }
        }

    }


    public static void setTimerForCreatingBlackOrb(BlackOrbModel blackOrb) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (blackOrb.blackOrbTimer == 20) {
                    blackOrb.draw1 = true;
                }
                if (blackOrb.blackOrbTimer == 15) {
                    blackOrb.draw2 = true;
                }
                if (blackOrb.blackOrbTimer == 10) {
                    blackOrb.draw3 = true;
                }
                if (blackOrb.blackOrbTimer == 5) {
                    blackOrb.draw4 = true;
                }
                if (blackOrb.blackOrbTimer == 0) {
                    blackOrb.draw5 = true;
                }
                blackOrb.blackOrbTimer--;
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
