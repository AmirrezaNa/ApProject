package controller.game;

import model.entity.BallModel;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;
import model.entity.enemy.NecropickModel;
import model.entity.enemy.OmenoctModel;

public class Impact {
    public static void turnOnImpact(double x1, double y1, double x2, double y2) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;


        impactEnemy1(xImpactPoint, yImpactPoint);
        impactEnemy2(xImpactPoint, yImpactPoint);
        impactOmenoct(xImpactPoint, yImpactPoint);
        impactNecropick(xImpactPoint, yImpactPoint);
        impactBall(xImpactPoint, yImpactPoint);
    }


    public static void impactEnemy1(double xImpactPoint, double yImpactPoint) {
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
    }

    public static void impactEnemy2(double xImpactPoint, double yImpactPoint) {
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
    }

    public static void impactOmenoct(double xImpactPoint, double yImpactPoint) {
        for (OmenoctModel omenoct : GameController.omenoctEnemies) {
            if (omenoct.enemyHealth > 0) {
                if (Math.pow((Math.abs(omenoct.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(omenoct.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    omenoct.ax = ((omenoct.x - xImpactPoint) / Math.sqrt(Math.pow((omenoct.x - xImpactPoint), 2) + Math.pow((omenoct.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < omenoct.y) {
                        omenoct.ay = Math.sqrt(Math.pow(OmenoctModel.enemyAcceleration, 2) - Math.pow(omenoct.ax, 2));
                    } else {
                        omenoct.ay = -Math.sqrt(Math.pow(OmenoctModel.enemyAcceleration, 2) - Math.pow(omenoct.ax, 2));
                    }
                }
            }
        }
    }


    public static void impactNecropick(double xImpactPoint, double yImpactPoint) {
        for (NecropickModel necropick : GameController.necropickEnemies) {
            if (necropick.enemyHealth > 0 && !necropick.hide) {
                if (Math.pow((Math.abs(necropick.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(necropick.y) - Math.abs(yImpactPoint)), 2) < 8100) {
                    necropick.ax = ((necropick.x - xImpactPoint) / Math.sqrt(Math.pow((necropick.x - xImpactPoint), 2) + Math.pow((necropick.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                    if (yImpactPoint < necropick.y) {
                        necropick.ay = Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(necropick.ax, 2));
                    } else {
                        necropick.ay = -Math.sqrt(Math.pow(NecropickModel.enemyAcceleration, 2) - Math.pow(necropick.ax, 2));
                    }
                }
            }
        }
    }

    public static void impactBall(double xImpactPoint, double yImpactPoint) {
        double xBallCenter = GameController.ball.x;
        double yBallCenter = GameController.ball.y;
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

    public static void banishImpact(double x1, double y1, double x2, double y2) {
        double xImpactPoint = (x1 + x2) / 2;
        double yImpactPoint = (y1 + y2) / 2;
        if (GameController.Banish > 0) {
            for (EnemyModel1 enemy1 : GameController.enemies1) {
                if (enemy1.enemyHealth > 0) {
                    if (Math.pow((Math.abs(enemy1.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy1.y) - Math.abs(yImpactPoint)), 2) < 10000) {
                        enemy1.ax = 2 * ((enemy1.x - xImpactPoint) / Math.sqrt(Math.pow((enemy1.x - xImpactPoint), 2) + Math.pow((enemy1.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                        if (yImpactPoint < enemy1.y) {
                            enemy1.ay = 2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                        } else {
                            enemy1.ay = -2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy1.ax, 2));
                        }
                    }
                }
            }

            for (EnemyModel2 enemy2 : GameController.enemies2) {
                if (enemy2.enemyHealth > 0) {
                    if (Math.pow((Math.abs(enemy2.x) - Math.abs(xImpactPoint)), 2) + Math.pow((Math.abs(enemy2.y) - Math.abs(yImpactPoint)), 2) < 10000) {
                        enemy2.ax = 2 * ((enemy2.x - xImpactPoint) / Math.sqrt(Math.pow((enemy2.x - xImpactPoint), 2) + Math.pow((enemy2.y - yImpactPoint), 2))) * EnemyModel1.enemyAcceleration;
                        if (yImpactPoint < enemy2.y) {
                            enemy2.ay = 2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                        } else {
                            enemy2.ay = -2 * Math.sqrt(Math.pow(EnemyModel1.enemyAcceleration, 2) - Math.pow(enemy2.ax, 2));
                        }
                    }
                }
            }
            GameController.Banish--;
        }


    }

}