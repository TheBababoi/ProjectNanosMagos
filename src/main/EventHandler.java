package main;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;

    EventHitbox eventHitbox[][];

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventHitbox = new EventHitbox[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        int collumn = 0, row =0;
        while ((collumn< gamePanel.maxWorldColumn && row< gamePanel.maxWorldRow)){
            eventHitbox[collumn][row] = new EventHitbox();
            eventHitbox[collumn][row].x = 40;
            eventHitbox[collumn][row].y = 40;
            eventHitbox[collumn][row].width = 20;
            eventHitbox[collumn][row].height = 20;
            eventHitbox[collumn][row].eventHitboxX = eventHitbox[collumn][row].x;
            eventHitbox[collumn][row].eventHitboxY = eventHitbox[collumn][row].y;
            collumn++;
            if (collumn == gamePanel.maxWorldColumn) {
                collumn = 0;
                row++;
            }
        }



    }

    public void checkEvent(){
        if(hitCheck(0, 0, "any")&&(!eventHitbox[0][0].eventDone)){
            gamePanel.hero.friendOrFoe = true;
            healSpot(0,0, GamePanel.Gamestate.DIALOGUESTATE);
        }
        if(hitCheck(2, 1, "any")&&(!eventHitbox[2][1].eventDone)){
            gamePanel.hero.friendOrFoe = true;
            expSpot(2,1, GamePanel.Gamestate.DIALOGUESTATE);
        }
        if(hitCheck(3, 1, "any")){
            gamePanel.hero.friendOrFoe = true;
           teleporter(3,1,GamePanel.Gamestate.DIALOGUESTATE);
        }
    }

    public boolean hitCheck(int collumn, int row, String requiredDirection ){

        boolean hitCheck = false;
        gamePanel.hero.hitbox.x = gamePanel.hero.worldX + gamePanel.hero.hitbox.x;
        gamePanel.hero.hitbox.y = gamePanel.hero.worldY + gamePanel.hero.hitbox.y;
        eventHitbox[collumn][row].x = collumn*gamePanel.spriteSize + eventHitbox[collumn][row].x;
        eventHitbox[collumn][row].y = row*gamePanel.spriteSize + eventHitbox[collumn][row].y;

        if (gamePanel.hero.hitbox.intersects(eventHitbox[collumn][row])){
            if(gamePanel.hero.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
                System.out.println("hope");
                hitCheck = true;
            }
        }
        // reset to default
        gamePanel.hero.hitbox.x = gamePanel.hero.hitboxX;
        gamePanel.hero.hitbox.y = gamePanel.hero.hitboxY;
        eventHitbox[collumn][row].x = eventHitbox[collumn][row].eventHitboxX;
        eventHitbox[collumn][row].y = eventHitbox[collumn][row].eventHitboxY;

        return hitCheck;

    }

    public  void healSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Restored to full Health";
            gamePanel.hero.health = gamePanel.hero.maxHealth;
            gamePanel.hero.mana = gamePanel.hero.maxMana;
            eventHitbox[collumn][row].eventDone = true;

        }

    }

    public  void expSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Souravlas Has granted you his wisdom - Gain 50 exp!";

            gamePanel.hero.exp += 50;
            gamePanel.hero.checkLevelUp();
            eventHitbox[collumn][row].eventDone = true;

        }

    }
    public  void teleporter(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Teleported!";
            gamePanel.hero.worldX = 5*gamePanel.spriteSize;
            gamePanel.hero.worldY = 5*gamePanel.spriteSize;


        }

    }


}
