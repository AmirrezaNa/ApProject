package controller.game;

import model.entity.BallModel;
import view.gameLoop.phase1.GameFrame;

import static controller.game.GameController.*;

public class GameRestart {


    // restarting the game ================================================================

    public static void restartGame() {
        numberOfBullets = bullets.size();
        bullets.clear();
        enemies2.clear();
        enemies1.clear();
        bullets.clear();
        collectibles.clear();
        ball.HP = 100;
        BallModel.ballRadius = 20;
        GameFrame.count = 0;
        GameFrame.x = 300;
        GameFrame.y = 50;
        GameFrame.width = 600;
        GameFrame.height = 600;
        WaveController.stopWave = true;
//        GameFrame.thread.interrupt();
    }
}
