package Items.consumables;

import Items.Item;
import main.GamePanel;

public class Key extends Item {
    public Key(GamePanel gamePanel) {
        super(gamePanel);
        getSprite("src/sprites/objects/key.png");
        name = "Key";
        price = 150;
    }
}
