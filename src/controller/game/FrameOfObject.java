package controller.game;

import view.phase2.GameInternalFrame;
import view.phase2.GamePanel2;

import static view.phase2.GameInternalFrame.createdFrames;

public class FrameOfObject {

    public static int getFrameOfBall() {
        for (int i = 0; i < createdFrames.length; i++) {
            int xMin = createdFrames[i].x;
            int yMin = createdFrames[i].y;
            int xMax = createdFrames[i].x + createdFrames[i].width;
            int yMax = createdFrames[i].y + createdFrames[i].height;
            if (GamePanel2.ball.x > xMin && GamePanel2.ball.x < xMax
                    && GamePanel2.ball.y > yMin && GamePanel2.ball.y < yMax) {
                return i;
            }
        }
        return -1;
    }
}
