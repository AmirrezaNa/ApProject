package view.gameLoop.phase2.normalAndMiniBossEnemies;

import controller.Constants;
import controller.data.controller.DataManager;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.GameRestart;
import view.gameLoop.phase1.GamePanel;
import view.gameLoop.phase2.finalBoss.FinalBossFrame;
import view.shop.ShopFrame;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static view.gameLoop.phase1.GamePanel.ball;
import static view.gameLoop.phase1.GamePanel.phase1over;
import static view.gameLoop.phase2.normalAndMiniBossEnemies.GamePanel2.phase2Over;

public class GameFrame2 extends JFrame implements ActionListener{


    GamePanel2 gamePanel;
    JButton storeButton;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public GameFrame2() {
        GamePanel.phase1over = true;
        this.setBounds(0, 0, width, height);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        this.setUndecorated(true); // Remove window decorations
        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background
        this.setOpacity(0.7f); // Set the opacity level (0.0f - 1.0f)
        this.setState(JFrame.NORMAL);


        gamePanel = new GamePanel2();
        Thread thread = new Thread(gamePanel);
        thread.start();



        storeButton = new JButton();
        storeButton.setFocusable(false);
        storeButton.setBackground(new Color(0x8F0404));
        storeButton.setText("Store");
        storeButton.setForeground(Color.BLACK);
        storeButton.setBounds(width - 60, 10, 50, 50);
        storeButton.addActionListener(this);
        storeButton.setBorder(BorderFactory.createEtchedBorder());



        this.add(storeButton);
        this.add(gamePanel);
        this.setVisible(true);
        check();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == storeButton) {
            GameController.pause = true;
            ShopFrame shopFrame = new ShopFrame();
        }
    }


    public void check() {
        Timer timer1 = new Timer(100, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gamePanel.revalidate();
                    gamePanel.repaint();
                    checkGameOver();
                    checkWinner();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (phase2Over) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer1.start();

    }

    public void checkGameOver() throws IOException {
        if (GameController.ball != null) {
            if (GameController.ball.HP <= 0) {
                if (!phase2Over) {
                    phase2Over = true;
                    this.dispose();
                    GameRestart.restartGame();
                    gamePanel.revalidate();
                    gamePanel.repaint();
                    if (DataManager.checkPlayerExists(EnterNamePage.player.getName())) {
                        DataManager.updatePlayerData();
                    } else {
                        DataManager.createPlayerData(EnterNamePage.player);
                    }
                    SoundEffects.playSound(Constants.END_SOUND_PATH);
//                GameOverFrame gameOverFrame = new GameOverFrame();
                    FinalBossFrame finalBossFrame = new FinalBossFrame();
                }
            }
        }
    }

    public void checkWinner() {
        if (GameController.ball != null) {
            boolean playerHasWonPhase2 = true;
            if (GameController.omenoctEnemies.size() + GameController.necropickEnemies.size()
                    + GameController.archmireEnemies.size() + GameController.wyrmEnemies.size()
                    + GameController.blackOrbEnemies.size() + GameController.barricadosEnemies1.size()
                    + GameController.barricadosEnemies2.size() < 20){
                playerHasWonPhase2 = false;
            }
            if (GameController.omenoctEnemies.size() + GameController.necropickEnemies.size()
                    + GameController.archmireEnemies.size() + GameController.wyrmEnemies.size()
                    + GameController.blackOrbEnemies.size() + GameController.barricadosEnemies1.size()
                    + GameController.barricadosEnemies2.size() == 10 && GameController.wave == 4){
                GameController.wave++;
            }
            if (GameController.omenoctEnemies.size() + GameController.necropickEnemies.size()
                    + GameController.archmireEnemies.size() + GameController.wyrmEnemies.size()
                    + GameController.blackOrbEnemies.size() + GameController.barricadosEnemies1.size()
                    + GameController.barricadosEnemies2.size() == 20) {
                for (int i = 0; i < GameController.omenoctEnemies.size(); i++) {
                    if (GameController.omenoctEnemies.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < GameController.necropickEnemies.size(); j++) {
                    if (GameController.necropickEnemies.get(j).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int i = 0; i < GameController.archmireEnemies.size(); i++) {
                    if (GameController.archmireEnemies.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < GameController.wyrmEnemies.size(); j++) {
                    if (GameController.wyrmEnemies.get(j).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int i = 0; i < GameController.blackOrbEnemies.size(); i++) {
                    if (GameController.blackOrbEnemies.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < GameController.barricadosEnemies1.size(); j++) {
                    if (GameController.barricadosEnemies1.get(j).enemyTimer > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
                for (int j = 0; j < GameController.barricadosEnemies2.size(); j++) {
                    if (GameController.barricadosEnemies2.get(j).enemyTimer > 0) {
                        playerHasWonPhase2 = false;
                        break;
                    }
                }
            }
            if (playerHasWonPhase2 && GameController.wave == 5) {
                GameController.wave++;
                displayEndOfPhase2();
                FinalBossFrame finalBossFrame = new FinalBossFrame();
                // in this part should create the bossFight frame
            }

        }
    }


    public void displayEndOfPhase2() {
        if (GameController.ball != null) {
            int hp = ball.HP;
            GameRestart.restartGame();
            ball.HP = hp;

            phase2Over = true;
            GameController.pause = false;
            this.dispose();
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }

}
