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

            //insert all grass --------------------------------------------------------------------------------------------
            backgroundTile[0] = new BackgroundTile();
            backgroundTile[0].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass00.png"));
            backgroundTile[7] = new BackgroundTile();
            backgroundTile[7].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass01.png"));
            backgroundTile[8] = new BackgroundTile();
            backgroundTile[8].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass02.png"));
            backgroundTile[9] = new BackgroundTile();
            backgroundTile[9].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass03.png"));
            backgroundTile[10] = new BackgroundTile();
            backgroundTile[10].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass04.png"));
            backgroundTile[11] = new BackgroundTile();
            backgroundTile[11].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass05.png"));
            backgroundTile[12] = new BackgroundTile();
            backgroundTile[12].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass06.png"));
            backgroundTile[13] = new BackgroundTile();
            backgroundTile[13].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass07.png"));
            backgroundTile[14] = new BackgroundTile();
            backgroundTile[14].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/grass08.png"));
            //-------------------------------------------------------------------------------------------------------------
            //insert all water ------------------------------------------------------------------------------------------------
            backgroundTile[1] = new BackgroundTile();
            backgroundTile[1].collision = true;
            backgroundTile[1].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/water00.png"));
            //-------------------------------------------------------------------------------------------------------------
            //insert all bricks ------------------------------------------------------------------------------------------------
            backgroundTile[2] = new BackgroundTile();
            backgroundTile[2].collision = true;
            backgroundTile[2].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/brick.png"));
            //-------------------------------------------------------------------------------------------------------------
            //insert all trees ---------------------------------------------------------------------------------------------
            backgroundTile[3] = new BackgroundTile();
            backgroundTile[3].collision = true;
            backgroundTile[3].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/tree.png"));
            //-------------------------------------------------------------------------------------------------------------
            //insert all gravels ------------------------------------------------------------------------------------------
            backgroundTile[4] = new BackgroundTile();
            backgroundTile[4].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/gravel00.png"));
            backgroundTile[15] = new BackgroundTile();
            backgroundTile[15].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/gravel01.png"));
            backgroundTile[16] = new BackgroundTile();
            backgroundTile[16].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/gravel02.png"));
            //-------------------------------------------------------------------------------------------------------------

            backgroundTile[5] = new BackgroundTile();
            backgroundTile[5].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/sand.png"));

            backgroundTile[6] = new BackgroundTile();
            backgroundTile[6].image = ImageIO.read(new FileInputStream("src/sprites/backgroundTiles/souravlas.png"));

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
