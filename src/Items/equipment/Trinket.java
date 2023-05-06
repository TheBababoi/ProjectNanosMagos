package Items.equipment;

import Items.Equipment;
import main.GamePanel;

public abstract class Trinket extends Equipment {


    int defence;

    public Trinket(GamePanel gamePanel) {
        super(gamePanel);




    }

    @Override
    public void recalculateHeroStats(GamePanel gamePanel) {
        gamePanel.getHero().setDefence(gamePanel.getHero().getBaseDefence() + this.defence + gamePanel.getHero().getCurrentArmor().getDefence());
    }



    public int getDefence() {
        return defence;
    }
}
