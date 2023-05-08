package main;

import Items.consumables.FortifiedPotion;
import Items.consumables.HealthPotion;
import Items.consumables.ManaPotion;
import Items.consumables.Mushroom;
import Items.equipment.CoolStar;
import Items.equipment.PurpleSword;
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
        gamePanel.getSuperObject()[mapNumber][0] = new Chest(gamePanel, new PurpleSword(gamePanel));
        gamePanel.getSuperObject()[mapNumber][0].setWorldX(1 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][0].setWorldY(10 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][1] = new Chest(gamePanel, new FortifiedPotion(gamePanel));
        gamePanel.getSuperObject()[mapNumber][1].setWorldX(1 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][1].setWorldY(21 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][2] = new Door(gamePanel);
        gamePanel.getSuperObject()[mapNumber][2].setWorldX(7 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][2].setWorldY(46 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3] = new Chest(gamePanel, new HealthPotion(gamePanel));
        gamePanel.getSuperObject()[mapNumber][3].setWorldX(29 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3].setWorldY(37 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6] = new Chest(gamePanel, new HealthPotion(gamePanel));
        gamePanel.getSuperObject()[mapNumber][6].setWorldX(30 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6].setWorldY(3 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][7] = new Chest(gamePanel, new ManaPotion(gamePanel));
        gamePanel.getSuperObject()[mapNumber][7].setWorldX(41 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][7].setWorldY(40 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][8] = new Chest(gamePanel, new CoolStar(gamePanel));
        gamePanel.getSuperObject()[mapNumber][8].setWorldX(41 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][8].setWorldY(40 * gamePanel.getSpriteSize());
        mapNumber =1;
        gamePanel.getSuperObject()[mapNumber][3] = new Chest(gamePanel, new Mushroom(gamePanel));
        gamePanel.getSuperObject()[mapNumber][3].setWorldX(14 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][3].setWorldY(8 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6] = new Chest(gamePanel, new Mushroom(gamePanel));
        gamePanel.getSuperObject()[mapNumber][6].setWorldX(18 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][6].setWorldY(10 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][7] = new Chest(gamePanel, new Mushroom(gamePanel));
        gamePanel.getSuperObject()[mapNumber][7].setWorldX(10 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][7].setWorldY(12 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][8] = new Chest(gamePanel, new Mushroom(gamePanel));
        gamePanel.getSuperObject()[mapNumber][8].setWorldX(18 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][8].setWorldY(12 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][5] = new Chest(gamePanel, new Mushroom(gamePanel));
        gamePanel.getSuperObject()[mapNumber][5].setWorldX(10 * gamePanel.getSpriteSize());
        gamePanel.getSuperObject()[mapNumber][5].setWorldY(14 * gamePanel.getSpriteSize());





        // DO NOT TOUCH THIS WILL BREAK THE GAME
        gamePanel.getSuperObject()[0][9] = new Tampouris(gamePanel);

        gamePanel.getSuperObject()[1][9] = new Tampouris(gamePanel);


    }

    public  void setNPC() {
        int mapNumber = 0;
        gamePanel.getNpc()[mapNumber][3] = new Bunny1(gamePanel);
        gamePanel.getNpc()[mapNumber][3].setWorldX(40 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][3].setWorldY(8 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][4] = new Bunny2(gamePanel);
//        gamePanel.getNpc()[mapNumber][4].setWorldX(5 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][4].setWorldY(5 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][5] = new Bunny3(gamePanel);
//        gamePanel.getNpc()[mapNumber][5].setWorldX(6 * gamePanel.getSpriteSize());
//        gamePanel.getNpc()[mapNumber][5].setWorldY(6 * gamePanel.getSpriteSize());

        gamePanel.getNpc()[mapNumber][7] = new Sentoni(gamePanel);
        gamePanel.getNpc()[mapNumber][7].setWorldX(36 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][7].setWorldY(28 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][8] = new Treedude(gamePanel);
        gamePanel.getNpc()[mapNumber][8].setWorldX(21 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][8].setWorldY(2 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][0] = new FlowerGuy(gamePanel);
        gamePanel.getNpc()[mapNumber][0].setWorldX(11 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][0].setWorldY(4 * gamePanel.getSpriteSize());


        mapNumber = 1;

        gamePanel.getNpc()[mapNumber][1] = new Merchant(gamePanel);
        gamePanel.getNpc()[mapNumber][1].setWorldX(30 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][1].setWorldY(23 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][2] = new Nikolaidis(gamePanel);
        gamePanel.getNpc()[mapNumber][2].setWorldX(35 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][2].setWorldY(34* gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][6] = new Bird(gamePanel);
        gamePanel.getNpc()[mapNumber][6].setWorldX(33 * gamePanel.getSpriteSize());
        gamePanel.getNpc()[mapNumber][6].setWorldY(37 * gamePanel.getSpriteSize());


    }

    public void setEnemy() {
        int mapNumber = 0;
       gamePanel.getEnemy()[mapNumber][0] = new CloudGuy(gamePanel);
       gamePanel.getEnemy()[mapNumber][0].setWorldX(23 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][0].setWorldY(7 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1] = new AchatEvil(gamePanel);
        gamePanel.getEnemy()[mapNumber][1].setWorldX(1 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1].setWorldY(47* gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][2] = new Faucet(gamePanel);
       gamePanel.getEnemy()[mapNumber][2].setWorldX(26 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][2].setWorldY(25 * gamePanel.getSpriteSize());
      gamePanel.getEnemy()[mapNumber][3] = new ReflectiveSurface(gamePanel);
       gamePanel.getEnemy()[mapNumber][3].setWorldX(11 * gamePanel.getSpriteSize());
       gamePanel.getEnemy()[mapNumber][3].setWorldY(9 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][6] = new ReflectiveSurface(gamePanel);
        gamePanel.getEnemy()[mapNumber][6].setWorldX(20 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][6].setWorldY(48 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4] = new Worm(gamePanel);
      gamePanel.getEnemy()[mapNumber][4].setWorldX(6 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4].setWorldY(10 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][5] = new CloudGuy(gamePanel);
        gamePanel.getEnemy()[mapNumber][5].setWorldX(34 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][5].setWorldY(8 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][6] = new ReflectiveSurface(gamePanel);
        gamePanel.getEnemy()[mapNumber][6].setWorldX(24 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][6].setWorldY(33 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][7] = new CloudGuy(gamePanel);
        gamePanel.getEnemy()[mapNumber][7].setWorldX(18 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][7].setWorldY(38 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][8] = new Faucet(gamePanel);
        gamePanel.getEnemy()[mapNumber][8].setWorldX(2 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][8].setWorldY(23 * gamePanel.getSpriteSize());

        mapNumber = 1;
        gamePanel.getEnemy()[mapNumber][0] = new Worm(gamePanel);
        gamePanel.getEnemy()[mapNumber][0].setWorldX(15 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][0].setWorldY(6 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1] = new Worm(gamePanel);
        gamePanel.getEnemy()[mapNumber][1].setWorldX(15 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][1].setWorldY(10 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][2] = new Worm(gamePanel);
        gamePanel.getEnemy()[mapNumber][2].setWorldX(11 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][2].setWorldY(10 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][3] = new Worm(gamePanel);
        gamePanel.getEnemy()[mapNumber][3].setWorldX(14 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][3].setWorldY(12 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4] = new Worm(gamePanel);
        gamePanel.getEnemy()[mapNumber][4].setWorldX(14 * gamePanel.getSpriteSize());
        gamePanel.getEnemy()[mapNumber][4].setWorldY(14 * gamePanel.getSpriteSize());


    }
}
