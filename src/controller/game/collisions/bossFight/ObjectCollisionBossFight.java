package controller.game.collisions.bossFight;

import controller.Constants;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.Impact;
import controller.game.collisions.phase2.ObjectCollisions2;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.boss.LeftHandModel;
import model.entity.enemy.boss.RightHandModel;
import model.entity.enemy.normalAndMiniBoss.BarricadosModel2;
import model.entity.enemy.normalAndMiniBoss.BlackOrbModel;
import model.entity.enemy.normalAndMiniBoss.OmenoctModel;
import view.settings.SettingsPanel;
import view.startPage.EnterNamePage;

import static controller.game.GameController.*;

public class ObjectCollisionBossFight {

    public static void checkCollisionsPhase2() {
        checkBulletCollisions();
        checkBallCollisions();



    }

    public static void checkBulletCollisions() {
        // bullets collisions
        ObjectCollisions2.checkCollisionBallEnemyBullet();
        if (rightHand.rightHandExists) {
            checkCollisionBulletRightHand();
        }
        if (leftHand.leftHandExists) {
            checkCollisionBulletLeftHand();
        }
        checkCollisionBulletSmiley();

    }

    public static void checkBallCollisions() {
        //ball collisions
        if (rightHand.rightHandExists) {
            checkCollisionBallRightHand();
        }
        if (leftHand.leftHandExists) {
            checkCollisionBallLeftHand();
        }
        ObjectCollisions2.checkCollisionBallArchmirePoints();


    }




    // ========================   bullet collisions     ==============================



    public static void checkCollisionBulletRightHand() {

        double xMin2 = rightHand.x;
        double xMax2 = rightHand.x + (double) RightHandModel.rightHandSize /2;
        double yMin2 = rightHand.y - (double) RightHandModel.rightHandSize /2;
        double yMax2 = rightHand.y + (double) RightHandModel.rightHandSize /2;
        int size = Math.min(bullets.size(), 20);

        if (!bullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (bullets.get(j).bulletHealth > 0) {

                    double xMin1 = bullets.get(j).x;
                    double xMax1 = bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = bullets.get(j).y;
                    double yMax1 = bullets.get(j).y + BulletModel.bulletSize;


                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        bullets.get(j).bulletHealth = 0;

                        if (smiley.projectileAttack || smiley.powerPunchAttack) {
                            if (GameController.bulletAres) {
                                rightHand.enemyHealth -= 7;
                            }
                            if (!GameController.bulletAres) {
                                rightHand.enemyHealth -= 5;
                            }
                            if (ball.ballChiron) {
                                ball.HP += 3;
                            }
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                rightHand.x + ((double) RightHandModel.rightHandSize / 2),
                                rightHand.y + ((double) RightHandModel.rightHandSize / 2));

                    }
                }
            }
        }
    }



    public static void checkCollisionBulletLeftHand() {

        double xMin2 = leftHand.x - (double) LeftHandModel.leftHandSize /2;
        double xMax2 = leftHand.x;
        double yMin2 = leftHand.y - (double) LeftHandModel.leftHandSize /2;
        double yMax2 = leftHand.y + (double) LeftHandModel.leftHandSize /2;
        int size = Math.min(bullets.size(), 20);

        if (!bullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (bullets.get(j).bulletHealth > 0) {

                    double xMin1 = bullets.get(j).x;
                    double xMax1 = bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = bullets.get(j).y;
                    double yMax1 = bullets.get(j).y + BulletModel.bulletSize;


                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        bullets.get(j).bulletHealth = 0;

                        if (smiley.projectileAttack || smiley.powerPunchAttack) {
                            if (GameController.bulletAres) {
                                leftHand.enemyHealth -= 7;
                            }
                            if (!GameController.bulletAres) {
                                leftHand.enemyHealth -= 5;
                            }
                            if (ball.ballChiron) {
                                ball.HP += 3;
                            }
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                leftHand.x + ((double) LeftHandModel.leftHandSize / 2),
                                leftHand.y + ((double) LeftHandModel.leftHandSize / 2));

                    }
                }
            }
        }
    }


    public static void checkCollisionBulletSmiley() {

        double x = smiley.x;
        double y = smiley.y;
        int size = Math.min(bullets.size(), 20);

        if (!bullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
                if (bullets.get(j).bulletHealth > 0) {

                    double x1 = bullets.get(j).x;
                    double y1 = bullets.get(j).y;

                    double distance = Math.pow((x-x1), 2) + Math.pow((y-y1), 2);
                    if (distance <= 40000) {

                        bullets.get(j).bulletHealth = 0;

                        if (smiley.squeezeAttack || smiley.vomitAttack || smiley.powerPunchAttack
                        || smiley.rapidFireAttack || smiley.slapAttack) {
                            if (GameController.bulletAres) {
                                smiley.enemyHealth -= 7;
                            }
                            if (!GameController.bulletAres) {
                                smiley.enemyHealth -= 5;
                            }
                            if (ball.ballChiron) {
                                ball.HP += 3;
                            }
                        }

                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                smiley.x, smiley.y);

                    }
                }
            }
        }
    }









    // ========================    ball collisions     ======================================


    public static void checkCollisionBallRightHand() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        double xMin2 = rightHand.x;
        double xMax2 = rightHand.x + RightHandModel.rightHandSize;
        double yMin2 = rightHand.y - RightHandModel.rightHandSize;
        double yMax2 = rightHand.y + BlackOrbModel.blackOrbSize;

        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

            if (EnterNamePage.player.isWritOfAstrape()) {
                rightHand.enemyHealth -= 2;
            }
            if (ball.ballCerberus) {
                rightHand.enemyHealth -= 10;
                ball.ballCerberus = false;
            }
            if (ball.ballChiron) {
                ball.HP += 3;
            }

            Impact.turnOnImpact(GameController.ball.x,
                    GameController.ball.y,
                    rightHand.x + ((double) RightHandModel.rightHandSize / 2),
                    rightHand.y + ((double) RightHandModel.rightHandSize / 2));

        }
    }


    public static void checkCollisionBallLeftHand() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        double xMin2 = leftHand.x - LeftHandModel.leftHandSize;
        double xMax2 = leftHand.x;
        double yMin2 = leftHand.y - LeftHandModel.leftHandSize;
        double yMax2 = leftHand.y + LeftHandModel.leftHandSize;

        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

            if (EnterNamePage.player.isWritOfAstrape()) {
                leftHand.enemyHealth -= 2;
            }
            if (ball.ballCerberus) {
                leftHand.enemyHealth -= 10;
                ball.ballCerberus = false;
            }
            if (ball.ballChiron) {
                ball.HP += 3;
            }

            Impact.turnOnImpact(GameController.ball.x,
                    GameController.ball.y,
                    leftHand.x - ((double) LeftHandModel.leftHandSize / 2),
                    leftHand.y + ((double) LeftHandModel.leftHandSize / 2));

        }
    }
}
