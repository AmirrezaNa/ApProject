package game.controller;

import settings.SettingsPanel;
import startPage.EnterNamePage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyInputListener implements KeyListener {

    Set<Integer> pressedKeys = new HashSet<>();

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
        if (pressedKeys.size() == 1) {

            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.y -= GameController.ball.dy/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.y -= GameController.ball.dy;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.y -= 2 * GameController.ball.dy;
                }

            }
            else if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.y += GameController.ball.dy/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.y += GameController.ball.dy;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.y += 2 * GameController.ball.dy;
                }

            }
            else if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.x -= GameController.ball.dx/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.x -= GameController.ball.dx;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.x -= 2 * GameController.ball.dx;
                }

            }
            else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.x += GameController.ball.dx/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.x += GameController.ball.dx;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.x += 2 * GameController.ball.dx;
                }

            }
            else if (pressedKeys.contains(KeyEvent.VK_B)) {
                Impact.banishImpact(GameController.ball.x, GameController.ball.y, GameController.ball.x, GameController.ball.y);
            }
            else if (pressedKeys.contains(KeyEvent.VK_P)) {
                if (EnterNamePage.player.XP >= 100) {
                    EnterNamePage.player.XP -= 100;
                    GameController.turnOnWritOfProteus();
                }
            }
            else if (pressedKeys.contains(KeyEvent.VK_S)) {
                if (EnterNamePage.player.XP >= 100) {
                    EnterNamePage.player.XP -= 100;
                    GameController.turnOnWritOfAres();
                }
            }
            else if (pressedKeys.contains(KeyEvent.VK_O)) {
                if (EnterNamePage.player.XP >= 100) {
                    EnterNamePage.player.XP -= 100;
                    GameController.turnOnWritOfAceso();
                }
            }

        } else {
            if (pressedKeys.contains(KeyEvent.VK_UP) && pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.x += GameController.ball.dx/2;
                    GameController.ball.y -= GameController.ball.dy/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.x += GameController.ball.dx;
                    GameController.ball.y -= GameController.ball.dy;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.x += 2 * GameController.ball.dx;
                    GameController.ball.y -= 2 * GameController.ball.dy;
                }

            } else if (pressedKeys.contains(KeyEvent.VK_UP) && pressedKeys.contains(KeyEvent.VK_LEFT)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.x -= GameController.ball.dx/2;
                    GameController.ball.y -= GameController.ball.dy/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.x -= GameController.ball.dx;
                    GameController.ball.y -= GameController.ball.dy;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.x -= 2 * GameController.ball.dx;
                    GameController.ball.y -= 2 * GameController.ball.dy;
                }

            } else if (pressedKeys.contains(KeyEvent.VK_DOWN) && pressedKeys.contains(KeyEvent.VK_LEFT)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.x -= GameController.ball.dx/2;
                    GameController.ball.y += GameController.ball.dy/2;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.x -= GameController.ball.dx;
                    GameController.ball.y += GameController.ball.dy;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.x -= 2 * GameController.ball.dx;
                    GameController.ball.y += 2 * GameController.ball.dy;
                }

            } else if (pressedKeys.contains(KeyEvent.VK_DOWN) && pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                if (SettingsPanel.sense == 1) {
                    GameController.ball.x += GameController.ball.dx;
                    GameController.ball.y += GameController.ball.dy;
                }
                if (SettingsPanel.sense == 2) {
                    GameController.ball.x += GameController.ball.dx/2;
                    GameController.ball.y += GameController.ball.dy/2;
                }
                if (SettingsPanel.sense == 3) {
                    GameController.ball.x += 2 * GameController.ball.dx;
                    GameController.ball.y += 2 * GameController.ball.dy;
                }

            }
        }

    }

    //==============================================================================


}
