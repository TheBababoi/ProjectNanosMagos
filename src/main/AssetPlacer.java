package main;

import creature.FlowerGuy;
import creature.Merchant;
import creature.enemies.CloudGuy;
import creature.enemies.NanosMagos;
import object.*;

public class AssetPlacer {

    private GamePanel gamePanel;

    public AssetPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public  void setObject() {
        int mapNumber =0;
        gamePanel.getSuperObject()[mapNumber][2] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][2].setWorldX(7 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][2].setWorldY(16 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][3].setWorldX(10 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3].setWorldY(38 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][4] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][4].setWorldX(13 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][4].setWorldY(42 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][5] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][5].setWorldX(13 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][5].setWorldY(42 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6] = new Chest(gamePanel);
        gamePanel.getSuperObject()[mapNumber][6].setWorldX(20 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6].setWorldY(48 * gamePanel.getSpriteSize());


        // DO NOT TOUCH THIS WILL BREAK THE GAME
        gamePanel.getSuperObject()[mapNumber][9] = new Tampouris(gamePanel);

         mapNumber =1;
        gamePanel.getSuperObject()[mapNumber][9] = new Tampouris(gamePanel);


    }

    public  void setNPC() {
        int mapNumber = 0;
        gamePanel.getNpc()[mapNumber][0] = new FlowerGuy(gamePanel);
        gamePanel.getNpc()[mapNumber][0].setWorldX(12 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][0].setWorldY(5 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][1] = new Merchant(gamePanel);
        gamePanel.getNpc()[mapNumber][1].setWorldX(7 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][1].setWorldY(13 * gamePanel.getSpriteSize());


    }

    public void setEnemy() {
        int mapNumber = 0;
       gamePanel.getEnemy()[mapNumber][0] = new CloudGuy(gamePanel);
       gamePanel.getEnemy()[mapNumber][0].setWorldX(7 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][0].setWorldY(3 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1] = new NanosMagos(gamePanel);
        gamePanel.getEnemy()[mapNumber][1].setWorldX(10 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1].setWorldY(10 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][2] = new CloudGuy(gamePanel);
       gamePanel.getEnemy()[mapNumber][2].setWorldX(9 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][2].setWorldY(44 * gamePanel.getSpriteSize());
      gamePanel.getEnemy()[mapNumber][3] = new CloudGuy(gamePanel);
       gamePanel.getEnemy()[mapNumber][3].setWorldX(11 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][3].setWorldY(44 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4] = new CloudGuy(gamePanel);
      gamePanel.getEnemy()[mapNumber][4].setWorldX(11 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4].setWorldY(45 * gamePanel.getSpriteSize());
//        gamePanel.enemy[mapNumber][5] = new CloudGuy(gamePanel);
//        gamePanel.enemy[mapNumber][5].worldX = 11*gamePanel.spriteSize;
//        gamePanel.enemy[mapNumber][5].worldY = 50*gamePanel.spriteSize;

    }
}
