package creature;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Creature {

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
    public Rectangle hitbox;
    public int hitboxX,hitboxY;
    public boolean collision = false;


    abstract  void setValues();
    abstract void getSprites();
}
