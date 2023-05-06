package Items.equipment;

import Items.Equipment;
import main.GamePanel;

public abstract class Gem extends Equipment {

    int mana;

    public Gem(GamePanel gamePanel) {
        super(gamePanel);




    }

    @Override
    public void recalculateHeroStats(GamePanel gamePanel) {
        gamePanel.getHero().setMaxMana(gamePanel.getHero().getBaseMaxMana() + this.mana);
    }

    public int getMana() {
        return mana;
    }

}
