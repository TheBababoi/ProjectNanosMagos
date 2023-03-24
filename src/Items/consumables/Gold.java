package Items.consumables;

import Items.Item;
import main.GamePanel;

public class Gold extends Item {
    public Gold(GamePanel gamePanel) {
        super(gamePanel);
        name = "Purple Gold Coins";
        description = "(Purple Gold Coins) \n Currency";
        getSprite("src/sprites/Items/gold.png");
        stackable = true;
    }
}
