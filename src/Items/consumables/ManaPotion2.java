package Items.consumables;

import Items.Consumable;

import main.GamePanel;

public class ManaPotion2 extends Consumable {
    public ManaPotion2(GamePanel gamePanel) {
        super(gamePanel);
        name = "Yel. Mana Potion";
        description = "(Yellow Mana Potion) \n Restores 60 Mana \n \"Very tasty!\"";
        battleDescription = "Restores 60 Mana";
        getSprite("src/sprites/Items/manapotion2.png");
        price = 60;
        stackable = true;
    }

    @Override
    public void overWorldUse() {
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.playSoundEffect(3);
        gamePanel.getUi().setCurrentDialogue("You drank the potion! \n Recovered 60 mana!");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +60);
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }


    }

    @Override
    public void battleUse() {
        gamePanel.getUi().setCurrentBattleDialogue("You drank the potion! \n Recovered 60 mana!");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() +60);
        if (gamePanel.getHero().getMana()>= gamePanel.getHero().getMaxMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }
    }
}
