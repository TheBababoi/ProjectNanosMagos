package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Door extends SuperObject{

    public Door() {

        name = "Door";
        try {
            image = ImageIO.read(new FileInputStream("src/sprites/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }

}
