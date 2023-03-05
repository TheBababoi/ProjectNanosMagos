package object;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Tampouris extends SuperObject {

    public Tampouris() {

        name = "Tampouris";
        try {
            image = ImageIO.read(new FileInputStream("src/sprites/objects/tampouris.png"));
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
    }
}

