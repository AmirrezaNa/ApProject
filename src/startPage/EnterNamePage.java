package startPage;

import game.DataManager;
import game.Player;
import game.SoundEffects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class EnterNamePage extends JFrame implements ActionListener {
    final int WIDTH = 350;
    final int HEIGHT = 550;
    JButton startButton;
    JTextField nameField;
    public static Player player;
    public static String name;

    public EnterNamePage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(Color.BLACK);


        nameField = new JTextField("Name");
        nameField.setBounds(75, 160, 170, 40);
        nameField.setFont(new Font("Consolas", Font.PLAIN, 17));
        nameField.setForeground(Color.BLACK);
        nameField.setBorder(BorderFactory.createEtchedBorder());
        nameField.setBackground(new Color(0x8F0404));


        startButton = new JButton();
        startButton.setFocusable(false);
        startButton.setBackground(new Color(0x8F0404));
        startButton.setText("Start");
        startButton.setForeground(Color.BLACK);
        startButton.setBounds(115, 300, 100, 50);
        startButton.addActionListener(this);
        startButton.setBorder(BorderFactory.createEtchedBorder());




        this.add(nameField);
        this.add(startButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            newPlayer();
            player.name = nameField.getText();
            try {
                if (DataManager.checkPlayerExists(player.name)) {
                    DataManager.loadPlayerData(player.name);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
            StartPageFrame startPageFrame = new StartPageFrame();
        }
    }

    public static void newPlayer() {
        player = new Player();
    }
}
