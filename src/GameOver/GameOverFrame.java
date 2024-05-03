package GameOver;

import startPage.StartPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverFrame extends JFrame implements ActionListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 550;
    JButton Menu;
    GameOverPanel gameOverPanel;

    public GameOverFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        Menu = new JButton();
        Menu.setFocusable(false);
        Menu.setBackground(new Color(0x8F0404));
        Menu.setText("Menu");
        Menu.setForeground(Color.BLACK);
        Menu.setBounds(115, 300, 100, 50);
        Menu.addActionListener(this);
        Menu.setBorder(BorderFactory.createEtchedBorder());
        this.add(Menu);



        gameOverPanel = new GameOverPanel();
        this.add(gameOverPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Menu) {
            StartPageFrame startPageFrame = new StartPageFrame();
        }
    }
}
