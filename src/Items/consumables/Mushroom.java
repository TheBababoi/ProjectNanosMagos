package Items.consumables;

import Items.Item;
import main.GamePanel;

public class Mushroom extends Item {
    public Mushroom(GamePanel gamePanel) {
        super(gamePanel);
        name = "Mushroom";
        description = "(Purple Mushroom) \n Very tasty!";
        getSprite("src/sprites/Items/mushroom.png");
    }
}
