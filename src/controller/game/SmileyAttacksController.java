package controller.game;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;

public class SmileyAttacksController {

    public static void startSmileyAttacks() {
        startSqueezeAttack();
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


    // =====================
}
