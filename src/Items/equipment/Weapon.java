package Items.equipment;

import Items.Equipment;
import main.GamePanel;

public class Weapon extends Equipment {

    int attack;

    public Weapon(GamePanel gamePanel) {
        super(gamePanel);
        name = "sword";
        description = "(Purple Sword) \n +2 attack";
        getSprite("src/sprites/equipment/sword.png");
        attack = 2;



    }

    public int getAttack() {
        return attack;
    }

}
