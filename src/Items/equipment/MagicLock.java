package Items.equipment;

import main.GamePanel;

public class MagicLock extends Trinket{

    public MagicLock(GamePanel gamePanel) {
        super(gamePanel);
        name = "Magic Lock";
        description = "(Magic Lock) \n +4 defence \n\"  Emits a magic aura\n that protects the hero! \" ";
        getSprite("src/sprites/equipment/lock.png");
        defence = 4;
        price = 200;
    }


}
