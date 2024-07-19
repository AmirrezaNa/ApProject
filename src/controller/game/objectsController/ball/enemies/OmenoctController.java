package controller.game.objectsController.ball.enemies;

import controller.game.FrameOfObject;
import controller.game.Rotation;
import model.entity.enemy.OmenoctModel;


import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static controller.game.GameController.*;
import static view.phase2.GameInternalFrame.createdFrames;

public class OmenoctController {


    public static void setDirectionForOmenoct() {
        if (!omenoctEnemies.isEmpty()) {
            for (int i = 0; i < omenoctEnemies.size(); i++) {
                if (omenoctEnemies.get(i).enemyHealth > 0) {
                    if (FrameOfObject.getFrameOfBall() != -1) {
                        int x = createdFrames[FrameOfObject.getFrameOfBall()].x + createdFrames[FrameOfObject.getFrameOfBall()].width - (OmenoctModel.omenoctSize / 2);
                        int y = createdFrames[FrameOfObject.getFrameOfBall()].y + createdFrames[FrameOfObject.getFrameOfBall()].height / 2;
                        omenoctEnemies.get(i).dx = -((omenoctEnemies.get(i).x - x) / Math.sqrt(Math.pow((omenoctEnemies.get(i).x - x), 2) + Math.pow((omenoctEnemies.get(i).y - y), 2))) * OmenoctModel.enemySpeed;
                        if (y < omenoctEnemies.get(i).y) {
                            omenoctEnemies.get(i).dy = -Math.sqrt(Math.pow(OmenoctModel.enemySpeed, 2) - Math.pow(omenoctEnemies.get(i).dx, 2));
                        } else {
                            omenoctEnemies.get(i).dy = Math.sqrt(Math.pow(OmenoctModel.enemySpeed, 2) - Math.pow(omenoctEnemies.get(i).dx, 2));
                        }
                    }
                }
            }
        }
    }


    public static void updateOmenoct() {
        setDirectionForOmenoct();
        if (!omenoctEnemies.isEmpty()) {
            for (int i = 0; i < omenoctEnemies.size(); i++) {
                if (omenoctEnemies.get(i).enemyHealth > 0) {
                    omenoctEnemies.get(i).x += omenoctEnemies.get(i).dx + omenoctEnemies.get(i).ax;
                    omenoctEnemies.get(i).y += omenoctEnemies.get(i).dy + omenoctEnemies.get(i).ay;

                    if (omenoctEnemies.get(i).ax != 0) {
                        if (omenoctEnemies.get(i).ax > 0) {
                            omenoctEnemies.get(i).ax -= 0.05;
                        } else {
                            omenoctEnemies.get(i).ax += 0.05;
                        }
                    }
                    if (omenoctEnemies.get(i).ay != 0) {
                        if (omenoctEnemies.get(i).ay > 0) {
                            omenoctEnemies.get(i).ay -= 0.05;
                        } else {
                            omenoctEnemies.get(i).ay += 0.05;
                        }
                    }
                    Rotation.enemy2Rotation();
                }
            }
        }

    }


//    public static boolean isOmenoctInFrame(OmenoctModel omnoct) {
//        for (int i = 0; i < createdFrames.length; i++) {
//            int x = createdFrames[i].x;
//            int y = createdFrames[i].y;
//            int width = createdFrames[i].width;
//            int height = createdFrames[i].height;
//
//            double xMin = omnoct.xAngles[0];
//            double xMax = omnoct.xAngles[0];
//            double yMin = omnoct.yAngles[0];
//            double yMax = omnoct.yAngles[0];
//            for (int j = 0; j < 8; j++) {
//                if (omnoct.xAngles[i] < xMin) {
//                    xMin = omnoct.xAngles[i];
//                }
//                if (omnoct.xAngles[i] > xMax) {
//                    xMax = omnoct.xAngles[i];
//                }
//                if (omnoct.yAngles[i] < yMin) {
//                    yMin = omnoct.yAngles[i];
//                }
//                if (omnoct.yAngles[i] > yMax) {
//                    yMax = omnoct.yAngles[i];
//                }
//            }
//            if ((xMin >= x) && (xMax <= (x + width))
//                    && (yMin >= y) && (yMax < (y + height))) {
//                return true;
//            }
//        }
//        return false;
//    }


    public static void shotBullet() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < omenoctEnemies.size(); i++) {
                    if (omenoctEnemies.get(i).enemyHealth > 0) {
                        Point point = new Point();
                        point.setLocation(omenoctEnemies.get(i).x + OmenoctModel.distanceToCenter,
                                omenoctEnemies.get(i).y + OmenoctModel.distanceToCenter);
                        newOmenoctBullet(point);
                    }
                }
            }

        };
        timer.scheduleAtFixedRate(task, 5000, 5000);
    }

}
