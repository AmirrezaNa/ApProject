package view.skillTree;

import controller.Constants;
import view.shop.ShopPanel;
import view.startPage.EnterNamePage;
import view.startPage.StartPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillTreeFrame extends JFrame implements ActionListener {

    JButton Menu;
    JButton writOfAres;
    JButton writOfAceso;
    JButton writOfProteus;
    SkillTreePanel skillTreePanel;

    public SkillTreeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        writOfAres = new JButton();
        writOfAres.setFocusable(false);
        writOfAres.setBackground(new Color(0x8F0404));
        writOfAres.setText("writOfAres  :  750 XP");
        writOfAres.setForeground(Color.BLACK);
        writOfAres.setBounds(95, 150, 150, 40);
        writOfAres.addActionListener(this);
        writOfAres.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfAres);


        writOfAceso = new JButton();
        writOfAceso.setFocusable(false);
        writOfAceso.setBackground(new Color(0x8F0404));
        writOfAceso.setText("writOfAceso   :  500 XP");
        writOfAceso.setForeground(Color.BLACK);
        writOfAceso.setBounds(95, 225, 150, 40);
        writOfAceso.addActionListener(this);
        writOfAceso.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfAceso);


        writOfProteus = new JButton();
        writOfProteus.setFocusable(false);
        writOfProteus.setBackground(new Color(0x8F0404));
        writOfProteus.setText("writOfProteus   :  1000 XP");
        writOfProteus.setForeground(Color.BLACK);
        writOfProteus.setBounds(95, 300, 150, 40);
        writOfProteus.addActionListener(this);
        writOfProteus.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfProteus);


        Menu = new JButton();
        Menu.setFocusable(false);
        Menu.setBackground(new Color(0x8F0404));
        Menu.setText("Menu");
        Menu.setForeground(Color.BLACK);
        Menu.setBounds(200, 440, 100, 50);
        Menu.addActionListener(this);
        Menu.setBorder(BorderFactory.createEtchedBorder());
        this.add(Menu);



        skillTreePanel = new SkillTreePanel();
        this.add(skillTreePanel);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == writOfAres) {
            if (EnterNamePage.player.getXP() >= 750) {
                EnterNamePage.player.setWritOfAres(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-750);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == writOfAceso) {
            if (EnterNamePage.player.getXP() >= 500) {
                EnterNamePage.player.setWritOfAceso(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-500);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }

        }
        if (e.getSource() == writOfProteus) {
            if (EnterNamePage.player.getXP() >= 1000) {
                EnterNamePage.player.setWritOfProteus(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-1000);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == Menu) {
            this.dispose();
            StartPageFrame startPageFrame = new StartPageFrame();
        }
    }
}
