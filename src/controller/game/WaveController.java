package controller.game;

import view.phase1.GameFrame;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;

public class WaveController {
    GameController gameController = new GameController();

    public static boolean stopWave;


    // ========================== creating wave1 enemies ================================


    public static EnemyModel1 setTimerForEnemy1() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies2.size() + enemies1.size() <= 10) {
                    newEnemy1();
                } else {
                    timer.cancel();
                    wave++;
                    setTimerForEnemy1wave2();
                }
                if (stopWave) {
                    timer.cancel();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 10000, 5000);

        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies1.size() + enemies2.size() <= 10) {
                    newEnemy2();
                } else {
                    timer.cancel();
                    setTimerForEnemy2wave2();
                }
                if (stopWave) {
                    timer.cancel();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 12500, 5000);

        return enemy2;
    }


    // =========================== creating wave2 enemies =======================================

    public static EnemyModel1 setTimerForEnemy1wave2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies2.size() + enemies1.size() <= 25) {
                    GameFrame.countDown = false;
                    newEnemy1();
                } else {
                    timer.cancel();
                    wave++;
                    setTimerForEnemy1wave3();
                }
                if (stopWave) {
                    timer.cancel();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2wave2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies2.size() + enemies1.size() <= 25) {
                    GameFrame.countDown = false;
                    newEnemy2();
                } else {
                    timer.cancel();
                    setTimerForEnemy2wave3();
                }
                if (stopWave) {
                    timer.cancel();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return enemy2;
    }

    // =========================== creating wave3 enemies =======================================

    public static EnemyModel1 setTimerForEnemy1wave3() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies1.size() + enemies2.size() <= 45) {
                    GameFrame.countDown = false;
                    newEnemy1();
                }
                if (stopWave) {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return enemy1;
    }

    public static EnemyModel2 setTimerForEnemy2wave3() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (enemies1.size() + enemies2.size() <= 45) {
                    GameFrame.countDown = false;
                    newEnemy2();
                }
                if (stopWave) {
                    timer.cancel();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return enemy2;
    }



}
