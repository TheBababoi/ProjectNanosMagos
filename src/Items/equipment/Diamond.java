package Items.equipment;

import main.GamePanel;

public class Diamond extends Gem{

    public Diamond(GamePanel gamePanel) {
        super(gamePanel);
        name = "Diamond";
        description = "(Diamond) \n +30 Max Mana \n \" Looks very expensive! \" ";
        getSprite("src/sprites/equipment/diamond.png");
        mana = 30;
        price = 400;
    }


}
