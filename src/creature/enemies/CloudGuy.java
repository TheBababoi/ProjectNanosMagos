package creature.enemies;

import Items.consumables.HealthPotion;
import Items.consumables.Mushroom;
import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        setDrops(gamePanel);
        spritesizeX=720;
        spritesizeY = 480;
        spriteX =  gamePanel.getScreenWidth() / 2 -400;
        spriteY = 200;
        battleText = "You can't hide from me. \n I' ll find you and shock you wherever you are.";
        soundIndex = 19;





    }

    @Override
    public void setDrops(GamePanel gamePanel) {
        drop = new HealthPotion(gamePanel);
        dropChance = 4;
        goldDrop = 10;
    }

    @Override
    public void setBattleStats() {
        maxHealth = 60;
        health = maxHealth;
        strength = 15;
        defence = 12;
        dexterity = 10;
        exp = 60;

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
        attackPower[0] = 12;
        attackAccuracy[0] = 10;
        attackMove[1] = "Rain";
        attackPower[1] = 10;
        attackAccuracy[1] = 12;
        attackMove[2] = "Wind";
        attackAccuracy[2] = 8;
        attackPower[2] = 8;
        attackMove[3] = "Fog";
        attackPower[3] = 7;
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
