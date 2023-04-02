package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class HealthPotion extends Consumable {
    public HealthPotion(GamePanel gamePanel) {
        super(gamePanel);
        name = "Health Potion";
        description = "(Health Potion) \n Restores 20 HP \n \"Very tasty!\"";
        battleDescription = "Restores 20 Health";
        getSprite("src/sprites/Items/mushroom.png");
        price = 3;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
        gamePanel.playSoundEffect(3);
        gamePanel.ui.currentDialogue = "You drank the potion \n Recovered 20 health!";
        gamePanel.hero.setHealth(gamePanel.hero.getHealth() +20);
        if (gamePanel.hero.getHealth()>=gamePanel.hero.getMaxHealth()){
            gamePanel.hero.setHealth(gamePanel.hero.maxHealth);
        }


    }

    public void battleUse() {
        gamePanel.ui.setCurrentBattleDialogue("You drank the potion \n Recovered 20 health!");
        gamePanel.hero.setHealth(gamePanel.hero.getHealth() +20);
        if (gamePanel.hero.getHealth()>=gamePanel.hero.getMaxHealth()){
            gamePanel.hero.setHealth(gamePanel.hero.maxHealth);
        }
    }
}
