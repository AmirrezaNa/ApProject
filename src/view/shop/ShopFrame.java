package view.shop;

import controller.game.GameController;
import view.game.GameFrame;
import view.game.GamePanel;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopFrame extends JFrame implements ActionListener {

    public static final int WIDTH = 350;
    public static final int HEIGHT = 550;
    GameController gameController = new GameController();
    JButton Resume;
    JButton Banish;
    JButton Empower;
    JButton Heal;
    ShopPanel shopPanel;

    public ShopFrame() {
        this.setUndecorated(true);
        this.setSize(WIDTH, HEIGHT);
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
            GamePanel.pause = false;
            this.dispose();
        }
        if (e.getSource() == Banish) {
            if (EnterNamePage.player.getXP() >= 100) {
                gameController.setBanish(gameController.getBanish()+1);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-100);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == Empower) {
            if (EnterNamePage.player.getXP() >= 75) {
                gameController.setEmpower(gameController.getEmpower()+1);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-75);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == Heal) {
            if (EnterNamePage.player.getXP() >= 50) {
                gameController.getBall().setHP(gameController.getBall().getHP()+10);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-50);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
    }
}
