package game.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrameStuff extends JFrame implements ActionListener {


    JButton storeButton;


    public GameFrameStuff() {
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        this.setBounds(1150, 10, 200, 150);


        storeButton = new JButton();
        storeButton.setFocusable(false);
        storeButton.setBackground(new Color(0x8F0404));
        storeButton.setText("Store");
        storeButton.setForeground(Color.BLACK);
        storeButton.setBounds(10, 10, 50, 50);
        storeButton.addActionListener(this);
        storeButton.setBorder(BorderFactory.createEtchedBorder());





        this.add(storeButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
