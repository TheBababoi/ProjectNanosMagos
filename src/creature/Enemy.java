package creature;

import main.GamePanel;


import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Creature{
    public String name;
    public BufferedImage battleImageDefault,battleImageAttack,battleImageHurt;
    public int health,maxHealth,strength,defence,dexterity;

    public int enemyChoice =99;
    public String[] attackMove = new String[4];
    public int[] attackPower = new int[4];
    public int[] attackAccuracy = new int[4];


    @Override
    public void draw(Graphics2D g2) {

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


    public Enemy(GamePanel gamePanel) {
        super(gamePanel);


    }

    public abstract void setBattleStats();

    public abstract void setBattleSprites(BufferedImage image);

    public abstract void enemyMoves();




}
