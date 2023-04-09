package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class Mushroom extends Consumable {
    public Mushroom(GamePanel gamePanel) {
        super(gamePanel);
        name = "Mushroom";
        description = "(Purple Mushroom) \n Restores 20 HP \n \"Very tasty!\"";
        battleDescription = "Restores 20 Health";
        getSprite("src/sprites/Items/mushroom.png");
        price = 3;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You ate the Mushroom! \n Recovered 20 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +20);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }


    }

    @Override
    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You ate the Mushroom! \n Recovered 20 health!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +20);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
    }
}
