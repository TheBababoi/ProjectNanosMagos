package main;

import creature.FlowerGuy;
import creature.enemies.CloudGuy;
import object.*;

public class AssetPlacer {

    GamePanel gamePanel;

    public AssetPlacer(GamePanel gamePanel) {
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

    public  void setNPC() {
        gamePanel.npc[0] = new FlowerGuy(gamePanel);
        gamePanel.npc[0].worldX = 18*gamePanel.spriteSize;
        gamePanel.npc[0].worldY = 2*gamePanel.spriteSize;
        gamePanel.npc[1] = new FlowerGuy(gamePanel);
        gamePanel.npc[1].worldX = 16*gamePanel.spriteSize;
        gamePanel.npc[1].worldY = 2*gamePanel.spriteSize;
        gamePanel.npc[2] = new FlowerGuy(gamePanel);
        gamePanel.npc[2].worldX = 20*gamePanel.spriteSize;
        gamePanel.npc[2].worldY = 2*gamePanel.spriteSize;

    }

    public void setEnemy() {
        gamePanel.enemy[0] = new CloudGuy(gamePanel);
        gamePanel.enemy[0].worldX = 7*gamePanel.spriteSize;
        gamePanel.enemy[0].worldY = 3*gamePanel.spriteSize;
    }
}
