package Items.equipment;

import Items.Equipment;
import main.GamePanel;

public class Armor extends Equipment {

    int defence;

    public Armor(GamePanel gamePanel) {
        super(gamePanel);
        name = "armor";
        description = "(Armor) \n +2 defence";
        getSprite("src/sprites/equipment/armor.png");
        defence = 2;
    }

    public int getDefence() {
        return defence;
    }
}
