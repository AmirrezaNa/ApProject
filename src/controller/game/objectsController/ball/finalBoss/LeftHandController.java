package controller.game.objectsController.ball.finalBoss;

import controller.game.GameController;
import model.entity.enemy.normalAndMiniBoss.WyrmModel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.finalBoss.EpsilonFrame.epsilonFrame;

public class LeftHandController {

    public static void updateLeftHand() {
        if (leftHand != null) {
            if (smiley.squeezeAttack) {
                if ((leftHand.x < epsilonFrame.x)) {
                    leftHand.x += leftHand.dx;
                }
                else {
                    leftHand.x -= leftHand.dx;
                }

            } else if (leftHand.x < 400) {
                leftHand.x += leftHand.dx;
            } else if (leftHand.x > 400) {
                leftHand.x -= leftHand.dx;
            } else {
                if (leftHand.dy > 0) {
                    if (leftHand.y > 500) {
                        leftHand.dy = -leftHand.dy;
                    }
                }
                if (leftHand.dy < 0) {
                    if (leftHand.y < 200) {
                        leftHand.dy = -leftHand.dy;
                    }
                }
                leftHand.y += leftHand.dy;
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
                    point.setLocation(leftHand.x, leftHand.y);
                    Point goal = new Point();
                    goal.setLocation(ball.x + 30 + ((Math.pow(-1, i) * i * 100)), ball.y);
                    GameController.newNecropickBullet(point, goal);
                }

            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }
}
