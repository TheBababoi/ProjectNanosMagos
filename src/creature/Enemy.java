package creature;

import main.GamePanel;

import javax.xml.namespace.QName;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Creature{
    String name;
    public BufferedImage battleImage;
    int health,maxHealth;

    public Enemy(GamePanel gamePanel) {
        super(gamePanel);


    }

    public abstract void setBattleSprites();




}
