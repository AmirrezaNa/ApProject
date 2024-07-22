package view.gameLoop.phase2.finalBoss;

import controller.Constants;
import controller.data.controller.DataManager;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import controller.game.GameRestart;
import model.entity.BallModel;
import model.entity.enemy.boss.SmileyModel;
import view.gameWinner.WinnerFrame;
import view.shop.ShopFrame;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FinalBossFrame extends JFrame implements ActionListener {
    FinalBossPanel gamePanel;
    JButton storeButton;

    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension screenSize = toolkit.getScreenSize();
    public static int x = 0;
    public static int y = 0;
    public static int width = screenSize.width;
    public static int height = screenSize.height;

    public FinalBossFrame() {
        this.setBounds(x, y, width, height);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen
        this.setUndecorated(true); // Remove window decorations
        this.setBackground(new Color(0, 0, 0, 0)); // Set transparent background
        this.setOpacity(0.7f); // Set the opacity level (0.0f - 1.0f)
        this.setState(JFrame.NORMAL);


        gamePanel = new FinalBossPanel();
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
        Timer timer1 = new Timer(100, new ActionListener() {

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
                GameController.pause = true;
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
            boolean playerHasWonFinalBoss = GameController.smiley.enemyHealth <= 0;
            if (playerHasWonFinalBoss) {
                SmileyModel.imageIcon = "E:\\java projects\\ApProjectPhase1\\src\\controller\\data\\store\\apIcons\\dead.png";
                displayWin();
            }

        }
    }


    Timer timer1;
    boolean isAnimationComplete = false;

    public void displayWin() {
        FinalBossPanel.finalBossOver = true;
        timer1 = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAnimationComplete) {
                    if (width >= 0) {
                        // reduce width gradually
                        x += 1;
                        BallModel.ballRadius++;
                        width -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (height >= 0) {
                        // reduce height gradually
                        BallModel.ballRadius++;
                        y += 1;
                        height -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (width <= 0 && height <= 0) {
                        isAnimationComplete = true;
                        ((Timer) e.getSource()).stop();
                        displayWinnerWindow();
                    }
                }
            }
        });
        timer1.start();
    }


    public void displayWinnerWindow() {
        if (GameController.ball != null) {
            this.dispose();
            try {
                if (DataManager.checkPlayerExists(EnterNamePage.player.getName())) {
                    DataManager.updatePlayerData();
                } else {
                    DataManager.createPlayerData(EnterNamePage.player);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            SoundEffects.playSound(Constants.WINNER_SOUND_PATH);

            WinnerFrame winnerFrame = new WinnerFrame();
        }
    }
}
