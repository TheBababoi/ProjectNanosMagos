package main;

import java.awt.*;

public class EventHandler {

    private GamePanel gamePanel;

    private EventHitbox eventHitbox[][][];
    private int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventHitbox = new EventHitbox[gamePanel.getMaxMap()][gamePanel.getMaxWorldColumn()][gamePanel.getMaxWorldRow()];
        int collumn = 0, row =0 , map =0;
        while ((collumn< gamePanel.getMaxWorldColumn() && row< gamePanel.getMaxWorldRow() && map < gamePanel.getMaxMap())){
            eventHitbox[map][collumn][row] = new EventHitbox();
            eventHitbox[map][collumn][row].x = 40;
            eventHitbox[map][collumn][row].y = 40;
            eventHitbox[map][collumn][row].width = 20;
            eventHitbox[map][collumn][row].height = 20;
            eventHitbox[map][collumn][row].eventHitboxX = eventHitbox[map][collumn][row].x;
            eventHitbox[map][collumn][row].eventHitboxY = eventHitbox[map][collumn][row].y;
            collumn++;
            if (collumn == gamePanel.getMaxWorldColumn()) {
                collumn = 0;
                row++;
                if ((row == gamePanel.getMaxWorldRow())){
                    row = 0;
                    map++;
                }
            }

        }



    }

    public void checkEvent(){
        if(hitCheck(0,0, 0, "any")&&(!eventHitbox[gamePanel.getCurrentMap()][0][0].eventDone)){
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
        if(mapNumber == gamePanel.getCurrentMap()){
            gamePanel.getHero().hitbox.x = gamePanel.getHero().getWorldX() + gamePanel.getHero().hitbox.x;
            gamePanel.getHero().hitbox.y = gamePanel.getHero().getWorldY() + gamePanel.getHero().hitbox.y;
            eventHitbox[mapNumber][collumn][row].x = collumn* gamePanel.getSpriteSize() + eventHitbox[mapNumber][collumn][row].x;
            eventHitbox[mapNumber][collumn][row].y = row* gamePanel.getSpriteSize() + eventHitbox[mapNumber][collumn][row].y;

            if (gamePanel.getHero().hitbox.intersects(eventHitbox[mapNumber][collumn][row])){
                if(gamePanel.getHero().getDirection().contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {

                    hitCheck = true;
                }
            }
            // reset to default
            gamePanel.getHero().hitbox.x = gamePanel.getHero().getHitboxX();
            gamePanel.getHero().hitbox.y = gamePanel.getHero().getHitboxY();
            eventHitbox[mapNumber][collumn][row].x = eventHitbox[mapNumber][collumn][row].eventHitboxX;
            eventHitbox[mapNumber][collumn][row].y = eventHitbox[mapNumber][collumn][row].eventHitboxY;

        }

        return hitCheck;

    }

    public  void healSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.getKeyboardInputs().isEnterPressed()){
            gamePanel.setGameState(gameState);
            gamePanel.getUi().setCurrentDialogue("Restored to full Health");
            gamePanel.getHero().setHealth(gamePanel.getHero().getMaxHealth());
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
            eventHitbox[gamePanel.getMaxMap()][collumn][row].eventDone = true;

        }

    }

    public  void expSpot(int collumn , int row, GamePanel.Gamestate gameState){
        if(gamePanel.getKeyboardInputs().isEnterPressed()){
            gamePanel.setGameState(gameState);
            gamePanel.getUi().setCurrentDialogue("Souravlas Has granted you his wisdom - Gain 50 exp!");

            gamePanel.getHero().setExp(gamePanel.getHero().getExp() + 50);
            gamePanel.getHero().checkLevelUp();
            eventHitbox[gamePanel.getMaxMap()][collumn][row].eventDone = true;

        }

    }
    public  void teleporter(int mapNumber,int column , int row, GamePanel.Gamestate gameState){


            gamePanel.setGameState(gameState);
            tempMap = mapNumber;
            tempCol = column;
            tempRow = row;




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


}
