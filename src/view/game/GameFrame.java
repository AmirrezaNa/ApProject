package view.game;


import view.GameOver.GameOverFrame;
import controller.data.controller.DataManager;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import model.entity.BallModel;
import view.gameWinner.WinnerFrame;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameFrame extends JFrame {

    public static int width = 600;
    public static int height = 600;
    public static int x = 300;
    public static int y = 50;
    GamePanel gamePanel;
    GameController gameController;
    static boolean GameIsRunning = true;
    GameFrameStuff gameFrameStuff;

    public GameFrame() {
        gameController = new GameController();
        GamePanel.pause = false;
        GamePanel.closeAllWindows();
        // after minimizing all windows setting the state to normal prevents minimizing the game frame
        this.setState(JFrame.NORMAL);
        gamePanel = new GamePanel();
        gameFrameStuff = new GameFrameStuff();


        Thread thread = new Thread(gamePanel);
        thread.start();


        //rotation = new Rotation();
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        this.add(gamePanel);
        setBounds(x, y, width, height);
        this.setVisible(true);
        countToTenSeconds();
        check();
    }

    public static int count = 0;
    public static boolean countDown = true;

    public void countToTenSeconds() {

        if (countDown) {
            Timer timer = new Timer(1000, new ActionListener() { // Timer with 1-second (1000 milliseconds) delay
                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;

                    if (count == 10) {
                        countDown = false;
                        gamePanel.revalidate();
                        gamePanel.repaint();
                        changeGameFrameSize();
                        ((Timer) e.getSource()).stop(); // Stop the timer after reaching 10 seconds
                    }
                }
            });

            timer.start(); // Start the timer
        }

    }

    Timer timer;

    public void changeGameFrameSize() {
        if (!countDown) {
            // this timer reduces the frame size ========================================
            timer = new Timer(100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        checkGameOver();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (width > 300 && !GamePanel.pause) {
                        // reduce width gradually
                        x += 1;
                        width -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (height > 300 && !GamePanel.pause) {
                        // reduce height gradually
                        y += 1;
                        height -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (gameController.getEnemies1().size() + gameController.getEnemies2().size() == 10) {
                        countDown = true;
                        SoundEffects.playSound("changeWave.wav");
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        Timer delayTimer = new Timer(20000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                gamePanel.revalidate();
                                gamePanel.repaint();
                                ((Timer) e.getSource()).stop(); // Stop the delay timer
                                timer.start(); // Restart the original timer
                            }
                        });
                        delayTimer.setRepeats(false); // Execute the delay timer only once
                        delayTimer.start(); // Start the delay timer
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (gameController.getEnemies1().size() + gameController.getEnemies2().size() == 25) {
                        countDown = true;
                        SoundEffects.playSound("changeWave.wav");
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        Timer delayTimer2 = new Timer(20000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                gamePanel.revalidate();
                                gamePanel.repaint();
                                ((Timer) e.getSource()).stop(); // Stop the delay timer
                                timer.start(); // Restart the original timer
                            }
                        });
                        delayTimer2.setRepeats(false); // Execute the delay timer only once
                        delayTimer2.start(); // Start the delay timer
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }

                }
            });
            timer.start();

            // ==========================================================================
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
            }
        });
        timer1.start();

    }

    public void checkGameOver() throws IOException {
        if (gameController.getBall() != null) {
            if (gameController.getBall().getHP() <= 0) {
                gameFrameStuff.dispose();
                GamePanel.pause = true;
                this.dispose();
                gameController.restartGame();
                gamePanel.revalidate();
                gamePanel.repaint();
                if (DataManager.checkPlayerExists(EnterNamePage.player.getName())) {
                    DataManager.updatePlayerData();
                } else {
                    DataManager.createPlayerData(EnterNamePage.player);
                }
                SoundEffects.playSound("endSound.wav");
                GameOverFrame gameOverFrame = new GameOverFrame();
            }
        }
    }

    public void checkWinner() {
        if (gameController.getBall() != null) {
            boolean playerHasWon = true;
            if (gameController.getEnemies1().size() + gameController.getEnemies2().size() < 35) {
                playerHasWon = false;
            }
            if (gameController.getEnemies1().size() + gameController.getEnemies2().size() == 35) {
                for (int i = 0; i < gameController.getEnemies1().size(); i++) {
                    if (gameController.getEnemies1().get(i).getEnemyHealth() > 0) {
                        playerHasWon = false;
                    }
                }
                for (int j = 0; j < gameController.getEnemies2().size(); j++) {
                    if (gameController.getEnemies2().get(j).getEnemyHealth() > 0) {
                        playerHasWon = false;
                    }
                }
            }
            if (playerHasWon) {
                displayWin();
            }

        }
    }


    Timer timer1;
    boolean isAnimationComplete = false;

    public void displayWin() {
        GamePanel.pause = true;
        timer1 = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAnimationComplete) {
                    if (width >= 0) {
                        // reduce width gradually
                        x += 1;
                        BallModel.setBallRadius(BallModel.getBallRadius()+1);
                        width -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (height >= 0) {
                        // reduce height gradually
                        BallModel.setBallRadius(BallModel.getBallRadius()+1);
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
        if (gameController.getBall() != null) {
            gameFrameStuff.dispose();
            GamePanel.pause = true;
            gameController.restartGame();
            gamePanel.revalidate();
            gamePanel.repaint();
            try {
                if (DataManager.checkPlayerExists(EnterNamePage.player.getName())) {
                    DataManager.updatePlayerData();
                } else {
                    DataManager.createPlayerData(EnterNamePage.player);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            SoundEffects.playSound("winnerSound.wav");
            WinnerFrame winnerFrame = new WinnerFrame();
        }
    }

}

