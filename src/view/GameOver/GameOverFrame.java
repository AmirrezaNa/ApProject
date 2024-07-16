package view.GameOver;

import controller.Constants;
import view.startPage.StartPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverFrame extends JFrame implements ActionListener {

    JButton Menu;
    GameOverPanel gameOverPanel;

    public GameOverFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
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
