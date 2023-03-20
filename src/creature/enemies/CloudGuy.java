package creature.enemies;

import Items.consumables.Mushroom;
import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;



public class CloudGuy extends Enemy {
    public boolean alive = true;
    public boolean defeated = false;

    public CloudGuy(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/cloudGuy");
        name = "CloudGuy";
        direction = "right";
        speed = 1;
        actionCounterMax = 30;
        setBattleStats();
        setDrops(gamePanel);





    }

    @Override
    public void setDrops(GamePanel gamePanel) {
        drop = new  Mushroom(gamePanel);
        dropChance = 10;
        goldDrop = 10;
    }

    @Override
    public void setBattleStats() {
        maxHealth = 1000;
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
            battleImageDefault = ImageIO.read(new FileInputStream("src/sprites/cloudGuy/battleimagedefault.png"));
            battleImageAttack = ImageIO.read(new FileInputStream("src/sprites/cloudGuy/battleimageattack.png"));
            battleImageHurt = ImageIO.read(new FileInputStream("src/sprites/cloudGuy/battleimagehurt.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void enemyMoves() {
        attackMove[0] = "Thunder";
        attackPower[0] = 2;
        attackAccuracy[0] = 7;
        attackMove[1] = "Rain";
        attackPower[1] = 2;
        attackAccuracy[1] = 1;
        attackMove[2] = "Wind";
        attackAccuracy[2] = 8;
        attackPower[2] = 2;
        attackMove[3] = "Fog";
        attackPower[3] = 2;
        attackAccuracy[3] = 8;

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
