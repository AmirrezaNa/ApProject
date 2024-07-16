package controller.game;

import view.game.GameFrame;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;

import java.util.Timer;
import java.util.TimerTask;

public class WaveController {
    GameController gameController = new GameController();


    // ========================== creating wave1 enemies ================================


    public void setTimerForEnemy1() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.getEnemies1().size() + gameController.getEnemies2().size() <= 10) {
                    gameController.newEnemy1();
                } else {
                    timer.cancel();
                    gameController.setWave(gameController.getWave()+1);
                    setTimerForEnemy1wave2();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 10000, 5000);

    }

    public void setTimerForEnemy2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.getEnemies1().size() + gameController.getEnemies2().size() <= 10) {
                    gameController.newEnemy2();
                } else {
                    timer.cancel();
                    setTimerForEnemy2wave2();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 12500, 5000);

    }


    // =========================== creating wave2 enemies =======================================

    public EnemyModel1 setTimerForEnemy1wave2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.getEnemies1().size() + gameController.getEnemies2().size() <= 25) {
                    GameFrame.countDown = false;
                    gameController.newEnemy1();
                } else {
                    timer.cancel();
                    gameController.setWave(gameController.getWave()+1);
                    setTimerForEnemy1wave3();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return gameController.getEnemy1();
    }

    public EnemyModel2 setTimerForEnemy2wave2() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.getEnemies1().size() + gameController.getEnemies2().size() <= 25) {
                    GameFrame.countDown = false;
                    gameController.newEnemy2();
                } else {
                    timer.cancel();
                    setTimerForEnemy2wave3();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return gameController.getEnemy2();
    }

    // =========================== creating wave3 enemies =======================================

    public EnemyModel1 setTimerForEnemy1wave3() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.getEnemies1().size() + gameController.getEnemies2().size() <= 45) {
                    GameFrame.countDown = false;
                    gameController.newEnemy1();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 15000, 5000);

        return gameController.getEnemy1();
    }

    public EnemyModel2 setTimerForEnemy2wave3() {   // this method creates an enemy every 5 seconds

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (gameController.getEnemies1().size() + gameController.getEnemies2().size() <= 45) {
                    GameFrame.countDown = false;
                    gameController.newEnemy2();
                }
            }

        };
        timer.scheduleAtFixedRate(task, 17500, 5000);

        return gameController.getEnemy2();
    }
}
