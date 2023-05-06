package creature.enemies;

import Items.consumables.Mushroom;
import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;



public class Worm extends Enemy {


    public Worm(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/worm");
        name = "Worm";
        direction = "right";
        speed = 0;
        actionCounterMax = 300;
        setBattleStats();
        setDrops(gamePanel);
        spritesizeX=720;
        spritesizeY = 495;
        spriteX =  gamePanel.getScreenWidth() / 2 -400;
        spriteY = 200;
        battleText = "You're never alone, my friends are always around you.";





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
            battleImageDefault = ImageIO.read(new FileInputStream("src/sprites/worm/down1.png"));
            battleImageAttack = ImageIO.read(new FileInputStream("src/sprites/worm/right1.png"));
            battleImageHurt = ImageIO.read(new FileInputStream("src/sprites/worm/hurt.png"));

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
            left2 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            right1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));
            up1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            down1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
