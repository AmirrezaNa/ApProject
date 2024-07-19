package controller.game.collisions.phase2;

import controller.Constants;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.Impact;
import model.entity.BallAngle;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.OmenoctModel;
import view.settings.SettingsPanel;

import static controller.game.GameController.*;

public class ObjectCollisions2 {


    public static void checkCollisionsPhase2() {
        checkCollisionBallEnemyBullet();
        checkBulletOmenoctCollision();
        checkCollisionBallOmenoct();
        checkCollisionBallAngleOmenoct();
    }


    public static void checkCollisionBallEnemyBullet() {
        double xMin2 = ball.x - BallModel.ballRadius;
        double xMax2 = ball.x + BallModel.ballRadius;
        double yMin2 = ball.y - BallModel.ballRadius;
        double yMax2 = ball.y + BallModel.ballRadius;

        if (!enemyBullets.isEmpty()) {
            for (int j = 0; j < enemyBullets.size(); j++) {
                if (enemyBullets.get(j).bulletHealth > 0) {

                    double xMin1 = enemyBullets.get(j).x;
                    double xMax1 = enemyBullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = enemyBullets.get(j).y;
                    double yMax1 = enemyBullets.get(j).y + BulletModel.bulletSize;


                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        enemyBullets.get(j).bulletHealth = 0;

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(enemyBullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                enemyBullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                ball.x + ((double) BallModel.ballRadius / 2),
                                ball.y + ((double) BallModel.ballRadius / 2));

                    }
                }
            }
        }
    }


    public static void checkBulletOmenoctCollision() {
        if (!GameController.bullets.isEmpty()) {
            for (int j = 0; j < GameController.bullets.size(); j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < omenoctEnemies.size(); k++) {
                        if (omenoctEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = omenoctEnemies.get(k).xAngles[6];
                            double xMax2 = omenoctEnemies.get(k).xAngles[2];
                            double yMin2 = omenoctEnemies.get(k).yAngles[0];
                            double yMax2 = omenoctEnemies.get(k).yAngles[4];

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;
                                if (GameController.bulletAres) {
                                    omenoctEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!GameController.bulletAres) {
                                    omenoctEnemies.get(k).enemyHealth -= 5;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (omenoctEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    GameController.newCollectible(omenoctEnemies.get(k).x, omenoctEnemies.get(k).y);
                                }
                                Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        omenoctEnemies.get(k).x + ((double) OmenoctModel.omenoctSize),
                                        omenoctEnemies.get(k).y + ((double) OmenoctModel.omenoctSize));

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionBallOmenoct() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < omenoctEnemies.size(); k++) {
            if (omenoctEnemies.get(k).enemyHealth > 0) {

                double xMin2 = omenoctEnemies.get(k).xAngles[6];
                double xMax2 = omenoctEnemies.get(k).xAngles[2];
                double yMin2 = omenoctEnemies.get(k).yAngles[0];
                double yMax2 = omenoctEnemies.get(k).yAngles[4];

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                    boolean angleCollided = false;
                    for (int i = 0; i < 8; i++) {
                        if ((xMin1 <= omenoctEnemies.get(k).xAngles[i]) &&
                                (xMax1 >= omenoctEnemies.get(k).xAngles[i]) &&
                                (yMin1 <= omenoctEnemies.get(k).yAngles[i]) &&
                                (yMax1 >= omenoctEnemies.get(k).yAngles[i])) {
                            angleCollided = true;
                        }
                    }
                    if (angleCollided) {
                        if (SettingsPanel.level == 1) {
                            GameController.ball.HP -= 6;
                        }
                        if (SettingsPanel.level == 2) {
                            GameController.ball.HP -= 8;
                        }
                        if (SettingsPanel.level == 3) {
                            GameController.ball.HP -= 10;
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                    }

                    Impact.turnOnImpact(GameController.ball.x,
                            GameController.ball.y,
                            omenoctEnemies.get(k).x,
                            omenoctEnemies.get(k).y);

                }
            }
        }
    }

    public static void checkCollisionBallAngleOmenoct() {
        if (GameController.ballAngle.angleExists) {

            double xMin1 = GameController.ballAngle.x - BallAngle.ballAngleRadius;
            double xMax1 = GameController.ballAngle.x + BallAngle.ballAngleRadius;
            double yMin1 = GameController.ballAngle.y - BallAngle.ballAngleRadius;
            double yMax1 = GameController.ballAngle.y + BallAngle.ballAngleRadius;
            for (int k = 0; k < omenoctEnemies.size(); k++) {
                if (omenoctEnemies.get(k).enemyHealth > 0) {

                    double xMin2 = omenoctEnemies.get(k).xAngles[6];
                    double xMax2 = omenoctEnemies.get(k).xAngles[2];
                    double yMin2 = omenoctEnemies.get(k).yAngles[0];
                    double yMax2 = omenoctEnemies.get(k).yAngles[4];

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        omenoctEnemies.get(k).enemyHealth -= 10;
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        if (omenoctEnemies.get(k).enemyHealth <= 0) {
                            SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        }
                        Impact.turnOnImpact(GameController.ball.x,
                                GameController.ball.y,
                                GameController.enemies2.get(k).x,
                                GameController.enemies2.get(k).y);

                    }
                }

            }
        }
    }



}
