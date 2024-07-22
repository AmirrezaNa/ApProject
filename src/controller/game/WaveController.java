package controller.game;

import controller.game.objectsController.ball.enemies.normalAndMiniBoss.NecropickController;
import model.entity.enemy.normalAndMiniBoss.*;
import view.gameLoop.phase1.GameFrame;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2.phase2Over;

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



    // =================       creating wave 4 and 5 enemies     =========================================


    public static OmenoctModel setTimerForOmenoct() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {
                    if (omenoctEnemies.isEmpty()) {
                        newOmenoct();
                    }
                    if (!omenoctEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < omenoctEnemies.size(); i++) {
                            if (omenoctEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            newOmenoct();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 5000);

        return omenoct;
    }


    public static NecropickModel setTimerForNecropick() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {

                    if (necropickEnemies.isEmpty()) {
                        newNecropick();
                        NecropickController.setNecropickHidingTime();
                    }
                    if (!necropickEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < necropickEnemies.size(); i++) {
                            if (necropickEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            newNecropick();
                            NecropickController.setNecropickHidingTime();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 5000);

        return necropick;
    }

    public static ArchmireModel setTimerForArchmire() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {

                    newArchmire();
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 20000);

        return archmire;
    }

    public static WyrmModel setTimerForWyrm() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {

                    if (wyrmEnemies.isEmpty()) {
                        newWyrm();
                    }
                    if (!wyrmEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < wyrmEnemies.size(); i++) {
                            if (wyrmEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            newWyrm();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);

        return wyrm;
    }


    public static BarricadosModel1 setTimerForBarricados1() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {

                    if (barricadosEnemies1.isEmpty()) {
                        newBarricados1();
                    }
                    if (!barricadosEnemies1.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < barricadosEnemies1.size(); i++) {
                            if (barricadosEnemies1.get(i).enemyTimer > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            newBarricados1();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 180000);

        return barricados1;
    }


    public static BarricadosModel2 setTimerForBarricados2() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {

                    if (barricadosEnemies2.isEmpty()) {
                        newBarricados2();
                    }
                    if (!barricadosEnemies2.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < barricadosEnemies2.size(); i++) {
                            if (barricadosEnemies2.get(i).enemyTimer > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            newBarricados2();
                        }
                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 15000, 180000);

        return barricados2;
    }


    public static BlackOrbModel setTimerForBlackOrb() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!phase2Over && !GameController.pause) {

                    if (blackOrbEnemies.isEmpty()) {
                        newBlackOrb();
                    }
                    if (!blackOrbEnemies.isEmpty()) {
                        boolean createNewOne = true;
                        for (int i = 0; i < blackOrbEnemies.size(); i++) {
                            if (blackOrbEnemies.get(i).enemyHealth > 0) {
                                createNewOne = false;
                                break;
                            }
                        }
                        if (createNewOne) {
                            newBlackOrb();
                        }

                    }
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 1000, 5000);

        return blackOrb;
    }

}
