package view.phase2.finalBoss;

import controller.game.GameController;
import controller.game.collisions.phase2.FrameCollisions2;
import view.phase2.normalAndMiniBossEnemies.CreateFrames;
import view.phase2.normalAndMiniBossEnemies.GamePanel2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpsilonFrame extends JInternalFrame {
    public static CreateFrames epsilonFrame;
    static JInternalFrame frame;

    public EpsilonFrame() {
        GameController.gameOver = false;

    }

    private static void createEpsilonFrame() {
        epsilonFrame = new CreateFrames(450, 100, 400, 300);
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
                    if (width > 300) {
                        // reduce width gradually
                        x++;
                        width--;
                        frame.setBounds(x, y, width, height);
                        epsilonFrame.x = x;
                        epsilonFrame.width = width;
                    }
                    if (height > 250) {
                        // reduce height gradually
                        y += 1;
                        height--;
                        frame.setBounds(x, y, width, height);
                        epsilonFrame.y = y;
                        epsilonFrame.height = height;
                    }
                }
            }
        });
        timer.start();

        // ==========================================================================

    }
}
