package controller.game.objectsController.ball;

import controller.game.GameController;
import controller.game.listener.MouseInputListener;
import model.entity.BallModel;

import static controller.game.GameController.ballAngle;
import static controller.game.GameController.ball;

public class BallAngleController {


    public static void updateBallAngle() {
        if (ballAngle.angleExists) {

            double x1 = ball.x;
            double y1 = ball.y;
            double x2 = MouseInputListener.x;
            double y2 = MouseInputListener.y;
            double deltaX = x2 - x1;
            double deltaY = y2 - y1;

            // Calculate the angle in radians
            ballAngle.angle = Math.atan2(deltaY, deltaX);
            ballAngle.x = ball.x + (BallModel.ballRadius * Math.cos(ballAngle.angle));
            ballAngle.y = ball.y + (BallModel.ballRadius * Math.sin(ballAngle.angle));
        }
    }
}
