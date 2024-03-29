package Items.equipment;

import Items.Equipment;
import main.GamePanel;

public abstract class Weapon extends Equipment {

    int attack;

    public Weapon(GamePanel gamePanel) {
        super(gamePanel);




    }

    @Override
    public void recalculateHeroStats(GamePanel gamePanel) {
        gamePanel.getHero().setStrength(gamePanel.getHero().getBaseStrength() + this.attack);
    }

    public int getAttack() {
        return attack;
    }

}
