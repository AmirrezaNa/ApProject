package view.shop;

import controller.Constants;
import controller.game.GameController;
import controller.game.ShopController;
import model.entity.BulletModel;
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
    JButton Dismay;
    JButton Slumber;
    JButton Slaughter;
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
        Banish.setBounds(30, 200, 125, 40);
        Banish.addActionListener(this);
        Banish.setBorder(BorderFactory.createEtchedBorder());
        this.add(Banish);


        Dismay = new JButton();
        Dismay.setFocusable(false);
        Dismay.setBackground(new Color(0x8F0404));
        Dismay.setText("Dismay  :  120 XP");
        Dismay.setForeground(Color.BLACK);
        Dismay.setBounds(200, 200, 125, 40);
        Dismay.addActionListener(this);
        Dismay.setBorder(BorderFactory.createEtchedBorder());
        this.add(Dismay);


        Empower = new JButton();
        Empower.setFocusable(false);
        Empower.setBackground(new Color(0x8F0404));
        Empower.setText("Empower   :  75 XP");
        Empower.setForeground(Color.BLACK);
        Empower.setBounds(30, 275, 125, 40);
        Empower.addActionListener(this);
        Empower.setBorder(BorderFactory.createEtchedBorder());
        this.add(Empower);


        Slumber = new JButton();
        Slumber.setFocusable(false);
        Slumber.setBackground(new Color(0x8F0404));
        Slumber.setText("Slumber   :  150 XP");
        Slumber.setForeground(Color.BLACK);
        Slumber.setBounds(200, 275, 125, 40);
        Slumber.addActionListener(this);
        Slumber.setBorder(BorderFactory.createEtchedBorder());
        this.add(Slumber);


        Heal = new JButton();
        Heal.setFocusable(false);
        Heal.setBackground(new Color(0x8F0404));
        Heal.setText("Heal   :  50 XP");
        Heal.setForeground(Color.BLACK);
        Heal.setBounds(30, 350, 125, 40);
        Heal.addActionListener(this);
        Heal.setBorder(BorderFactory.createEtchedBorder());
        this.add(Heal);


        Slaughter = new JButton();
        Slaughter.setFocusable(false);
        Slaughter.setBackground(new Color(0x8F0404));
        Slaughter.setText("Slaughter   :  50 XP");
        Slaughter.setForeground(Color.BLACK);
        Slaughter.setBounds(200, 350, 125, 40);
        Slaughter.addActionListener(this);
        Slaughter.setBorder(BorderFactory.createEtchedBorder());
        this.add(Slaughter);


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
        if (!GameController.ball.ballSlumber && !GameController.ball.ballSlaughter) {
            if (e.getSource() == Banish) {
                if (EnterNamePage.player.getXP() >= 100) {
                    GameController.Banish++;
                    EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 100);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough XP!");
                }
            }
            if (e.getSource() == Empower) {
                if (EnterNamePage.player.getXP() >= 75) {
                    GameController.Empower++;
                    EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 75);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough XP!");
                }
            }
            if (e.getSource() == Heal) {
                if (EnterNamePage.player.getXP() >= 50) {
                    GameController.ball.HP += 10;
                    EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 50);
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough XP!");
                }
            }
            if (e.getSource() == Dismay) {
                if (EnterNamePage.player.getXP() >= 120) {
                    EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 120);
                    GameController.ball.ballDismay = true;
                    ShopController.TimerForDismay();
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough XP!");
                }
            }
            if (e.getSource() == Slumber) {
                if (EnterNamePage.player.getXP() >= 150) {
                    EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 150);
                    GameController.ball.ballSlumber = true;
                    ShopController.TimerForSlumber();
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough XP!");
                }
            }
            if (e.getSource() == Slaughter) {
                if (EnterNamePage.player.getXP() >= 200) {
                    EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 200);
                    GameController.ball.ballSlumber = true;
                    ShopController.TimerForSlaughter();
                    BulletModel.bulletSlaughter = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Not Enough XP!");
                }
            }

        }
    }
}
