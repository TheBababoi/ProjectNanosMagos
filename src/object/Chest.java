package object;

import Items.Item;
import Items.equipment.LegendaryPen;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Chest extends SuperObject{
    public Item content;
    public Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        content = new LegendaryPen(gamePanel);
        try {
            image = ImageIO.read(new FileInputStream("src/sprites/objects/chest.png"));
        } catch (IOException e) {
                e.printStackTrace();

        }
    }
}

