package view.gameLoop.phase1;


import controller.Constants;
import controller.game.GameRestart;
import controller.data.controller.DataManager;
import controller.data.controller.SoundEffects;
import controller.game.GameController;
import model.entity.BallModel;
import view.gameLoop.phase2.normalAndMiniBossEnemies.GameFrame2;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static view.gameLoop.phase1.GamePanel.phase1over;

public class GameFrame extends JFrame {

    public static int width = 600;
    public static int height = 600;
    public static int x = 300;
    public static int y = 50;
    GamePanel gamePanel;
    static boolean GameIsRunning = true;
    GameFrameStuff gameFrameStuff;
    public static Thread thread;

    public GameFrame() {
        phase1over = false;
        GamePanel.closeAllWindows();
        // after minimizing all windows setting the state to normal prevents minimizing the game frame
        this.setState(JFrame.NORMAL);
        gamePanel = new GamePanel();
        gameFrameStuff = new GameFrameStuff();


        thread = new Thread(gamePanel);
        thread.start();


        this.setUndecorated(true);
//        this.setLocationRelativeTo(null);
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

                    if (width > 300 && !phase1over) {
                        // reduce width gradually
                        x += 1;
                        width -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (height > 300 && !phase1over) {
                        // reduce height gradually
                        y += 1;
                        height -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (GameController.enemies1.size() + GameController.enemies2.size() == 10) {
                        countDown = true;
                        SoundEffects.playSound(Constants.CHANGE_WAVE_SOUND_PATH);
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
                    if (GameController.enemies1.size() + GameController.enemies2.size() == 25) {
                        countDown = true;
                        SoundEffects.playSound(Constants.CHANGE_WAVE_SOUND_PATH);
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
                    checkPhase1Over();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (phase1over) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer1.start();

    }

    public void checkGameOver() throws IOException {
        if (GameController.ball != null) {
            if (GameController.ball.HP <= 0) {
                if (!phase1over) {
                    gameFrameStuff.dispose();
                    phase1over = true;
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
    }

    public void checkPhase1Over() {
        if (GameController.ball != null) {
            boolean playerHasPassedPhase1 = true;
            if (GameController.enemies1.size() + GameController.enemies2.size() < 35) {
                playerHasPassedPhase1 = false;
            }
            if (GameController.enemies1.size() + GameController.enemies2.size() == 35) {
                for (int i = 0; i < GameController.enemies1.size(); i++) {
                    if (GameController.enemies1.get(i).enemyHealth > 0) {
                        playerHasPassedPhase1 = false;
                    }
                }
                for (int j = 0; j < GameController.enemies2.size(); j++) {
                    if (GameController.enemies2.get(j).enemyHealth > 0) {
                        playerHasPassedPhase1 = false;
                    }
                }
            }
            if (playerHasPassedPhase1) {
                displayWin();
            }

        }
    }


    Timer timer1;
    boolean isAnimationComplete = false;

    public void displayWin() {
        phase1over = true;
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
                        displayEndOfPhase1();
                        GameFrame2 gameFrame2 = new GameFrame2();
                    }
                }
            }
        });
        timer1.start();
    }


    public void displayEndOfPhase1() {
        if (GameController.ball != null) {
            this.dispose();
            gameFrameStuff.dispose();
            phase1over = true;
            GameRestart.restartGame();
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }

}

