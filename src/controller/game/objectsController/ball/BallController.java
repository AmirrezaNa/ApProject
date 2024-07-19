package controller.game.objectsController.ball;

import controller.game.FrameOfObject;
import model.entity.BallModel;

import static controller.game.GameController.ball;
import static view.phase2.GameInternalFrame.createdFrames;

public class BallController {

    public static void updateTheBall() {
        ball.x += ball.ax;
        ball.y += ball.ay;

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

    public static void getBallIntoFrame2() {
        ball.x = createdFrames[0].x + ((double) createdFrames[0].width /2);
        ball.y = createdFrames[0].y + ((double) createdFrames[0].height /2);
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



}
