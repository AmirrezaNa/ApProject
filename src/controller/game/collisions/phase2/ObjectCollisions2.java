package controller.game.collisions.phase2;

import controller.Constants;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.Impact;
import controller.game.collisions.phase1.ObjectCollision;
import model.entity.BallAngle;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.enemy.normalAndMiniBoss.*;
import view.settings.SettingsPanel;
import view.startPage.EnterNamePage;

import static controller.game.GameController.*;

public class ObjectCollisions2 {


    public static void checkCollisionsPhase2() {
        checkBulletCollisions();
        checkBallCollisions();
        checkBallAngleCollisions();
        checkEnemyCollisions();


    }

    public static void checkBulletCollisions() {
        // bullets collisions
        checkCollisionBallEnemyBullet();
        checkBulletOmenoctCollision();
        checkBulletNecropickCollision();
        checkBulletWyrmCollision();
        checkBulletArchmireCollision();
        checkBulletBarricados1Collision();
        checkBulletBarricados2Collision();
        checkBulletBlackOrbCollision();

    }

    public static void checkBallCollisions() {
        //ball collisions
        checkCollisionBallOmenoct();
        checkCollisionBallNecropick();
        checkCollisionBallArchmire();
        checkCollisionBallArchmirePoints();
        checkCollisionBallWyrm();
        checkCollisionBallBarricados1();
        checkCollisionBallBarricados2();
        checkCollisionBallBlackOrb();
        ObjectCollision.checkCollisionBallCollectible();
    }

    public static void checkBallAngleCollisions() {
        //ball-angle collisions
        checkCollisionBallAngleNecropick();
        checkCollisionBallAngleOmenoct();
    }

    public static void checkEnemyCollisions() {
        //enemies collisions
        checkCollisionNecropickOmenoct();
        checkCollisionWyrmOmenoct();
        checkCollisionBarricados1Omenoct();
        checkCollisionBarricados2Omenoct();
        checkCollisionNecropickWyrm();
        checkCollisionBarricados1Necropick();
        checkCollisionBarricados2Necropick();
        checkCollisionBarricados1Wyrm();
        checkCollisionBarricados2Wyrm();
    }


    // =========================     bullets collisions    ==========================


    public static void checkCollisionBallEnemyBullet() {
        double xMin2 = ball.x - BallModel.ballRadius;
        double xMax2 = ball.x + BallModel.ballRadius;
        double yMin2 = ball.y - BallModel.ballRadius;
        double yMax2 = ball.y + BallModel.ballRadius;
        int size = Math.min(enemyBullets.size(), 20);

        if (!enemyBullets.isEmpty()) {
            for (int j = 0; j < size; j++) {
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
                        if (SettingsPanel.level == 1) {
                            GameController.ball.HP -= 4;
                        }
                        if (SettingsPanel.level == 2) {
                            GameController.ball.HP -= 5;
                        }
                        if (SettingsPanel.level == 3) {
                            GameController.ball.HP -= 6;
                        }

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
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
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
                                if (ball.ballChiron) {
                                    ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    omenoctEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
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


    public static void checkBulletNecropickCollision() {
        if (!GameController.bullets.isEmpty()) {
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < necropickEnemies.size(); k++) {
                        if (necropickEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = necropickEnemies.get(k).xAngles[0];
                            double xMax2 = necropickEnemies.get(k).xAngles[1];
                            double yMin2 = necropickEnemies.get(k).yAngles[0];
                            double yMax2 = necropickEnemies.get(k).yAngles[1];

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;
                                if (GameController.bulletAres) {
                                    necropickEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!GameController.bulletAres) {
                                    necropickEnemies.get(k).enemyHealth -= 5;
                                }
                                if (ball.ballChiron) {
                                    ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    necropickEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (necropickEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    GameController.newCollectible(necropickEnemies.get(k).x, necropickEnemies.get(k).y);
                                }
                                Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        necropickEnemies.get(k).x + ((double) NecropickModel.necropickSize),
                                        necropickEnemies.get(k).y + ((double) NecropickModel.necropickSize));

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletArchmireCollision() {
        if (!GameController.bullets.isEmpty()) {
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < archmireEnemies.size(); k++) {
                        if (archmireEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = archmireEnemies.get(k).x;
                            double xMax2 = archmireEnemies.get(k).x + ArchmireModel.archmireSize;
                            double yMin2 = archmireEnemies.get(k).y;
                            double yMax2 = archmireEnemies.get(k).y + ArchmireModel.archmireSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;
                                if (GameController.bulletAres) {
                                    archmireEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!GameController.bulletAres) {
                                    archmireEnemies.get(k).enemyHealth -= 5;
                                }
                                if (ball.ballChiron) {
                                    ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    archmireEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (archmireEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    GameController.newCollectible(archmireEnemies.get(k).x, archmireEnemies.get(k).y);
                                }


                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletWyrmCollision() {
        if (!GameController.bullets.isEmpty()) {
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < wyrmEnemies.size(); k++) {
                        if (wyrmEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = wyrmEnemies.get(k).x;
                            double xMax2 = wyrmEnemies.get(k).x + WyrmModel.wyrmSize;
                            double yMin2 = wyrmEnemies.get(k).y;
                            double yMax2 = wyrmEnemies.get(k).y + WyrmModel.wyrmSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;
                                if (GameController.bulletAres) {
                                    wyrmEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!GameController.bulletAres) {
                                    wyrmEnemies.get(k).enemyHealth -= 5;
                                }
                                if (ball.ballChiron) {
                                    ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    wyrmEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (wyrmEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    GameController.newCollectible(wyrmEnemies.get(k).x, wyrmEnemies.get(k).y);
                                }
                                Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        wyrmEnemies.get(k).x + ((double) WyrmModel.wyrmSize / 2),
                                        wyrmEnemies.get(k).y + ((double) WyrmModel.wyrmSize / 2));

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletBarricados1Collision() {
        if (!GameController.bullets.isEmpty()) {
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < barricadosEnemies1.size(); k++) {
                        if (barricadosEnemies1.get(k).enemyTimer > 0) {

                            double xMin2 = barricadosEnemies1.get(k).x;
                            double xMax2 = barricadosEnemies1.get(k).x + BarricadosModel1.barricadosSize;
                            double yMin2 = barricadosEnemies1.get(k).y;
                            double yMax2 = barricadosEnemies1.get(k).y + BarricadosModel1.barricadosSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;

                                Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        barricadosEnemies1.get(k).x + ((double) BarricadosModel1.barricadosSize / 2),
                                        barricadosEnemies1.get(k).y + ((double) BarricadosModel1.barricadosSize / 2));

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletBarricados2Collision() {
        if (!GameController.bullets.isEmpty()) {
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < barricadosEnemies2.size(); k++) {
                        if (barricadosEnemies2.get(k).enemyTimer > 0) {

                            double xMin2 = barricadosEnemies2.get(k).x;
                            double xMax2 = barricadosEnemies2.get(k).x + BarricadosModel2.barricadosSize;
                            double yMin2 = barricadosEnemies2.get(k).y;
                            double yMax2 = barricadosEnemies2.get(k).y + BarricadosModel2.barricadosSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;

                                Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        barricadosEnemies2.get(k).x + ((double) BarricadosModel2.barricadosSize / 2),
                                        barricadosEnemies2.get(k).y + ((double) BarricadosModel2.barricadosSize / 2));

                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkBulletBlackOrbCollision() {
        if (!GameController.bullets.isEmpty()) {
            int size = Math.min(bullets.size(), 15);
            for (int j = 0; j < size; j++) {
                if (GameController.bullets.get(j).bulletHealth > 0) {

                    double xMin1 = GameController.bullets.get(j).x;
                    double xMax1 = GameController.bullets.get(j).x + BulletModel.bulletSize;
                    double yMin1 = GameController.bullets.get(j).y;
                    double yMax1 = GameController.bullets.get(j).y + BulletModel.bulletSize;
                    for (int k = 0; k < blackOrbEnemies.size(); k++) {
                        if (blackOrbEnemies.get(k).enemyHealth > 0) {

                            double xMin2 = blackOrbEnemies.get(k).x;
                            double xMax2 = blackOrbEnemies.get(k).x + BlackOrbModel.blackOrbSize;
                            double yMin2 = blackOrbEnemies.get(k).y;
                            double yMax2 = blackOrbEnemies.get(k).y + BlackOrbModel.blackOrbSize;

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                GameController.bullets.get(j).bulletHealth = 0;
                                if (GameController.bulletAres) {
                                    blackOrbEnemies.get(k).enemyHealth -= 7;
                                }
                                if (!GameController.bulletAres) {
                                    blackOrbEnemies.get(k).enemyHealth -= 5;
                                }
                                if (ball.ballChiron) {
                                    ball.HP += 3;
                                }
                                if (BulletModel.bulletSlaughter) {
                                    blackOrbEnemies.get(k).enemyHealth -= 50;
                                    BulletModel.bulletSlaughter = false;
                                }
                                SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                                if (blackOrbEnemies.get(k).enemyHealth <= 0) {
                                    SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                                    GameController.newCollectible(blackOrbEnemies.get(k).x, blackOrbEnemies.get(k).y);
                                }


                                Impact.turnOnImpact(GameController.bullets.get(j).x + ((double) BulletModel.bulletSize / 2),
                                        GameController.bullets.get(j).y + ((double) BulletModel.bulletSize / 2),
                                        blackOrbEnemies.get(k).x + ((double) BlackOrbModel.blackOrbSize / 2),
                                        blackOrbEnemies.get(k).y + ((double) BlackOrbModel.blackOrbSize / 2));

                            }
                        }
                    }
                }
            }
        }
    }


    // =========================     ball collisions    ==========================


    public static void checkCollisionBallOmenoct() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < omenoctEnemies.size(); k++) {
            if (omenoctEnemies.get(k).enemyHealth > 0) {

                double xMin2 = omenoctEnemies.get(k).x - OmenoctModel.distanceToCenter;
                double xMax2 = omenoctEnemies.get(k).x + OmenoctModel.distanceToCenter;
                double yMin2 = omenoctEnemies.get(k).y;
                double yMax2 = omenoctEnemies.get(k).y + (2*OmenoctModel.distanceToCenter);

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                    if (SettingsPanel.level == 1) {
                        GameController.ball.HP -= 6;
                    }
                    if (SettingsPanel.level == 2) {
                        GameController.ball.HP -= 8;
                    }
                    if (SettingsPanel.level == 3) {
                        GameController.ball.HP -= 10;
                    }
                    if (EnterNamePage.player.isWritOfAstrape()) {
                        omenoctEnemies.get(k).enemyHealth -= 2;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }
                    if (ball.ballCerberus) {
                        omenoctEnemies.get(k).enemyHealth -= 10;
                        ball.ballCerberus = false;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }

                    SoundEffects.playSound(Constants.HURT_SOUND_PATH);

                    Impact.turnOnImpact(GameController.ball.x,
                            GameController.ball.y,
                            omenoctEnemies.get(k).x,
                            omenoctEnemies.get(k).y);

                }
            }
        }
    }


    public static void checkCollisionBallNecropick() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < necropickEnemies.size(); k++) {
            if (necropickEnemies.get(k).enemyHealth > 0 && !necropickEnemies.get(k).hide) {

                double xMin2 = necropickEnemies.get(k).xAngles[0];
                double xMax2 = necropickEnemies.get(k).xAngles[1];
                double yMin2 = necropickEnemies.get(k).yAngles[0];
                double yMax2 = necropickEnemies.get(k).yAngles[1];

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (EnterNamePage.player.isWritOfAstrape()) {
                        necropickEnemies.get(k).enemyHealth -= 2;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }
                    if (ball.ballCerberus) {
                        necropickEnemies.get(k).enemyHealth -= 10;
                        ball.ballCerberus = false;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }

                    Impact.turnOnImpact(GameController.ball.x,
                            GameController.ball.y,
                            necropickEnemies.get(k).x,
                            necropickEnemies.get(k).y);

                }
            }
        }
    }


    public static void checkCollisionBallArchmire() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < archmireEnemies.size(); k++) {
            if (archmireEnemies.get(k).enemyHealth > 0) {

                double xMin2 = archmireEnemies.get(k).x;
                double xMax2 = archmireEnemies.get(k).x + ArchmireModel.archmireSize;
                double yMin2 = archmireEnemies.get(k).y;
                double yMax2 = archmireEnemies.get(k).y + ArchmireModel.archmireSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (EnterNamePage.player.isWritOfAstrape()) {
                        archmireEnemies.get(k).enemyHealth -= 2;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }
                    if (ball.ballCerberus) {
                        archmireEnemies.get(k).enemyHealth -= 10;
                        ball.ballCerberus = false;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }
                    ball.ballInArchmire = true;

                } else {
                    ball.ballInArchmire = false;
                }
            }
        }
    }


    public static void checkCollisionBallArchmirePoints() {
        if (!archmirePoints.isEmpty()) {
            double xMin1 = GameController.ball.x - BallModel.ballRadius;
            double xMax1 = GameController.ball.x + BallModel.ballRadius;
            double yMin1 = GameController.ball.y - BallModel.ballRadius;
            double yMax1 = GameController.ball.y + BallModel.ballRadius;
            int size;
            if (archmirePoints.size() > 20) {
                size = 20;
            } else {
                size = archmirePoints.size();
            }
            for (int k = 0; k < size; k++) {
                if (archmirePoints.get(k).archmirePointTimer > 0) {

                    double xMin2 = archmirePoints.get(k).x;
                    double xMax2 = archmirePoints.get(k).x + ArchmireModel.archmireSize;
                    double yMin2 = archmirePoints.get(k).y;
                    double yMax2 = archmirePoints.get(k).y + ArchmireModel.archmireSize;

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        ball.ballInArchmireTrace = true;

                    } else {
                        ball.ballInArchmireTrace = false;
                    }
                }
            }
        }
    }

    public static void checkCollisionBallWyrm() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < wyrmEnemies.size(); k++) {
            if (wyrmEnemies.get(k).enemyHealth > 0) {

                double xMin2 = wyrmEnemies.get(k).x;
                double xMax2 = wyrmEnemies.get(k).x + WyrmModel.wyrmSize;
                double yMin2 = wyrmEnemies.get(k).y;
                double yMax2 = wyrmEnemies.get(k).y + WyrmModel.wyrmSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (EnterNamePage.player.isWritOfAstrape()) {
                        wyrmEnemies.get(k).enemyHealth -= 2;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }
                    if (ball.ballCerberus) {
                        wyrmEnemies.get(k).enemyHealth -= 10;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                        ball.ballCerberus = false;
                    }

                    Impact.turnOnImpact(GameController.ball.x,
                            GameController.ball.y,
                            wyrmEnemies.get(k).x + ((double) WyrmModel.wyrmSize / 2),
                            wyrmEnemies.get(k).y + ((double) WyrmModel.wyrmSize / 2));


                }
            }
        }
    }

    public static void checkCollisionBallBarricados1() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        if (!barricadosEnemies1.isEmpty()) {
            for (int k = 0; k < barricadosEnemies1.size(); k++) {
                if (barricadosEnemies1.get(k).enemyTimer > 0) {

                    double xMin2 = barricadosEnemies1.get(k).x;
                    double xMax2 = barricadosEnemies1.get(k).x + BarricadosModel1.barricadosSize;
                    double yMin2 = barricadosEnemies1.get(k).y;
                    double yMax2 = barricadosEnemies1.get(k).y + BarricadosModel1.barricadosSize;

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                        Impact.turnOnImpact(GameController.ball.x,
                                GameController.ball.y,
                                barricadosEnemies1.get(k).x + ((double) BarricadosModel1.barricadosSize / 2),
                                barricadosEnemies1.get(k).y + ((double) BarricadosModel1.barricadosSize / 2));


                    }
                }
            }
        }
    }


    public static void checkCollisionBallBarricados2() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < barricadosEnemies2.size(); k++) {
            if (barricadosEnemies2.get(k).enemyTimer > 0) {

                double xMin2 = barricadosEnemies2.get(k).x;
                double xMax2 = barricadosEnemies2.get(k).x + BarricadosModel2.barricadosSize;
                double yMin2 = barricadosEnemies2.get(k).y;
                double yMax2 = barricadosEnemies2.get(k).y + BarricadosModel2.barricadosSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    Impact.turnOnImpact(GameController.ball.x,
                            GameController.ball.y,
                            barricadosEnemies2.get(k).x + ((double) BarricadosModel2.barricadosSize / 2),
                            barricadosEnemies2.get(k).y + ((double) BarricadosModel2.barricadosSize / 2));


                }
            }
        }
    }


    public static void checkCollisionBallBlackOrb() {
        double xMin1 = GameController.ball.x - BallModel.ballRadius;
        double xMax1 = GameController.ball.x + BallModel.ballRadius;
        double yMin1 = GameController.ball.y - BallModel.ballRadius;
        double yMax1 = GameController.ball.y + BallModel.ballRadius;
        for (int k = 0; k < blackOrbEnemies.size(); k++) {
            if (blackOrbEnemies.get(k).enemyHealth > 0) {

                double xMin2 = blackOrbEnemies.get(k).x;
                double xMax2 = blackOrbEnemies.get(k).x + BlackOrbModel.blackOrbSize;
                double yMin2 = blackOrbEnemies.get(k).y;
                double yMax2 = blackOrbEnemies.get(k).y + BlackOrbModel.blackOrbSize;

                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                    if (EnterNamePage.player.isWritOfAstrape()) {
                        blackOrbEnemies.get(k).enemyHealth -= 2;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }
                    if (ball.ballCerberus) {
                        blackOrbEnemies.get(k).enemyHealth -= 10;
                        ball.ballCerberus = false;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                    }

                    ball.ballInBlackOrb = true;

                } else {
                    ball.ballInBlackOrb = false;
                }
            }
        }
    }


    // =========================     ball-angle collisions    ==========================


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
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
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


    public static void checkCollisionBallAngleNecropick() {
        if (GameController.ballAngle.angleExists) {

            double xMin1 = GameController.ballAngle.x - BallAngle.ballAngleRadius;
            double xMax1 = GameController.ballAngle.x + BallAngle.ballAngleRadius;
            double yMin1 = GameController.ballAngle.y - BallAngle.ballAngleRadius;
            double yMax1 = GameController.ballAngle.y + BallAngle.ballAngleRadius;
            for (int k = 0; k < necropickEnemies.size(); k++) {
                if (necropickEnemies.get(k).enemyHealth > 0 && !necropickEnemies.get(k).hide) {

                    double xMin2 = necropickEnemies.get(k).xAngles[0];
                    double xMax2 = necropickEnemies.get(k).xAngles[1];
                    double yMin2 = necropickEnemies.get(k).yAngles[0];
                    double yMax2 = necropickEnemies.get(k).yAngles[1];

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        necropickEnemies.get(k).enemyHealth -= 10;
                        if (ball.ballChiron) {
                            ball.HP += 3;
                        }
                        SoundEffects.playSound(Constants.HURT_SOUND_PATH);
                        if (necropickEnemies.get(k).enemyHealth <= 0) {
                            SoundEffects.playSound(Constants.HIT_SOUND_PATH);
                        }
                        Impact.turnOnImpact(GameController.ball.x,
                                GameController.ball.y,
                                necropickEnemies.get(k).x + ((double) NecropickModel.necropickSize / 2),
                                necropickEnemies.get(k).y + ((double) NecropickModel.necropickSize / 2));

                    }
                }

            }
        }
    }


    // =========================     enemies collisions    ==========================


    public static void checkCollisionWyrmOmenoct() {
        if (!wyrmEnemies.isEmpty()) {
            for (int i = 0; i < wyrmEnemies.size(); i++) {
                if (wyrmEnemies.get(i).enemyHealth > 0) {

                    double xMin1 = wyrmEnemies.get(i).x;
                    double xMax1 = wyrmEnemies.get(i).x + WyrmModel.wyrmSize;
                    double yMin1 = wyrmEnemies.get(i).y;
                    double yMax1 = wyrmEnemies.get(i).y + WyrmModel.wyrmSize;

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

                                Impact.turnOnImpact(wyrmEnemies.get(i).x + ((double) WyrmModel.wyrmSize / 2),
                                        wyrmEnemies.get(i).y + ((double) WyrmModel.wyrmSize / 2),
                                        omenoctEnemies.get(k).x,
                                        omenoctEnemies.get(k).y + OmenoctModel.distanceToCenter);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionNecropickOmenoct() {
        for (int i = 0; i < necropickEnemies.size(); i++) {
            if (necropickEnemies.get(i).enemyHealth > 0 && !necropickEnemies.get(i).hide) {

                double xMin1 = necropickEnemies.get(i).xAngles[0];
                double xMax1 = necropickEnemies.get(i).xAngles[1];
                double yMin1 = necropickEnemies.get(i).yAngles[0];
                double yMax1 = necropickEnemies.get(i).yAngles[1];

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

                            Impact.turnOnImpact(necropickEnemies.get(i).x + ((double) NecropickModel.necropickSize / 2),
                                    necropickEnemies.get(i).y + ((double) NecropickModel.necropickSize / 2),
                                    omenoctEnemies.get(k).x,
                                    omenoctEnemies.get(k).y + OmenoctModel.distanceToCenter);
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionBarricados1Omenoct() {
        if (!barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < barricadosEnemies1.size(); i++) {
                if (barricadosEnemies1.get(i).enemyTimer > 0) {

                    double xMin1 = barricadosEnemies1.get(i).x;
                    double xMax1 = barricadosEnemies1.get(i).x + BarricadosModel1.barricadosSize;
                    double yMin1 = barricadosEnemies1.get(i).y;
                    double yMax1 = barricadosEnemies1.get(i).y + BarricadosModel1.barricadosSize;

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

                                Impact.turnOnImpact(barricadosEnemies1.get(i).x + ((double) BarricadosModel1.barricadosSize / 2),
                                        barricadosEnemies1.get(i).y + ((double) BarricadosModel1.barricadosSize / 2),
                                        omenoctEnemies.get(k).x,
                                        omenoctEnemies.get(k).y + OmenoctModel.distanceToCenter);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void checkCollisionBarricados2Omenoct() {
        if (!barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < barricadosEnemies2.size(); i++) {
                if (barricadosEnemies2.get(i).enemyTimer > 0) {

                    double xMin1 = barricadosEnemies2.get(i).x;
                    double xMax1 = barricadosEnemies2.get(i).x + BarricadosModel2.barricadosSize;
                    double yMin1 = barricadosEnemies2.get(i).y;
                    double yMax1 = barricadosEnemies2.get(i).y + BarricadosModel2.barricadosSize;

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

                                Impact.turnOnImpact(barricadosEnemies2.get(i).x + ((double) BarricadosModel2.barricadosSize / 2),
                                        barricadosEnemies2.get(i).y + ((double) BarricadosModel2.barricadosSize / 2),
                                        omenoctEnemies.get(k).x,
                                        omenoctEnemies.get(k).y + OmenoctModel.distanceToCenter);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void checkCollisionNecropickWyrm() {
        if (!necropickEnemies.isEmpty()) {
            for (int i = 0; i < necropickEnemies.size(); i++) {
                if (necropickEnemies.get(i).enemyHealth > 0 && !necropickEnemies.get(i).hide) {

                    double xMin1 = necropickEnemies.get(i).xAngles[0];
                    double xMax1 = necropickEnemies.get(i).xAngles[1];
                    double yMin1 = necropickEnemies.get(i).yAngles[0];
                    double yMax1 = necropickEnemies.get(i).yAngles[1];

                    if (!wyrmEnemies.isEmpty()) {
                        for (int j = 0; j < wyrmEnemies.size(); j++) {
                            if (wyrmEnemies.get(j).enemyHealth > 0) {

                                double xMin2 = wyrmEnemies.get(j).x;
                                double xMax2 = wyrmEnemies.get(j).x + WyrmModel.wyrmSize;
                                double yMin2 = wyrmEnemies.get(j).y;
                                double yMax2 = wyrmEnemies.get(j).y + WyrmModel.wyrmSize;

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(necropickEnemies.get(j).x + ((double) NecropickModel.necropickSize / 2),
                                            necropickEnemies.get(j).y + ((double) NecropickModel.necropickSize / 2),
                                            wyrmEnemies.get(j).x,
                                            wyrmEnemies.get(j).y + (double) WyrmModel.wyrmSize / 2);
                                }
                            }
                        }
                    }
                }
            }

        }
    }


    public static void checkCollisionBarricados1Necropick() {
        if (!barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < barricadosEnemies1.size(); i++) {
                if (barricadosEnemies1.get(i).enemyTimer > 0) {

                    double xMin1 = barricadosEnemies1.get(i).x;
                    double xMax1 = barricadosEnemies1.get(i).x + BarricadosModel1.barricadosSize;
                    double yMin1 = barricadosEnemies1.get(i).y;
                    double yMax1 = barricadosEnemies1.get(i).y + BarricadosModel1.barricadosSize;

                    if (!necropickEnemies.isEmpty()) {
                        for (int j = 0; j < necropickEnemies.size(); j++) {
                            if (necropickEnemies.get(j).enemyHealth > 0 && !necropickEnemies.get(j).hide) {

                                double xMin2 = necropickEnemies.get(j).xAngles[0];
                                double xMax2 = necropickEnemies.get(j).xAngles[1];
                                double yMin2 = necropickEnemies.get(j).yAngles[0];
                                double yMax2 = necropickEnemies.get(j).yAngles[1];

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(barricadosEnemies1.get(j).x + ((double) BarricadosModel1.barricadosSize / 2),
                                            barricadosEnemies1.get(j).y + ((double) BarricadosModel1.barricadosSize / 2),
                                            necropickEnemies.get(j).x + (double) NecropickModel.necropickSize / 2,
                                            necropickEnemies.get(j).y + (double) NecropickModel.necropickSize / 2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void checkCollisionBarricados2Necropick() {
        if (!barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < barricadosEnemies2.size(); i++) {
                if (barricadosEnemies2.get(i).enemyTimer > 0) {

                    double xMin1 = barricadosEnemies2.get(i).x;
                    double xMax1 = barricadosEnemies2.get(i).x + BarricadosModel2.barricadosSize;
                    double yMin1 = barricadosEnemies2.get(i).y;
                    double yMax1 = barricadosEnemies2.get(i).y + BarricadosModel2.barricadosSize;

                    if (!necropickEnemies.isEmpty()) {
                        for (int j = 0; j < necropickEnemies.size(); j++) {
                            if (necropickEnemies.get(j).enemyHealth > 0 && !necropickEnemies.get(j).hide) {

                                double xMin2 = necropickEnemies.get(j).xAngles[0];
                                double xMax2 = necropickEnemies.get(j).xAngles[1];
                                double yMin2 = necropickEnemies.get(j).yAngles[0];
                                double yMax2 = necropickEnemies.get(j).yAngles[1];

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(barricadosEnemies2.get(j).x + ((double) BarricadosModel2.barricadosSize / 2),
                                            barricadosEnemies2.get(j).y + ((double) BarricadosModel2.barricadosSize / 2),
                                            necropickEnemies.get(j).x + (double) NecropickModel.necropickSize / 2,
                                            necropickEnemies.get(j).y + (double) NecropickModel.necropickSize / 2);
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    public static void checkCollisionBarricados2Wyrm() {
        if (!barricadosEnemies2.isEmpty()) {
            for (int i = 0; i < barricadosEnemies2.size(); i++) {
                if (barricadosEnemies2.get(i).enemyTimer > 0) {

                    double xMin1 = barricadosEnemies2.get(i).x;
                    double xMax1 = barricadosEnemies2.get(i).x + BarricadosModel2.barricadosSize;
                    double yMin1 = barricadosEnemies2.get(i).y;
                    double yMax1 = barricadosEnemies2.get(i).y + BarricadosModel2.barricadosSize;

                    if (!wyrmEnemies.isEmpty()) {
                        for (int j = 0; j < wyrmEnemies.size(); j++) {
                            if (wyrmEnemies.get(j).enemyHealth > 0) {

                                double xMin2 = wyrmEnemies.get(j).x;
                                double xMax2 = wyrmEnemies.get(j).x + WyrmModel.wyrmSize;
                                double yMin2 = wyrmEnemies.get(j).y;
                                double yMax2 = wyrmEnemies.get(j).y + WyrmModel.wyrmSize;

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(barricadosEnemies2.get(i).x + ((double) BarricadosModel2.barricadosSize / 2),
                                            barricadosEnemies2.get(i).y + ((double) BarricadosModel2.barricadosSize / 2),
                                            wyrmEnemies.get(j).x + (double) WyrmModel.wyrmSize / 2,
                                            wyrmEnemies.get(j).y + (double) WyrmModel.wyrmSize / 2);
                                }
                            }
                        }
                    }
                }
            }
        }

    }



    public static void checkCollisionBarricados1Wyrm() {
        if (!barricadosEnemies1.isEmpty()) {
            for (int i = 0; i < barricadosEnemies1.size(); i++) {
                if (barricadosEnemies1.get(i).enemyTimer > 0) {

                    double xMin1 = barricadosEnemies1.get(i).x;
                    double xMax1 = barricadosEnemies1.get(i).x + BarricadosModel1.barricadosSize;
                    double yMin1 = barricadosEnemies1.get(i).y;
                    double yMax1 = barricadosEnemies1.get(i).y + BarricadosModel1.barricadosSize;

                    if (!wyrmEnemies.isEmpty()) {
                        for (int j = 0; j < wyrmEnemies.size(); j++) {
                            if (wyrmEnemies.get(j).enemyHealth > 0) {

                                double xMin2 = wyrmEnemies.get(j).x;
                                double xMax2 = wyrmEnemies.get(j).x + WyrmModel.wyrmSize;
                                double yMin2 = wyrmEnemies.get(j).y;
                                double yMax2 = wyrmEnemies.get(j).y + WyrmModel.wyrmSize;

                                if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                        || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                        || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                    Impact.turnOnImpact(barricadosEnemies1.get(i).x + ((double) BarricadosModel1.barricadosSize / 2),
                                            barricadosEnemies1.get(i).y + ((double) BarricadosModel1.barricadosSize / 2),
                                            wyrmEnemies.get(j).x + (double) WyrmModel.wyrmSize / 2,
                                            wyrmEnemies.get(j).y + (double) WyrmModel.wyrmSize / 2);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
