package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class FlowerGuy extends NPC {
    public FlowerGuy(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/flowerGuy");
        direction = "right";
        speed = 1;
        actionCounterMax = 30;
        setDialogue();
    }

    @Override
    public void setDialogue() {

        dialogues[0][0] = "Hello Hero!";
        dialogues[0][1] = "I'm the Flower Guy";
        dialogues[0][2] = "I can only walk like a crab \n because of my roots!";

        dialogues[1][0] = "One day this game might be completed";
        dialogues[1][1] = "One day  I might get some proper dialogue";
        dialogues[1][2] = "I'm sick at looking at this code";

        dialogues[2][0] = "I wonder if anyone will even help me \n with this shit";
    }




    @Override
    public void getSprites(String filePath) {
        try {
            left1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath + "/left2.png"));
            right1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath + "/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        BufferedImage image = null;
        int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.screenX;
        int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.screenY;
        if (direction == "left") {
            if (spriteNumber == 1) {
                image = left1;
            }
            if (spriteNumber == 2) {
                image = left2;
            }
        } else if (direction == "right") {
            if (spriteNumber == 1) {
                image = right1;
            }
            if (spriteNumber == 2) {
                image = right2;
            }
        }
        g2.drawImage(image,screenX,screenY, gamePanel.spriteSize,gamePanel.spriteSize,null);
    }

    @Override
    public void faceHero() {
        switch (gamePanel.hero.direction) {
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }



    @Override
    public void setAction() {
        actionCounter++;
        if (actionCounter>=actionCounterMax){
        Random random = new Random();
        int i =random.nextInt(100)+1; //pick a number 0-100

        if( i <=50){
            direction = "left";
        }
        if(i>50 && i <=100){
            direction = "right";
        }
        actionCounter = 0;
    }


}

}
