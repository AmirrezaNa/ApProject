package controller.game;

import model.entity.BallModel;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;

public class Impact {
    GameController gameController = new GameController();
    public void turnOnImpact(double x1, double y1, double x2, double y2) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;

        impactEnemy1(xImpactPoint, yImpactPoint);
        impactEnemy2(xImpactPoint, yImpactPoint);
        impactBall(xImpactPoint, yImpactPoint);
    }

    public void impactEnemy1(double xImpactPoint, double yImpactPoint) {
        for (EnemyModel1 enemy1 : gameController.getEnemies1()) {
            if (enemy1.getEnemyHealth() > 0) {
                if (Math.pow((Math.abs(enemy1.getX()) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy1.getY()) - Math.abs(yImpactPoint)), 2) < 8100) {
                    enemy1.setDx(((enemy1.getX() - xImpactPoint) / Math.sqrt(Math.pow((enemy1.getX() - xImpactPoint), 2) + Math.pow((enemy1.getY() - yImpactPoint), 2))) * EnemyModel1.getEnemyAcceleration());
                    if (yImpactPoint < enemy1.getY()) {
                        enemy1.setAy(Math.sqrt(Math.pow(EnemyModel1.getEnemyAcceleration(), 2) - Math.pow(enemy1.getAx(), 2)));
                    } else {
                        enemy1.setAy(-Math.sqrt(Math.pow(EnemyModel1.getEnemyAcceleration(), 2) - Math.pow(enemy1.getAx(), 2)));
                    }
                }
            }
        }

    }

    public void impactEnemy2(double xImpactPoint, double yImpactPoint) {
        for (EnemyModel2 enemy2 : gameController.getEnemies2()) {
            if (enemy2.getEnemyHealth() > 0) {
                if (Math.pow((Math.abs(enemy2.getX()) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy2.getY()) - Math.abs(yImpactPoint)), 2) < 8100) {
                    enemy2.setAx(((enemy2.getX() - xImpactPoint) / Math.sqrt(Math.pow((enemy2.getX() - xImpactPoint), 2) + Math.pow((enemy2.getY() - yImpactPoint), 2))) * EnemyModel1.getEnemyAcceleration());
                    if (yImpactPoint < enemy2.getY()) {
                        enemy2.setAy(Math.sqrt(Math.pow(EnemyModel1.getEnemyAcceleration(), 2) - Math.pow(enemy2.getAx(), 2)));
                    } else {
                        enemy2.setAy(-Math.sqrt(Math.pow(EnemyModel1.getEnemyAcceleration(), 2) - Math.pow(enemy2.getAx(), 2)));
                    }
                }
            }
        }
    }

    public void impactBall(double xImpactPoint, double yImpactPoint) {
        BallModel ball = gameController.getBall();
        double xBallCenter = ball.getX();
        double yBallCenter = ball.getY();
        if (Math.pow((Math.abs(xBallCenter) - Math.abs(xImpactPoint)), 2) +
                Math.pow((Math.abs(yBallCenter) - Math.abs(yImpactPoint)), 2) < 8100) {
            ball.setAx(((xBallCenter - xImpactPoint) / Math.sqrt(Math.pow((xBallCenter - xImpactPoint), 2) + Math.pow((yBallCenter - yImpactPoint), 2))) * BallModel.getBallAcceleration());
            if (yImpactPoint < yBallCenter) {
                ball.setAy(Math.sqrt(Math.pow(BallModel.getBallAcceleration(), 2) - Math.pow(ball.getAx(), 2)));
            } else {
                ball.setAy(-Math.sqrt(Math.pow(BallModel.getBallAcceleration(), 2) - Math.pow(ball.getAx(), 2)));
            }
        }
    }



    public void banishImpact(double x1, double y1, double x2, double y2) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;
        if (gameController.getBanish() > 0) {
            for (EnemyModel1 enemy1 : gameController.getEnemies1()) {
                if (enemy1.getEnemyHealth() > 0) {
                    if (Math.pow((Math.abs(enemy1.getX()) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy1.getY()) - Math.abs(yImpactPoint)), 2) < 10000) {
                        enemy1.setAx(2 * ((enemy1.getX() - xImpactPoint) / Math.sqrt(Math.pow((enemy1.getX() - xImpactPoint), 2) + Math.pow((enemy1.getY() - yImpactPoint), 2))) * EnemyModel1.getEnemyAcceleration());
                        if (yImpactPoint < enemy1.getY()) {
                            enemy1.setAy(2 * Math.sqrt(Math.pow(EnemyModel1.getEnemyAcceleration(), 2) - Math.pow(enemy1.getAx(), 2)));
                        } else {
                            enemy1.setAy(-2 * Math.sqrt(Math.pow(EnemyModel1.getEnemyAcceleration(), 2) - Math.pow(enemy1.getAx(), 2)));
                        }
                    }
                }
            }

            for (EnemyModel2 enemy2 : gameController.getEnemies2()) {
                if (enemy2.getEnemyHealth() > 0) {
                    if (Math.pow((Math.abs(enemy2.getX()) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy2.getY()) - Math.abs(yImpactPoint)), 2) < 10000) {
                        enemy2.setAx(2 * ((enemy2.getX() - xImpactPoint) / Math.sqrt(Math.pow((enemy2.getX() - xImpactPoint), 2) + Math.pow((enemy2.getY() - yImpactPoint), 2))) * EnemyModel2.getEnemyAcceleration());
                        if (yImpactPoint < enemy2.getY()) {
                            enemy2.setAy(2 * Math.sqrt(Math.pow(EnemyModel2.getEnemyAcceleration(), 2) - Math.pow(enemy2.getAx(), 2)));
                        } else {
                            enemy2.setAy(-2 * Math.sqrt(Math.pow(EnemyModel2.getEnemyAcceleration(), 2) - Math.pow(enemy2.getAx(), 2)));
                        }
                    }
                }
            }
            gameController.setBanish(gameController.getBanish()-1);
        }


    }

}
