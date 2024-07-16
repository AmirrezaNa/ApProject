package view.settings;

import controller.Constants;
import view.GameOver.GameOverFrame;

import javax.swing.*;
import java.awt.*;

public class KeyBindingPanel extends JPanel {
    KeyBindingPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Up : ", 15, 55);
        g.drawString("Down : " , 15, 105);
        g.drawString("Left : " , 15, 155);
        g.drawString("Right : " , 15, 205);
        g.drawString("Banish : " , 15, 255);
        g.drawString("Writ Of Ares : " , 15, 305);
        g.drawString("Writ Of Aceso : " , 15, 355);
        g.drawString("Writ Of Proteus : " , 15, 405);
    }
}
