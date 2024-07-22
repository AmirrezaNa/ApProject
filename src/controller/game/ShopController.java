package controller.game;

import java.util.Timer;
import java.util.TimerTask;

public class ShopController {

    public static void TimerForDismay() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GameController.ball.ballDismay = false;
            }
        };
        timer.schedule(task, 10000);
    }

    public static void TimerForSlumber() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GameController.ball.ballSlumber = false;
            }
        };
        timer.schedule(task, 10000);
    }


}
