package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Treedude extends NPC {
    public Treedude(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/treedude");
        direction = "right";
        speed = 1;
        actionCounterMax = 30;
        setDialogue();
    }

    @Override
    public void setDialogue() {

        dialogues[0][0] = "You can't go this way.";
        dialogues[0][1] = "Sorry bud, but you have to leaf";


        dialogues[1][0] = "Can't pass from here mate.";
        dialogues[1][1] = "Find another way";


        dialogues[2][0] = "Wrong path. Or did you wish to talk to me?";
        dialogues[2][1] = "Cuz if it's the latter, nah bud.";
    }




    @Override
    public void getSprites(String filePath) {
        try {
            left1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            right1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            down1 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath + "/down1.png"));

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

        } else if (direction == "down") {
            if (spriteNumber == 1) {
                image = down1;
            }
            if (spriteNumber == 2) {
                image = down1;
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
            case "up":
                direction = "down";
                break;
        }
    }



    @Override
    public void setAction() {
        actionCounter++;
        if (actionCounter>=actionCounterMax){
            Random random = new Random();
            int i =random.nextInt(150)+1; //pick a number 0-150

            if( i <=50){
                direction = "left";
            }
            if(i>50 && i <=100){
                direction = "right";
            }else {
                direction = "down";
            }
            actionCounter = 0;
        }


    }

}
