package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class Mushroom extends Consumable {
    public Mushroom(GamePanel gamePanel) {
        super(gamePanel);
        name = "Mushroom";
        description = "(Purple Mushroom) \n Restores 20 HP \n \"Very tasty!\"";
        getSprite("src/sprites/Items/mushroom.png");
    }

    @Override
    public void overWorldUse() {
        gamePanel.hero.friendOrFoe = true;
        gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
        gamePanel.playSoundEffect(3);
        gamePanel.ui.currentDialogue = "You ate the Mushroom! \n Recovered 20 health!";
        gamePanel.hero.setHealth(gamePanel.hero.getHealth() +20);
        if (gamePanel.hero.getHealth()>=gamePanel.hero.getMaxHealth()){
            gamePanel.hero.setHealth(gamePanel.hero.maxHealth);
        }


    }
}
