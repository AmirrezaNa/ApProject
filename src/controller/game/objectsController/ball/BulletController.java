package controller.game.objectsController.ball;

import controller.game.GameController;
import model.entity.BulletModel;
import view.game.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

public class BulletController {
    GameController gameController = new GameController();


    public void updateBullet() {
        if (gameController.getEmpower() == 1) {
            empowerBullet();
            gameController.setEmpower(gameController.getEmpower()-1);
        }
        if (!gameController.getBullets().isEmpty()) {
            for (int i = 0; i < gameController.getBullets().size(); i++) {
                BulletModel bullet = gameController.getBullets().get(i);
                if (bullet.getBulletHealth() > 0) {
                    bullet.setX(bullet.getX() + bullet.getDx());
                    bullet.setY(bullet.getY() + bullet.getDy());
                }
            }
        }
    }


    public void empowerBullet() {
        if (!GamePanel.pause) {
            gameController.setEmpowerBullet(true);
            Timer timer = new Timer();
            int[] countDownEmpower = {10};
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (countDownEmpower[0] > 0) {
                        countDownEmpower[0]--;
                    } else {
                        gameController.setEmpowerBullet(false);
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }
}
