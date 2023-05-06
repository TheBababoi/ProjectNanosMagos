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

        dialogues[0][0] = "What a beautiful day to be a flower!";
        dialogues[0][1] = "I enjoy sunny days like this.";
        dialogues[0][2] = "The rain from the clouds is nutricious. I love it.";


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
        int screenX = worldX - gamePanel.getHero().worldX + gamePanel.getHero().getScreenX();
        int screenY = worldY - gamePanel.getHero().worldY + gamePanel.getHero().getScreenY();
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
        g2.drawImage(image,screenX,screenY, gamePanel.getSpriteSize(), gamePanel.getSpriteSize(),null);
    }

    @Override
    public void faceHero() {
        switch (gamePanel.getHero().direction) {
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
