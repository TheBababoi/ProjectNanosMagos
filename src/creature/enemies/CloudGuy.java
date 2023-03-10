package creature.enemies;

import creature.Enemy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class CloudGuy extends Enemy {

    public CloudGuy(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/cloudGuy");
        direction = "right";
        speed = 1;
        actionCounterMax = 30;
    }

    @Override
    public void setBattleSprites() {
        try{
            battleImage = ImageIO.read(new FileInputStream("src/sprites/cloudGuy/batlleimage1.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void getSprites(String filePath) {
        try {
            left1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath + "/left2.png"));
            right1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath + "/right2.png"));
            up1 = ImageIO.read(new FileInputStream(filePath + "/left1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath + "/left2.png"));
            down1 = ImageIO.read(new FileInputStream(filePath + "/right1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath + "/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
