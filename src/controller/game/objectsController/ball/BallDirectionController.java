package controller.game.objectsController.ball;

import controller.game.listener.MouseInputListener;
import view.gameLoop.phase1.GamePanel;
import view.gameLoop.phase2.finalBoss.FinalBossPanel;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2;

public class BallDirectionController {


    public static void updateBallDirection() {

        double x1 = GamePanel.ball.x;
        double y1 = GamePanel.ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        GamePanel.ballDirection.angle = Math.atan2(deltaY, deltaX);
        GamePanel.ballDirection.x = GamePanel.ball.x + (10 * Math.cos(GamePanel.ballDirection.angle));
        GamePanel.ballDirection.y = GamePanel.ball.y + (10 * Math.sin(GamePanel.ballDirection.angle));
    }

    public static void updateBallDirectionPanel2() {

        double x1 = GamePanel2.ball.x;
        double y1 = GamePanel2.ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        GamePanel2.ballDirection.angle = Math.atan2(deltaY, deltaX);
        GamePanel2.ballDirection.x = GamePanel2.ball.x + (10 * Math.cos(GamePanel2.ballDirection.angle));
        GamePanel2.ballDirection.y = GamePanel2.ball.y + (10 * Math.sin(GamePanel2.ballDirection.angle));
    }

    public static void updateBallDirectionFinalBoss() {

        double x1 = FinalBossPanel.ball.x;
        double y1 = FinalBossPanel.ball.y;
        double x2 = MouseInputListener.x;
        double y2 = MouseInputListener.y;
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calculate the angle in radians
        FinalBossPanel.ballDirection.angle = Math.atan2(deltaY, deltaX);
        FinalBossPanel.ballDirection.x = FinalBossPanel.ball.x + (10 * Math.cos(FinalBossPanel.ballDirection.angle));
        FinalBossPanel.ballDirection.y = FinalBossPanel.ball.y + (10 * Math.sin(FinalBossPanel.ballDirection.angle));
    }
}

