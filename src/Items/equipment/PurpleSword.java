package Items.equipment;

import main.GamePanel;

public class PurpleSword extends Weapon{

    public PurpleSword(GamePanel gamePanel) {
        super(gamePanel);
        name = "sword";
        description = "(Purple Sword) \n +2 attack";
        getSprite("src/sprites/equipment/sword.png");
        attack = 2;
        price = 100;
    }


}
