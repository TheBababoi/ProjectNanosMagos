package creature.enemies;

import Items.consumables.Mushroom;
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
        battleText = "Who are you? What are you doing in Flowerland? I'm Chatzigeorgiou, \n the wizard of this place.You don't seem trustworthy." +
                "I will not allow you to go forth to Sunny bay \n unless I'm certain about your intentions.";





    }

    @Override
    public void setDrops(GamePanel gamePanel) {
        drop = new  Mushroom(gamePanel);
        dropChance = 10;
        goldDrop = 10;
    }

    @Override
    public void setBattleStats() {
        maxHealth = 100;
        health = maxHealth;
        strength = 10;
        defence = 10;
        dexterity = 11;
        exp = 20;

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
        attackMove[0] = "Thunder";
        attackPower[0] = 10;
        attackAccuracy[0] = 10;
        attackMove[1] = "Rain";
        attackPower[1] = 8;
        attackAccuracy[1] = 12;
        attackMove[2] = "Wind";
        attackAccuracy[2] = 8;
        attackPower[2] = 6;
        attackMove[3] = "Fog";
        attackPower[3] = 5;
        attackAccuracy[3] = 8;

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
