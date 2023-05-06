package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class FortifiedPotion extends Consumable {
    public FortifiedPotion(GamePanel gamePanel) {
        super(gamePanel);
        name = "Fort. Potion";
        description = "(Fortified Potion) \n Restores 15 HP and 15 Mana \n \"Very tasty!\"";
        battleDescription = "Restores 15 Health and 15 Mana";
        getSprite("src/sprites/Items/fortifiedpotion.png");
        price = 30;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion \n Recovered 15 health and 15 mana!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +15);
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +15);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }


    }

    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion \n Recovered 15 health and 15 mana!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +15);
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +15);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }
    }
}
