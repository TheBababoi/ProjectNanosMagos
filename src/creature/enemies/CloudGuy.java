package creature.enemies;

import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class CloudGuy extends Enemy {

    public CloudGuy(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/cloudGuy");
        name = "CloudGuy";
        direction = "right";
        speed = 1;
        actionCounterMax = 30;
        setBattleStats();



    }

    @Override
    public void setBattleStats() {
        maxHealth = 1000;
        health = maxHealth;
        strength = 10;
        defence = 10;
        dexterity = 11;

        enemyMoves();
    }

    @Override
    public void setBattleSprites() {
        try{
            battleImage = ImageIO.read(new FileInputStream("src/sprites/cloudGuy/batlleimage1.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enemyMoves() {
        attackMove[0] = "Thunder";
        attackPower[0] = 3;
        attackAccuracy[0] = 5;
        attackMove[1] = "Rain";
        attackPower[1] = 1;
        attackAccuracy[1] = 10;
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