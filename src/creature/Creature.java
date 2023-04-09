package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public abstract class Creature {

   protected GamePanel gamePanel;
    protected int worldX;
    protected int worldY;
    protected int speed;
    //sprite stuff
    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;
     protected String direction;
    //animation stuff
    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
    //collision stuff
    public Rectangle hitbox = new Rectangle(12,24,56,56);
    protected int hitboxX;
    protected int hitboxY;
    protected boolean collision = false;
    protected int actionCounter;
    protected int actionCounterMax;





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
        int screenX = worldX - gamePanel.getHero().worldX + gamePanel.getHero().getScreenX(); // tile's position in the world - hero's position in the world + the "camera's" range so the hero will always remain in the middle of the screen even if he is on the corner of the world map
        int screenY = worldY - gamePanel.getHero().worldY + gamePanel.getHero().getScreenY();
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
        g2.drawImage(image,screenX,screenY, gamePanel.getSpriteSize(), gamePanel.getSpriteSize(),null);

    }

    public void refresh() {

        setAction();

        collision = false;
        gamePanel.getCollisionCheck().checkBackgroundTile(this);
        gamePanel.getCollisionCheck().checkObject(this,false);
        gamePanel.getCollisionCheck().checkPlayer(this);
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


    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }


    public int getHitboxX() {
        return hitboxX;
    }

    public int getHitboxY() {
        return hitboxY;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }



    public void setCollision(boolean collision) {
        this.collision = collision;
    }

}
