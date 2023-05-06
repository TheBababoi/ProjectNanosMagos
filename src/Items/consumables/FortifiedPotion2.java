package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class FortifiedPotion2 extends Consumable {
    public FortifiedPotion2(GamePanel gamePanel) {
        super(gamePanel);
        name = "Or. Fort. Potion";
        description = "(Orange Fortified Potion) \n Restores 30 HP and 30 Mana \n \"Very tasty!\"";
        battleDescription = "Restores 30 Health and 30 Mana";
        getSprite("src/sprites/Items/fortified potion2.png");
        price = 60;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion \n Recovered 30 health and 30 mana!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +30);
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +30);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }


    }

    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion \n Recovered 30 health and 30 mana!");
        gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() +30);
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +30);
        if (gamePanel.getHero().getHealth()>= gamePanel.getHero().getMaxHealth()){
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
        }
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }
    }
}
