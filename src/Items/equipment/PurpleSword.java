package Items.equipment;

import main.GamePanel;

public class PurpleSword extends Weapon{

    public PurpleSword(GamePanel gamePanel) {
        super(gamePanel);
        name = "sword";
        description = "(Purple Sword) \n +4 attack\n \"It's so purple!\"";
        getSprite("src/sprites/equipment/sword.png");
        attack = 4;
        price = 100;
    }


}
