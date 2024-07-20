package view.phase2.finalBoss;

import controller.game.FrameOfObject;
import controller.game.collisions.phase2.FrameCollisions2;
import controller.game.collisions.phase2.ObjectCollisions2;
import controller.game.objectsController.ball.BallAngleController;
import controller.game.objectsController.ball.BallController;
import controller.game.objectsController.ball.BallDirectionController;
import controller.game.objectsController.ball.BulletController;
import controller.game.objectsController.ball.enemies.*;

import javax.swing.*;
import java.awt.*;

public class FinalBossPanel extends JPanel implements Runnable {





    @Override
    public void run() {

        while (true) {


            update();




            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }



    public void update() {





        revalidate();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);






        revalidate();
        repaint();
    }


}
