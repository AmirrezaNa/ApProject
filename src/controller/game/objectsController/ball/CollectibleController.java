package controller.game.objectsController.ball;

import controller.game.GameController;
import model.entity.Collectible;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CollectibleController {
    GameController gameController = new GameController();


    public static void countDownCollectible(int seconds, Collectible collectible1) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = seconds;

            @Override
            public void run() {
                if (remainingSeconds > 0) {
                    remainingSeconds--;
                } else {
                    collectible1.setCollectibleHealth(0);
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
}
