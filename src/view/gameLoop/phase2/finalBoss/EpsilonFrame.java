package view.gameLoop.phase2.finalBoss;

import controller.game.GameController;
import view.gameLoop.phase2.normalAndMiniBossEnemies.CreateFrames;
import view.startPage.EnterNamePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.game.GameController.smiley;
import static view.gameLoop.phase1.GamePanel.ball;

public class EpsilonFrame extends JInternalFrame {
    public static CreateFrames epsilonFrame;
    static JInternalFrame frame;

    public EpsilonFrame() {
        GameController.gameOver = false;
        createEpsilonFrame();
    }

    private void createEpsilonFrame() {
        epsilonFrame = new CreateFrames(450, 300, 400, 400);
        frame = new JInternalFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(epsilonFrame.x, epsilonFrame.y, epsilonFrame.width, epsilonFrame.height);
        frame.setVisible(true);



        changeGameFrameSize(frame);

    }


    static Timer timer;
    int athena = 0;

    public void changeGameFrameSize(JInternalFrame frame) {
        // this timer reduces the frame size ========================================
        timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    checkGameOver();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
                if (EnterNamePage.player.isWritOfAthena()) {
                    athena++;
                    if (athena % 5 != 0) {
                        if (!GameController.pause && !ball.ballSlumber) {
                            int x = epsilonFrame.x;
                            int y = epsilonFrame.y;
                            int width = epsilonFrame.width;
                            int height = epsilonFrame.height;
                            if (width > 300 && !smiley.squeezeAttack) {
                                // reduce width gradually
                                x += 3;
                                width -= 6;
                                frame.setBounds(x, y, width, height);
                                epsilonFrame.x = x;
                                epsilonFrame.width = width;
                            }
                            if (height < 400 && !smiley.squeezeAttack) {
                                y--;
                                height += 2;
                                frame.setBounds(x, y, width, height);
                                epsilonFrame.y = y;
                                epsilonFrame.height = height;
                            }
                        }
                    }
                }
                else {
                    if (!GameController.pause && !ball.ballSlumber) {
                        int x = epsilonFrame.x;
                        int y = epsilonFrame.y;
                        int width = epsilonFrame.width;
                        int height = epsilonFrame.height;
                        if (width > 300 && !smiley.squeezeAttack) {
                            // reduce width gradually
                            x += 3;
                            width -= 6;
                            frame.setBounds(x, y, width, height);
                            epsilonFrame.x = x;
                            epsilonFrame.width = width;
                        }
                        if (height < 400 && !smiley.squeezeAttack) {
                            y--;
                            height += 2;
                            frame.setBounds(x, y, width, height);
                            epsilonFrame.y = y;
                            epsilonFrame.height = height;
                        }
                    }
                }
            }
        });
        timer.start();

        // ==========================================================================

    }
}
