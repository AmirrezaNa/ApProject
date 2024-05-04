package settings;

import GameOver.GameOverPanel;
import startPage.StartPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame implements ActionListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 550;
    JButton Menu;
    JButton keyBindings;
    SettingsPanel settingsPanel;

    public SettingsFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        keyBindings = new JButton();
        keyBindings.setFocusable(false);
        keyBindings.setBackground(new Color(0x8F0404));
        keyBindings.setText("keyBindings");
        keyBindings.setForeground(Color.BLACK);
        keyBindings.setBounds(115, 340, 100, 50);
        keyBindings.addActionListener(this);
        keyBindings.setBorder(BorderFactory.createEtchedBorder());
        this.add(keyBindings);




        Menu = new JButton();
        Menu.setFocusable(false);
        Menu.setBackground(new Color(0x8F0404));
        Menu.setText("Menu");
        Menu.setForeground(Color.BLACK);
        Menu.setBounds(115, 410, 100, 50);
        Menu.addActionListener(this);
        Menu.setBorder(BorderFactory.createEtchedBorder());
        this.add(Menu);



        settingsPanel = new SettingsPanel();
        this.add(settingsPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == keyBindings) {
            this.dispose();
            KeyBindingFrame keyBindingFrame = new KeyBindingFrame();
        }
        if (e.getSource() == Menu) {
            this.dispose();
            StartPageFrame startPageFrame = new StartPageFrame();
        }
    }

}
