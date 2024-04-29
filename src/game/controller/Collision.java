package game.controller;

import game.entity.BallModel;
import game.entity.BulletModel;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;
import game.frame.GameFrame;

public class Collision {
    public static void checkBulletHitFrame() {
        for (BulletModel bullet : GameController.bullets) {
            if (bullet.bulletHealth > 0) {

                if (bullet.x > GameFrame.width) {
                    bullet.dx = 0;
                    bullet.dy = 0;
                    bullet.bulletHealth = 0;
                    GameFrame.width += 20;
                    GameFrame.x += 5;
                } else if (bullet.y > GameFrame.height) {
                    bullet.dx = 0;
                    bullet.dy = 0;
                    bullet.bulletHealth = 0;
                    GameFrame.height += 20;
                    GameFrame.y += 5;
                } else if (bullet.x < 0) {
                    bullet.dx = 0;
                    bullet.dy = 0;
                    bullet.bulletHealth = 0;
                    GameFrame.x -= 20;
                } else if (bullet.y < 0) {
                    bullet.dx = 0;
                    bullet.dy = 0;
                    bullet.bulletHealth = 0;
                    GameFrame.y -= 20;
                    GameFrame.height += 20;
                }

            }
        }
    }

    public static void checkBallCollisionToFrame() {
        if (GameController.ball.x + BallModel.ballRadius >= GameFrame.width) {
            GameController.ball.x = GameFrame.width - BallModel.ballRadius;
        }
        if (GameController.ball.x <= 0) {
            GameController.ball.x = 0;
        }
        if (GameController.ball.y + BallModel.ballRadius >= GameFrame.height) {
            GameController.ball.y = GameFrame.height - BallModel.ballRadius;
        }
        if (GameController.ball.y <= 0) {
            GameController.ball.y = 0;
        }
    }


    // ====================================================================================


    // ===========  Here is where we are checking the intersections of different types of objects ===============


    // a method for checking intersections between enemy1 and enemy2

    public static void checkCollisionEnemy1Enemy2() {
        for (EnemyModel1 enemy1 : GameController.enemies1) {
            double xMin1 = enemy1.xAngles[0];
            double xMax1 = enemy1.xAngles[0];
            double yMin1 = enemy1.yAngles[0];
            double yMax1 = enemy1.yAngles[0];
            for (int i = 0; i < 4; i++) {
                if (enemy1.xAngles[i] < xMin1) {
                    xMin1 = enemy1.xAngles[i];
                }
                if (enemy1.xAngles[i] > xMax1) {
                    xMax1 = enemy1.xAngles[i];
                }
                if (enemy1.yAngles[i] < yMin1) {
                    yMin1 = enemy1.yAngles[i];
                }
                if (enemy1.yAngles[i] > yMax1) {
                    yMax1 = enemy1.yAngles[i];
                }
            }
            for (EnemyModel2 enemy2 : GameController.enemies2) {
                double xMin2 = enemy2.xAngles[0];
                double xMax2 = enemy2.xAngles[0];
                double yMin2 = enemy2.yAngles[0];
                double yMax2 = enemy2.yAngles[0];
                for (int i = 0; i < 3; i++) {
                    if (enemy2.xAngles[i] < xMin2) {
                        xMin2 = enemy2.xAngles[i];
                    }
                    if (enemy2.xAngles[i] > xMax2) {
                        xMax2 = enemy2.xAngles[i];
                    }
                    if (enemy2.yAngles[i] < yMin2) {
                        yMin2 = enemy2.yAngles[i];
                    }
                    if (enemy2.yAngles[i] > yMax2) {
                        yMax2 = enemy2.yAngles[i];
                    }
                }

                boolean collisionHappened = false;
                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                    // collision might have happened
                    // checking if it's actually happened
                    A:
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 2; j++) {
                            double x1 = enemy1.xAngles[i];
                            double y1 = enemy1.yAngles[i];
                            double x2 = enemy1.xAngles[i + 1];
                            double y2 = enemy1.yAngles[i + 1];
                            double x3 = enemy2.xAngles[j];
                            double y3 = enemy2.yAngles[j];
                            double x4 = enemy2.xAngles[j + 1];
                            double y4 = enemy2.yAngles[j + 1];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break A;
                            }
                        }
                    }
                    if (!collisionHappened) {
                        for (int j = 0; j < 2; j++) {
                            double x1 = enemy1.xAngles[3];
                            double y1 = enemy1.yAngles[3];
                            double x2 = enemy1.xAngles[0];
                            double y2 = enemy1.yAngles[0];
                            double x3 = enemy2.xAngles[j];
                            double y3 = enemy2.yAngles[j];
                            double x4 = enemy2.xAngles[j + 1];
                            double y4 = enemy2.yAngles[j + 1];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break;
                            }
                        }
                    }
                    if (!collisionHappened) {
                        for (int j = 0; j < 3; j++) {
                            double x1 = enemy1.xAngles[j];
                            double y1 = enemy1.yAngles[j];
                            double x2 = enemy1.xAngles[j + 1];
                            double y2 = enemy1.yAngles[j + 1];
                            double x3 = enemy2.xAngles[2];
                            double y3 = enemy2.yAngles[2];
                            double x4 = enemy2.xAngles[0];
                            double y4 = enemy2.yAngles[0];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break;
                            }
                        }
                    }
                    if (collisionHappened) {

                        //======================================================================

                    }
                }
            }
        }
    }


    // ==========================================================================================


    // this method is for checking the intersections between enemies of type1 with each other
    public static void checkCollisionEnemy1Enemy1() {
        for (int i = 0; i < GameController.enemies1.size() - 1; i++) {
            double xMin1 = GameController.enemies1.get(i).xAngles[0];
            double xMax1 = GameController.enemies1.get(i).xAngles[0];
            double yMin1 = GameController.enemies1.get(i).yAngles[0];
            double yMax1 = GameController.enemies1.get(i).yAngles[0];
            for (int k = 0; k < 4; k++) {
                if (xMin1 > GameController.enemies1.get(i).xAngles[k]) {
                    xMin1 = GameController.enemies1.get(i).xAngles[k];
                }
                if (xMax1 < GameController.enemies1.get(i).xAngles[k]) {
                    xMax1 = GameController.enemies1.get(i).xAngles[k];
                }
                if (yMin1 > GameController.enemies1.get(i).yAngles[k]) {
                    yMin1 = GameController.enemies1.get(i).yAngles[k];
                }
                if (yMax1 < GameController.enemies1.get(i).yAngles[k]) {
                    yMax1 = GameController.enemies1.get(i).yAngles[k];
                }
            }
            for (int j = i + 1; j < GameController.enemies1.size(); j++) {
                double xMin2 = GameController.enemies1.get(j).xAngles[0];
                double xMax2 = GameController.enemies1.get(j).xAngles[0];
                double yMin2 = GameController.enemies1.get(j).yAngles[0];
                double yMax2 = GameController.enemies1.get(j).yAngles[0];
                for (int k = 0; k < 4; k++) {
                    if (xMin2 > GameController.enemies1.get(j).xAngles[k]) {
                        xMin2 = GameController.enemies1.get(j).xAngles[k];
                    }
                    if (xMax2 < GameController.enemies1.get(j).xAngles[k]) {
                        xMax2 = GameController.enemies1.get(j).xAngles[k];
                    }
                    if (yMin2 > GameController.enemies1.get(j).yAngles[k]) {
                        yMin2 = GameController.enemies1.get(j).yAngles[k];
                    }
                    if (yMax2 < GameController.enemies1.get(j).yAngles[k]) {
                        yMax2 = GameController.enemies1.get(j).yAngles[k];
                    }
                }

                boolean collisionHappened = false;
                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    for (int t = 0; t < 3; t++) {
                        double x1 = GameController.enemies1.get(i).xAngles[t];
                        double y1 = GameController.enemies1.get(i).yAngles[t];
                        double x2 = GameController.enemies1.get(i).xAngles[t + 1];
                        double y2 = GameController.enemies1.get(i).yAngles[t + 1];
                        double x3 = GameController.enemies1.get(j).xAngles[t];
                        double y3 = GameController.enemies1.get(j).yAngles[t];
                        double x4 = GameController.enemies1.get(j).xAngles[t + 1];
                        double y4 = GameController.enemies1.get(j).yAngles[t + 1];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break;
                        }
                    }
                    if (!collisionHappened) {
                        for (int t = 0; t < 3; t++) {
                            double x1 = GameController.enemies1.get(i).xAngles[3];
                            double y1 = GameController.enemies1.get(i).yAngles[3];
                            double x2 = GameController.enemies1.get(i).xAngles[0];
                            double y2 = GameController.enemies1.get(i).yAngles[0];
                            double x3 = GameController.enemies1.get(j).xAngles[t];
                            double y3 = GameController.enemies1.get(j).yAngles[t];
                            double x4 = GameController.enemies1.get(j).xAngles[t + 1];
                            double y4 = GameController.enemies1.get(j).yAngles[t + 1];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break;
                            }
                        }
                    }
                    if (!collisionHappened) {
                        for (int t = 0; t < 3; t++) {
                            double x1 = GameController.enemies1.get(i).xAngles[t];
                            double y1 = GameController.enemies1.get(i).yAngles[t];
                            double x2 = GameController.enemies1.get(i).xAngles[t + 1];
                            double y2 = GameController.enemies1.get(i).yAngles[t + 1];
                            double x3 = GameController.enemies1.get(j).xAngles[3];
                            double y3 = GameController.enemies1.get(j).yAngles[3];
                            double x4 = GameController.enemies1.get(j).xAngles[0];
                            double y4 = GameController.enemies1.get(j).yAngles[0];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break;
                            }
                        }
                    }
                    if (collisionHappened) {


                    }

                }
            }
        }
    }

    // this method is for checking the intersections between enemies of type2 with each other

    public static void checkCollisionEnemy2Enemy2() {
        for (int i = 0; i < GameController.enemies2.size() - 1; i++) {
            double xMin1 = GameController.enemies2.get(i).xAngles[0];
            double xMax1 = GameController.enemies2.get(i).xAngles[0];
            double yMin1 = GameController.enemies2.get(i).yAngles[0];
            double yMax1 = GameController.enemies2.get(i).yAngles[0];
            for (int k = 0; k < 3; k++) {
                if (xMin1 > GameController.enemies2.get(i).xAngles[k]) {
                    xMin1 = GameController.enemies2.get(i).xAngles[k];
                }
                if (xMax1 < GameController.enemies2.get(i).xAngles[k]) {
                    xMax1 = GameController.enemies2.get(i).xAngles[k];
                }
                if (yMin1 > GameController.enemies2.get(i).yAngles[k]) {
                    yMin1 = GameController.enemies2.get(i).yAngles[k];
                }
                if (yMax1 < GameController.enemies2.get(i).yAngles[k]) {
                    yMax1 = GameController.enemies2.get(i).yAngles[k];
                }
            }
            for (int j = i + 1; j < GameController.enemies2.size(); j++) {
                double xMin2 = GameController.enemies2.get(j).xAngles[0];
                double xMax2 = GameController.enemies2.get(j).xAngles[0];
                double yMin2 = GameController.enemies2.get(j).yAngles[0];
                double yMax2 = GameController.enemies2.get(j).yAngles[0];
                for (int k = 0; k < 3; k++) {
                    if (xMin2 > GameController.enemies2.get(j).xAngles[k]) {
                        xMin2 = GameController.enemies2.get(j).xAngles[k];
                    }
                    if (xMax2 < GameController.enemies2.get(j).xAngles[k]) {
                        xMax2 = GameController.enemies2.get(j).xAngles[k];
                    }
                    if (yMin2 > GameController.enemies2.get(j).yAngles[k]) {
                        yMin2 = GameController.enemies2.get(j).yAngles[k];
                    }
                    if (yMax2 < GameController.enemies2.get(j).yAngles[k]) {
                        yMax2 = GameController.enemies2.get(j).yAngles[k];
                    }
                }

                boolean collisionHappened = false;
                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    for (int t = 0; t < 2; t++) {
                        double x1 = GameController.enemies1.get(i).xAngles[t];
                        double y1 = GameController.enemies1.get(i).yAngles[t];
                        double x2 = GameController.enemies1.get(i).xAngles[t + 1];
                        double y2 = GameController.enemies1.get(i).yAngles[t + 1];
                        double x3 = GameController.enemies1.get(j).xAngles[t];
                        double y3 = GameController.enemies1.get(j).yAngles[t];
                        double x4 = GameController.enemies1.get(j).xAngles[t + 1];
                        double y4 = GameController.enemies1.get(j).yAngles[t + 1];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break;
                        }
                    }
                    if (!collisionHappened) {
                        for (int t = 0; t < 2; t++) {
                            double x1 = GameController.enemies1.get(i).xAngles[2];
                            double y1 = GameController.enemies1.get(i).yAngles[2];
                            double x2 = GameController.enemies1.get(i).xAngles[0];
                            double y2 = GameController.enemies1.get(i).yAngles[0];
                            double x3 = GameController.enemies1.get(j).xAngles[t];
                            double y3 = GameController.enemies1.get(j).yAngles[t];
                            double x4 = GameController.enemies1.get(j).xAngles[t + 1];
                            double y4 = GameController.enemies1.get(j).yAngles[t + 1];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break;
                            }
                        }
                    }
                    if (!collisionHappened) {
                        for (int t = 0; t < 2; t++) {
                            double x1 = GameController.enemies1.get(i).xAngles[t];
                            double y1 = GameController.enemies1.get(i).yAngles[t];
                            double x2 = GameController.enemies1.get(i).xAngles[t + 1];
                            double y2 = GameController.enemies1.get(i).yAngles[t + 1];
                            double x3 = GameController.enemies1.get(j).xAngles[2];
                            double y3 = GameController.enemies1.get(j).yAngles[2];
                            double x4 = GameController.enemies1.get(j).xAngles[0];
                            double y4 = GameController.enemies1.get(j).yAngles[0];
                            collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                            if (collisionHappened) {
                                break;
                            }
                        }
                    }
                    if (collisionHappened) {


                    }

                }
            }
        }
    }


    // a method for checking intersections between enemy1 and the ball

    public static void checkCollisionBallEnemy1() {
        double xMin1 = GameController.ball.x;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        double[] xAnglesBall = {GameController.ball.x, GameController.ball.x + BallModel.ballRadius,
                GameController.ball.x + BallModel.ballRadius, GameController.ball.x};
        double[] yAnglesBall = {GameController.ball.y, GameController.ball.y,
                GameController.ball.y + BallModel.ballRadius, GameController.ball.y + BallModel.ballRadius};
        for (EnemyModel1 enemy1 : GameController.enemies1) {
            double xMin2 = enemy1.xAngles[0];
            double xMax2 = enemy1.xAngles[0];
            double yMin2 = enemy1.yAngles[0];
            double yMax2 = enemy1.yAngles[0];
            for (int i = 0; i < 4; i++) {
                if (enemy1.xAngles[i] < xMin2) {
                    xMin2 = enemy1.xAngles[i];
                }
                if (enemy1.xAngles[i] > xMax2) {
                    xMax2 = enemy1.xAngles[i];
                }
                if (enemy1.yAngles[i] < yMin2) {
                    yMin2 = enemy1.yAngles[i];
                }
                if (enemy1.yAngles[i] > yMax2) {
                    yMax2 = enemy1.yAngles[i];
                }
            }


            boolean collisionHappened = false;
            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                A:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        double x1 = enemy1.xAngles[i];
                        double y1 = enemy1.yAngles[i];
                        double x2 = enemy1.xAngles[i + 1];
                        double y2 = enemy1.yAngles[i + 1];
                        double x3 = xAnglesBall[j];
                        double y3 = yAnglesBall[j];
                        double x4 = xAnglesBall[j + 1];
                        double y4 = yAnglesBall[j + 1];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break A;
                        }
                    }
                }

                if (!collisionHappened) {
                    for (int j = 0; j < 3; j++) {
                        double x1 = enemy1.xAngles[3];
                        double y1 = enemy1.yAngles[3];
                        double x2 = enemy1.xAngles[0];
                        double y2 = enemy1.yAngles[0];
                        double x3 = xAnglesBall[j];
                        double y3 = yAnglesBall[j];
                        double x4 = xAnglesBall[j + 1];
                        double y4 = yAnglesBall[j + 1];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break;
                        }
                    }
                }
                if (!collisionHappened) {
                    for (int i = 0; i < 3; i++) {
                        double x1 = enemy1.xAngles[i];
                        double y1 = enemy1.yAngles[i];
                        double x2 = enemy1.xAngles[i + 1];
                        double y2 = enemy1.yAngles[i + 1];
                        double x3 = xAnglesBall[3];
                        double y3 = yAnglesBall[3];
                        double x4 = xAnglesBall[0];
                        double y4 = yAnglesBall[0];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break;
                        }
                    }
                }

                if (collisionHappened) {


                }

            }

        }
    }



    // a method for checking intersections between enemy2 and the ball

    public static void checkCollisionBallEnemy2() {
        double xMin1 = GameController.ball.x;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        double[] xAnglesBall = {GameController.ball.x, GameController.ball.x + BallModel.ballRadius,
                GameController.ball.x + BallModel.ballRadius, GameController.ball.x};
        double[] yAnglesBall = {GameController.ball.y, GameController.ball.y,
                GameController.ball.y + BallModel.ballRadius, GameController.ball.y + BallModel.ballRadius};
        for (EnemyModel2 enemy2 : GameController.enemies2) {
            double xMin2 = enemy2.xAngles[0];
            double xMax2 = enemy2.xAngles[0];
            double yMin2 = enemy2.yAngles[0];
            double yMax2 = enemy2.yAngles[0];
            for (int i = 0; i < 3; i++) {
                if (enemy2.xAngles[i] < xMin2) {
                    xMin2 = enemy2.xAngles[i];
                }
                if (enemy2.xAngles[i] > xMax2) {
                    xMax2 = enemy2.xAngles[i];
                }
                if (enemy2.yAngles[i] < yMin2) {
                    yMin2 = enemy2.yAngles[i];
                }
                if (enemy2.yAngles[i] > yMax2) {
                    yMax2 = enemy2.yAngles[i];
                }
            }


            boolean collisionHappened = false;
            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                A:
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        double x1 = enemy2.xAngles[i];
                        double y1 = enemy2.yAngles[i];
                        double x2 = enemy2.xAngles[i + 1];
                        double y2 = enemy2.yAngles[i + 1];
                        double x3 = xAnglesBall[j];
                        double y3 = yAnglesBall[j];
                        double x4 = xAnglesBall[j + 1];
                        double y4 = yAnglesBall[j + 1];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break A;
                        }
                    }
                }

                if (!collisionHappened) {
                    for (int j = 0; j < 3; j++) {
                        double x1 = enemy2.xAngles[3];
                        double y1 = enemy2.yAngles[3];
                        double x2 = enemy2.xAngles[0];
                        double y2 = enemy2.yAngles[0];
                        double x3 = xAnglesBall[j];
                        double y3 = yAnglesBall[j];
                        double x4 = xAnglesBall[j + 1];
                        double y4 = yAnglesBall[j + 1];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break;
                        }
                    }
                }
                if (!collisionHappened) {
                    for (int i = 0; i < 2; i++) {
                        double x1 = enemy2.xAngles[i];
                        double y1 = enemy2.yAngles[i];
                        double x2 = enemy2.xAngles[i + 1];
                        double y2 = enemy2.yAngles[i + 1];
                        double x3 = xAnglesBall[3];
                        double y3 = yAnglesBall[3];
                        double x4 = xAnglesBall[0];
                        double y4 = yAnglesBall[0];
                        collisionHappened = doLinesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
                        if (collisionHappened) {
                            break;
                        }
                    }
                }

                if (collisionHappened) {


                }

            }

        }
    }


    // ============================================================================================


    // a method for checking if two sides have intersection or not

    public static boolean doLinesIntersect(double x1, double y1, double x2, double y2,
                                           double x3, double y3, double x4, double y4) {
        double denominator = ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));

        if (denominator == 0) {
            return false; // Lines are parallel
        }

        double intersectX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        // Check if the intersection point lies within both line segments
        if (intersectX >= Math.min(x1, x2) && intersectX <= Math.max(x1, x2) &&
                intersectX >= Math.min(x3, x4) && intersectX <= Math.max(x3, x4) &&
                intersectY >= Math.min(y1, y2) && intersectY <= Math.max(y1, y2) &&
                intersectY >= Math.min(y3, y4) && intersectY <= Math.max(y3, y4)) {
            return true;
        }

        return false;
    }
}
