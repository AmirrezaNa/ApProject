package game.frame;


import GameOver.GameOverFrame;
import game.DataManager;
import game.controller.GameController;
import game.controller.Rotation;
import startPage.EnterNamePage;
import startPage.StartPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimerTask;

public class GameFrame extends JFrame {

    public static int width = 600;
    public static int height = 600;
    public static int x = 300;
    public static int y = 50;
    GamePanel gamePanel;
    static boolean GameIsRunning = true;
    GameFrameStuff gameFrameStuff;

    public GameFrame() {
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

    int count = 0;
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
                    if (GameController.enemies1.size() + GameController.enemies2.size() == 10) {
                        countDown = true;
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
                    checkGameOver();
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
                gameFrameStuff.dispose();
                this.dispose();
                GameController.restartGame();
                if (DataManager.checkPlayerExists(EnterNamePage.player.name)) {
                    DataManager.updatePlayerData();
                } else {
                    DataManager.createPlayerData(EnterNamePage.player);
                }
                GameOverFrame gameOverFrame = new GameOverFrame();
            }
        }
    }

}

