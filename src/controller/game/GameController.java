package controller.game;

import controller.data.controller.SoundEffects;
import controller.game.objectsController.ball.CollectibleController;
import model.entity.*;
import model.entity.enemy.EnemyModel1;
import model.entity.enemy.EnemyModel2;
import view.game.GameFrame;
import view.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class GameController {

    private BallModel ball;
    private BulletModel bullet;
    private EnemyModel1 enemy1;
    private EnemyModel2 enemy2;
    private Collectible collectible;
    private BallDirection ballDirection;
    private BallAngle ballAngle;
    private ArrayList<BulletModel> bullets = new ArrayList<>();
    private ArrayList<EnemyModel1> enemies1 = new ArrayList<>();
    private ArrayList<EnemyModel2> enemies2 = new ArrayList<>();
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private int wave = 1;
    private int Banish = 0;
    private int Empower = 0;
    private boolean bulletAres;
    private boolean empowerBullet;


    public BallAngle getBallAngle() {
        return ballAngle;
    }

    public void setBallAngle(BallAngle ballAngle) {
        this.ballAngle = ballAngle;
    }

    public BallModel getBall() {
        return ball;
    }

    public void setBall(BallModel ball) {
        this.ball = ball;
    }

    public BulletModel getBullet() {
        return bullet;
    }

    public void setBullet(BulletModel bullet) {
        this.bullet = bullet;
    }

    public EnemyModel1 getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(EnemyModel1 enemy1) {
        this.enemy1 = enemy1;
    }

    public EnemyModel2 getEnemy2() {
        return enemy2;
    }

    public void setEnemy2(EnemyModel2 enemy2) {
        this.enemy2 = enemy2;
    }

    public Collectible getCollectible() {
        return collectible;
    }

    public void setCollectible(Collectible collectible) {
        this.collectible = collectible;
    }

    public BallDirection getBallDirection() {
        return ballDirection;
    }

    public void setBallDirection(BallDirection ballDirection) {
        this.ballDirection = ballDirection;
    }

    public ArrayList<BulletModel> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<BulletModel> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<EnemyModel1> getEnemies1() {
        return enemies1;
    }

    public void setEnemies1(ArrayList<EnemyModel1> enemies1) {
        this.enemies1 = enemies1;
    }

    public ArrayList<EnemyModel2> getEnemies2() {
        return enemies2;
    }

    public void setEnemies2(ArrayList<EnemyModel2> enemies2) {
        this.enemies2 = enemies2;
    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getBanish() {
        return Banish;
    }

    public void setBanish(int banish) {
        this.Banish = banish;
    }

    public int getEmpower() {
        return Empower;
    }

    public void setEmpower(int empower) {
        this.Empower = empower;
    }

    public boolean isBulletAres() {
        return bulletAres;
    }

    public void setBulletAres(boolean bulletAres) {
        this.bulletAres = bulletAres;
    }

    public boolean isEmpowerBullet() {
        return empowerBullet;
    }

    public void setEmpowerBullet(boolean empowerBullet) {
        this.empowerBullet = empowerBullet;
    }

    // creating the ball ================================================

    public BallModel newBall() {
        ball = new BallModel((double) (GameFrame.width / 2) - 20, (double) (GameFrame.height / 2) - 20);
        return ball;
    }


    public BallAngle createBallAngle() {
        ballAngle = new BallAngle(ball.getX(), ball.getY());
        return ballAngle;
    }


    public BallDirection createBallDirection() {
        ballDirection = new BallDirection(ball.getX(), ball.getY());
        return ballDirection;
    }


    // creating the bullets ================================================

    public BulletModel newBullet(Point point) {
        if (ball != null) {
            if (!GamePanel.pause) {
                bullet = new BulletModel(ball.getX(), ball.getY());
                bullet.setDx(((point.x - (ball.getX())) / Math.sqrt(Math.pow((point.x - (ball.getX())), 2) + Math.pow((point.y - (ball.getY())), 2))) * BulletModel.getBulletSpeed());
                if (bullet.getY() < point.y) {
                    bullet.setDy(Math.sqrt(Math.pow(BulletModel.getBulletSpeed(), 2) - Math.pow(bullet.getDx(), 2)));
                } else {
                    bullet.setDy(-(Math.sqrt(Math.pow(BulletModel.getBulletSpeed(), 2) - Math.pow(bullet.getDx(), 2))));
                }

                bullets.add(0, bullet);

                return bullet;
            }

        }
        return null;
    }


    // creating the enemies ================================================

    public void newEnemy1() {
        if (!GamePanel.pause) {

            if (enemies1.size() % 2 == 0) {
                SoundEffects.playSound("E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\enemyEnter.wav");
                enemy1 = new EnemyModel1(50, (double) GameFrame.height / 2);
                enemies1.add(0, enemy1);
            } else {
                SoundEffects.playSound("E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\enemyEnter.wav");
                enemy1 = new EnemyModel1((double) GameFrame.width / 2, 50);
                enemy1.setDash(true);
                enemies1.add(0, enemy1);
            }
        }
    }

    public void newEnemy2() {
        if (!GamePanel.pause) {

            if (enemies2.size() % 2 == 0) {
                SoundEffects.playSound("E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\enemyEnter.wav");
                enemy2 = new EnemyModel2((double) GameFrame.width - 60, (double) GameFrame.height / 2);
                enemies2.add(0, enemy2);
            } else {
                SoundEffects.playSound("E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\enemyEnter.wav");
                enemy2 = new EnemyModel2(((double) GameFrame.width / 2), GameFrame.height - 60);
                enemies2.add(0, enemy2);
            }

        }
    }


    // this part is for collectibles ====================================================

    public void newCollectible(double x, double y) {
        collectible = new Collectible(x, y);
        collectibles.add(collectible);
        CollectibleController.countDownCollectible(10, collectible);//collectible will disappear in 10 seconds
    }


    // restarting the game ================================================================

    public void restartGame() {
        bullets.clear();
        enemies2.clear();
        enemies1.clear();
        bullets.clear();
        collectibles.clear();
        ball.setHP(100);
        GameFrame.count = 0;
        GameFrame.x = 300;
        GameFrame.y = 50;
        GameFrame.width = 600;
        GameFrame.height = 600;
    }


}
