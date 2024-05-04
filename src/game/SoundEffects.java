package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffects {
    public static double volume = 0.5;
    public static void playSound(String soundFilePath) {
        File soundFile = new File(soundFilePath);
        if (!soundFile.exists()) {
            System.err.println("Sound file not found: " + soundFilePath);
            return;
        }
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            setVolume(clip, volume);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void playSong(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            while (clip.isActive()) {
                // Add a delay or update the game logic here if needed
            }

            clip.close();
            playSound(soundFilePath); // Repeat the sound when it ends
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void setVolume(Clip clip, double volume) {
        if (volume < 0.0 || volume > 1.0) {
            throw new IllegalArgumentException("Volume should be between 0.0 and 1.0");
        }

        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        control.setValue(dB);
    }

}
