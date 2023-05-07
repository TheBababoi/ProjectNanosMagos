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
        name = "Wooormy";
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
        soundIndex = 20;





    }

    @Override
    public void setDrops(GamePanel gamePanel) {
        drop = new  Mushroom(gamePanel);
        dropChance = 7;
        goldDrop = 3;
    }

    @Override
    public void setBattleStats() {
        maxHealth = 30;
        health = maxHealth;
        strength = 10;
        defence = 5;
        dexterity = 14;
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
        attackMove[0] = "Dirt Throw";
        attackPower[0] = 10;
        attackAccuracy[0] = 10;
        attackMove[1] = "Dig";
        attackPower[1] = 10;
        attackAccuracy[1] = 8;
        attackMove[2] = "Body Slam";
        attackAccuracy[2] = 9;
        attackPower[2] = 10;
        attackMove[3] = "Slap";
        attackPower[3] = 8;
        attackAccuracy[3] = 12;

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
