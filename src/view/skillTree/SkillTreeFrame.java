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
    JButton writOfAstrape;
    JButton writOfCerberus;

    JButton writOfAceso;
    JButton writOfMelampus;
    JButton writOfChiron;

    JButton writOfProteus;
    JButton writOfEmpusa;
    JButton writOfDolus;
    SkillTreePanel skillTreePanel;

    public SkillTreeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        // ===================================================================


        writOfAres = new JButton();
        writOfAres.setFocusable(false);
        writOfAres.setBackground(new Color(0x8F0404));
        writOfAres.setText("writOfAres  :  750 XP");
        writOfAres.setForeground(Color.BLACK);
        writOfAres.setBounds(20, 20, 150, 40);
        writOfAres.addActionListener(this);
        writOfAres.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfAres);

        writOfAstrape = new JButton();
        writOfAstrape.setFocusable(false);
        writOfAstrape.setBackground(new Color(0x8F0404));
        writOfAstrape.setText("writOfAstrape  :  1000 XP");
        writOfAstrape.setForeground(Color.BLACK);
        writOfAstrape.setBounds(40, 70, 150, 40);
        writOfAstrape.addActionListener(this);
        writOfAstrape.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfAstrape);

        writOfCerberus = new JButton();
        writOfCerberus.setFocusable(false);
        writOfCerberus.setBackground(new Color(0x8F0404));
        writOfCerberus.setText("writOfCerberus : 1500 XP");
        writOfCerberus.setForeground(Color.BLACK);
        writOfCerberus.setBounds(20, 120, 150, 40);
        writOfCerberus.addActionListener(this);
        writOfCerberus.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfCerberus);


        // ==============================================================


        writOfAceso = new JButton();
        writOfAceso.setFocusable(false);
        writOfAceso.setBackground(new Color(0x8F0404));
        writOfAceso.setText("writOfAceso   :  500 XP");
        writOfAceso.setForeground(Color.BLACK);
        writOfAceso.setBounds(170, 180, 150, 40);
        writOfAceso.addActionListener(this);
        writOfAceso.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfAceso);


        writOfMelampus = new JButton();
        writOfMelampus.setFocusable(false);
        writOfMelampus.setBackground(new Color(0x8F0404));
        writOfMelampus.setText("writOfMelampus  : 750 XP");
        writOfMelampus.setForeground(Color.BLACK);
        writOfMelampus.setBounds(150, 230, 150, 40);
        writOfMelampus.addActionListener(this);
        writOfMelampus.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfMelampus);

        writOfChiron = new JButton();
        writOfChiron.setFocusable(false);
        writOfChiron.setBackground(new Color(0x8F0404));
        writOfChiron.setText("writOfChiron   :  900 XP");
        writOfChiron.setForeground(Color.BLACK);
        writOfChiron.setBounds(170, 280, 150, 40);
        writOfChiron.addActionListener(this);
        writOfChiron.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfChiron);



        // ============================================================================


        writOfProteus = new JButton();
        writOfProteus.setFocusable(false);
        writOfProteus.setBackground(new Color(0x8F0404));
        writOfProteus.setText("writOfProteus   :  1000 XP");
        writOfProteus.setForeground(Color.BLACK);
        writOfProteus.setBounds(20, 340, 150, 40);
        writOfProteus.addActionListener(this);
        writOfProteus.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfProteus);

        writOfEmpusa = new JButton();
        writOfEmpusa.setFocusable(false);
        writOfEmpusa.setBackground(new Color(0x8F0404));
        writOfEmpusa.setText("writOfEmpusa   :  750 XP");
        writOfEmpusa.setForeground(Color.BLACK);
        writOfEmpusa.setBounds(40, 390, 150, 40);
        writOfEmpusa.addActionListener(this);
        writOfEmpusa.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfEmpusa);

        writOfDolus = new JButton();
        writOfDolus.setFocusable(false);
        writOfDolus.setBackground(new Color(0x8F0404));
        writOfDolus.setText("writOfDolus   :  1500 XP");
        writOfDolus.setForeground(Color.BLACK);
        writOfDolus.setBounds(20, 440, 150, 40);
        writOfDolus.addActionListener(this);
        writOfDolus.setBorder(BorderFactory.createEtchedBorder());
        this.add(writOfDolus);


        // ========================================================================


        Menu = new JButton();
        Menu.setFocusable(false);
        Menu.setBackground(new Color(0x8F0404));
        Menu.setText("Menu");
        Menu.setForeground(Color.BLACK);
        Menu.setBounds(240, 450, 70, 40);
        Menu.addActionListener(this);
        Menu.setBorder(BorderFactory.createEtchedBorder());
        this.add(Menu);



        skillTreePanel = new SkillTreePanel();
        this.add(skillTreePanel);
        this.setVisible(true);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        // ======================================================================

        if (e.getSource() == writOfAres) {
            if (EnterNamePage.player.getXP() >= 750) {
                EnterNamePage.player.setWritOfAres(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-750);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == writOfAstrape) {
            if (!EnterNamePage.player.isWritOfAres()) {
                JOptionPane.showMessageDialog(null, "You have to buy the skills in order!");
            }
            else if (EnterNamePage.player.getXP() >= 1000) {
                EnterNamePage.player.setWritOfAstrape(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-1000);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == writOfCerberus) {
            if (!EnterNamePage.player.isWritOfAres() || !EnterNamePage.player.isWritOfAstrape()) {
                JOptionPane.showMessageDialog(null, "You have to buy the skills in order!");
            }
            else if (EnterNamePage.player.getXP() >= 1500) {
                EnterNamePage.player.setWritOfCerberus(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-1500);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }


        // ======================================================================


        if (e.getSource() == writOfAceso) {
            if (EnterNamePage.player.getXP() >= 500) {
                EnterNamePage.player.setWritOfAceso(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-500);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }

        }
        if (e.getSource() == writOfMelampus) {
            if (!EnterNamePage.player.isWritOfAceso()) {
                JOptionPane.showMessageDialog(null, "You have to buy the skills in order!");
            }
            else if (EnterNamePage.player.getXP() >= 750) {
                EnterNamePage.player.setWritOfMelampus(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-750);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }

        }
        if (e.getSource() == writOfChiron) {
            if (!EnterNamePage.player.isWritOfAceso() || !EnterNamePage.player.isWritOfMelampus()) {
                JOptionPane.showMessageDialog(null, "You have to buy the skills in order!");
            }
            else if (EnterNamePage.player.getXP() >= 900) {
                EnterNamePage.player.setWritOfChiron(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-900);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }

        }

        // ======================================================================


        if (e.getSource() == writOfProteus) {
            if (EnterNamePage.player.getXP() >= 1000) {
                EnterNamePage.player.setWritOfProteus(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-1000);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == writOfEmpusa) {
            if (!EnterNamePage.player.isWritOfProteus()) {
                JOptionPane.showMessageDialog(null, "You have to buy the skills in order!");

            }
            else if (EnterNamePage.player.getXP() >= 750) {
                EnterNamePage.player.setWritOfEmpusa(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-750);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }
        if (e.getSource() == writOfDolus) {
            if (!EnterNamePage.player.isWritOfProteus() || !EnterNamePage.player.isWritOfEmpusa()) {
                JOptionPane.showMessageDialog(null, "You have to buy the skills in order!");
            }
            else if (EnterNamePage.player.getXP() >= 1500) {
                EnterNamePage.player.setWritOfDolus(true);
                EnterNamePage.player.setXP(EnterNamePage.player.getXP()-1500);
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Enough XP!");
            }
        }


        // ======================================================================

        if (e.getSource() == Menu) {
            this.dispose();
            StartPageFrame startPageFrame = new StartPageFrame();
        }
    }
}
