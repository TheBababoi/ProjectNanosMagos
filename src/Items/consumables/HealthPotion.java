package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class HealthPotion extends Consumable {
    public HealthPotion(GamePanel gamePanel) {
        super(gamePanel);
        name = "Health Potion";
        description = "(Health Potion) \n Restores 30 HP \n \"Very tasty!\"";
        battleDescription = "Restores 30 Health";
        getSprite("src/sprites/Items/healthpotion.png");
        price = 30;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion \n Recovered 30 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +30);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }


    }

    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion \n Recovered 30 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +30);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
    }
}
