package controller.game;

import view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2;

import static controller.game.GameController.bullets;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame.createdFrames;

public class FrameOfObject {




    public static void getCollidedFrames() {
        for (int i = 0; i < createdFrames.length; i++) {
            if (i == getFrameOfBall()) {
                continue;
            }
            int k = getFrameOfBall();
            if (k != -1) {
                int xMini = createdFrames[i].x + 3;
                int yMini = createdFrames[i].y + 3;
                int xMaxi = createdFrames[i].x + createdFrames[i].width - 3;
                int yMaxi = createdFrames[i].y + createdFrames[i].height - 3;


                int xMink = createdFrames[k].x + 3;
                int yMink = createdFrames[k].y + 3;
                int xMaxk = createdFrames[k].x + createdFrames[k].width - 3;
                int yMaxk = createdFrames[k].y + createdFrames[k].height - 3;

                if (((xMini >= xMink && xMini <= xMaxk) || (xMink >= xMini && xMink <= xMaxi))
                        && ((yMini >= yMink && yMini <= yMaxk) || (yMink >= yMini && yMink <= yMaxi))) {
                    GameInternalFrame.collidedFrames.add(i);
                } else {
                    if (GameInternalFrame.collidedFrames.contains(i)) {
                        GameInternalFrame.collidedFrames.remove(i);
                    }
                }
            }
        }

    }



    public static int getFrameOfBall() {
        if (GamePanel2.framesCreated) {
            for (int i = 0; i < createdFrames.length; i++) {
                int xMin = createdFrames[i].x;
                int yMin = createdFrames[i].y;
                int xMax = createdFrames[i].x + createdFrames[i].width;
                int yMax = createdFrames[i].y + createdFrames[i].height;
                if (GameController.ball.x > xMin && GameController.ball.x < xMax
                        && GameController.ball.y > yMin && GameController.ball.y < yMax) {
                    return i;
                }
            }
        }
        return -1;
    }


    public static void FrameOfBullet() {
        for (int j = 0; j < bullets.size(); j++) {
            for (int i = 0; i < createdFrames.length; i++) {
                if (GameInternalFrame.collidedFrames.contains(i)) {
                    int xMin = createdFrames[i].x;
                    int yMin = createdFrames[i].y;
                    int xMax = createdFrames[i].x + createdFrames[i].width;
                    int yMax = createdFrames[i].y + createdFrames[i].height;
                    if (bullets.get(j).x > xMin && bullets.get(j).x < xMax
                            && bullets.get(j).y > yMin && bullets.get(j).y < yMax) {
                        bullets.get(j).bulletFrame = i;
                    }
                }

            }
        }
    }
}
