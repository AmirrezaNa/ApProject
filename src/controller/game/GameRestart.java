package controller.game;

import view.game.GameFrame;

import static controller.game.GameController.*;

public class GameRestart {


    // restarting the game ================================================================

    public static void restartGame() {
        bullets.clear();
        enemies2.clear();
        enemies1.clear();
        bullets.clear();
        collectibles.clear();
        ball.HP = 100;
        GameFrame.count = 0;
        GameFrame.x = 300;
        GameFrame.y = 50;
        GameFrame.width = 600;
        GameFrame.height = 600;
    }
}
