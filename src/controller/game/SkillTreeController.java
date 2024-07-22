package controller.game;

import view.gameLoop.phase1.GamePanel;
import view.startPage.EnterNamePage;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.ball;
import static controller.game.GameController.ballAngle;

public class SkillTreeController {

    public static void turnOnWritOfProteus() {
        if (EnterNamePage.player.isWritOfProteus()) {
            if (!GameController.pause) {
                ballAngle.angleExists = true;
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            EnterNamePage.player.setWritOfProteus(false);
                        } else {
                            ballAngle.angleExists = false;
                            EnterNamePage.player.setWritOfProteus(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }


    public static void turnOnWritOfAceso() {
        if (EnterNamePage.player.isWritOfAceso()) {
            if (!GameController.pause) {
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            ball.HP++;
                            EnterNamePage.player.setWritOfProteus(false);
                        } else {
                            EnterNamePage.player.setWritOfProteus(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }

    public static void turnOnWritOfAres() {
        if (EnterNamePage.player.isWritOfAres()) {
            if (!GameController.pause) {
                GameController.bulletAres = true;
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            EnterNamePage.player.setWritOfAres(false);
                        } else {
                            GameController.bulletAres = false;
                            EnterNamePage.player.setWritOfAres(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }

}


