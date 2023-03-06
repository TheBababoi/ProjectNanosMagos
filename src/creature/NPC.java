package creature;

import main.GamePanel;
import java.util.Random;



public abstract class NPC extends Creature {



    public NPC(GamePanel gamePanel) {
        super(gamePanel);
        speed = 4;
        direction = "down";
    }

}
