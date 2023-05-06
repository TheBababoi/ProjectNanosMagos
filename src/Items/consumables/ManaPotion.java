package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class ManaPotion extends Consumable {
    public ManaPotion(GamePanel gamePanel) {
        super(gamePanel);
        name = "Mana Potion";
        description = "(Mana Potion) \n Restores 30 Mana \n \"Very tasty!\"";
        battleDescription = "Restores 30 Mana";
        getSprite("src/sprites/Items/manapotion.png");
        price = 30;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion! \n Recovered 30 mana!");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +30);
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }


    }

    @Override
    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion! \n Recovered 30 mana!");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +30);
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }
    }
}
