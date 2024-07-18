package view.phase2;

import javax.swing.*;
import java.awt.*;

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
    }
}
