package main;

import javax.imageio.IIOException;
import java.io.*;

public class Config {

    private GamePanel gamePanel;

    public Config(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void saveConfig(){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("config.txt"));
            if ((gamePanel.fullScreenOn)){
                bufferedWriter.write("On");
            }else{
                bufferedWriter.write("Off");
            }
            bufferedWriter.newLine();

            bufferedWriter.write(String.valueOf((gamePanel.music.getVolumeScale())));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf((gamePanel.soundEffect.getVolumeScale())));
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadConfig(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));
            String string = bufferedReader.readLine();
            if (string.equals("On")){
                gamePanel.fullScreenOn = true;
            }else {
                gamePanel.fullScreenOn = false;
            }
            string = bufferedReader.readLine();
            gamePanel.music.setVolumeScale(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.soundEffect.setVolumeScale(Integer.parseInt(string));
            bufferedReader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
