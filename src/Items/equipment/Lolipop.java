package Items.equipment;

import main.GamePanel;

public class Lolipop extends Weapon{

    public Lolipop(GamePanel gamePanel) {
        super(gamePanel);
        name = "Lollipop";
        description = "(Lollipop) \n +1 attack \n It would take ages to\n eat this! ";
        getSprite("src/sprites/equipment/lolipop.png");
        attack = 1;
        price = 50;
    }


}
