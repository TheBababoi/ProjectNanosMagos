package backroundTile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class BackgroundTileManager {
    GamePanel gamePanel;
    BackgroundTile[] backgroundTile;
    int mapLayout[][];

    public BackgroundTileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        backgroundTile = new BackgroundTile[40];
        mapLayout= new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];
        getTileImage();
        loadMapfromTextFile("src/sprites/maps/map1.txt");
    }

    public void loadMapfromTextFile(String filePath){
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int collumn = 0;
            int row = 0;
            while(collumn < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow){
                String line = bufferedReader.readLine();
                while(collumn < gamePanel.maxScreenColumn){
                    String digit[] = line.split(" "); // fills the array with the digits that are getting split up by the spaces
                    int digit2 = Integer.parseInt(digit[collumn]); // converting string to int
                    mapLayout[collumn][row] = digit2;
                    collumn++;
                }
                if(collumn==gamePanel.maxScreenColumn){
                    collumn = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch(Exception e){

        }
    }

    public void getTileImage() {
        try{

            backgroundTile[0] = new BackgroundTile();
            backgroundTile[0].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass.png"));
            backgroundTile[1] = new BackgroundTile();
            backgroundTile[1].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/water.png"));
            backgroundTile[2] = new BackgroundTile();
            backgroundTile[2].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/brick.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int collumn = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(collumn < gamePanel.maxScreenColumn && row< gamePanel.maxScreenRow) {
            int tileDigit = mapLayout[collumn][row];
            g2.drawImage(backgroundTile[tileDigit].image,x,y, gamePanel.spriteSize,gamePanel.spriteSize,null);
            collumn++;
            x+= gamePanel.spriteSize;
                if(collumn == gamePanel.maxScreenColumn){
                    collumn = 0;
                    x = 0; //resetting collumn and x
                    row++;
                    y+= gamePanel.spriteSize;
                }
        }
    }
}
