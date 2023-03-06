package creature;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;



public abstract class NPC extends Creature {
    int dialogueIndex;

    String dialogues[] = new String[20];


    public NPC(GamePanel gamePanel) {
        super(gamePanel);
        speed = 4;
        direction = "down";
    }

    public abstract void setDialogue();

    public void speak(){
        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
    }

    public  void switchDirection(){
        switch (gamePanel.hero.direction){
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

}
