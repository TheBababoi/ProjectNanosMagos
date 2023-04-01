package Items;

import main.GamePanel;

public abstract class Consumable extends Item{
    protected String battleDescription;
    public Consumable(GamePanel gamePanel) {
        super(gamePanel);
    }

    public abstract void overWorldUse();
    public abstract void battleUse();

    public String getBattleDescription() {
        return battleDescription;
    }
}
