package controller;

import view.game.GameFrame;

import javax.swing.*;

public class HandleFramesThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            SwingUtilities.invokeLater(() -> {
                GameFrame gameFrame = new GameFrame();
            });
        }

    }

}
