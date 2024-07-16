package view.settings;

import controller.game.listener.KeyInputListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class KeyBindingFrame  extends JFrame implements ActionListener , KeyListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 550;
    JButton ok;
    KeyBindingPanel keyBindingPanel;

    JTextField up;
    JTextField down;
    JTextField left;
    JTextField right;
    JTextField banish;
    JTextField writOfAres;
    JTextField writOfAceso;
    JTextField writOfProteus;

    public KeyBindingFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        up = new JTextField(KeyEvent.getKeyText(KeyInputListener.upKey).charAt(0));
        up.setBounds(135, 30, 170, 40);
        up.setFont(new Font("Consolas", Font.PLAIN, 17));
        up.setForeground(Color.BLACK);
        up.setBorder(BorderFactory.createEtchedBorder());
        up.setBackground(new Color(0x8F0404));
        up.addKeyListener(this);



        down = new JTextField(KeyEvent.getKeyText(KeyInputListener.downKey).charAt(0));
        down.setBounds(135, 80, 170, 40);
        down.setFont(new Font("Consolas", Font.PLAIN, 17));
        down.setForeground(Color.BLACK);
        down.setBorder(BorderFactory.createEtchedBorder());
        down.setBackground(new Color(0x8F0404));
        down.addKeyListener(this);





        left = new JTextField(KeyEvent.getKeyText(KeyInputListener.leftKey).charAt(0));
        left.setBounds(135, 130, 170, 40);
        left.setFont(new Font("Consolas", Font.PLAIN, 17));
        left.setForeground(Color.BLACK);
        left.setBorder(BorderFactory.createEtchedBorder());
        left.setBackground(new Color(0x8F0404));
        left.addKeyListener(this);




        right = new JTextField(KeyEvent.getKeyText(KeyInputListener.rightKey).charAt(0));
        right.setBounds(135, 180, 170, 40);
        right.setFont(new Font("Consolas", Font.PLAIN, 17));
        right.setForeground(Color.BLACK);
        right.setBorder(BorderFactory.createEtchedBorder());
        right.setBackground(new Color(0x8F0404));
        right.addKeyListener(this);





        banish = new JTextField(KeyEvent.getKeyText(KeyInputListener.banishKey).charAt(0));
        banish.setBounds(135, 230, 170, 40);
        banish.setFont(new Font("Consolas", Font.PLAIN, 17));
        banish.setForeground(Color.BLACK);
        banish.setBorder(BorderFactory.createEtchedBorder());
        banish.setBackground(new Color(0x8F0404));
        banish.addKeyListener(this);





        writOfAres = new JTextField(KeyEvent.getKeyText(KeyInputListener.writOfAresKey).charAt(0));
        writOfAres.setBounds(135, 280, 170, 40);
        writOfAres.setFont(new Font("Consolas", Font.PLAIN, 17));
        writOfAres.setForeground(Color.BLACK);
        writOfAres.setBorder(BorderFactory.createEtchedBorder());
        writOfAres.setBackground(new Color(0x8F0404));
        writOfAres.addKeyListener(this);





        writOfAceso = new JTextField(KeyEvent.getKeyText(KeyInputListener.writOfAcesoKey).charAt(0));
        writOfAceso.setBounds(135, 330, 170, 40);
        writOfAceso.setFont(new Font("Consolas", Font.PLAIN, 17));
        writOfAceso.setForeground(Color.BLACK);
        writOfAceso.setBorder(BorderFactory.createEtchedBorder());
        writOfAceso.setBackground(new Color(0x8F0404));
        writOfAceso.addKeyListener(this);





        writOfProteus = new JTextField(KeyEvent.getKeyText(KeyInputListener.writOfProteusKey).charAt(0));
        writOfProteus.setBounds(135, 380, 170, 40);
        writOfProteus.setFont(new Font("Consolas", Font.PLAIN, 17));
        writOfProteus.setForeground(Color.BLACK);
        writOfProteus.setBorder(BorderFactory.createEtchedBorder());
        writOfProteus.setBackground(new Color(0x8F0404));
        writOfProteus.addKeyListener(this);





        ok = new JButton();
        ok.setFocusable(false);
        ok.setBackground(new Color(0x8F0404));
        ok.setText("Ok");
        ok.setForeground(Color.BLACK);
        ok.setBounds(115, 440, 100, 50);
        ok.addActionListener(this);
        ok.setBorder(BorderFactory.createEtchedBorder());



        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
        this.add(banish);
        this.add(writOfAres);
        this.add(writOfAceso);
        this.add(writOfProteus);
        this.add(ok);
        keyBindingPanel = new KeyBindingPanel();
        this.add(keyBindingPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            keys.clear();
            checkKeys();
            if (keys.size() == 8) {
                this.dispose();
                SettingsFrame settingsFrame = new SettingsFrame();
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid KeyBindings!");
            }

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        if (textField == up) {
            KeyInputListener.upKey = e.getKeyCode();
        } else if (textField == down) {
            KeyInputListener.downKey = e.getKeyCode();
        }
        else if (textField == left) {
            KeyInputListener.leftKey = e.getKeyCode();
        }
        else if (textField == right) {
            KeyInputListener.rightKey = e.getKeyCode();
        }
        else if (textField == banish) {
            KeyInputListener.banishKey = e.getKeyCode();
        }
        else if (textField == writOfAres) {
            KeyInputListener.writOfAresKey = e.getKeyCode();
        }
        else if (textField == writOfAceso) {
            KeyInputListener.writOfAcesoKey = e.getKeyCode();
        }
        else if (textField == writOfProteus) {
            KeyInputListener.writOfProteusKey = e.getKeyCode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static List<Integer> keys = new ArrayList<>();
    public static boolean checkKeys() {
        if (!keys.contains(KeyInputListener.upKey)) {
            keys.add(KeyInputListener.upKey);
        }
        if (!keys.contains(KeyInputListener.downKey)) {
            keys.add(KeyInputListener.downKey);
        }
        if (!keys.contains(KeyInputListener.leftKey)) {
            keys.add(KeyInputListener.leftKey);
        }
        if (!keys.contains(KeyInputListener.rightKey)) {
            keys.add(KeyInputListener.rightKey);
        }
        if (!keys.contains(KeyInputListener.banishKey)) {
            keys.add(KeyInputListener.banishKey);
        }
        if (!keys.contains(KeyInputListener.writOfAresKey)) {
            keys.add(KeyInputListener.writOfAresKey);
        }
        if (!keys.contains(KeyInputListener.writOfAcesoKey)) {
            keys.add(KeyInputListener.writOfAcesoKey);
        }
        if (!keys.contains(KeyInputListener.writOfProteusKey)) {
            keys.add(KeyInputListener.writOfProteusKey);
        }
        return keys.size() == 8;
    }
}
