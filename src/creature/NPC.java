package creature;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;



public abstract class NPC extends Creature {

    private int dialogueIndex = 0;
    private int dialogueSet = -1;
    private boolean encounteredNPC = false;
    protected String[][] dialogues = new String[20][20];


    public NPC(GamePanel gamePanel) {
        super(gamePanel);
        speed = 4;
        direction = "down";
    }

    public abstract void setDialogue();

    public void speak(){
      faceHero();
      startDialogue(this,dialogueSet);
      dialogueSet++;
      if (dialogues[dialogueSet][0] == null){
          dialogueSet = 0;
      }

    }
    public void startDialogue(NPC npc, int setNumber){
        setEncounteredNPC(true);
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.getUi().setNpc(npc);
        dialogueSet = setNumber;
    }



    public  void faceHero(){
        switch (gamePanel.getHero().direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
        }
    }

    public String[][] getDialogues() {
        return dialogues;
    }

    public int getDialogueIndex() {
        return dialogueIndex;
    }

    public int getDialogueSet() {
        return dialogueSet;
    }

    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }

    public void increaseDialogueIndex() {
        this.dialogueIndex++;
    }


    public void setEncounteredNPC(boolean encounteredNPC) {
        this.encounteredNPC = encounteredNPC;
    }

}
