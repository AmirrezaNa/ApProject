package controller.game.objectsController.ball;

import controller.game.GameController;
import controller.game.listener.MouseInputListener;
import model.entity.BallModel;

import static view.game.GamePanel.ball;

public class BallDirectionController {

    GameController gameController = new GameController();

    public void updateBallDirection() {
        if (gameController.getBall() != null) {
            BallModel ball = gameController.getBall();

            double x1 = ball.getX();
            double y1 = ball.getY();
            double x2 = MouseInputListener.x;
            double y2 = MouseInputListener.y;
            double deltaX = x2 - x1;
            double deltaY = y2 - y1;

            // Calculate the angle in radians
            gameController.getBallDirection().setAngle(Math.atan2(deltaY, deltaX));
            gameController.getBallDirection().setX(ball.getX() + (10 * Math.cos(gameController.getBallDirection().getAngle())));
            gameController.getBallDirection().setY(ball.getY() + (10 * Math.sin(gameController.getBallDirection().getAngle())));
        }
    }
}

