package controller.game.listener;

import controller.game.GameController;
import controller.game.Impact;
import controller.game.SkillTreeController;
import model.entity.BallModel;
import view.settings.SettingsPanel;
import view.startPage.EnterNamePage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class KeyInputListener implements KeyListener {
    GameController gameController = new GameController();
    SkillTreeController skillTreeController = new SkillTreeController();
    Impact impact = new Impact();

    Set<Integer> pressedKeys = new HashSet<>();

    // Default key codes that can be changed based on user input
    public static int upKey = KeyEvent.VK_UP;
    public static int downKey = KeyEvent.VK_DOWN;
    public static int leftKey = KeyEvent.VK_LEFT;
    public static int rightKey = KeyEvent.VK_RIGHT;
    public static int banishKey = KeyEvent.VK_B;
    public static int writOfAresKey = KeyEvent.VK_S;
    public static int writOfAcesoKey = KeyEvent.VK_O;
    public static int writOfProteusKey = KeyEvent.VK_P;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        handleKeyPressedCombination();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }


    // this method is for checking which keys are being pressed at every moment ================================
    public void handleKeyPressedCombination() {
        if (gameController.getBall() != null) {
            BallModel ball = gameController.getBall();
            if (pressedKeys.size() == 1) {

                if (pressedKeys.contains(upKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setY(ball.getY() - (ball.getDy() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setY(ball.getY() - ball.getDy());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setY(ball.getY() - (2 * ball.getDy()));
                    }

                } else if (pressedKeys.contains(downKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setY(ball.getY() + (ball.getDy() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setY(ball.getY() + ball.getDy());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setY(ball.getY() + (2 * ball.getDy()));
                    }

                } else if (pressedKeys.contains(leftKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setX(ball.getX() - (ball.getDx() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setX(ball.getX() - ball.getDx());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setX(ball.getX() - (2 * ball.getDx()));
                    }

                } else if (pressedKeys.contains(rightKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setX(ball.getX() + (ball.getDx() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setX(ball.getX() + ball.getDx());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setX(ball.getX() + (2 * ball.getDx()));
                    }

                } else if (pressedKeys.contains(banishKey)) {
                    impact.banishImpact(ball.getX(), ball.getY(), ball.getX(), ball.getY());
                } else if (pressedKeys.contains(writOfProteusKey)) {
                    if (EnterNamePage.player.getXP() >= 100) {
                        EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 100);
                        skillTreeController.turnOnWritOfProteus();
                    }
                } else if (pressedKeys.contains(writOfAresKey)) {
                    if (EnterNamePage.player.getXP() >= 100) {
                        EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 100);
                        skillTreeController.turnOnWritOfAres();
                    }
                } else if (pressedKeys.contains(writOfAcesoKey)) {
                    if (EnterNamePage.player.getXP() >= 100) {
                        EnterNamePage.player.setXP(EnterNamePage.player.getXP() - 100);
                        skillTreeController.turnOnWritOfAceso();
                    }
                }

            } else {
                if (pressedKeys.contains(upKey) && pressedKeys.contains(rightKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setX(ball.getX() + (ball.getDx() / 2));
                        ball.setY(ball.getY() - (ball.getDy() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setX(ball.getX() + ball.getDx());
                        ball.setY(ball.getY() - ball.getDy());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setX(ball.getX() + (2 * ball.getDx()));
                        ball.setY(ball.getY() - (2 * ball.getDy()));
                    }

                } else if (pressedKeys.contains(upKey) && pressedKeys.contains(leftKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setX(ball.getX() - (ball.getDx() / 2));
                        ball.setY(ball.getY() - (ball.getDy() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setX(ball.getX() - ball.getDx());
                        ball.setY(ball.getY() - ball.getDy());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setX(ball.getX() - (2 * ball.getDx()));
                        ball.setY(ball.getY() - (2 * ball.getDy()));
                    }

                } else if (pressedKeys.contains(downKey) && pressedKeys.contains(leftKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setX(ball.getX() - (ball.getDx() / 2));
                        ball.setY(ball.getY() + (ball.getDy() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setX(ball.getX() - ball.getDx());
                        ball.setY(ball.getY() + ball.getDy());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setX(ball.getX() - (2 * ball.getDx()));
                        ball.setY(ball.getY() + (2 * ball.getDy()));
                    }

                } else if (pressedKeys.contains(downKey) && pressedKeys.contains(rightKey)) {
                    if (SettingsPanel.sense == 1) {
                        ball.setX(ball.getX() + (ball.getDx() / 2));
                        ball.setY(ball.getY() + (ball.getDy() / 2));
                    }
                    if (SettingsPanel.sense == 2) {
                        ball.setX(ball.getX() + ball.getDx());
                        ball.setY(ball.getY() + ball.getDy());
                    }
                    if (SettingsPanel.sense == 3) {
                        ball.setX(ball.getX() + (2 * ball.getDx()));
                        ball.setY(ball.getY() + (2 * ball.getDy()));
                    }


                }
            }

            gameController.setBall(ball);

        }
    }

    //==============================================================================


}
