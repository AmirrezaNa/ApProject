package view.gameWinner;

import view.GameOver.GameOverFrame;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;

public class WinnerPanel extends JPanel {
    WinnerPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(GameOverFrame.WIDTH, GameOverFrame.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString("You Did Great!", 80, 150);
        g.drawString("XP : " + EnterNamePage.player.getXP(), 120, 220);
    }
}
