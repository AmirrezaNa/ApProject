import startPage.EnterNamePage;

import static game.SoundEffects.playSong;

public class Main {
    public static void main(String[] args) {
        Thread soundThread = new Thread(() -> {
            playSong("gameSong.wav");
        });
        soundThread.start();
        EnterNamePage enterNamePage = new EnterNamePage();
    }
}