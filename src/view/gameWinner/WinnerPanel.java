package view.gameWinner;

import controller.Constants;
import controller.game.GameController;
import view.GameOver.GameOverFrame;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;

public class WinnerPanel extends JPanel {
    WinnerPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString("You Did Great!", 80, 100);
        g.drawString("XP : " + EnterNamePage.player.getXP(), 110, 150);
        g.drawString("Bullets : " + GameController.numberOfBullets, 80, 200);
        g.drawString("Enemies : 156" , 70, 250);
    }
}
