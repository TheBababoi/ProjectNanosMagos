package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStreamReader;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[20];

    public Sound() {
        soundURL[0] = getClass().getResource("src/tunes/worldMusic/World.wav");
        soundURL[1] = getClass().getResource("src/tunes/worldSoundEffects/chest.wav");
        soundURL[2] = getClass().getResource("src/tunes/worldSoundEffects/door.wav");
        soundURL[3] = getClass().getResource("src/tunes/worldSoundEffects/key.wav");
    }

    public void setFile(int index) {
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e){

        }
    }

    public void
}
