package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class Watermelon extends Consumable {
    public Watermelon(GamePanel gamePanel) {
        super(gamePanel);
        name = "Watermelon";
        description = "(Watermelon) \n Restores 20 HP \n \"Very tasty!\"";
        battleDescription = "Restores 20 Health";
        getSprite("src/sprites/Items/watermelon.png");
        price = 10;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You ate the Watermelon! \n Recovered 20 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +20);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }


    }

    @Override
    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You ate the Watermelon! \n Recovered 20 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +20);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
    }
}
