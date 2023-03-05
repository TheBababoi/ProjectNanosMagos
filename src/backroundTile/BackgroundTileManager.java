package backroundTile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class BackgroundTileManager {
    GamePanel gamePanel;
    public BackgroundTile[] backgroundTile;
    public int mapLayout[][];

    public BackgroundTileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        backgroundTile = new BackgroundTile[40];
        mapLayout= new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        getTileImage();
        loadMapfromTextFile("src/sprites/maps/world01.txt");
    }

    public void loadMapfromTextFile(String filePath){
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int collumn = 0;
            int row = 0;
            while(collumn < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow){
                String line = bufferedReader.readLine();
                while(collumn < gamePanel.maxWorldColumn){
                    String digit[] = line.split(" "); // fills the array with the digits that are getting split up by the spaces
                    int digit2 = Integer.parseInt(digit[collumn]); // converting string to int
                    mapLayout[collumn][row] = digit2;
                    collumn++;
                }
                if(collumn==gamePanel.maxWorldColumn){
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
            backgroundTile[1].collision = true;
            backgroundTile[1].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/water.png"));
            backgroundTile[2] = new BackgroundTile();
            backgroundTile[2].collision = true;
            backgroundTile[2].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/brick.png"));
            backgroundTile[3] = new BackgroundTile();
            backgroundTile[3].collision = true;
            backgroundTile[3].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/brick.png"));
            backgroundTile[5] = new BackgroundTile();
            backgroundTile[5].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/sand.png"));
            backgroundTile[4] = new BackgroundTile();
            backgroundTile[4].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/gravel.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCollumn = 0;
        int worldRow = 0;
        while(worldCollumn < gamePanel.maxWorldColumn && worldRow< gamePanel.maxWorldRow) {
            int tileDigit = mapLayout[worldCollumn][worldRow];
            int worldX = worldCollumn * gamePanel.spriteSize;
            int worldY = worldRow * gamePanel.spriteSize;
            int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.screenX; // tile's position in the world - hero's position in the world + the "camera's" range so the hero will always remain in the middle of the screen even if he is on the corner of the world map
            int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.screenY;
            g2.drawImage(backgroundTile[tileDigit].image,screenX,screenY, gamePanel.spriteSize,gamePanel.spriteSize,null);
            worldCollumn++;

                if(worldCollumn == gamePanel.maxWorldColumn){
                    worldCollumn = 0;
                    worldRow++;
                }
        }
    }
}
