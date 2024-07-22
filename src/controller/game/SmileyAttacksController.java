package controller.game;

import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.normalAndMiniBoss.ArchmirePoints;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static controller.game.objectsController.ball.enemies.normalAndMiniBoss.ArchmireController.setTimerForPoint;
import static view.gameLoop.phase2.finalBoss.EpsilonFrame.epsilonFrame;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2.phase2Over;

public class SmileyAttacksController {

    public static void startSmileyAttacks() {
        startSqueezeAttack();
        startProjectileAttack();
        startVomitAttack();
        startQuakeAttack();
        startRapidFireAttack();
        startSlapAttack();
    }


    // =======================      Squeeze Attack      ==============================

    public static void startSqueezeAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (leftHand.leftHandExists && rightHand.rightHandExists) {
                        smiley.squeezeAttack = true;
                        timerForSqueezeAttack();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 120000);
    }

    public static void timerForSqueezeAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                smiley.squeezeAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }


    // ==========================================================================


    // =====================     Projectile Attack     =======================================


    public static void startProjectileAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (leftHand.leftHandExists && rightHand.rightHandExists) {
                        smiley.projectileAttack = true;
                        timerForProjectileAttack();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 22000, 120000);
    }

    public static void timerForProjectileAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (smiley.projectileAttack) {
                        for (int i = 0; i < 5; i++) {
                            Point point = new Point();
                            point.setLocation(rightHand.x + ((double) RightHandModel.rightHandSize / 2), rightHand.y);
                            Point goal = new Point();
                            goal.setLocation(ball.x, ball.y + 30 + ((Math.pow(-1, i) * i * 150)));
                            GameController.newNecropickBullet(point, goal);
                        }
                        for (int i = 0; i < 5; i++) {
                            Point point = new Point();
                            point.setLocation(leftHand.x - ((double) LeftHandModel.leftHandSize / 2), leftHand.y);
                            Point goal = new Point();
                            goal.setLocation(ball.x, ball.y + 30 + ((Math.pow(-1, i) * i * 150)));
                            GameController.newNecropickBullet(point, goal);
                        }
                        smiley.projectileAttack = false;
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task, 3000, 15000);
    }





    // ==========================================================================


    // =====================     Vomit Attack     =======================================




    public static void startVomitAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    smiley.vomitAttack = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            Point point = new Point();
                            point.setLocation((epsilonFrame.x + 30 + (i * 100)),
                                    epsilonFrame.y + 30 + (j * 150));
                            ArchmirePoints vomitPoint = new ArchmirePoints(point.x, point.y);
                            archmirePoints.add(0, vomitPoint);
                            setTimerForPoint(vomitPoint);
                        }

                    }
                    timerForVomitAttack();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 30000, 120000);
    }

    public static void timerForVomitAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                smiley.vomitAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }





    // ==========================================================================


    // =====================     PowerPunch Attack     =======================================


    public static void startPowerPunchAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (smiley.punchExists) {
                        smiley.powerPunchAttack = true;
                        timerForPowerPunchAttack();
                        epsilonFrame.y += 50;
                        epsilonFrame.height -= 50;
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 40000, 120000);
    }

    public static void timerForPowerPunchAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                smiley.powerPunchAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }






    // ==========================================================================


    // =====================     Quake Attack     =======================================


    public static void startQuakeAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (smiley.punchExists) {
                        smiley.quakeAttack = true;
                        Impact.impactQuakeAttack();
                        timerForQuakeAttack();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 55000, 120000);
    }

    public static void timerForQuakeAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                smiley.quakeAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 8000, 15000);
    }






    // ==========================================================================


    // =====================     Rapid Fire Attack     =======================================


    public static void startRapidFireAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    smiley.rapidFireAttack = true;
                    shotRapidFireAttackBullets();
                    timerForRapidFireAttack();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 65000, 120000);
    }

    public static void shotRapidFireAttackBullets() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (smiley.rapidFireAttack) {
                        for (int i = 0; i < 5; i++) {
                            Point point = new Point();
                            point.setLocation(smiley.x, smiley.y);
                            Point goal = new Point();
                            goal.setLocation(ball.x + 10 + ((Math.pow(-1, i) * i * 150)), ball.y);
                            GameController.newNecropickBullet(point, goal);
                        }
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 3000);
    }


    public static void timerForRapidFireAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                smiley.rapidFireAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 30000, 15000);
    }





    // ==========================================================================


    // =====================     Slap Attack     =======================================


    public static void startSlapAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (phase2Over && !GameController.pause) {

                    if (smiley.punchExists) {
                        smiley.slapAttack = true;
                        Impact.impactQuakeAttack();
                        ball.HP -= 10;
                        timerForSlapAttack();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 100000, 120000);
    }

    public static void timerForSlapAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                smiley.slapAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 8000, 15000);
    }


}
