package main;

import Items.consumables.HealthPotion;
import Items.consumables.ManaPotion;
import creature.*;
import creature.enemies.*;
import object.*;

public class AssetPlacer {

    private GamePanel gamePanel;

    public AssetPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public  void setObject() {
        int mapNumber =0;
        gamePanel.getSuperObject()[mapNumber][2] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][2].setWorldX(4 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][2].setWorldY(7 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][3].setWorldX(10 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3].setWorldY(38 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][4] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][4].setWorldX(13 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][4].setWorldY(42 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][5] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][5].setWorldX(13 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][5].setWorldY(42 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6] = new Chest(gamePanel, new HealthPotion(gamePanel));
        gamePanel.getSuperObject()[mapNumber][6].setWorldX(0 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6].setWorldY(6 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6] = new Chest(gamePanel, new ManaPotion(gamePanel));
        gamePanel.getSuperObject()[mapNumber][6].setWorldX(0 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6].setWorldY(8 * gamePanel.getSpriteSize());


        // DO NOT TOUCH THIS WILL BREAK THE GAME
        gamePanel.getSuperObject()[mapNumber][9] = new Tampouris(gamePanel);

         mapNumber =1;
        gamePanel.getSuperObject()[mapNumber][9] = new Tampouris(gamePanel);


    }

    public  void setNPC() {
        int mapNumber = 0;
//        gamePanel.getNpc()[mapNumber][3] = new Bunny1(gamePanel);
//        gamePanel.getNpc()[mapNumber][3].setWorldX(4 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][3].setWorldY(4 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][4] = new Bunny2(gamePanel);
//        gamePanel.getNpc()[mapNumber][4].setWorldX(5 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][4].setWorldY(5 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][5] = new Bunny3(gamePanel);
//        gamePanel.getNpc()[mapNumber][5].setWorldX(6 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][5].setWorldY(6 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][6] = new Bird(gamePanel);
        gamePanel.getNpc()[mapNumber][6].setWorldX(7 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][6].setWorldY(7 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][7] = new Sentoni(gamePanel);
        gamePanel.getNpc()[mapNumber][7].setWorldX(8 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][7].setWorldY(8 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][0] = new FlowerGuy(gamePanel);
        gamePanel.getNpc()[mapNumber][0].setWorldX(12 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][0].setWorldY(5 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][1] = new Merchant(gamePanel);
        gamePanel.getNpc()[mapNumber][1].setWorldX(7 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][1].setWorldY(13 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][2] = new Nikolaidis(gamePanel);
        gamePanel.getNpc()[mapNumber][2].setWorldX(3 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][2].setWorldY(3 * gamePanel.getSpriteSize());



    }

    public void setEnemy() {
        int mapNumber = 0;
       gamePanel.getEnemy()[mapNumber][0] = new CloudGuy(gamePanel);
       gamePanel.getEnemy()[mapNumber][0].setWorldX(8 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][0].setWorldY(6 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1] = new AchatEvil(gamePanel);
        gamePanel.getEnemy()[mapNumber][1].setWorldX(5 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1].setWorldY(5 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][2] = new Faucet(gamePanel);
       gamePanel.getEnemy()[mapNumber][2].setWorldX(3 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][2].setWorldY(3 * gamePanel.getSpriteSize());
      gamePanel.getEnemy()[mapNumber][3] = new ReflectiveSurface(gamePanel);
       gamePanel.getEnemy()[mapNumber][3].setWorldX(5 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][3].setWorldY(5 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4] = new Worm(gamePanel);
      gamePanel.getEnemy()[mapNumber][4].setWorldX(2 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4].setWorldY(2 * gamePanel.getSpriteSize());


    }
}
