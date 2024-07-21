package controller.game.objectsController.ball.finalBoss;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.finalBoss.EpsilonFrame.epsilonFrame;

public class PunchController {

    public static void updatePunch() {
        if (punch != null) {
            if (smiley.powerPunchAttack || smiley.quakeAttack) {
                if ((punch.y < epsilonFrame.y)) {
                    punch.y += punch.dy;
                }

            } else if (punch.y < 250) {
                punch.y += punch.dy;
            } else {
                punch.y -= punch.dy;
            }
        }
    }
}
