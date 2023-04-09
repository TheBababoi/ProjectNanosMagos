package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    protected GamePanel gamePanel;

    public SuperObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    protected BufferedImage image;
    protected String name;
    protected boolean collision = true;
    protected int worldX, worldY;
    protected Rectangle hitbox = new Rectangle(0,0,80,80);
    protected int hitboxX = 0;
    protected int hitboxY = 0;

    public void draw(Graphics2D g2){
        int screenX = worldX - gamePanel.getHero().getWorldX() + gamePanel.getHero().getScreenX(); // tile's position in the world - hero's position in the world + the "camera's" range so the hero will always remain in the middle of the screen even if he is on the corner of the world map
        int screenY = worldY - gamePanel.getHero().getWorldY() + gamePanel.getHero().getScreenY();
        g2.drawImage(image,screenX,screenY, gamePanel.getSpriteSize(), gamePanel.getSpriteSize(),null);

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
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

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

}
