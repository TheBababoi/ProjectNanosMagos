package creature;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Creature {

    public int worldX;
    public int worldY;
    public int speed;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle hitbox;
    public boolean collision = false;
    abstract  void setValues();
    abstract void getSprites();
}
