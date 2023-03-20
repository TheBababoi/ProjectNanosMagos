package main;

import creature.Creature;
import creature.Hero;

import javax.swing.text.html.parser.Entity;

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
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureUpRow];//up and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision || gamePanel.backgroundTileManager.backgroundTile[sprite2].collision)//if either is a solid sprite turn the creature's collision on
            {
                creature.collision = true;
            }
        } else if (creature.direction=="down") {
            creatureDownRow = (creatureDownY + creature.speed)/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureDownRow]; //down and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureDownRow];//down and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision || gamePanel.backgroundTileManager.backgroundTile[sprite2].collision)
            {
                creature.collision = true;
            }
        } else if (creature.direction=="left") {
            creatureLeftCollumn = (creatureLeftX + creature.speed)/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureDownRow];//down and left
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision || gamePanel.backgroundTileManager.backgroundTile[sprite2].collision)
            {
                creature.collision = true;
            }
        } else if (creature.direction=="right") {
            creatureRightCollumn = (creatureRightX + creature.speed)/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureDownRow];//up and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].collision || gamePanel.backgroundTileManager.backgroundTile[sprite2].collision)
            {
                creature.collision = true;
            }
        }

    }

    public  int checkObject(Creature creature, boolean hero){
        int index=9; //source of the bug this is why there must always be an object #9
        for (int i = 0; i < gamePanel.superObject[1].length; i++) {
            if(gamePanel.superObject[gamePanel.currentMap][i] != null){
                //calculating creature's hitbox position
                creature.hitbox.x = creature.worldX + creature.hitbox.x;
                creature.hitbox.y = creature.worldY + creature.hitbox.y;
                //calculating object's hitbox position
                gamePanel.superObject[gamePanel.currentMap][i].hitbox.x = gamePanel.superObject[gamePanel.currentMap][i].worldX;
                gamePanel.superObject[gamePanel.currentMap][i].hitbox.y = gamePanel.superObject[gamePanel.currentMap][i].worldY;


                if(creature.direction == "up"){
                    creature.hitbox.y -= creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].hitbox)){
                        if(gamePanel.superObject[gamePanel.currentMap][i].collision){
                            creature.collision = true;
                        }
                        if(hero){
                            index = i;
                        }
                    }
                } else if (creature.direction=="down") {
                    creature.hitbox.y += creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].hitbox)){
                        if(gamePanel.superObject[gamePanel.currentMap][i].collision){
                            creature.collision = true;
                        }
                        if(hero){
                            index = i;
                        }

                    }
                } else if (creature.direction=="left") {
                    creature.hitbox.x -= creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].hitbox)){
                        if(gamePanel.superObject[gamePanel.currentMap][i].collision){
                            creature.collision = true;
                        }
                        if(hero){
                            index = i;
                        }

                    }
                } else if (creature.direction=="right") {
                    creature.hitbox.x += creature.speed;
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].hitbox)){
                        if(gamePanel.superObject[gamePanel.currentMap][i].collision){
                            creature.collision = true;
                        }
                        if(hero){
                            index = i;
                        }
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.hitboxX;
                creature.hitbox.y = creature.hitboxY;
                gamePanel.superObject[gamePanel.currentMap][i].hitbox.x = gamePanel.superObject[gamePanel.currentMap][i].hitboxX;
                gamePanel.superObject[gamePanel.currentMap][i].hitbox.y = gamePanel.superObject[gamePanel.currentMap][i].hitboxY;
            }
        }
        return index;
    }
    //  creature to creature collision
    public int checkCreature(Creature creature, Creature[][] target){
        int index = 666;
        for (int i = 0; i <target[1].length; i++) {
            if(target[gamePanel.currentMap][i] != null){
                //calculating creature's hitbox position
                creature.hitbox.x = creature.worldX + creature.hitbox.x;
                creature.hitbox.y = creature.worldY + creature.hitbox.y;
                //calculating target's hitbox position
                target[gamePanel.currentMap][i].hitbox.x = target[gamePanel.currentMap][i].worldX;
                target[gamePanel.currentMap][i].hitbox.y = target[gamePanel.currentMap][i].worldY;


                if(creature.direction == "up"){
                    creature.hitbox.y -= creature.speed;
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                            creature.collision = true;
                            index = i;
                    }
                } else if (creature.direction=="down") {
                    creature.hitbox.y += creature.speed;
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                        creature.collision = true;
                        index = i;
                    }
                } else if (creature.direction=="left") {
                    creature.hitbox.x -= creature.speed;
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                        creature.collision = true;
                        index = i;
                    }
                } else if (creature.direction=="right") {
                    creature.hitbox.x += creature.speed;
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                        creature.collision = true;
                        index = i;
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.hitboxX;
                creature.hitbox.y = creature.hitboxY;
                target[gamePanel.currentMap][i].hitbox.x = target[gamePanel.currentMap][i].hitboxX;
                target[gamePanel.currentMap][i].hitbox.y = target[gamePanel.currentMap][i].hitboxY;
            }
        }
        return index;

    }
    public void checkPlayer(Creature creature){


                //calculating creature's hitbox position
                creature.hitbox.x = creature.worldX + creature.hitbox.x;
                creature.hitbox.y = creature.worldY + creature.hitbox.y;
                //calculating target's hitbox position
                gamePanel.hero.hitbox.x = gamePanel.hero.worldX;
                gamePanel.hero.hitbox.y = gamePanel.hero.worldY;


                if(creature.direction == "up"){
                    creature.hitbox.y -= creature.speed;
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.collision = true;
                    }
                } else if (creature.direction=="down") {
                    creature.hitbox.y += creature.speed;
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.collision = true;
                    }
                } else if (creature.direction=="left") {
                    creature.hitbox.x -= creature.speed;
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.collision = true;
                    }
                } else if (creature.direction=="right") {
                    creature.hitbox.x += creature.speed;
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.collision = true;
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.hitboxX;
                creature.hitbox.y = creature.hitboxY;
                gamePanel.hero.hitbox.x = gamePanel.hero.hitboxX;
                gamePanel.hero.hitbox.y = gamePanel.hero.hitboxY;
            }
        }


