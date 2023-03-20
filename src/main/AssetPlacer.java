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
        int mapNumber =0;
        gamePanel.superObject[mapNumber][0] = new Key();
        gamePanel.superObject[mapNumber][0].worldX = 5*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][0].worldY = 5*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][1] = new Key();
        gamePanel.superObject[mapNumber][1].worldX = 10*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][1].worldY = 11*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][2] = new Door();
        gamePanel.superObject[mapNumber][2].worldX = 5*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][2].worldY = 8*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][3] = new Chest();
        gamePanel.superObject[mapNumber][3].worldX = 6*gamePanel.spriteSize;
        gamePanel.superObject[mapNumber][3].worldY = 5*gamePanel.spriteSize;


        // DO NOT TOUCH THIS WILL BREAK THE GAME
        gamePanel.superObject[mapNumber][9] = new Tampouris();

         mapNumber =1;
        gamePanel.superObject[mapNumber][9] = new Tampouris();


    }

    public  void setNPC() {
        int mapNumber = 0;
        gamePanel.npc[mapNumber][0] = new FlowerGuy(gamePanel);
        gamePanel.npc[mapNumber][0].worldX = 18*gamePanel.spriteSize;
        gamePanel.npc[mapNumber][0].worldY = 2*gamePanel.spriteSize;
        gamePanel.npc[mapNumber][1] = new FlowerGuy(gamePanel);
        gamePanel.npc[mapNumber][1].worldX = 16*gamePanel.spriteSize;
        gamePanel.npc[mapNumber][1].worldY = 2*gamePanel.spriteSize;
        gamePanel.npc[mapNumber][2] = new FlowerGuy(gamePanel);
        gamePanel.npc[mapNumber][2].worldX = 20*gamePanel.spriteSize;
        gamePanel.npc[mapNumber][2].worldY = 2*gamePanel.spriteSize;

    }

    public void setEnemy() {
        int mapNumber = 0;
        gamePanel.enemy[mapNumber][0] = new CloudGuy(gamePanel);
        gamePanel.enemy[mapNumber][0].worldX = 7*gamePanel.spriteSize;
        gamePanel.enemy[mapNumber][0].worldY = 3*gamePanel.spriteSize;
    }
}
