package game.controller;

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

    public void handleKeyPressedCombination() {
        if (pressedKeys.size() == 1) {
            if (pressedKeys.contains(KeyEvent.VK_UP)) {
                GameController.ball.y--;
            }
            else if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
                GameController.ball.y++;
            }else if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
                GameController.ball.x--;
            }
            else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                GameController.ball.x++;
            }
        }
        else {
            if (pressedKeys.contains(KeyEvent.VK_UP) && pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                GameController.ball.x++;
                GameController.ball.y--;
            } else if (pressedKeys.contains(KeyEvent.VK_UP) && pressedKeys.contains(KeyEvent.VK_LEFT)) {
                GameController.ball.x--;
                GameController.ball.y--;
            } else if (pressedKeys.contains(KeyEvent.VK_DOWN) && pressedKeys.contains(KeyEvent.VK_LEFT)) {
                GameController.ball.x--;
                GameController.ball.y++;
            }else if (pressedKeys.contains(KeyEvent.VK_DOWN) && pressedKeys.contains(KeyEvent.VK_RIGHT)) {
                GameController.ball.x++;
                GameController.ball.y++;
            }
        }

    }
}
