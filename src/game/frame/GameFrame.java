package game.frame;


import game.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    public static int width = 600;
    public static int height = 600;
    public static int x = 300;
    public static int y = 50;
    GamePanel gamePanel;
    static boolean GameIsRunning = true;

    public GameFrame() {
        GamePanel.closeAllWindows();
        // after minimizing all windows setting the state to normal prevents minimizing the game frame
        this.setState(JFrame.NORMAL);
        gamePanel = new GamePanel();
        GameFrameStuff gameFrameStuff = new GameFrameStuff();


        Thread thread = new Thread(gamePanel);
        thread.start();

        changeGameFrameSize();
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        this.add(gamePanel);
        this.setVisible(true);
    }

    public void changeGameFrameSize() {


        // this timer reduces the frame size ========================================
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (width > 300) {
                    // reduce width gradually
                    x += 1;
                    width -= 2;
                    setBounds(x, y, width, height);
                }
                if (height > 300) {
                    // reduce height gradually
                    y += 1;
                    height -= 2;
                    setBounds(x, y, width, height);
                }
            }
        });
        timer.start();

        // ==========================================================================

    }

}

