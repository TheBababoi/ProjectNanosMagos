package main;

import creature.Creature;
import creature.Hero;

public class CollisionCheck {
    GamePanel gamePanel;

    public CollisionCheck(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkBackgroundTile(Creature creature){

        //hitbox world coordinate calculations
        int creatureLeftX = creature.worldX +creature.hitbox.x;
        int creatureRightX = creature.worldX +creature.hitbox.x + creature.hitbox.width;
        int creatureUpY = creature.worldY + creature.hitbox.y;
        int creatureDownY = creature.worldY + creature.hitbox.y + creature.hitbox.height;

        //hitbox row and collumn calculations
        int creatureLeftCollumn = creatureLeftX/gamePanel.spriteSize;
        int creatureRightCollumn = creatureRightX/gamePanel.spriteSize;
        int creatureUpRow = creatureUpY/gamePanel.spriteSize;
        int creatureDownRow = creatureDownY/gamePanel.spriteSize;

        int sprite1,sprite2;

        if(creature.direction == "up"){
            creatureUpRow = (creatureUpY + creature.speed)/gamePanel.spriteSize; // calculating what sprites is the hero trying to step in
            sprite1 = gamePanel.backgroundTileManager.mapLayout[creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[creatureRightCollumn][creatureUpRow];//up and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision == true||gamePanel.backgroundTileManager.backgroundTile[sprite2].collision == true)//if either is a solid sprite turn the creature's collision on
            {
                creature.collision = true;
            }
        } else if (creature.direction=="down") {
            creatureDownRow = (creatureDownY + creature.speed)/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[creatureLeftCollumn][creatureDownRow]; //down and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[creatureRightCollumn][creatureDownRow];//down and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision == true||gamePanel.backgroundTileManager.backgroundTile[sprite2].collision == true)
            {
                creature.collision = true;
            }
        } else if (creature.direction=="left") {
            creatureLeftCollumn = (creatureLeftX + creature.speed)/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[creatureLeftCollumn][creatureDownRow];//down and left
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision == true||gamePanel.backgroundTileManager.backgroundTile[sprite2].collision == true)
            {
                creature.collision = true;
            }
        } else if (creature.direction=="right") {
            creatureRightCollumn = (creatureRightX + creature.speed)/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[creatureRightCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[creatureRightCollumn][creatureDownRow];//up and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision == true||gamePanel.backgroundTileManager.backgroundTile[sprite2].collision == true)
            {
                creature.collision = true;
            }
        }

    }

    public  int checkObject(Creature creature, boolean hero){
        int index=9;
        for (int i = 0; i <gamePanel.superObject.length; i++) {
            if(gamePanel.superObject[i] != null){
                //calculating creature's hitbox position
                creature.hitbox.x = creature.worldX + creature.hitbox.x;
                creature.hitbox.y = creature.worldY + creature.hitbox.y;
                //calculating object's hitbox position
                gamePanel.superObject[i].hitbox.x = gamePanel.superObject[i].worldX;
                gamePanel.superObject[i].hitbox.y = gamePanel.superObject[i].worldY;


                if(creature.direction == "up"){
                    creature.hitbox.y -= creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[i].hitbox)){
                        if(gamePanel.superObject[i].collision == true){
                            creature.collision = true;
                        }
                        if( hero == true){
                            index = i;
                        }
                    }
                } else if (creature.direction=="down") {
                    creature.hitbox.y += creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[i].hitbox)){
                        if(gamePanel.superObject[i].collision == true){
                            creature.collision = true;
                        }
                        if( hero == true){
                            index = i;
                        }

                    }
                } else if (creature.direction=="left") {
                    creature.hitbox.x -= creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[i].hitbox)){
                        if(gamePanel.superObject[i].collision == true){
                            creature.collision = true;
                        }
                        if( hero == true){
                            index = i;
                        }

                    }
                } else if (creature.direction=="right") {
                    creature.hitbox.x += creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[i].hitbox)){
                        if(gamePanel.superObject[i].collision == true){
                            creature.collision = true;
                        }
                        if( hero == true){
                            index = i;
                        }
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.hitboxX;
                creature.hitbox.y = creature.hitboxY;
                gamePanel.superObject[i].hitbox.x = gamePanel.superObject[i].hitboxX;
                gamePanel.superObject[i].hitbox.x = gamePanel.superObject[i].hitboxX;
            }
        }
        return index;
    }
}
