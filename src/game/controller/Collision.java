package game.controller;

import game.Player;
import game.entity.BallModel;
import game.entity.BulletModel;
import game.entity.Collectible;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;
import game.frame.GameFrame;

public class Collision {
    public static void checkBulletHitFrame() {
        if (!GameController.bullets.isEmpty()) {
            for (BulletModel bullet : GameController.bullets) {
                if (bullet.bulletHealth > 0) {

                    if (bullet.x > GameFrame.width) {
                        bullet.dx = 0;
                        bullet.dy = 0;
                        bullet.bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.width += 20;
                            GameFrame.x += 5;
                            Impact.turnOnImpact(bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2),
                                    bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (bullet.y > GameFrame.height) {
                        bullet.dx = 0;
                        bullet.dy = 0;
                        bullet.bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.height += 20;
                            GameFrame.y += 5;
                            Impact.turnOnImpact(bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2),
                                    bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (bullet.x < 0) {
                        bullet.dx = 0;
                        bullet.dy = 0;
                        bullet.bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.x -= 20;
                            GameFrame.width += 10;
                            Impact.turnOnImpact(bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2),
                                    bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2));
                        }

                    } else if (bullet.y < 0) {
                        bullet.dx = 0;
                        bullet.dy = 0;
                        bullet.bulletHealth = 0;
                        if (!GameFrame.countDown) {
                            GameFrame.y -= 20;
                            GameFrame.height += 10;
                            Impact.turnOnImpact(bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2),
                                    bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2));
                        }

                    }

                }
            }
        }
    }

    public static void checkBallCollisionToFrame() {
        if (GameController.ball.x + BallModel.ballRadius >= GameFrame.width) {
            GameController.ball.x = GameFrame.width - BallModel.ballRadius;
            Impact.turnOnImpact(GameController.ball.x + BallModel.ballRadius,
                    GameController.ball.y + ((double) BallModel.ballRadius /2),
                    GameController.ball.x + BallModel.ballRadius,
                    GameController.ball.y + ((double) BallModel.ballRadius /2));
        }
        if (GameController.ball.x <= 0) {
            GameController.ball.x = 0;
            Impact.turnOnImpact(GameController.ball.x,
                    GameController.ball.y + ((double) BallModel.ballRadius /2),
                    GameController.ball.x,
                    GameController.ball.y + ((double) BallModel.ballRadius /2));
        }
        if (GameController.ball.y + BallModel.ballRadius >= GameFrame.height) {
            GameController.ball.y = GameFrame.height - BallModel.ballRadius;
            Impact.turnOnImpact(GameController.ball.x + ((double) BallModel.ballRadius /2),
                    GameController.ball.y + BallModel.ballRadius,
                    GameController.ball.x + ((double) BallModel.ballRadius /2),
                    GameController.ball.y + BallModel.ballRadius);
        }
        if (GameController.ball.y <= 0) {
            GameController.ball.y = 0;
            Impact.turnOnImpact(GameController.ball.x + ((double) BallModel.ballRadius /2),
                    GameController.ball.y,
                    GameController.ball.x + ((double) BallModel.ballRadius /2),
                    GameController.ball.y);
        }
    }


    public static void checkEnemy1CollisionToFrame() {
        if (!GameController.enemies1.isEmpty()) {
            for (EnemyModel1 enemy1 : GameController.enemies1) {
                if (enemy1.enemyHealth > 0) {

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
                    if (xMax1 >= GameFrame.width) {
                        Impact.turnOnImpact(enemy1.x + enemy1.enemy1Size,
                                enemy1.y,
                                enemy1.x + enemy1.enemy1Size,
                                enemy1.y);
                    }
                    if (xMin1 <= 0) {
                        Impact.turnOnImpact(enemy1.x - enemy1.enemy1Size,
                                enemy1.y,
                                enemy1.x - enemy1.enemy1Size,
                                enemy1.y);
                    }
                    if (yMax1 >= GameFrame.height) {
                        Impact.turnOnImpact(enemy1.x,
                                enemy1.y + enemy1.enemy1Size,
                                enemy1.x,
                                enemy1.y + enemy1.enemy1Size);
                    }
                    if (yMin1 <= 0) {
                        Impact.turnOnImpact(enemy1.x,
                                enemy1.y - enemy1.enemy1Size,
                                enemy1.x,
                                enemy1.y - enemy1.enemy1Size);
                    }
                }
            }
        }
    }


    public static void checkEnemy2CollisionToFrame() {
        if (!GameController.enemies2.isEmpty()) {
            for (EnemyModel2 enemy2 : GameController.enemies2) {
                if (enemy2.enemyHealth > 0) {

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
                    if (xMax2 >= GameFrame.width) {
                        Impact.turnOnImpact(enemy2.x + enemy2.enemy2Size,
                                enemy2.y,
                                enemy2.x + enemy2.enemy2Size,
                                enemy2.y);
                    }
                    if (xMin2 <= 0) {
                        Impact.turnOnImpact(enemy2.x - enemy2.enemy2Size,
                                enemy2.y,
                                enemy2.x - enemy2.enemy2Size,
                                enemy2.y);
                    }
                    if (yMax2 >= GameFrame.height) {
                        Impact.turnOnImpact(enemy2.x,
                                enemy2.y + enemy2.enemy2Size,
                                enemy2.x,
                                enemy2.y + enemy2.enemy2Size);
                    }
                    if (yMin2 <= 0) {
                        Impact.turnOnImpact(enemy2.x,
                                enemy2.y - enemy2.enemy2Size,
                                enemy2.x,
                                enemy2.y - enemy2.enemy2Size);
                    }
                }
            }
        }
    }


    // ====================================================================================


    // ===========  Here is where we are checking the intersections of different types of objects ===============


    // a method for checking intersections between enemy1 and enemy2

    public static void checkCollisionEnemy1Enemy2() {
        for (EnemyModel1 enemy1 : GameController.enemies1) {
            if (enemy1.enemyHealth > 0) {

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
                    if (enemy2.enemyHealth > 0) {

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

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                            enemy1.dash = false;
                            Impact.turnOnImpact(enemy1.x, enemy1.y, enemy2.x, enemy2.y);
                        }
                    }
                }
            }
        }
    }


    // ==========================================================================================


    // this method is for checking the intersections between enemies of type1 with each other
    public static void checkCollisionEnemy1Enemy1() {
        for (int i = 0; i < GameController.enemies1.size() - 1; i++) {
            if (GameController.enemies1.get(i).enemyHealth > 0) {

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
                    if (GameController.enemies1.get(j).enemyHealth > 0) {

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

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            GameController.enemies1.get(i).dash = false;
                            GameController.enemies1.get(j).dash = false;
                            Impact.turnOnImpact(GameController.enemies1.get(i).x, GameController.enemies1.get(i).y,
                                    GameController.enemies1.get(j).x, GameController.enemies1.get(j).y);

                        }
                    }
                }
            }
        }
    }

    // this method is for checking the intersections between enemies of type2 with each other

    public static void checkCollisionEnemy2Enemy2() {
        for (int i = 0; i < GameController.enemies2.size() - 1; i++) {
            if (GameController.enemies2.get(i).enemyHealth > 0) {

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
                    if (GameController.enemies2.get(j).enemyHealth > 0) {

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

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            Impact.turnOnImpact(GameController.enemies2.get(i).x, GameController.enemies2.get(i).y,
                                    GameController.enemies2.get(j).x, GameController.enemies2.get(j).y);

                        }
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
        for (EnemyModel1 enemy1 : GameController.enemies1) {
            if (enemy1.enemyHealth > 0) {

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

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                    boolean angleCollided = false;
                    for (int i = 0; i < 4; i++) {
                        if ((xMin1 <= enemy1.xAngles[i]) &&
                                (xMax1 >= enemy1.xAngles[i]) &&
                                (yMin1 <= enemy1.yAngles[i]) &&
                                (yMax1 >= enemy1.yAngles[i])) {
                            angleCollided = true;
                        }
                    }
                    if (angleCollided) {
                        Player.HP -= 6;
                    }

                    enemy1.dash = false;
                    enemy1.dAngle = Math.PI;
                    Impact.turnOnImpact(GameController.ball.x + ((double) BallModel.ballRadius / 2),
                            GameController.ball.y + ((double) BallModel.ballRadius / 2),
                            enemy1.x + ((double) enemy1.enemy1Size / 2),
                            enemy1.y + ((double) enemy1.enemy1Size / 2));

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
        for (EnemyModel2 enemy2 : GameController.enemies2) {
            if (enemy2.enemyHealth > 0) {

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

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    boolean angleCollided = false;
                    for (int i = 0; i < 3; i++) {
                        if ((xMin1 <= enemy2.xAngles[i]) &&
                                (xMax1 >= enemy2.xAngles[i]) &&
                                (yMin1 <= enemy2.yAngles[i]) &&
                                (yMax1 >= enemy2.yAngles[i])) {
                            angleCollided = true;
                        }
                    }
                    if (angleCollided) {
                        Player.HP -= 10;
                    }
                    enemy2.dAngle = Math.PI;
                    Impact.turnOnImpact(GameController.ball.x + ((double) BallModel.ballRadius / 2),
                            GameController.ball.y + ((double) BallModel.ballRadius / 2),
                            enemy2.x + ((double) enemy2.enemy2Size / 2),
                            enemy2.y + ((double) enemy2.enemy2Size / 2));

                }
            }

        }
    }

    // =================================================================================

    public static void checkCollisionBulletEnemy1() {
        for (BulletModel bullet : GameController.bullets) {
            if (bullet.bulletHealth > 0) {

                double xMin1 = bullet.x;
                double xMax1 = bullet.x + BulletModel.bulletSize;
                double yMin1 = bullet.y;
                double yMax1 = bullet.y + BulletModel.bulletSize;
                for (EnemyModel1 enemy1 : GameController.enemies1) {
                    if (enemy1.enemyHealth > 0) {

                        double xMin2 = enemy1.xAngles[0];
                        double xMax2 = enemy1.xAngles[0];
                        double yMin2 = enemy1.yAngles[0];
                        double yMax2 = enemy1.yAngles[0];
                        for (int i = 0; i < 3; i++) {
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

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            bullet.bulletHealth = 0;
                            enemy1.enemyHealth -= 5;
                            if (enemy1.enemyHealth <= 0) {
                                GameController.newCollectible(enemy1.x, enemy1.y);
                            }
                            Impact.turnOnImpact(bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2),
                                    enemy1.x + ((double) enemy1.enemy1Size / 2),
                                    enemy1.y + ((double) enemy1.enemy1Size / 2));

                        }
                    }
                }
            }
        }
    }

    public static void checkCollisionBulletEnemy2() {
        for (BulletModel bullet : GameController.bullets) {
            if (bullet.bulletHealth > 0) {

                double xMin1 = bullet.x;
                double xMax1 = bullet.x + BulletModel.bulletSize;
                double yMin1 = bullet.y;
                double yMax1 = bullet.y + BulletModel.bulletSize;
                for (EnemyModel2 enemy2 : GameController.enemies2) {
                    if (enemy2.enemyHealth > 0) {

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

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                            bullet.bulletHealth = 0;
                            enemy2.enemyHealth -= 5;
                            if (enemy2.enemyHealth <= 0) {
                                GameController.newCollectible(enemy2.x, enemy2.y);
                                GameController.newCollectible(enemy2.x + Collectible.collectibleSize, enemy2.y + Collectible.collectibleSize);
                            }
                            Impact.turnOnImpact(bullet.x + ((double) BulletModel.bulletSize / 2),
                                    bullet.y + ((double) BulletModel.bulletSize / 2),
                                    enemy2.x + ((double) enemy2.enemy2Size / 2),
                                    enemy2.y + ((double) enemy2.enemy2Size / 2));

                        }
                    }
                }
            }
        }
    }

    // ===============================================================================


    public static void checkCollisionBallCollectible() {

            double xMin1 = GameController.ball.x;
            double xMax1 = GameController.ball.x + BallModel.ballRadius;
            double yMin1 = GameController.ball.y;
            double yMax1 = GameController.ball.y + BallModel.ballRadius;
            for (Collectible collectible : GameController.collectibles) {
                if (collectible.collectibleHealth > 0) {

                    double xMin2 = collectible.x;
                    double xMax2 = collectible.x + Collectible.collectibleSize;
                    double yMin2 = collectible.y;
                    double yMax2 = collectible.y + Collectible.collectibleSize;

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        collectible.collectibleHealth = 0;
                        GameController.ball.XP += 5;

                    }
                }

            }

    }
}