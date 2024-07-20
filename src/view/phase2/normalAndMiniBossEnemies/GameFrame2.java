package view.phase2.normalAndMiniBossEnemies;

import controller.Constants;
import controller.data.controller.DataManager;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.GameRestart;
import view.phase1.GamePanel;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame2 extends JFrame{


    GamePanel2 gamePanel;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension screenSize = toolkit.getScreenSize();
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public GameFrame2() {
        this.setBounds(0, 0, width, height);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        this.setUndecorated(true); // Remove window decorations
        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background
        this.setOpacity(0.7f); // Set the opacity level (0.0f - 1.0f)
        this.setState(JFrame.NORMAL);


        gamePanel = new GamePanel2();
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
                GameFrame2 gameFrame2 = new GameFrame2();
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


//    Timer timer1;
//    boolean isAnimationComplete = false;
//
//    public void displayWin() {
//        GamePanel.phase1over = true;
//        timer1 = new Timer(100, new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!isAnimationComplete) {
//                    if (width >= 0) {
//                        // reduce width gradually
//                        x += 1;
//                        BallModel.ballRadius++;
//                        width -= 2;
//                        setBounds(x, y, width, height);
//                        gamePanel.revalidate();
//                        gamePanel.repaint();
//                    }
//                    if (height >= 0) {
//                        // reduce height gradually
//                        BallModel.ballRadius++;
//                        y += 1;
//                        height -= 2;
//                        setBounds(x, y, width, height);
//                        gamePanel.revalidate();
//                        gamePanel.repaint();
//                    }
//                    if (width <= 0 && height <= 0) {
//                        isAnimationComplete = true;
//                        ((Timer) e.getSource()).stop();
//                        displayWinnerWindow();
//                    }
//                }
//            }
//        });
//        timer1.start();
//    }
//
//
//    public void displayWinnerWindow() {
//        if (GameController.ball != null) {
//            this.dispose();
//            try {
//                if (DataManager.checkPlayerExists(EnterNamePage.player.getName())) {
//                    DataManager.updatePlayerData();
//                } else {
//                    DataManager.createPlayerData(EnterNamePage.player);
//                }
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//            SoundEffects.playSound(Constants.WINNER_SOUND_PATH);
//
//            WinnerFrame winnerFrame = new WinnerFrame();
//        }
//    }
}
