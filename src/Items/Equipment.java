package Items;

import main.GamePanel;

public abstract class Equipment extends Item {
    public Equipment(GamePanel gamePanel) {
        super(gamePanel);
    }
    public abstract void recalculateHeroStats(GamePanel gamePanel);
}
