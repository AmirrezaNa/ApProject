package view.gameLoop.phase2.finalBoss;

import controller.game.GameController;
import view.gameLoop.phase2.normalAndMiniBossEnemies.CreateFrames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.game.GameController.smiley;

public class EpsilonFrame extends JInternalFrame {
    public static CreateFrames epsilonFrame;
    static JInternalFrame frame;

    public EpsilonFrame() {
        GameController.gameOver = false;
        createEpsilonFrame();
    }

    private static void createEpsilonFrame() {
        epsilonFrame = new CreateFrames(450, 300, 400, 400);
        frame = new JInternalFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(epsilonFrame.x, epsilonFrame.y, epsilonFrame.width, epsilonFrame.height);
        frame.setVisible(true);



        new Thread(() -> {
            while (true) {
                synchronized (frame) {
                    changeGameFrameSize(frame);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    static Timer timer;
    private static final Object lock = new Object();

    public static void changeGameFrameSize(JInternalFrame frame) {
        // this timer reduces the frame size ========================================
        timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    checkGameOver();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
                synchronized (lock) {
                    int x = epsilonFrame.x;
                    int y = epsilonFrame.y;
                    int width = epsilonFrame.width;
                    int height = epsilonFrame.height;
                    if (width > 300 && !smiley.squeezeAttack) {
                        // reduce width gradually
                        x++;
                        width--;
                        frame.setBounds(x, y, width, height);
                        epsilonFrame.x = x;
                        epsilonFrame.width = width;
                    }
                }
            }
        });
        timer.start();

        // ==========================================================================

    }
}
