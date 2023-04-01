package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class ManaPotion extends Consumable {
    public ManaPotion(GamePanel gamePanel) {
        super(gamePanel);
        name = "mana Potion";
        description = "(Mana Potion) \n Restores 20 Mana \n \"Very tasty!\"";
        battleDescription = "Restores 20 Mana";
        getSprite("src/sprites/Items/mushroom.png");
        price = 3;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
        gamePanel.playSoundEffect(3);
        gamePanel.ui.currentDialogue = "You drank the potion! \n Recovered 20 mana!";
        gamePanel.hero.setMana(gamePanel.hero.getMana() +20);
        if (gamePanel.hero.getMana()>=gamePanel.hero.getMaxMana()){
            gamePanel.hero.setMana(gamePanel.hero.maxMana);
        }


    }

    @Override
    public void battleUse() {
        gamePanel.ui.setCurrentBattleDialogue("You drank the potion! \n Recovered 20 mana!");
        gamePanel.hero.setMana(gamePanel.hero.getMana() +20);
        if (gamePanel.hero.getMana()>=gamePanel.hero.getMaxMana()){
            gamePanel.hero.setMana(gamePanel.hero.maxMana);
        }
    }
}
