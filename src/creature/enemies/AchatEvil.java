package creature.enemies;

import Items.consumables.Mushroom;
import Items.equipment.Diamond;
import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;



public class AchatEvil extends Enemy {


    public AchatEvil(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/achatEvil");
        name = "Achat";
        direction = "down";
        speed = 0;
        actionCounterMax = 30;
        setBattleStats();
        setDrops(gamePanel);
        spritesizeX=720;
        spritesizeY = 720;
        spriteX =  gamePanel.getScreenWidth() / 2 -400;
        spriteY = 100;
        battleText = "Who are you? What are you doing in Flowerland?\n I'm Chatzigeorgiou, the wizard of this place.\nYou don't seem trustworthy." +
                "I will not allow you to go forth to Sunny bay \n unless I'm certain about your intentions.";
        soundIndex = 23;





    }

    @Override
    public void setDrops(GamePanel gamePanel) {
        drop = new Diamond(gamePanel);
        dropChance = 10;
        goldDrop = 25;
    }

    @Override
    public void setBattleStats() {
        maxHealth = 200;
        health = maxHealth;
        strength = 10;
        defence = 10;
        dexterity = 11;
        exp = 500;

        enemyMoves();
    }

    @Override
    public void setBattleSprites(BufferedImage image) {
        try{
            battleImageDefault = ImageIO.read(new FileInputStream("src/sprites/achatEvil/down1.png"));
            battleImageAttack = ImageIO.read(new FileInputStream("src/sprites/achatEvil/down1.png"));
            battleImageHurt = ImageIO.read(new FileInputStream("src/sprites/achatEvil/down2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void enemyMoves() {
        attackMove[0] = "Fire Blast";
        attackPower[0] = 25;
        attackAccuracy[0] = 10;
        attackMove[1] = "Inferno";
        attackPower[1] = 30;
        attackAccuracy[1] = 12;
        attackMove[2] = "Eruption";
        attackAccuracy[2] = 8;
        attackPower[2] = 40;
        attackMove[3] = "Flame Wheel";
        attackPower[3] = 20;
        attackAccuracy[3] = 13;

    }


    @Override
    public void getSprites(String filePath) {
        try {
            left1 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));
            right1 = ImageIO.read(new FileInputStream(filePath + "/down2.png"));
            right2 = ImageIO.read(new FileInputStream(filePath + "/down2.png"));
            up1 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));
            down1 = ImageIO.read(new FileInputStream(filePath + "/down2.png"));
            down2 = ImageIO.read(new FileInputStream(filePath + "/down2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
