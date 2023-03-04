package backroundTile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class BackgroundTileManager {
    GamePanel gamePanel;
    BackgroundTile[] backgroundTile;

    public BackgroundTileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        backgroundTile = new BackgroundTile[40];
        getTileImage();
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
        while(collumn <= gamePanel.maxScreenColumn && row<= gamePanel.maxScreenRow) {
            g2.drawImage(backgroundTile[0].image,x,y, gamePanel.spriteSize,gamePanel.spriteSize,null);
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
