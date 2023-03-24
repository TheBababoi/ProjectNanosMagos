package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Key extends SuperObject {

    public Key(GamePanel gamePanel) {
        super(gamePanel);

        name = "Key";
        try {
            image = ImageIO.read(new FileInputStream("src/sprites/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }
}
