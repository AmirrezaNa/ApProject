package controller.game.objectsController.ball.finalBoss;

import controller.game.GameController;
import model.entity.enemy.normalAndMiniBoss.WyrmModel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.finalBoss.EpsilonFrame.epsilonFrame;

public class RightHandController {

    public static void updateRightHand() {
        if (rightHand != null) {

            if ((rightHand.x > (epsilonFrame.x + epsilonFrame.width)) && smiley.squeezeAttack) {
                rightHand.x += rightHand.dx;
            } else if (rightHand.x > 1000) {
                rightHand.x += rightHand.dx;
            } else if (rightHand.x < 1000) {
                rightHand.x -= rightHand.dx;
            } else {
                if (rightHand.dy > 0) {
                    if (rightHand.y > 500) {
                        rightHand.dy = -rightHand.dy;
                    }
                }
                if (rightHand.dy < 0) {
                    if (rightHand.y < 200) {
                        rightHand.dy = -rightHand.dy;
                    }
                }
                rightHand.y += rightHand.dy;
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
                    point.setLocation(rightHand.x, rightHand.y);
                    Point goal = new Point();
                    goal.setLocation(ball.x + 30 + ((Math.pow(-1,i) * i * 100)), ball.y);
                    GameController.newNecropickBullet(point, goal);
                }

            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }

}
