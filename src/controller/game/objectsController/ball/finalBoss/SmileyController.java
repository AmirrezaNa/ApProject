package controller.game.objectsController.ball.finalBoss;

import controller.game.GameController;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;

public class SmileyController {


    public static void updateSmiley() {
        if (smiley != null) {
            if (smiley.y < 100) {
                smiley.y += smiley.dy;
            } else {
                if (smiley.dx == 0) {
                    smiley.dx = 1;
                }
                if (smiley.dx > 0) {
                    if (smiley.x > 750) {
                        smiley.dx = -smiley.dx;
                    }
                }
                if (smiley.dx < 0) {
                    if (smiley.x < 600) {
                        smiley.dx = -smiley.dx;
                    }
                }
                smiley.x += smiley.dx;
            }
        }
    }


    public static void shotBullet() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Point point = new Point();
                    point.setLocation(smiley.x, smiley.y);
                    Point goal = new Point();
                    goal.setLocation(ball.x + 30 + ((Math.pow(-1,i) * i * 100)), ball.y);
                    GameController.newNecropickBullet(point, goal);
                }

            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }
}
