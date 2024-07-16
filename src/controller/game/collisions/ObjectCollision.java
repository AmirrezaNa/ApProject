package controller.game.collisions;

import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.Impact;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;
import view.game.GamePanel;
import model.entity.BallAngle;
import model.entity.BallModel;
import model.entity.BulletModel;
import model.entity.Collectible;
import view.settings.SettingsPanel;
import view.startPage.EnterNamePage;

public class ObjectCollision {

    GameController gameController = new GameController();
    Impact impact = new Impact();


    // ===========  Here is where we are checking the intersections of different types of objects ===============


    public void checkObjectsCollisions() {
        if (!GamePanel.pause) {
            checkCollisionEnemy1Enemy2();
            checkCollisionEnemy1Enemy1();
            checkCollisionEnemy2Enemy2();
            checkCollisionBallEnemy1();
            checkCollisionBallEnemy2();
            checkCollisionBulletEnemy1();
            checkCollisionBulletEnemy2();
            checkCollisionBallCollectible();
            checkCollisionBallAngleEnemy1();
            checkCollisionBallAngleEnemy2();
        }

    }


    // a method for checking intersections between enemy1 and enemy2

    public void checkCollisionEnemy1Enemy2() {
        if (!gameController.getEnemies1().isEmpty()) {
            for (int k = 0; k < gameController.getEnemies1().size(); k++) {
                EnemyModel1 enemy1 = gameController.getEnemies1().get(k);
                if (enemy1.getEnemyHealth() > 0) {

                    double xMin1 = enemy1.getxAngles()[0];
                    double xMax1 = enemy1.getxAngles()[0];
                    double yMin1 = enemy1.getyAngles()[0];
                    double yMax1 = enemy1.getyAngles()[0];
                    for (int i = 0; i < 4; i++) {
                        if (enemy1.getxAngles()[i] < xMin1) {
                            xMin1 = enemy1.getxAngles()[i];
                        }
                        if (enemy1.getxAngles()[i] > xMax1) {
                            xMax1 = enemy1.getxAngles()[i];
                        }
                        if (enemy1.getyAngles()[i] < yMin1) {
                            yMin1 = enemy1.getyAngles()[i];
                        }
                        if (enemy1.getyAngles()[i] > yMax1) {
                            yMax1 = enemy1.getyAngles()[i];
                        }
                    }
                    for (int j = 0; j < gameController.getEnemies2().size(); j++) {
                        EnemyModel2 enemy2 = gameController.getEnemies2().get(j);
                        if (enemy2.getEnemyHealth() > 0) {

                            double xMin2 = enemy2.getxAngles()[0];
                            double xMax2 = enemy2.getxAngles()[0];
                            double yMin2 = enemy2.getyAngles()[0];
                            double yMax2 = enemy2.getyAngles()[0];
                            for (int i = 0; i < 3; i++) {
                                if (enemy2.getxAngles()[i] < xMin2) {
                                    xMin2 = enemy2.getxAngles()[i];
                                }
                                if (enemy2.getxAngles()[i] > xMax2) {
                                    xMax2 = enemy2.getxAngles()[i];
                                }
                                if (enemy2.getyAngles()[i] < yMin2) {
                                    yMin2 = enemy2.getyAngles()[i];
                                }
                                if (enemy2.getyAngles()[i] > yMax2) {
                                    yMax2 = enemy2.getyAngles()[i];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                                enemy1.setDash(false);
                                impact.turnOnImpact(enemy1.getX(),
                                        enemy1.getY(),
                                        enemy2.getX(),
                                        enemy2.getY());
                            }
                        }
                    }
                }

                gameController.getEnemies1().set(k, enemy1);
            }
        }
    }


    // ==========================================================================================


    // this method is for checking the intersections between enemies of type1 with each other
    public void checkCollisionEnemy1Enemy1() {
        if (!gameController.getEnemies1().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies1().size() - 1; i++) {
                EnemyModel1 enemy11 = gameController.getEnemies1().get(i);
                if (enemy11.getEnemyHealth() > 0) {

                    double xMin1 = enemy11.getxAngles()[0];
                    double xMax1 = enemy11.getxAngles()[0];
                    double yMin1 = enemy11.getyAngles()[0];
                    double yMax1 = enemy11.getyAngles()[0];
                    for (int k = 0; k < 4; k++) {
                        if (enemy11.getxAngles()[k] < xMin1) {
                            xMin1 = enemy11.getxAngles()[k];
                        }
                        if (enemy11.getxAngles()[k] > xMax1) {
                            xMax1 = enemy11.getxAngles()[k];
                        }
                        if (enemy11.getyAngles()[k] < yMin1) {
                            yMin1 = enemy11.getyAngles()[k];
                        }
                        if (enemy11.getyAngles()[k] > yMax1) {
                            yMax1 = enemy11.getyAngles()[k];
                        }
                    }
                    for (int j = 0; j < gameController.getEnemies1().size(); j++) {
                        EnemyModel1 enemy12 = gameController.getEnemies1().get(j);
                        if (enemy12.getEnemyHealth() > 0) {

                            double xMin2 = enemy12.getxAngles()[0];
                            double xMax2 = enemy12.getxAngles()[0];
                            double yMin2 = enemy12.getyAngles()[0];
                            double yMax2 = enemy12.getyAngles()[0];
                            for (int k = 0; k < 4; k++) {
                                if (enemy12.getxAngles()[k] < xMin2) {
                                    xMin2 = enemy12.getxAngles()[k];
                                }
                                if (enemy12.getxAngles()[k] > xMax2) {
                                    xMax2 = enemy12.getxAngles()[k];
                                }
                                if (enemy12.getyAngles()[k] < yMin2) {
                                    yMin2 = enemy12.getyAngles()[k];
                                }
                                if (enemy12.getyAngles()[k] > yMax2) {
                                    yMax2 = enemy12.getyAngles()[k];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                enemy11.setDash(false);
                                enemy12.setDash(false);
                                impact.turnOnImpact(enemy11.getX(),
                                        enemy11.getY(),
                                        enemy12.getX(),
                                        enemy12.getY());

                            }
                        }

                        gameController.getEnemies1().set(j, enemy12);
                    }
                }

                gameController.getEnemies1().set(i, enemy11);
            }
        }
    }

    // this method is for checking the intersections between enemies of type2 with each other

    public void checkCollisionEnemy2Enemy2() {
        if (!gameController.getEnemies2().isEmpty()) {
            for (int i = 0; i < gameController.getEnemies2().size() - 1; i++) {
                EnemyModel2 enemy21 = gameController.getEnemies2().get(i);
                if (enemy21.getEnemyHealth() > 0) {

                    double xMin1 = enemy21.getxAngles()[0];
                    double xMax1 = enemy21.getxAngles()[0];
                    double yMin1 = enemy21.getyAngles()[0];
                    double yMax1 = enemy21.getyAngles()[0];
                    for (int k = 0; k < 3; k++) {
                        if (enemy21.getxAngles()[k] < xMin1) {
                            xMin1 = enemy21.getxAngles()[k];
                        }
                        if (enemy21.getxAngles()[k] > xMax1) {
                            xMax1 = enemy21.getxAngles()[k];
                        }
                        if (enemy21.getyAngles()[k] < yMin1) {
                            yMin1 = enemy21.getyAngles()[k];
                        }
                        if (enemy21.getyAngles()[k] > yMax1) {
                            yMax1 = enemy21.getyAngles()[k];
                        }
                    }
                    for (int j = 0; j < gameController.getEnemies2().size(); j++) {
                        EnemyModel2 enemy22 = gameController.getEnemies2().get(j);
                        if (enemy22.getEnemyHealth() > 0) {

                            double xMin2 = enemy22.getxAngles()[0];
                            double xMax2 = enemy22.getxAngles()[0];
                            double yMin2 = enemy22.getyAngles()[0];
                            double yMax2 = enemy22.getyAngles()[0];
                            for (int k = 0; k < 3; k++) {
                                if (enemy22.getxAngles()[k] < xMin2) {
                                    xMin2 = enemy22.getxAngles()[k];
                                }
                                if (enemy22.getxAngles()[k] > xMax2) {
                                    xMax2 = enemy22.getxAngles()[k];
                                }
                                if (enemy22.getyAngles()[k] < yMin2) {
                                    yMin2 = enemy22.getyAngles()[k];
                                }
                                if (enemy22.getyAngles()[k] > yMax2) {
                                    yMax2 = enemy22.getyAngles()[k];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                impact.turnOnImpact(enemy21.getX(),
                                        enemy21.getY(),
                                        enemy22.getX(),
                                        enemy22.getY());

                            }
                        }
                    }
                }

            }
        }
    }


    // a method for checking intersections between enemy1 and the ball

    public void checkCollisionBallEnemy1() {
        if (gameController.getBall() != null) {

            BallModel ball = gameController.getBall();

            double xMin1 = ball.getX() - BallModel.getBallRadius();
            double xMax1 = ball.getX() + BallModel.getBallRadius();
            double yMin1 = ball.getY() - BallModel.getBallRadius();
            double yMax1 = ball.getY() + BallModel.getBallRadius();
            if (!gameController.getEnemies1().isEmpty()) {
                for (int k = 0; k < gameController.getEnemies1().size(); k++) {
                    EnemyModel1 enemy1 = gameController.getEnemies1().get(k);
                    if (enemy1.getEnemyHealth() > 0) {

                        double xMin2 = enemy1.getxAngles()[0];
                        double xMax2 = enemy1.getxAngles()[0];
                        double yMin2 = enemy1.getyAngles()[0];
                        double yMax2 = enemy1.getyAngles()[0];
                        for (int i = 0; i < 4; i++) {
                            if (enemy1.getxAngles()[i] < xMin2) {
                                xMin2 = enemy1.getxAngles()[i];
                            }
                            if (enemy1.getxAngles()[i] > xMax2) {
                                xMax2 = enemy1.getxAngles()[i];
                            }
                            if (enemy1.getyAngles()[i] < yMin2) {
                                yMin2 = enemy1.getyAngles()[i];
                            }
                            if (enemy1.getyAngles()[i] > yMax2) {
                                yMax2 = enemy1.getyAngles()[i];
                            }
                        }

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                            boolean angleCollided = false;
                            for (int i = 0; i < 4; i++) {
                                if ((xMin1 <= enemy1.getxAngles()[i]) &&
                                        (xMax1 >= enemy1.getxAngles()[i]) &&
                                        (yMin1 <= enemy1.getyAngles()[i]) &&
                                        (yMax1 >= enemy1.getyAngles()[i])) {
                                    angleCollided = true;
                                }
                            }
                            if (angleCollided) {
                                if (SettingsPanel.level == 1) {
                                    ball.setHP(ball.getHP() - 4);
                                }
                                if (SettingsPanel.level == 2) {
                                    ball.setHP(ball.getHP() - 6);
                                }
                                if (SettingsPanel.level == 3) {
                                    ball.setHP(ball.getHP() - 8);
                                }

                                SoundEffects.playSound("hurt.wav");
                            }

                            enemy1.setDash(false);
                            enemy1.setdAngle(Math.PI);
                            impact.turnOnImpact(ball.getX(),
                                    ball.getY(),
                                    enemy1.getX(),
                                    enemy1.getY());

                        }
                    }

                    gameController.getEnemies1().set(k, enemy1);
                }
            }
            gameController.setBall(ball);
        }
    }


    // a method for checking intersections between enemy2 and the ball

    public void checkCollisionBallEnemy2() {
        if (gameController.getBall() != null) {
            BallModel ball = gameController.getBall();

            double xMin1 = ball.getX() - BallModel.getBallRadius();
            double xMax1 = ball.getX() + BallModel.getBallRadius();
            double yMin1 = ball.getY() - BallModel.getBallRadius();
            double yMax1 = ball.getY() + BallModel.getBallRadius();
            if (!gameController.getEnemies2().isEmpty()) {
                for (int k = 0; k < gameController.getEnemies2().size(); k++) {
                    EnemyModel2 enemy2 = gameController.getEnemies2().get(k);
                    if (enemy2.getEnemyHealth() > 0) {

                        double xMin2 = enemy2.getxAngles()[0];
                        double xMax2 = enemy2.getxAngles()[0];
                        double yMin2 = enemy2.getyAngles()[0];
                        double yMax2 = enemy2.getyAngles()[0];
                        for (int i = 0; i < 3; i++) {
                            if (enemy2.getxAngles()[i] < xMin2) {
                                xMin2 = enemy2.getxAngles()[i];
                            }
                            if (enemy2.getxAngles()[i] > xMax2) {
                                xMax2 = enemy2.getxAngles()[i];
                            }
                            if (enemy2.getyAngles()[i] < yMin2) {
                                yMin2 = enemy2.getyAngles()[i];
                            }
                            if (enemy2.getyAngles()[i] > yMax2) {
                                yMax2 = enemy2.getyAngles()[i];
                            }
                        }

                        if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {
                            boolean angleCollided = false;
                            for (int i = 0; i < 3; i++) {
                                if ((xMin1 <= enemy2.getxAngles()[i]) &&
                                        (xMax1 >= enemy2.getxAngles()[i]) &&
                                        (yMin1 <= enemy2.getyAngles()[i]) &&
                                        (yMax1 >= enemy2.getyAngles()[i])) {
                                    angleCollided = true;
                                }
                            }
                            if (angleCollided) {
                                if (SettingsPanel.level == 1) {
                                    ball.setHP(ball.getHP() - 8);
                                }
                                if (SettingsPanel.level == 2) {
                                    ball.setHP(ball.getHP() - 10);
                                }
                                if (SettingsPanel.level == 3) {
                                    ball.setHP(ball.getHP() - 12);
                                }

                                SoundEffects.playSound("hurt.wav");
                            }


                            enemy2.setdAngle(Math.PI);
                            impact.turnOnImpact(ball.getX(),
                                    ball.getY(),
                                    enemy2.getX(),
                                    enemy2.getY());

                        }
                    }

                    gameController.getEnemies2().set(k, enemy2);
                }
            }
            gameController.setBall(ball);
        }
    }

    // =================================================================================

    public void checkCollisionBulletEnemy1() {
        if (!gameController.getBullets().isEmpty()) {
            for (int j = 0; j < gameController.getBullets().size(); j++) {
                BulletModel bullet = gameController.getBullets().get(j);
                if (bullet.getBulletHealth() > 0) {

                    double xMin1 = bullet.getX();
                    double xMax1 = bullet.getX() + BulletModel.getBulletSize();
                    double yMin1 = bullet.getY();
                    double yMax1 = bullet.getY() + BulletModel.getBulletSize();
                    for (int k = 0; k < gameController.getEnemies1().size(); k++) {
                        EnemyModel1 enemy1 = gameController.getEnemies1().get(k);
                        if (enemy1.getEnemyHealth() > 0) {

                            double xMin2 = enemy1.getxAngles()[0];
                            double xMax2 = enemy1.getxAngles()[0];
                            double yMin2 = enemy1.getyAngles()[0];
                            double yMax2 = enemy1.getyAngles()[0];
                            for (int i = 0; i < 4; i++) {
                                if (enemy1.getxAngles()[i] < xMin2) {
                                    xMin2 = enemy1.getxAngles()[i];
                                }
                                if (enemy1.getxAngles()[i] > xMax2) {
                                    xMax2 = enemy1.getxAngles()[i];
                                }
                                if (enemy1.getyAngles()[i] < yMin2) {
                                    yMin2 = enemy1.getyAngles()[i];
                                }
                                if (enemy1.getyAngles()[i] > yMax2) {
                                    yMax2 = enemy1.getyAngles()[i];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                bullet.setBulletHealth(0);
                                if (gameController.isBulletAres()) {
                                    enemy1.setEnemyHealth(enemy1.getEnemyHealth() - 7);
                                }
                                if (!gameController.isBulletAres()) {
                                    enemy1.setEnemyHealth(enemy1.getEnemyHealth() - 5);
                                }
                                SoundEffects.playSound("hurt.wav");
                                if (enemy1.getEnemyHealth() <= 0) {
                                    SoundEffects.playSound("hit.wav");
                                    gameController.newCollectible(enemy1.getX(), enemy1.getY());
                                }
                                impact.turnOnImpact(bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                        bullet.getY() + ((double) BulletModel.getBulletSize() / 2),
                                        enemy1.getX() + ((double) EnemyModel1.getEnemy1Size() / 2),
                                        enemy1.getY() + ((double) EnemyModel1.getEnemy1Size() / 2));

                            }
                        }

                        gameController.getEnemies1().set(k, enemy1);
                    }
                }

                gameController.getBullets().set(j, bullet);
            }
        }
    }

    public void checkCollisionBulletEnemy2() {
        if (!gameController.getBullets().isEmpty()) {
            for (int j = 0; j < gameController.getBullets().size(); j++) {
                BulletModel bullet = gameController.getBullets().get(j);
                if (bullet.getBulletHealth() > 0) {

                    double xMin1 = bullet.getX();
                    double xMax1 = bullet.getX() + BulletModel.getBulletSize();
                    double yMin1 = bullet.getY();
                    double yMax1 = bullet.getY() + BulletModel.getBulletSize();
                    for (int k = 0; k < gameController.getEnemies2().size(); k++) {
                        EnemyModel2 enemy2 = gameController.getEnemies2().get(k);
                        if (enemy2.getEnemyHealth() > 0) {

                            double xMin2 = enemy2.getxAngles()[0];
                            double xMax2 = enemy2.getxAngles()[0];
                            double yMin2 = enemy2.getyAngles()[0];
                            double yMax2 = enemy2.getyAngles()[0];
                            for (int i = 0; i < 3; i++) {
                                if (enemy2.getxAngles()[i] < xMin2) {
                                    xMin2 = enemy2.getxAngles()[i];
                                }
                                if (enemy2.getxAngles()[i] > xMax2) {
                                    xMax2 = enemy2.getxAngles()[i];
                                }
                                if (enemy2.getyAngles()[i] < yMin2) {
                                    yMin2 = enemy2.getyAngles()[i];
                                }
                                if (enemy2.getyAngles()[i] > yMax2) {
                                    yMax2 = enemy2.getyAngles()[i];
                                }
                            }

                            if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                                    || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                                    || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {

                                bullet.setBulletHealth(0);
                                if (gameController.isBulletAres()) {
                                    enemy2.setEnemyHealth(enemy2.getEnemyHealth() - 7);
                                }
                                if (!gameController.isBulletAres()) {
                                    enemy2.setEnemyHealth(enemy2.getEnemyHealth() - 5);
                                }
                                SoundEffects.playSound("hurt.wav");
                                if (enemy2.getEnemyHealth() <= 0) {
                                    SoundEffects.playSound("hit.wav");
                                    gameController.newCollectible(enemy2.getX(), enemy2.getY());
                                }
                                impact.turnOnImpact(bullet.getX() + ((double) BulletModel.getBulletSize() / 2),
                                        bullet.getY() + ((double) BulletModel.getBulletSize() / 2),
                                        enemy2.getX() + ((double) EnemyModel1.getEnemy1Size() / 2),
                                        enemy2.getY() + ((double) EnemyModel1.getEnemy1Size() / 2));

                            }
                        }

                        gameController.getEnemies2().set(k, enemy2);
                    }
                }

                gameController.getBullets().set(j, bullet);
            }
        }
    }

    // ===============================================================================


    public void checkCollisionBallCollectible() {
        if (gameController.getBall() != null) {
            BallModel ball = gameController.getBall();
            double xMin1 = ball.getX() - BallModel.getBallRadius();
            double xMax1 = ball.getX() + BallModel.getBallRadius();
            double yMin1 = ball.getY() - BallModel.getBallRadius();
            double yMax1 = ball.getY() + BallModel.getBallRadius();
            if (SettingsPanel.level == 1) {
                xMin1 = ball.getX() - ((double) (BallModel.getBallRadius() * 3) / 2);
                xMax1 = ball.getX() + ((double) (BallModel.getBallRadius() * 3) / 2);
                yMin1 = ball.getY() - ((double) (BallModel.getBallRadius() * 3) / 2);
                yMax1 = ball.getY() + ((double) (BallModel.getBallRadius() * 3) / 2);
            }

            if (!gameController.getCollectibles().isEmpty()) {
                for (int i = 0; i < gameController.getCollectibles().size(); i++) {
                    Collectible collectible = gameController.getCollectibles().get(i);
                    if (collectible.getCollectibleHealth() > 0) {
                        double xMin2 = collectible.getX();
                        double xMax2 = collectible.getX() + Collectible.getCollectibleSize();
                        double yMin2 = collectible.getY();
                        double yMax2 = collectible.getY() + Collectible.getCollectibleSize();

                        if (SettingsPanel.level == 1) {
                            xMin2 = collectible.getX() - (Collectible.getCollectibleSize() / 2);
                            xMax2 = collectible.getX() + (Collectible.getCollectibleSize() * 3 / 2);
                            yMin2 = collectible.getY() - (Collectible.getCollectibleSize() / 2);
                            yMax2 = collectible.getY() + (Collectible.getCollectibleSize() * 3 / 2);
                        }

                        if (((xMin2 >= xMin1 && xMin2 <= xMax1) && (yMin2 >= yMin1 && yMin2 <= yMax1))
                                || ((xMin2 <= xMin1 && xMax2 >= xMin1) && (yMin2 >= yMin1 && yMin2 <= yMax1))
                                || ((xMin2 >= xMin1 && xMin2 <= xMax1) && (yMax2 >= yMin1 && yMax2 <= yMax1))
                                || ((xMin2 <= xMin1 && xMax2 >= xMin1) && (yMax2 >= yMin1 && yMax2 <= yMax1))) {
                            collectible.setCollectibleHealth(0);
                            EnterNamePage.player.setXP(EnterNamePage.player.getXP() + 5);

                        }
                    }

                    gameController.getCollectibles().set(i, collectible);

                }
            }
        }
    }


    // ===============================================================================


    // a method for checking intersections between enemy1 and the ballAngle

    public void checkCollisionBallAngleEnemy1() {
        if (gameController.getBallAngle() != null) {
            BallAngle ballAngle = gameController.getBallAngle();
            double xMin1 = ballAngle.getX() - BallAngle.getBallAngleRadius();
            double xMax1 = ballAngle.getX() + BallAngle.getBallAngleRadius();
            double yMin1 = ballAngle.getY() - BallAngle.getBallAngleRadius();
            double yMax1 = ballAngle.getY() + BallAngle.getBallAngleRadius();
            for (int k = 0; k < gameController.getEnemies1().size(); k++) {
                EnemyModel1 enemy1 = gameController.getEnemies1().get(k);
                if (enemy1.getEnemyHealth() > 0) {

                    double xMin2 = enemy1.getxAngles()[0];
                    double xMax2 = enemy1.getxAngles()[0];
                    double yMin2 = enemy1.getyAngles()[0];
                    double yMax2 = enemy1.getyAngles()[0];
                    for (int i = 0; i < 4; i++) {
                        if (enemy1.getxAngles()[i] < xMin2) {
                            xMin2 = enemy1.getxAngles()[i];
                        }
                        if (enemy1.getxAngles()[i] > xMax2) {
                            xMax2 = enemy1.getxAngles()[i];
                        }
                        if (enemy1.getyAngles()[i] < yMin2) {
                            yMin2 = enemy1.getyAngles()[i];
                        }
                        if (enemy1.getyAngles()[i] > yMax2) {
                            yMax2 = enemy1.getyAngles()[i];
                        }
                    }

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        enemy1.setDash(false);
                        enemy1.setdAngle(Math.PI);
                        enemy1.setEnemyHealth(enemy1.getEnemyHealth() - 10);
                        SoundEffects.playSound("hurt.wav");
                        if (enemy1.getEnemyHealth() <= 0) {
                            SoundEffects.playSound("hit.wav");
                        }
                        impact.turnOnImpact(gameController.getBall().getX(),
                                gameController.getBall().getY(),
                                enemy1.getX(),
                                enemy1.getY());

                    }
                }

                gameController.getEnemies1().set(k, enemy1);
            }
        }
    }


    // a method for checking intersections between enemy2 and the ball

    public void checkCollisionBallAngleEnemy2() {
        if (gameController.getBallAngle() != null) {
            BallAngle ballAngle = gameController.getBallAngle();
            double xMin1 = ballAngle.getX() - BallAngle.getBallAngleRadius();
            double xMax1 = ballAngle.getX() + BallAngle.getBallAngleRadius();
            double yMin1 = ballAngle.getY() - BallAngle.getBallAngleRadius();
            double yMax1 = ballAngle.getY() + BallAngle.getBallAngleRadius();
            for (int k = 0; k < gameController.getEnemies2().size(); k++) {
                EnemyModel2 enemy2 = gameController.getEnemies2().get(k);
                if (enemy2.getEnemyHealth() > 0) {

                    double xMin2 = enemy2.getxAngles()[0];
                    double xMax2 = enemy2.getxAngles()[0];
                    double yMin2 = enemy2.getyAngles()[0];
                    double yMax2 = enemy2.getyAngles()[0];
                    for (int i = 0; i < 3; i++) {
                        if (enemy2.getxAngles()[i] < xMin2) {
                            xMin2 = enemy2.getxAngles()[i];
                        }
                        if (enemy2.getxAngles()[i] > xMax2) {
                            xMax2 = enemy2.getxAngles()[i];
                        }
                        if (enemy2.getyAngles()[i] < yMin2) {
                            yMin2 = enemy2.getyAngles()[i];
                        }
                        if (enemy2.getyAngles()[i] > yMax2) {
                            yMax2 = enemy2.getyAngles()[i];
                        }
                    }

                    if (((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMin1 >= yMin2 && yMin1 <= yMax2))
                            || ((xMin1 >= xMin2 && xMin1 <= xMax2) && (yMax1 >= yMin2 && yMax1 <= yMax2))
                            || ((xMin1 <= xMin2 && xMax1 >= xMin2) && (yMax1 >= yMin2 && yMax1 <= yMax2))) {


                        enemy2.setdAngle(Math.PI);
                        enemy2.setEnemyHealth(enemy2.getEnemyHealth() - 10);
                        SoundEffects.playSound("hurt.wav");
                        if (enemy2.getEnemyHealth() <= 0) {
                            SoundEffects.playSound("hit.wav");
                        }
                        impact.turnOnImpact(gameController.getBall().getX(),
                                gameController.getBall().getY(),
                                enemy2.getX(),
                                enemy2.getY());

                    }
                }

                gameController.getEnemies2().set(k, enemy2);

            }
        }
    }
}