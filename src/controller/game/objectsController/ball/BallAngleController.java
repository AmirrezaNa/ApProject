package controller.game.objectsController.ball;

import controller.game.GameController;
import controller.game.listener.MouseInputListener;
import model.entity.BallModel;

import static view.game.GamePanel.ball;

public class BallAngleController {

    GameController gameController = new GameController();

    public void updateBallAngle() {
        if (gameController.getBallAngle() != null) {
            if (gameController.getBallAngle().isAngleExists()) {

                double x1 = gameController.getBall().getX();
                double y1 = gameController.getBall().getY();
                double x2 = MouseInputListener.x;
                double y2 = MouseInputListener.y;
                double deltaX = x2 - x1;
                double deltaY = y2 - y1;

                // Calculate the angle in radians
                gameController.getBallAngle().setAngle(Math.atan2(deltaY, deltaX));
                gameController.getBallAngle().setX(gameController.getBall().getX() + (BallModel.getBallRadius() * Math.cos(gameController.getBallAngle().getAngle())));
                gameController.getBallAngle().setY(gameController.getBall().getY() + (BallModel.getBallRadius() * Math.sin(gameController.getBallAngle().getAngle())));
            }
        }
    }
}
