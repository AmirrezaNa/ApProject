package game.frame;


import game.controller.GameController;
import game.controller.Rotation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class GameFrame extends JFrame {

    public static int width = 600;
    public static int height = 600;
    public static int x = 300;
    public static int y = 50;
    GamePanel gamePanel;
    static boolean GameIsRunning = true;
    Rotation rotation;

    public GameFrame() {
        GamePanel.closeAllWindows();
        // after minimizing all windows setting the state to normal prevents minimizing the game frame
        this.setState(JFrame.NORMAL);
        gamePanel = new GamePanel();
        GameFrameStuff gameFrameStuff = new GameFrameStuff();


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

    public void changeGameFrameSize() {
        if (!countDown) {
            // this timer reduces the frame size ========================================
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (width > 300) {
                        // reduce width gradually
                        x += 1;
                        width -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }
                    if (height > 300) {
                        // reduce height gradually
                        y += 1;
                        height -= 2;
                        setBounds(x, y, width, height);
                        gamePanel.revalidate();
                        gamePanel.repaint();
                    }

                }
            });
            timer.start();

            // ==========================================================================
        }

    }

}

