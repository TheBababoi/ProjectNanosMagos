package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public abstract class Creature {

    GamePanel gamePanel;
    public int worldX;
    public int worldY;
    public int speed;
    //sprite stuff
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    //animation stuff
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    //collision stuff
    public Rectangle hitbox = new Rectangle(12,24,56,56);
    public int hitboxX,hitboxY;
    public boolean collision = false;
    public int actionCounter;
    public int actionCounterMax;





    public Creature(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        direction = "down";
        speed = 4;

    }


    public void getSprites(String filePath) {

        try{
            up1 = ImageIO.read(new FileInputStream(filePath+"/up1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath+"/up2.png"));
            down1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            left1 = ImageIO.read(new FileInputStream(filePath+"/left1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath+"/left2.png"));
            right1 = ImageIO.read(new FileInputStream(filePath+"/right1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath+"/right2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.screenX; // tile's position in the world - hero's position in the world + the "camera's" range so the hero will always remain in the middle of the screen even if he is on the corner of the world map
        int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.screenY;
        if(direction=="up"){
            if(spriteNumber == 1){
                image = up1;
            }
            if(spriteNumber == 2){
                image = up2;
            }

        } else if (direction=="down") {
            if(spriteNumber == 1){
                image  = down1;
            }
            if(spriteNumber == 2){
                image  = down2;
            }
        } else if (direction=="left") {
            if(spriteNumber == 1){
                image  = left1;
            }
            if(spriteNumber == 2){
                image  = left2;
            }
        } else if (direction=="right") {
            if(spriteNumber == 1){
                image = right1;
            }
            if(spriteNumber == 2){
                image = right2;
            }
        }
        g2.drawImage(image,screenX,screenY, gamePanel.spriteSize,gamePanel.spriteSize,null);

    }

    public void refresh() {

        setAction();

        collision = false;
        gamePanel.collisionCheck.checkBackgroundTile(this);
        gamePanel.collisionCheck.checkObject(this,false);
        gamePanel.collisionCheck.checkPlayer(this);
        if (collision == false) {
            if (direction == "up") {
                worldY -= speed;
            } else if (direction == "down") {
                worldY += speed;
            } else if (direction == "left") {
                worldX -= speed;
            } else if (direction == "right") {
                worldX += speed;
            }
        }


        spriteCounter++; // counter gets upped 60 times per second
        if (spriteCounter > 15) {   //every 60/15 = 4 times a second
            if (spriteNumber == 1) { // forcing sprite to change 4 times a second
                spriteNumber = 2;
            } else {
                spriteNumber = 1;
            }
            spriteCounter = 0; // resetting counter
        }
    }

    public void setAction() {

        actionCounter++;
        if ( actionCounter > actionCounterMax){
            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick a number 0-100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionCounter = 0;
        }

    }
}
