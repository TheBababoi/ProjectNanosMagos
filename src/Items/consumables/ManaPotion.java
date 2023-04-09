package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class ManaPotion extends Consumable {
    public ManaPotion(GamePanel gamePanel) {
        super(gamePanel);
        name = "Mana Potion";
        description = "(Mana Potion) \n Restores 20 Mana \n \"Very tasty!\"";
        battleDescription = "Restores 20 Mana";
        getSprite("src/sprites/Items/mushroom.png");
        price = 3;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion! \n Recovered 20 mana!");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +20);
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }


    }

    @Override
    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion! \n Recovered 20 mana!");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +20);
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }
    }
}
