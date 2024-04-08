package game.controller;

import game.ball.BallModel;
import game.ball.BulletModel;

import java.awt.*;
import java.util.ArrayList;

public class GameController {
    static BallModel ball;
    static BulletModel bullet;
    public static ArrayList<BulletModel> bullets = new ArrayList<>();

    public static BallModel newBall() {
        ball = new BallModel(140,140);
        return ball;
    }

    public static void updateTheBall() {

    }

    public static BulletModel newBullet(Point point) {
       bullet = new BulletModel((ball.x + ((double) ball.ballRadius / 2)), (ball.y + ((double) ball.ballRadius / 2)));
        bullet.dx = ((point.x - (ball.x + (ball.ballRadius / 2))) / Math.sqrt(Math.pow((point.x - (ball.x + ((double) ball.ballRadius / 2))), 2) + Math.pow((point.y - (ball.y + ((double) ball.ballRadius / 2))), 2))) * BulletModel.bulletSpeed;
        if (bullet.y < point.y) {
            bullet.dy = Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2));
        } else {
            bullet.dy = -(Math.sqrt(Math.pow(BulletModel.bulletSpeed, 2) - Math.pow(bullet.dx, 2)));
        }

        bullets.add(0, bullet);
        return bullet;
    }

    public static void updateBullet() {
        if (!GameController.bullets.isEmpty()) {
            for (BulletModel bullet : GameController.bullets) {
                if (bullet.bulletHealth > 0) {
                    bullet.x += bullet.dx;
                    bullet.y += bullet.dy;
                }
            }
        }
    }


}
