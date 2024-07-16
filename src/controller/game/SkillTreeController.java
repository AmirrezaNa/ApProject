package controller.game;

import view.game.GamePanel;
import view.startPage.EnterNamePage;

import java.util.Timer;
import java.util.TimerTask;

public class SkillTreeController {

    GameController gameController = new GameController();

    public void turnOnWritOfProteus() {
        if (EnterNamePage.player.isWritOfProteus()) {
            if (!GamePanel.pause) {
                gameController.getBallAngle().setAngleExists(true);
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            EnterNamePage.player.setWritOfProteus(false);
                        } else {
                            gameController.getBallAngle().setAngleExists(false);
                            EnterNamePage.player.setWritOfProteus(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }


    public void turnOnWritOfAceso() {
        if (EnterNamePage.player.isWritOfAceso()) {
            if (!GamePanel.pause) {
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            gameController.getBall().setHP(gameController.getBall().getHP()+1);
                            EnterNamePage.player.setWritOfAceso(false);
                        } else {
                            EnterNamePage.player.setWritOfAceso(true);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 1000);
            }
        }
    }

    public void turnOnWritOfAres() {
        if (EnterNamePage.player.isWritOfAres()) {
            if (!GamePanel.pause) {
                gameController.setBulletAres(true);
                Timer timer = new Timer();
                int[] countDownEmpower = {300};
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (countDownEmpower[0] > 0) {
                            countDownEmpower[0]--;
                            EnterNamePage.player.setWritOfAres(false);
                        } else {
                            gameController.setBulletAres(false);
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


