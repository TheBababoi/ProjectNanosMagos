package Items;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class Item {
    protected GamePanel gamePanel;
    protected String name;
    protected String description;
    BufferedImage image;

    public Item(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void getSprite(String path){
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
