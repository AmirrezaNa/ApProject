package controller.game.objectsController.ball;

import controller.game.GameController;
import model.entity.BallModel;

import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.ball;
import static controller.game.GameController.smiley;
import static view.gameLoop.phase2.finalBoss.EpsilonFrame.epsilonFrame;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame.createdFrames;

public class BallController {

    public static void updateTheBall() {
        ball.x += ball.ax;
        ball.y += ball.ay;
        if (smiley != null && smiley.quakeAttack) {
            if (ball.ax != 0) {
                if (ball.ax > 0) {
                    ball.ax -= 0.0005;
                } else {
                    ball.ax += 0.0005;
                }
            }
            if (ball.ay != 0) {
                if (ball.ay > 0) {
                    ball.ay -= 0.0005;
                } else {
                    ball.ay += 0.0005;
                }
            }
        }

        else {
            if (ball.ax != 0) {
                if (ball.ax > 0) {
                    ball.ax -= 0.05;
                } else {
                    ball.ax += 0.05;
                }
            }
            if (ball.ay != 0) {
                if (ball.ay > 0) {
                    ball.ay -= 0.05;
                } else {
                    ball.ay += 0.05;
                }
            }
        }
    }

    public static void getBallIntoFrame2() {
        ball.x = createdFrames[0].x + ((double) createdFrames[0].width /2);
        ball.y = createdFrames[0].y + ((double) createdFrames[0].height /2);
    }

    public static void getBallIntoFinalFrame() {
        ball.x = epsilonFrame.x + ((double) epsilonFrame.width /2);
        ball.y = epsilonFrame.y + ((double) epsilonFrame.height /2);
    }


    public static boolean checkIfBallIsInFrame() {
        for (int i = 0; i < createdFrames.length; i++) {
            int x = createdFrames[i].x;
            int y = createdFrames[i].y;
            int width = createdFrames[i].width;
            int height = createdFrames[i].height;
            if ((ball.x >= (x + BallModel.ballRadius)) && (ball.x <= (x + width - BallModel.ballRadius))
                    && (ball.y > (y + BallModel.ballRadius)) && (ball.y < (y + height - BallModel.ballRadius))) {
                return true;
            }
        }
        return false;
    }

    public static void checkIfBallInArchmire() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (ball.ballInArchmire && !GameController.pause) {
                    ball.HP -= 10;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }

    public static void checkIfBallInArchmireTrace() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (ball.ballInArchmireTrace && !GameController.pause) {
                    ball.HP -= 2;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }

    public static void checkIfBallInBlackOrb() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (ball.ballInBlackOrb && !GameController.pause) {
                    ball.HP -= 12;
                }
//                if (stopWave) {
//                    timer.cancel();
//                }
            }

        };
        timer.scheduleAtFixedRate(task, 500, 1000);
    }



}
