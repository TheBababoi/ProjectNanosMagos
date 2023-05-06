package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class HealthPotion2 extends Consumable {
    public HealthPotion2(GamePanel gamePanel) {
        super(gamePanel);
        name = "Pink Health Potion";
        description = "(Pink Health Potion) \n Restores 60 HP \n \"Very tasty!\"";
        battleDescription = "Restores 60 Health";
        getSprite("src/sprites/Items/healthpotion2.png");
        price = 60;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion \n Recovered 60 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +60);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }


    }

    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion \n Recovered 60 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +60);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
    }
}
