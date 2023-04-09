package backroundTile;

import java.awt.image.BufferedImage;

public class BackgroundTile {

    BufferedImage image;
    Boolean collision = false;

    public Boolean getCollision() {
        return collision;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}

