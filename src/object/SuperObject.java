package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = true;
    public int worldX, worldY;
    public Rectangle hitbox = new Rectangle(0,0,80,80);
    public int hitboxX = 0;
    public int hitboxY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel){
        int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.screenX; // tile's position in the world - hero's position in the world + the "camera's" range so the hero will always remain in the middle of the screen even if he is on the corner of the world map
        int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.screenY;
        g2.drawImage(image,screenX,screenY, gamePanel.spriteSize,gamePanel.spriteSize,null);

    }
}
