package game.controller;

import game.ball.BallModel;

public class GameController {
    static BallModel ball;

    public static BallModel newBall() {
        ball = new BallModel(140,140);
        return ball;
    }

    public static void updateTheBall() {

    }

}
