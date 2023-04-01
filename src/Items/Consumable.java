package Items;

import main.GamePanel;

public abstract class Consumable extends Item{
    public Consumable(GamePanel gamePanel) {
        super(gamePanel);
    }

    public abstract void overWorldUse();
    public abstract void battleUse();


}
