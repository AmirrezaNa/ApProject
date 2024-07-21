package controller.game.collisions.phase2;

import controller.game.FrameOfObject;
import controller.game.Impact;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BulletController;
import controller.game.objectsController.ball.enemies.normalAndMiniBoss.BarricadosController2;
import model.entity.BallModel;
import model.entity.BulletModel;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GameFrame2;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2;

import static controller.game.GameController.*;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GameInternalFrame.createdFrames;

public class FrameCollisions2 {

    public static void checkFramesCollisions2() {
        FrameOfObject.getCollidedFrames();
        checkBulletHitFrames();
        checkBallCollisionToFrames2();
        checkEnemyBulletOut();
    }

    public static boolean frameCollided(int k) {//this method checks if a frame has a collision with another frame
        int numOfFramesCollided = 0;
        for (int i = 0; i < createdFrames.length; i++) {
            int x = createdFrames[i].x;
            int y = createdFrames[i].y;
            int width = createdFrames[i].width;
            int height = createdFrames[i].height;
            if ((ball.x >= (x + BallModel.ballRadius)) && (ball.x <= (x + width - BallModel.ballRadius))
                    && (ball.y > (y + BallModel.ballRadius)) && (ball.y < (y + height - BallModel.ballRadius))) {
                numOfFramesCollided++;
            }
        }
        return numOfFramesCollided >= 2;
    }


    public static void checkBulletHitFrames() {
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).bulletHealth > 0) {
                    int k;
                    if (!GameInternalFrame.collidedFrames.isEmpty()) {
                        k = bullets.get(i).bulletFrame;
                    } else {
                        k = FrameOfObject.getFrameOfBall();
                    }
                    if (k != -1) {

                        if (bullets.get(i).x > createdFrames[k].x + createdFrames[k].width) {
                            if (!BulletController.isBulletInAFrame(bullets.get(i))) {
                                bullets.get(i).dx = 0;
                                bullets.get(i).dy = 0;
                                bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k)) {

                                    createdFrames[k].width += 20;
                                    createdFrames[k].x += 5;
                                }
                                Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                            }

                        } else if (bullets.get(i).y > createdFrames[k].y + createdFrames[k].height) {
                            if (!BulletController.isBulletInAFrame(bullets.get(i))) {
                                bullets.get(i).dx = 0;
                                bullets.get(i).dy = 0;
                                bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k)) {
                                    createdFrames[k].height += 20;
                                    createdFrames[k].y += 5;
                                }
                                Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                            }

                        } else if (bullets.get(i).x < createdFrames[k].x) {
                            if (!BulletController.isBulletInAFrame(bullets.get(i))) {
                                bullets.get(i).dx = 0;
                                bullets.get(i).dy = 0;
                                bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k)) {

                                    createdFrames[k].x -= 20;
                                    createdFrames[k].width += 10;
                                }
                                Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                            }

                        } else if (bullets.get(i).y < createdFrames[k].y) {
                            if (!BulletController.isBulletInAFrame(bullets.get(i))) {
                                bullets.get(i).dx = 0;
                                bullets.get(i).dy = 0;
                                bullets.get(i).bulletHealth = 0;
                                if (!BarricadosController2.isBarricados2InFrame(k)) {

                                    createdFrames[k].y -= 20;
                                    createdFrames[k].height += 10;
                                }
                                Impact.turnOnImpact(bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).x + ((double) BulletModel.bulletSize / 2),
                                        bullets.get(i).y + ((double) BulletModel.bulletSize / 2));
                            }

                        }
                    }

                }
            }
        }
    }


    public static void checkBallCollisionToFrames2() {
        int k = FrameOfObject.getFrameOfBall();
        if (k != -1) {
            if (ball.x + BallModel.ballRadius >= createdFrames[k].x + createdFrames[k].width) {
                ballCanMoveOn();
                if (!GamePanel2.ballBetweenFrames && !BallController.checkIfBallIsInFrame()) {
                    Impact.turnOnImpact(ball.x + BallModel.ballRadius,
                            ball.y,
                            ball.x + BallModel.ballRadius,
                            ball.y);
                }
            }
            if (ball.x - BallModel.ballRadius <= createdFrames[k].x) {
                ballCanMoveOn();
                if (!GamePanel2.ballBetweenFrames && !BallController.checkIfBallIsInFrame()) {
                    Impact.turnOnImpact(ball.x - BallModel.ballRadius,
                            ball.y,
                            ball.x - BallModel.ballRadius,
                            ball.y);
                }
            }
            if (ball.y + BallModel.ballRadius >= createdFrames[k].y + createdFrames[k].height) {
                ballCanMoveOn();
                if (!GamePanel2.ballBetweenFrames && !BallController.checkIfBallIsInFrame()) {
                    Impact.turnOnImpact(ball.x,
                            ball.y + BallModel.ballRadius,
                            ball.x,
                            ball.y + BallModel.ballRadius);
                }
            }
            if (ball.y - BallModel.ballRadius <= createdFrames[k].y) {
                ballCanMoveOn();
                if (!GamePanel2.ballBetweenFrames && !BallController.checkIfBallIsInFrame()) {
                    Impact.turnOnImpact(ball.x,
                            ball.y - BallModel.ballRadius,
                            ball.x,
                            ball.y - BallModel.ballRadius);
                }
            }
        }
    }

    public static void ballCanMoveOn() {
        int currentFrame = FrameOfObject.getFrameOfBall();

        for (int i = 0; i < createdFrames.length; i++) {
            if (i == currentFrame) {
                continue;
            }
            if (ball.x >= createdFrames[i].x && (ball.x <= (createdFrames[i].x + createdFrames[i].width))
                    && ((Math.abs(ball.y - (createdFrames[i].y)) <= BallModel.ballRadius)
                    || (Math.abs(ball.y - (createdFrames[i].y + createdFrames[i].height)) <= BallModel.ballRadius))) {

                GamePanel2.ballBetweenFrames = true;
                break;

            } else if (ball.y >= createdFrames[i].y && (ball.y <= (createdFrames[i].y + createdFrames[i].height))
                    && ((Math.abs(ball.x - (createdFrames[i].x)) <= BallModel.ballRadius)
                    || (Math.abs(ball.x - (createdFrames[i].x + createdFrames[i].width)) <= BallModel.ballRadius))) {
                GamePanel2.ballBetweenFrames = true;
                break;
            } else {
                GamePanel2.ballBetweenFrames = false;
            }
        }
    }


    public static void checkEnemyBulletOut() {
        if (!enemyBullets.isEmpty()) {
            for (int i = 0; i < enemyBullets.size(); i++) {
                if (enemyBullets.get(i).x < 0 || enemyBullets.get(i).x > GameFrame2.width
                        || enemyBullets.get(i).y < 0 || enemyBullets.get(i).y > GameFrame2.height) {
                    enemyBullets.get(i).bulletHealth = 0;
                }
            }
        }
    }
}


