package Items.equipment;

import main.GamePanel;

public class MagicKey extends Trinket{

    public MagicKey(GamePanel gamePanel) {
        super(gamePanel);
        name = "Magic Key";
        description = "(Magic Key) \n +2 defence \n\"  Emits a magic aura\n that protects the hero! \" ";
        getSprite("src/sprites/equipment/key.png");
        defence = 2;
        price = 100;
    }


}
