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
        int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.getScreenX(); // tile's position in the world - hero's position in the world + the "camera's" range so the hero will always remain in the middle of the screen even if he is on the corner of the world map
        int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.getScreenY();
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

    public GamePanel getGamePanel() {
        return gamePanel;
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

    public BufferedImage getUp1() {
        return up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public String getDirection() {
        return direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getHitboxX() {
        return hitboxX;
    }

    public int getHitboxY() {
        return hitboxY;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getActionCounter() {
        return actionCounter;
    }

    public int getActionCounterMax() {
        return actionCounterMax;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void setHitboxX(int hitboxX) {
        this.hitboxX = hitboxX;
    }

    public void setHitboxY(int hitboxY) {
        this.hitboxY = hitboxY;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void setActionCounter(int actionCounter) {
        this.actionCounter = actionCounter;
    }

    public void setActionCounterMax(int actionCounterMax) {
        this.actionCounterMax = actionCounterMax;
    }
}
