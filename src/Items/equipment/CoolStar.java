package Items.equipment;

import main.GamePanel;

public class CoolStar extends Gem{

    public CoolStar(GamePanel gamePanel) {
        super(gamePanel);
        name = "Cool Star";
        description = "(Cool Star) \n +20 Max Mana\n \"  Looks very cool! \" ";
        getSprite("src/sprites/equipment/star.png");
        mana = 20;
        price = 200;
    }


}
