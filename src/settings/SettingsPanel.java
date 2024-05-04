package settings;

import GameOver.GameOverFrame;
import game.SoundEffects;
import game.entity.BallModel;
import game.entity.enemy.EnemyModel1;
import game.entity.enemy.EnemyModel2;
import startPage.EnterNamePage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SettingsPanel extends JPanel implements ChangeListener {

    JSlider volume;
    JSlider gameLevel;
    JSlider sensitivity;
    public static int level = 2;
    public static int sense = 2;

    SettingsPanel() {
        this.setBackground(Color.BLACK);
        this.setSize(GameOverFrame.WIDTH, GameOverFrame.HEIGHT);
        this.setLayout(null);


        volume = new JSlider(0, 100, 50);
        volume.setBounds(70, 80, 200, 30);
        volume.setPaintTicks(true);
        volume.setMinorTickSpacing(10);
        volume.setBackground(new Color(0x8F0404));
        volume.addChangeListener(e -> {
            SoundEffects.volume = (double) volume.getValue() /100;
        });



        gameLevel = new JSlider(1, 3, 2);
        gameLevel.setBounds(70, 180, 200, 30);
        gameLevel.setPaintTicks(true);
        gameLevel.setMinorTickSpacing(1);
        gameLevel.setBackground(new Color(0x8F0404));
        gameLevel.addChangeListener(e -> {
            level = gameLevel.getValue();
            changeGameData();
        });



        sensitivity = new JSlider(1, 3, 2);
        sensitivity.setBounds(70, 280, 200, 30);
        sensitivity.setPaintTicks(true);
        sensitivity.setMinorTickSpacing(1);
        sensitivity.setBackground(new Color(0x8F0404));
        sensitivity.addChangeListener(e -> {
            sense = sensitivity.getValue();
        });




        this.add(volume);
        this.add(gameLevel);
        this.add(sensitivity);
        this.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x8F0404));
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString("Volume : ", 120, 60);
        g.drawString("Game level : " , 100, 160);
        g.drawString("Sensitivity : " , 95, 260);
    }

    public void changeGameData() {
        if (level == 1) {
            BallModel.ballRadius = 10;
            EnemyModel1.enemy1Size = 40;
            EnemyModel2.enemy2Size = 40;
            EnemyModel1.enemySpeed = 0.2;
            EnemyModel2.enemySpeed = 0.2;
        }
        if (level == 3) {
            EnemyModel1.enemy1Size = 20;
            EnemyModel2.enemy2Size = 20;
            EnemyModel1.enemySpeed = 0.6;
            EnemyModel2.enemySpeed = 0.6;
        }
    }
}
