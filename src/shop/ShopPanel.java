package shop;

import GameOver.GameOverFrame;
import game.controller.GameController;
import startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    ShopPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(GameOverFrame.WIDTH, GameOverFrame.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Wave : " + GameController.wave, 30, 50);
        g.drawString("Elapsed time : " + EnterNamePage.player.XP, 150, 50);
        g.drawString("XP : " + EnterNamePage.player.XP, 30, 100);
        g.drawString("HP : " + GameController.ball.HP, 150, 100);

    }
}
