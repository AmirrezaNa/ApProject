package game.controller;

import game.entity.BallModel;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;

public class Impact {
    public static void turnOnImpact(double x1, double y1, double x2, double y2) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;

        for (EnemyModel1 enemy1 : GameController.enemies1) {
            if (enemy1.enemyHealth > 0) {
                if (Math.pow((Math.abs(enemy1.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy1.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    enemy1.ax = ((enemy1.x - xImpactPoint) / Math.sqrt(Math.pow((enemy1.x - xImpactPoint), 2) + Math.pow((enemy1.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < enemy1.y) {
                        enemy1.ay = Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                    } else {
                        enemy1.ay = -Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                    }
                }
            }
        }

        for (EnemyModel2 enemy2 : GameController.enemies2) {
            if (enemy2.enemyHealth > 0) {
                if (Math.pow((Math.abs(enemy2.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy2.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    enemy2.ax = ((enemy2.x - xImpactPoint) / Math.sqrt(Math.pow((enemy2.x - xImpactPoint), 2) + Math.pow((enemy2.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < enemy2.y) {
                        enemy2.ay = Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                    } else {
                        enemy2.ay = -Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                    }
                }
            }
        }

        double xBallCenter = GameController.ball.x + ((double) BallModel.ballRadius / 2);
        double yBallCenter = GameController.ball.y + ((double) BallModel.ballRadius / 2);
        if (Math.pow((Math.abs(xBallCenter) - Math.abs(xImpactPoint)), 2) +
                Math.pow((Math.abs(yBallCenter) - Math.abs(yImpactPoint)), 2) < 8100) {
            GameController.ball.ax = ((xBallCenter - xImpactPoint) / Math.sqrt(Math.pow((xBallCenter - xImpactPoint), 2) + Math.pow((yBallCenter - yImpactPoint), 2))) * BallModel.ballAcceleration;
            if (yImpactPoint < yBallCenter) {
                GameController.ball.ay = Math.sqrt(Math.pow(BallModel.ballAcceleration, 2) - Math.pow(GameController.ball.ax, 2));
            } else {
                GameController.ball.ay = -Math.sqrt(Math.pow(BallModel.ballAcceleration, 2) - Math.pow(GameController.ball.ax, 2));
            }
        }
    }

}
