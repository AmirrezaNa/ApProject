package view.shop;

import controller.Constants;
import view.GameOver.GameOverFrame;
import controller.game.GameController;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    GameController gameController = new GameController();
    ShopPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Wave : " + GameController.wave, 30, 50);
        g.drawString("Elapsed time : " + EnterNamePage.player.getXP(), 150, 50);
        g.drawString("XP : " + EnterNamePage.player.getXP(), 30, 100);
        g.drawString("HP : " + GameController.ball.HP, 150, 100);

    }
}
