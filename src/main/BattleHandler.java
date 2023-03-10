package main;

import java.awt.event.KeyEvent;

public class BattleHandler {
    GamePanel gamePanel;
    String battle = "Prepare for battle!";
    int monsterIndex;

    public BattleHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }



    public void startBattle(int index){
        monsterIndex = index;
        gamePanel.music.stop();
        gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
        gamePanel.ui.currentDialogue = battle;
        gamePanel.ui.drawDialogueScreen();

    }
}
