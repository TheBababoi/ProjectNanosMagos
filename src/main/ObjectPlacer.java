package main;

import object.*;

public class ObjectPlacer {

    GamePanel gamePanel;

    public ObjectPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public  void setObject() {
        gamePanel.superObject[0] = new Key();
        gamePanel.superObject[0].worldX = 5*gamePanel.spriteSize;
        gamePanel.superObject[0].worldY = 5*gamePanel.spriteSize;
        gamePanel.superObject[1] = new Key();
        gamePanel.superObject[1].worldX = 10*gamePanel.spriteSize;
        gamePanel.superObject[1].worldY = 11*gamePanel.spriteSize;
        gamePanel.superObject[2] = new Door();
        gamePanel.superObject[2].worldX = 5*gamePanel.spriteSize;
        gamePanel.superObject[2].worldY = 8*gamePanel.spriteSize;
        gamePanel.superObject[3] = new Chest();
        gamePanel.superObject[3].worldX = 6*gamePanel.spriteSize;
        gamePanel.superObject[3].worldY = 5*gamePanel.spriteSize;


        // DO NOT TOUCH THIS WILL BREAK THE GAME
        gamePanel.superObject[9] = new Tampouris();
    }
}
