package view.shop;

import controller.Constants;
import controller.game.GameController;
import view.gameLoop.phase1.GamePanel;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopFrame extends JFrame implements ActionListener {

    JButton Resume;
    JButton Banish;
    JButton Empower;
    JButton Heal;
    ShopPanel shopPanel;

    public ShopFrame() {
        this.setUndecorated(true);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        Banish = new JButton();
        Banish.setFocusable(false);
        Banish.setBackground(new Color(0x8F0404));
        Banish.setText("Banish  :  100 XP");
        Banish.setForeground(Color.BLACK);
        Banish.setBounds(95, 150, 150, 40);
        Banish.addActionListener(this);
        Banish.setBorder(BorderFactory.createEtchedBorder());
        this.add(Banish);


        Empower = new JButton();
        Empower.setFocusable(false);
        Empower.setBackground(new Color(0x8F0404));
        Empower.setText("Empower   :  75 XP");
        Empower.setForeground(Color.BLACK);
        Empower.setBounds(95, 225, 150, 40);
        Empower.addActionListener(this);
        Empower.setBorder(BorderFactory.createEtchedBorder());
        this.add(Empower);


        Heal = new JButton();
        Heal.setFocusable(false);
        Heal.setBackground(new Color(0x8F0404));
        Heal.setText("Heal   :  50 XP");
        Heal.setForeground(Color.BLACK);
        Heal.setBounds(95, 300, 150, 40);
        Heal.addActionListener(this);
        Heal.setBorder(BorderFactory.createEtchedBorder());
        this.add(Heal);


        Resume = new JButton();
        Resume.setFocusable(false);
        Resume.setBackground(new Color(0x8F0404));
        Resume.setText("Resume");
        Resume.setForeground(Color.BLACK);
        Resume.setBounds(200, 440, 100, 50);
        Resume.addActionListener(this);
        Resume.setBorder(BorderFactory.createEtchedBorder());
        this.add(Resume);



        shopPanel = new ShopPanel();
        this.add(shopPanel);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Resume) {
            GameController.pause = false;
            this.dispose();
        }
        if (e.getSource() == Banish) {
            if (EnterNamePage.player.getXP() >= 100) {
                GameController.Banish++;
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-100);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == Empower) {
            if (EnterNamePage.player.getXP() >= 75) {
                GameController.Empower++;
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-75);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == Heal) {
            if (EnterNamePage.player.getXP() >= 50) {
                GameController.ball.HP += 10;
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-50);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
    }
}
