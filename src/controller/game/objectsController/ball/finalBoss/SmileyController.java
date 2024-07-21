package controller.game.objectsController.ball.finalBoss;

import controller.game.GameController;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;

public class SmileyController {


    public static void updateSmiley() {
        if (smiley != null) {
            if (smiley.y < 100) {
                smiley.y += smiley.dy;
            } else {
                if (smiley.dx == 0) {
                    smiley.dx = 1;
                }
                if (smiley.dx > 0) {
                    if (smiley.x > 750) {
                        smiley.dx = -smiley.dx;
                    }
                }
                if (smiley.dx < 0) {
                    if (smiley.x < 600) {
                        smiley.dx = -smiley.dx;
                    }
                }
                smiley.x += smiley.dx;
            }
        }
    }

}
