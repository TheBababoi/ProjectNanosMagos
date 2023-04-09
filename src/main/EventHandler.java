package main;

import java.awt.*;

public class EventHandler {

    private GamePanel gamePanel;

    private EventHitbox eventHitbox[][][];
    private int tempMap, tempCol, tempRow;

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
            healSpot(0,0, GamePanel.Gamestate.DIALOGUESTATE);
        }
//        if(hitCheck(0,2, 1, "any")&&(!eventHitbox[gamePanel.currentMap][2][1].eventDone)){
//            gamePanel.hero.friendOrFoe = true;
//            expSpot(2,1, GamePanel.Gamestate.DIALOGUESTATE);
//        }
        if(hitCheck(0,0, 0, "any")){
            teleporter(1,30,30,GamePanel.Gamestate.TRANSITION);
        }
        if(hitCheck(1,1, 2, "any")){
            teleporter(0,5,5,GamePanel.Gamestate.TRANSITION);
        }
    }

    public boolean hitCheck(int mapNumber,int collumn, int row, String requiredDirection ){

        boolean hitCheck = false;
        if(mapNumber == gamePanel.currentMap){
            gamePanel.hero.hitbox.x = gamePanel.hero.getWorldX() + gamePanel.hero.hitbox.x;
            gamePanel.hero.hitbox.y = gamePanel.hero.getWorldY() + gamePanel.hero.hitbox.y;
            eventHitbox[mapNumber][collumn][row].x = collumn*gamePanel.spriteSize + eventHitbox[mapNumber][collumn][row].x;
            eventHitbox[mapNumber][collumn][row].y = row*gamePanel.spriteSize + eventHitbox[mapNumber][collumn][row].y;

            if (gamePanel.hero.hitbox.intersects(eventHitbox[mapNumber][collumn][row])){
                if(gamePanel.hero.getDirection().contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {

                    hitCheck = true;
                }
            }
            // reset to default
            gamePanel.hero.hitbox.x = gamePanel.hero.getHitboxX();
            gamePanel.hero.hitbox.y = gamePanel.hero.getHitboxY();
            eventHitbox[mapNumber][collumn][row].x = eventHitbox[mapNumber][collumn][row].eventHitboxX;
            eventHitbox[mapNumber][collumn][row].y = eventHitbox[mapNumber][collumn][row].eventHitboxY;

        }

        return hitCheck;

    }

    public  void healSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.isEnterPressed()){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Restored to full Health";
            gamePanel.hero.setHealth(gamePanel.hero.getMaxHealth());
            gamePanel.hero.setMana(gamePanel.hero.getMaxMana());
            eventHitbox[gamePanel.maxMap][collumn][row].eventDone = true;

        }

    }

    public  void expSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.keyboardInputs.isEnterPressed()){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Souravlas Has granted you his wisdom - Gain 50 exp!";

            gamePanel.hero.setExp(gamePanel.hero.getExp() + 50);
            gamePanel.hero.checkLevelUp();
            eventHitbox[gamePanel.maxMap][collumn][row].eventDone = true;

        }

    }
    public  void teleporter(int mapNumber,int column , int row, GamePanel.Gamestate gameState){


            gamePanel.gameState = gameState;
            tempMap = mapNumber;
            tempCol = column;
            tempRow = row;




    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public EventHitbox[][][] getEventHitbox() {
        return eventHitbox;
    }

    public int getTempMap() {
        return tempMap;
    }

    public int getTempCol() {
        return tempCol;
    }

    public int getTempRow() {
        return tempRow;
    }


    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setEventHitbox(EventHitbox[][][] eventHitbox) {
        this.eventHitbox = eventHitbox;
    }

    public void setTempMap(int tempMap) {
        this.tempMap = tempMap;
    }

    public void setTempCol(int tempCol) {
        this.tempCol = tempCol;
    }

    public void setTempRow(int tempRow) {
        this.tempRow = tempRow;
    }
}
