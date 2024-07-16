package view.GameOver;

import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    GameOverPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(GameOverFrame.WIDTH, GameOverFrame.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString("Game Over!", 100, 150);
        g.drawString("XP : " + EnterNamePage.player.getXP(), 120, 220);
    }

}
