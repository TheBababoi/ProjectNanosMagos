package main;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;

    EventHitbox eventHitbox[][][];

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventHitbox = new EventHitbox[gamePanel.maxMap][gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        int collumn = 0, row =0 , map =0;
        while ((collumn< gamePanel.maxWorldColumn && row< gamePanel.maxWorldRow && map < gamePanel.maxMap )){
            eventHitbox[map][collumn][row] = new EventHitbox();
            eventHitbox[map][collumn][row].x = 40;
            eventHitbox[map][collumn][row].y = 40;
            eventHitbox[map][collumn][row].width = 20;
            eventHitbox[map][collumn][row].height = 20;
            eventHitbox[map][collumn][row].eventHitboxX = eventHitbox[map][collumn][row].x;
            eventHitbox[map][collumn][row].eventHitboxY = eventHitbox[map][collumn][row].y;
            collumn++;
            if (collumn == gamePanel.maxWorldColumn) {
                collumn = 0;
                row++;
                if ((row == gamePanel.maxWorldRow)){
                    row = 0;
                    map++;
                }
            }

        }



    }

    public void checkEvent(){
        if(hitCheck(0,0, 0, "any")&&(!eventHitbox[gamePanel.currentMap][0][0].eventDone)){
            gamePanel.hero.friendOrFoe = true;
            healSpot(0,0, GamePanel.Gamestate.DIALOGUESTATE);
        }
//        if(hitCheck(0,2, 1, "any")&&(!eventHitbox[gamePanel.currentMap][2][1].eventDone)){
//            gamePanel.hero.friendOrFoe = true;
//            expSpot(2,1, GamePanel.Gamestate.DIALOGUESTATE);
//        }
        if(hitCheck(0,1, 2, "any")){
            gamePanel.hero.friendOrFoe = true;
            teleporter(1,30,30,GamePanel.Gamestate.DIALOGUESTATE);
        }
    }

    public boolean hitCheck(int mapNumber,int collumn, int row, String requiredDirection ){

        boolean hitCheck = false;
        if(mapNumber == gamePanel.currentMap){
            gamePanel.hero.hitbox.x = gamePanel.hero.worldX + gamePanel.hero.hitbox.x;
            gamePanel.hero.hitbox.y = gamePanel.hero.worldY + gamePanel.hero.hitbox.y;
            eventHitbox[mapNumber][collumn][row].x = collumn*gamePanel.spriteSize + eventHitbox[mapNumber][collumn][row].x;
            eventHitbox[mapNumber][collumn][row].y = row*gamePanel.spriteSize + eventHitbox[mapNumber][collumn][row].y;

            if (gamePanel.hero.hitbox.intersects(eventHitbox[mapNumber][collumn][row])){
                if(gamePanel.hero.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
                    System.out.println("hope");
                    hitCheck = true;
                }
            }
            // reset to default
            gamePanel.hero.hitbox.x = gamePanel.hero.hitboxX;
            gamePanel.hero.hitbox.y = gamePanel.hero.hitboxY;
            eventHitbox[mapNumber][collumn][row].x = eventHitbox[mapNumber][collumn][row].eventHitboxX;
            eventHitbox[mapNumber][collumn][row].y = eventHitbox[mapNumber][collumn][row].eventHitboxY;

        }

        return hitCheck;

    }

    public  void healSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Restored to full Health";
            gamePanel.hero.health = gamePanel.hero.maxHealth;
            gamePanel.hero.mana = gamePanel.hero.maxMana;
            eventHitbox[gamePanel.maxMap][collumn][row].eventDone = true;

        }

    }

    public  void expSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Souravlas Has granted you his wisdom - Gain 50 exp!";

            gamePanel.hero.exp += 50;
            gamePanel.hero.checkLevelUp();
            eventHitbox[gamePanel.maxMap][collumn][row].eventDone = true;

        }

    }
    public  void teleporter(int mapNumber,int collumn , int row, GamePanel.Gamestate gameState){
       // if(gamePanel.keyboardInputs.enterPressed){
            System.out.println("ay yo");
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Teleported!";
            gamePanel.currentMap = mapNumber;
            gamePanel.hero.worldX = collumn*gamePanel.spriteSize;
            gamePanel.hero.worldY = row*gamePanel.spriteSize;


      //  }

    }


}
