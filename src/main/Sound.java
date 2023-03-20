package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

public class Sound {

    Clip clip;
    public File[] soundURL = new File[30];
    FloatControl floatControl;
    int volumeScale = 5;
    float volume;

    public Sound() {
        soundURL[0] = new File("src/tunes/worldMusic/World.wav");
        soundURL[1] = new File("src/tunes/worldSoundEffects/key.wav");
        soundURL[2] = new File("src/tunes/worldSoundEffects/chest.wav");
        soundURL[3] = new File("src/tunes/worldSoundEffects/door.wav");
        soundURL[4] = new File("src/tunes/worldMusic/fight.wav");
        soundURL[5] = new File("src/tunes/battleSoundEffects/attack.wav");
        soundURL[6] = new File("src/tunes/battleSoundEffects/hit.wav");
        soundURL[7] = new File("src/tunes/battleSoundEffects/menu.wav");
        soundURL[8] = new File("src/tunes/battleSoundEffects/fireball.wav");

    }

    public void setFile(int index) {
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e){

        }
    }

    public void checkVolume(){
        switch (volumeScale){
            case 0: volume = - 80f; break;
            case 1: volume = - 25f; break;
            case 2: volume = -20f; break;
            case 3: volume = -16f; break;
            case 4: volume = -12f;  break;
            case 5: volume = -8f; break;
            case 6: volume = -5f; break;
            case 7: volume = -2f; break;
            case 8: volume = 1f; break;
            case 9: volume = 3f; break;
            case 10: volume = 6f; break;

        }
        floatControl.setValue(volume);
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
