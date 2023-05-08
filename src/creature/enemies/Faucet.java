package creature.enemies;

import Items.consumables.ManaPotion;
import Items.consumables.Mushroom;
import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;



public class Faucet extends Enemy {


    public Faucet(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/faucet");
        name = "Mr Leaky";
        direction = "right";
        speed = 1;
        actionCounterMax = 30;
        setBattleStats();
        setDrops(gamePanel);
        spritesizeX=720;
        spritesizeY = 512;
        spriteX =  gamePanel.getScreenWidth() / 2 -400;
        spriteY = 200;
        battleText = "Do you like hot water? Cuz like it or not, it's coming at you.";
        soundIndex = 22;





    }

    @Override
    public void setDrops(GamePanel gamePanel) {
        drop = new ManaPotion(gamePanel);
        dropChance = 5;
        goldDrop = 20;
    }

    @Override
    public void setBattleStats() {
        maxHealth = 100;
        health = maxHealth;
        strength = 20;
        defence = 10;
        dexterity = 11;
        exp = 200;

        enemyMoves();
    }

    @Override
    public void setBattleSprites(BufferedImage image) {
        try{
            battleImageDefault = ImageIO.read(new FileInputStream("src/sprites/faucet/left1.png"));
            battleImageAttack = ImageIO.read(new FileInputStream("src/sprites/faucet/left2.png"));
            battleImageHurt = ImageIO.read(new FileInputStream("src/sprites/faucet/right1.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void enemyMoves() {
        attackMove[0] = "Headbutt";
        attackPower[0] = 15;
        attackAccuracy[0] = 10;
        attackMove[1] = "Water Pistol";
        attackPower[1] = 13;
        attackAccuracy[1] = 12;
        attackMove[2] = "Water Pump";
        attackAccuracy[2] = 20;
        attackPower[2] = 10;
        attackMove[3] = "Boiling Water";
        attackPower[3] = 25;
        attackAccuracy[3] = 9;

    }


    @Override
    public void getSprites(String filePath) {
        try {
            left1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath + "/left2.png"));
            right1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath + "/right2.png"));
            up1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath + "/left2.png"));
            down1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath + "/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
