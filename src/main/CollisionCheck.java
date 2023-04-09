package main;

import creature.Creature;
import creature.Hero;

import javax.swing.text.html.parser.Entity;

public class CollisionCheck {
    private GamePanel gamePanel;

    public CollisionCheck(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkBackgroundTile(Creature creature){

        //hitbox world coordinate calculations
        int creatureLeftX = creature.getWorldX() +creature.hitbox.x;
        int creatureRightX = creature.getWorldX() +creature.hitbox.x + creature.hitbox.width;
        int creatureUpY = creature.getWorldY() + creature.hitbox.y;
        int creatureDownY = creature.getWorldY() + creature.hitbox.y + creature.hitbox.height;

        //hitbox row and collumn calculations
        int creatureLeftCollumn = creatureLeftX/gamePanel.spriteSize;
        int creatureRightCollumn = creatureRightX/gamePanel.spriteSize;
        int creatureUpRow = creatureUpY/gamePanel.spriteSize;
        int creatureDownRow = creatureDownY/gamePanel.spriteSize;

        int sprite1,sprite2;

        if(creature.getDirection() == "up"){
            creatureUpRow = (creatureUpY + creature.getSpeed())/gamePanel.spriteSize; // calculating what sprites is the hero trying to step in
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureUpRow];//up and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].getCollision() || gamePanel.backgroundTileManager.backgroundTile[sprite2].getCollision())//if either is a solid sprite turn the creature's collision on
            {
                creature.setCollision(true);
            }
        } else if (creature.getDirection() =="down") {
            creatureDownRow = (creatureDownY + creature.getSpeed())/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureDownRow]; //down and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureDownRow];//down and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].getCollision() || gamePanel.backgroundTileManager.backgroundTile[sprite2].getCollision())
            {
                creature.setCollision(true);
            }
        } else if (creature.getDirection() =="left") {
            creatureLeftCollumn = (creatureLeftX + creature.getSpeed())/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureLeftCollumn][creatureDownRow];//down and left
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].getCollision() || gamePanel.backgroundTileManager.backgroundTile[sprite2].getCollision())
            {
                creature.setCollision(true);
            }
        } else if (creature.getDirection() =="right") {
            creatureRightCollumn = (creatureRightX + creature.getSpeed())/gamePanel.spriteSize;
            sprite1 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.backgroundTileManager.mapLayout[gamePanel.currentMap][creatureRightCollumn][creatureDownRow];//up and right
            if ( gamePanel.backgroundTileManager.backgroundTile[sprite1].getCollision() || gamePanel.backgroundTileManager.backgroundTile[sprite2].getCollision())
            {
                creature.setCollision(true);
            }
        }

    }

    public  int checkObject(Creature creature, boolean hero){
        int index=9; //source of the bug this is why there must always be an object #9
        for (int i = 0; i < gamePanel.superObject[1].length; i++) {
            if(gamePanel.superObject[gamePanel.currentMap][i] != null){
                //calculating creature's hitbox position
                creature.hitbox.x = creature.getWorldX() + creature.hitbox.x;
                creature.hitbox.y = creature.getWorldY() + creature.hitbox.y;
                //calculating object's hitbox position
                gamePanel.superObject[gamePanel.currentMap][i].getHitbox().x = gamePanel.superObject[gamePanel.currentMap][i].getWorldX();
                gamePanel.superObject[gamePanel.currentMap][i].getHitbox().y = gamePanel.superObject[gamePanel.currentMap][i].getWorldY();


                if(creature.getDirection() == "up"){
                    creature.hitbox.y -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].getHitbox())){
                        if(gamePanel.superObject[gamePanel.currentMap][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }
                    }
                } else if (creature.getDirection() =="down") {
                    creature.hitbox.y += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].getHitbox())){
                        if(gamePanel.superObject[gamePanel.currentMap][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }

                    }
                } else if (creature.getDirection() =="left") {
                    creature.hitbox.x -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].getHitbox())){
                        if(gamePanel.superObject[gamePanel.currentMap][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }

                    }
                } else if (creature.getDirection() =="right") {
                    creature.hitbox.x += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.superObject[gamePanel.currentMap][i].getHitbox())){
                        if(gamePanel.superObject[gamePanel.currentMap][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.getHitboxX();
                creature.hitbox.y = creature.getHitboxY();
                gamePanel.superObject[gamePanel.currentMap][i].getHitbox().x = gamePanel.superObject[gamePanel.currentMap][i].getHitboxX();
                gamePanel.superObject[gamePanel.currentMap][i].getHitbox().y = gamePanel.superObject[gamePanel.currentMap][i].getHitboxY();
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
                creature.hitbox.x = creature.getWorldX() + creature.hitbox.x;
                creature.hitbox.y = creature.getWorldY() + creature.hitbox.y;
                //calculating target's hitbox position
                target[gamePanel.currentMap][i].hitbox.x = target[gamePanel.currentMap][i].getWorldX();
                target[gamePanel.currentMap][i].hitbox.y = target[gamePanel.currentMap][i].getWorldY();


                if(creature.getDirection() == "up"){
                    creature.hitbox.y -= creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                            creature.setCollision(true);
                            index = i;
                    }
                } else if (creature.getDirection() =="down") {
                    creature.hitbox.y += creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                        creature.setCollision(true);
                        index = i;
                    }
                } else if (creature.getDirection() =="left") {
                    creature.hitbox.x -= creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                        creature.setCollision(true);
                        index = i;
                    }
                } else if (creature.getDirection() =="right") {
                    creature.hitbox.x += creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.currentMap][i].hitbox)){
                        creature.setCollision(true);
                        index = i;
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.getHitboxX();
                creature.hitbox.y = creature.getHitboxY();
                target[gamePanel.currentMap][i].hitbox.x = target[gamePanel.currentMap][i].getHitboxX();
                target[gamePanel.currentMap][i].hitbox.y = target[gamePanel.currentMap][i].getHitboxY();
            }
        }
        return index;

    }
    public void checkPlayer(Creature creature){


                //calculating creature's hitbox position
                creature.hitbox.x = creature.getWorldX() + creature.hitbox.x;
                creature.hitbox.y = creature.getWorldY() + creature.hitbox.y;
                //calculating target's hitbox position
                gamePanel.hero.hitbox.x = gamePanel.hero.getWorldX();
                gamePanel.hero.hitbox.y = gamePanel.hero.getWorldY();


                if(creature.getDirection() == "up"){
                    creature.hitbox.y -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.setCollision(true);
                    }
                } else if (creature.getDirection() =="down") {
                    creature.hitbox.y += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.setCollision(true);
                    }
                } else if (creature.getDirection() =="left") {
                    creature.hitbox.x -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.setCollision(true);
                    }
                } else if (creature.getDirection() =="right") {
                    creature.hitbox.x += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.hero.hitbox)){
                        creature.setCollision(true);
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.getHitboxX();
                creature.hitbox.y = creature.getHitboxY();
                gamePanel.hero.hitbox.x = gamePanel.hero.getHitboxX();
                gamePanel.hero.hitbox.y = gamePanel.hero.getHitboxY();
            }
        }


