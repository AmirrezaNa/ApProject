package view.gameLoop.phase2.normalAndMiniBossEnemies;

import controller.game.GameController;
import controller.game.collisions.phase2.FrameCollisions2;
import controller.game.objectsController.ball.enemies.normalAndMiniBoss.BarricadosController2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class GameInternalFrame extends JInternalFrame {

    public static CreateFrames[] createdFrames = new CreateFrames[4];
    public static CreateFrames createFrames0;
    public static CreateFrames createFrames1;
    public static CreateFrames createFrames2;
    public static CreateFrames createFrames3;


    static JInternalFrame frame0;
    static JInternalFrame frame1;
    static JInternalFrame frame2;
    static JInternalFrame frame3;

    public static JInternalFrame[] frames = new JInternalFrame[4];

    public static Set<Integer> collidedFrames = new HashSet<>();

    public GameInternalFrame() {
        GameController.gameOver = false;
        createInternalFrames();
    }

    private static void createInternalFrames() {
        createFrames0 = new CreateFrames(450, 100, 400, 300);
        frame0 = new JInternalFrame();
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setBounds(createFrames0.x, createFrames0.y, createFrames0.width, createFrames0.height);
        frame0.setVisible(true);
        createdFrames[0] = createFrames0;
        frames[0] = frame0;


        createFrames1 = new CreateFrames(50, 50, 400, 300);
        frame1 = new JInternalFrame();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setBounds(createFrames1.x, createFrames1.y, createFrames1.width, createFrames1.height);
        frame1.setVisible(true);
        createdFrames[1] = createFrames1;
        frames[1] = frame1;


        createFrames2 = new CreateFrames(850, 50, 400, 300);
        frame2 = new JInternalFrame();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setBounds(createFrames2.x, createFrames2.y, createFrames2.width, createFrames2.height);
        frame2.setVisible(true);
        createdFrames[2] = createFrames2;
        frames[2] = frame2;


        createFrames3 = new CreateFrames(450, 400, 400, 300);
        frame3 = new JInternalFrame();
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setBounds(createFrames3.x, createFrames3.y, createFrames3.width, createFrames3.height);
        frame3.setVisible(true);
        createdFrames[3] = createFrames3;
        frames[3] = frame3;


        new Thread(() -> {
            while (true) {
                synchronized (frame0) {
                    changeGameFrameSize(frame0, 0);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (frame1) {
                    changeGameFrameSize(frame1, 1);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (frame2) {
                    changeGameFrameSize(frame2, 2);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (frame3) {
                    changeGameFrameSize(frame3, 3);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        GamePanel2.framesCreated = true;
    }


    static Timer timer;
    private static final Object lock = new Object();

    public static void changeGameFrameSize(JInternalFrame frame, int i) {
        // this timer reduces the frame size ========================================
        synchronized (lock) {
            if (!FrameCollisions2.frameCollided(i) && !GameController.pause) {
                int x = createdFrames[i].x;
                int y = createdFrames[i].y;
                int width = createdFrames[i].width;
                int height = createdFrames[i].height;
                if (width > 300) {
                    // reduce width gradually
                    x++;
                    width--;
                    frames[i].setBounds(x, y, width, height);
                    createdFrames[i].x = x;
                    createdFrames[i].width = width;
                }
                if (height > 250) {
                    // reduce height gradually
                    y += 1;
                    height--;
                    frames[i].setBounds(x, y, width, height);
                    createdFrames[i].y = y;
                    createdFrames[i].height = height;
                }
            }
        }

        // ==========================================================================

    }
}
