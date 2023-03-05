package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Chest extends SuperObject{
    public Chest() {

        name = "Chest";
        try {
            image = ImageIO.read(new FileInputStream("src/sprites/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }

}
