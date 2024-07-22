package controller.game.objectsController.ball;

import controller.game.GameController;
import model.entity.BulletModel;
import view.gameLoop.phase1.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame.createdFrames;

public class BulletController {


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

    public static void updateEnemyBullet() {
        if (!enemyBullets.isEmpty()) {
            for (int i = 0; i < enemyBullets.size(); i++) {
                if (enemyBullets.get(i).bulletHealth > 0) {
                    enemyBullets.get(i).x += enemyBullets.get(i).dx;
                    enemyBullets.get(i).y += enemyBullets.get(i).dy;
                }
            }
        }
    }


    public static void empowerBullet() {
        if (!GameController.pause) {
            GameController.empowerBullet = true;
            Timer timer = new Timer();
            int[] countDownEmpower = {10};
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (countDownEmpower[0] > 0) {
                        countDownEmpower[0]--;
                    } else {
                        GameController.empowerBullet = false;
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }


    public static boolean isBulletInAFrame(BulletModel bullet) {
        for (int i = 0; i < createdFrames.length; i++) {
            int x = createdFrames[i].x;
            int y = createdFrames[i].y;
            int width = createdFrames[i].width;
            int height = createdFrames[i].height;
            if ((bullet.x >= x) && (bullet.x <= (x + width - BulletModel.bulletSize))
                    && (bullet.y > y) && (bullet.y < (y + height - BulletModel.bulletSize))) {
                return true;
            }
        }
        return false;
    }
}
