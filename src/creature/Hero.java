package creature;

import main.GamePanel;
import main.KeyboardInputs;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;




public class Hero extends Creature {

    GamePanel gamePanel;
    KeyboardInputs keyboardInputs;

    public Hero(GamePanel gamePanel, KeyboardInputs keyboardInputs) {
        this.gamePanel = gamePanel;
        this.keyboardInputs = keyboardInputs;
        setValues();
        getSprites();
    }

    public void setValues() {
        x = 100;
        y = 100;
        speed = 10;
        direction = "down";
    }

    @Override
    void getSprites() {

        try{
            up1 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("src/sprites/hero/hero_right_2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        if (keyboardInputs.up || keyboardInputs.down || keyboardInputs.left || keyboardInputs.right){ //checking for keyboard inputs so the sprite wont change when character is standing still
            if(keyboardInputs.up){
                direction = "up";
                y -= speed;
            } else if (keyboardInputs.down) {
                direction = "down";
                y += speed;
            } else if (keyboardInputs.left) {
                direction = "left";
                x -= speed;
            } else if (keyboardInputs.right) {
                direction = "right";
                x += speed;
            }
            spriteCounter++; // counter gets upped 60 times per second
            if(spriteCounter > 15){   //every 60/15 = 4 times a second
                if(spriteNumber == 1){ // forcing sprite to change 4 times a second
                    spriteNumber = 2;
                } else{
                    spriteNumber = 1;
                }
                spriteCounter = 0; // resetting counter
            }
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.black);
       // g2.fillOval(x,y,gamePanel.spriteSize,gamePanel.spriteSize);
        BufferedImage sprite = null;

        if(direction=="up"){
            if(spriteNumber == 1){
                sprite = up1;
            }
            if(spriteNumber == 2){
                sprite = up2;
            }

        } else if (direction=="down") {
            if(spriteNumber == 1){
                sprite = down1;
            }
            if(spriteNumber == 2){
                sprite = down2;
            }
        } else if (direction=="left") {
            if(spriteNumber == 1){
                sprite = left1;
            }
            if(spriteNumber == 2){
                sprite = left2;
            }
        } else if (direction=="right") {
            if(spriteNumber == 1){
                sprite = right1;
            }
            if(spriteNumber == 2){
                sprite = right2;
            }
        }
        g2.drawImage(sprite, x,y, gamePanel.spriteSize,gamePanel.spriteSize,null);
    }
}
