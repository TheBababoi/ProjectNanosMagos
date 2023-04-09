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
        price = 100;
    }

    @Override
    public void recalculateHeroStats(GamePanel gamePanel) {
        gamePanel.getHero().setDefence(gamePanel.getHero().getBaseDefence() + this.defence);
    }

    public int getDefence() {
        return defence;
    }
}
