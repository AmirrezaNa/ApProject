package controller.game.objectsController.ball;

import controller.game.GameController;
import model.entity.BallModel;

public class BallController {
    GameController gameController = new GameController();

    public void updateTheBall() {
        if (gameController.getBall() != null) {
            BallModel ball = gameController.getBall();
            ball.setX(ball.getX() + ball.getAx());
            ball.setY(ball.getY() + ball.getAy());

            if (ball.getAx() != 0) {
                if (ball.getAx() > 0) {
                    ball.setAx(ball.getAx() - 0.05);
                } else {
                    ball.setAx(ball.getAx() + 0.05);
                }
            }
            if (ball.getAy() != 0) {
                if (ball.getAy() > 0) {
                    ball.setAy(ball.getAy() - 0.05);
                } else {
                    ball.setAy(ball.getAy() + 0.05);
                }
            }
            gameController.setBall(ball);

        }
    }
}
