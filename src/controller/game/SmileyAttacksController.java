package controller.game;

import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.normalAndMiniBoss.ArchmireModel;
import model.entity.enemy.normalAndMiniBoss.ArchmirePoints;
import model.entity.enemy.normalAndMiniBoss.NecropickModel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static controller.game.objectsController.ball.enemies.normalAndMiniBoss.ArchmireController.setTimerForPoint;

public class SmileyAttacksController {

    public static void startSmileyAttacks() {
        startSqueezeAttack();
        startProjectileAttack();
    }


    // =======================      Squeeze Attack      ==============================

    public static void startSqueezeAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (leftHand.leftHandExists && rightHand.rightHandExists) {
                    smiley.squeezeAttack = true;
                    timerForSqueezeAttack();
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
                if (leftHand.leftHandExists && rightHand.rightHandExists) {
                    smiley.projectileAttack = true;
                    timerForProjectileAttack();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 20000, 120000);
    }

    public static void timerForProjectileAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (smiley.projectileAttack) {
                    for (int i = 0; i < 5; i++) {
                        Point point = new Point();
                        point.setLocation(rightHand.x + ((double) RightHandModel.rightHandSize / 2), rightHand.y);
                        Point goal = new Point();
                        goal.setLocation(ball.x, ball.y + 30 + ((Math.pow(-1,i) * i * 150)));
                        GameController.newNecropickBullet(point, goal);
                    }
                    for (int i = 0; i < 5; i++) {
                        Point point = new Point();
                        point.setLocation(leftHand.x - ((double) LeftHandModel.leftHandSize / 2), leftHand.y);
                        Point goal = new Point();
                        goal.setLocation(ball.x, ball.y + 30 + ((Math.pow(-1,i) * i * 150)));
                        GameController.newNecropickBullet(point, goal);
                    }
                    smiley.projectileAttack = false;
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
                smiley.vomitAttack = true;
                timerForVomitAttack();
            }
        };
        timer.scheduleAtFixedRate(task, 25000, 120000);
    }

    public static void timerForVomitAttack() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (smiley.vomitAttack) {
                    for (int i = 0; i < 10; i++) {
                        Point point = new Point();
                        point.setLocation(ball.x + 30 + ((Math.pow(-1,i) * i * 50)),
                                ball.y + 30 + ((Math.pow(-1,i) * i * 50)));
                        setTrace(point.x, point.y);
                    }
                }
                smiley.vomitAttack = false;
            }
        };
        timer.scheduleAtFixedRate(task, 10000, 15000);
    }



    public static void setTrace(double x, double y) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (archmire.enemyHealth > 0) {
                    ArchmirePoints vomitPoint = new ArchmirePoints(x , y);
                    archmirePoints.add(0, vomitPoint);
                    setTimerForPoint(vomitPoint);
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 500);
    }



}
