package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

public class Sound {

    private Clip clip;
    private File[] soundURL = new File[30];
    private FloatControl floatControl;
    private int volumeScale = 5;
    private float volume;

    public Sound() {
        soundURL[0] = new File("src/tunes/worldMusic/World1.wav");
        soundURL[1] = new File("src/tunes/worldSoundEffects/key.wav");
        soundURL[2] = new File("src/tunes/worldSoundEffects/chest.wav");
        soundURL[3] = new File("src/tunes/worldSoundEffects/door.wav");
        soundURL[4] = new File("src/tunes/worldMusic/fight.wav");
        soundURL[5] = new File("src/tunes/battleSoundEffects/attack.wav");
        soundURL[6] = new File("src/tunes/battleSoundEffects/hit.wav");
        soundURL[7] = new File("src/tunes/battleSoundEffects/menu.wav");
        soundURL[8] = new File("src/tunes/battleSoundEffects/fireball.wav");
        soundURL[9] = new File("src/tunes/worldMusic/menu.wav");
        soundURL[10] = new File("src/tunes/battleSoundEffects/punch.wav");
        soundURL[11] = new File("src/tunes/battleSoundEffects/kick.wav");
        soundURL[12] = new File("src/tunes/battleSoundEffects/suplex.wav");
        soundURL[13] = new File("src/tunes/battleSoundEffects/headbutt.wav");
        soundURL[14] = new File("src/tunes/battleSoundEffects/meditation.wav");
        soundURL[15] = new File("src/tunes/battleSoundEffects/dance.wav");
        soundURL[16] = new File("src/tunes/battleSoundEffects/focus.wav");
        soundURL[17] = new File("src/tunes/battleSoundEffects/focus.wav");
        soundURL[18] = new File("src/tunes/battleSoundEffects/eating.wav");
        soundURL[19] = new File("src/tunes/battleSoundEffects/cloud(1).wav");
        soundURL[20] = new File("src/tunes/battleSoundEffects/worm.wav");
        soundURL[21] = new File("src/tunes/battleSoundEffects/lamarina.wav");
        soundURL[22] = new File("src/tunes/battleSoundEffects/vrisi.wav");
        soundURL[23] = new File("src/tunes/battleSoundEffects/magos_foties.wav");
        soundURL[24] = new File("src/tunes/worldSoundEffects/correct.wav");
        soundURL[25] = new File("src/tunes/worldSoundEffects/wrong.wav");


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
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -25f;
            case 2 -> volume = -20f;
            case 3 -> volume = -16f;
            case 4 -> volume = -12f;
            case 5 -> volume = -8f;
            case 6 -> volume = -5f;
            case 7 -> volume = -2f;
            case 8 -> volume = 1f;
            case 9 -> volume = 3f;
            case 10 -> volume = 6f;
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

    public int getVolumeScale() {
        return volumeScale;
    }


    public void setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
    }
}
