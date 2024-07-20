package view.phase2.finalBoss;

import controller.Constants;
import controller.data.controller.DataManager;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.GameRestart;
import view.phase1.GamePanel;
import view.phase2.normalAndMiniBossEnemies.GameFrame2;
import view.phase2.normalAndMiniBossEnemies.GamePanel2;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FinalBossFrame extends JFrame {
    FinalBossPanel gamePanel;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public FinalBossFrame() {
        this.setBounds(0, 0, width, height);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        this.setUndecorated(true); // Remove window decorations
        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background
        this.setOpacity(0.7f); // Set the opacity level (0.0f - 1.0f)
        this.setState(JFrame.NORMAL);


        gamePanel = new FinalBossPanel();
        Thread thread = new Thread(gamePanel);
        thread.start();


        this.add(gamePanel);



        this.setVisible(true);
        check();
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
            }
        });
        timer1.start();

    }

    public void checkGameOver() throws IOException {
        if (GameController.ball != null) {
            if (GameController.ball.HP <= 0) {
                GamePanel.phase1over = true;
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

            }
        }
    }

    public void checkWinner() {
        if (GameController.ball != null) {
            boolean playerHasWonPhase2 = true;
            if (GameController.enemies1.size() + GameController.enemies2.size() < 35) {
                playerHasWonPhase2 = false;
            }
            if (GameController.enemies1.size() + GameController.enemies2.size() == 35) {
                for (int i = 0; i < GameController.enemies1.size(); i++) {
                    if (GameController.enemies1.get(i).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                    }
                }
                for (int j = 0; j < GameController.enemies2.size(); j++) {
                    if (GameController.enemies2.get(j).enemyHealth > 0) {
                        playerHasWonPhase2 = false;
                    }
                }
            }
            if (playerHasWonPhase2) {
                // in this part should create the bossFight frame
            }

        }
    }
}
