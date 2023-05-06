package Items.equipment;

import main.GamePanel;

public class ShinyMushroom extends Gem{

    public ShinyMushroom(GamePanel gamePanel) {
        super(gamePanel);
        name = "Shiny Mushroom";
        description = "(Shiny Mushroom) \n +10 Max Mana\n \" So shinyyyy! \" ";
        getSprite("src/sprites/equipment/mushroom (3).png");
        mana = 10;
        price = 100;
    }


}
