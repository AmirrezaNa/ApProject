package controller.game.objectsController.ball;

import controller.game.GameController;
import controller.game.listener.MouseInputListener;
import model.entity.BallModel;

import static view.game.GamePanel.ball;
import static view.game.GamePanel.ballDirection;

public class BallDirectionController {

    GameController gameController = new GameController();

    public static void updateBallDirection() {

        double x1 = ball.x;
        double y1 = ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        ballDirection.angle = Math.atan2(deltaY, deltaX);
        ballDirection.x = ball.x + (10 * Math.cos(ballDirection.angle));
        ballDirection.y = ball.y + (10 * Math.sin(ballDirection.angle));
    }
}

