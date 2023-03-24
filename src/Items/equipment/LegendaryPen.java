package Items.equipment;

import main.GamePanel;

public class LegendaryPen extends Weapon {
    public LegendaryPen(GamePanel gamePanel) {
        super(gamePanel);
        name = "Legendary Pen";
        description = "(Legendary Pen) \n +4 attack \n  \"This pen was used to pass \n Roumelioti's Operating Systems\"  ";
        getSprite("src/sprites/equipment/legendaryPen.png");
        attack = 4;
        price = 200;
    }


}
