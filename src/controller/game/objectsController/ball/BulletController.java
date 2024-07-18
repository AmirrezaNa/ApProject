package controller.game.objectsController.ball;

import controller.game.GameController;
import view.phase1.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.Empower;
import static controller.game.GameController.bullets;

public class BulletController {
    GameController gameController = new GameController();


    public static void updateBullet() {
        if (Empower == 1) {
            empowerBullet();
            Empower--;
        }
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).bulletHealth > 0) {
                    bullets.get(i).x += bullets.get(i).dx;
                    bullets.get(i).y += bullets.get(i).dy;
                }
            }
        }
    }


    public static void empowerBullet() {
        if (!GamePanel.phase1over) {
            GameController.empowerBullet = true;
            Timer timer = new Timer();
            int[] countDownEmpower = {10};
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (countDownEmpower[0] > 0) {
                        countDownEmpower[0]--;
                        System.out.println("hi");
                    } else {
                        GameController.empowerBullet = false;
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }
}
