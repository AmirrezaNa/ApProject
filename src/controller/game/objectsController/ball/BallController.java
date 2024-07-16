package controller.game.objectsController.ball;

import controller.game.GameController;
import model.entity.BallModel;

import static controller.game.GameController.ball;

public class BallController {
    GameController gameController = new GameController();

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
}
