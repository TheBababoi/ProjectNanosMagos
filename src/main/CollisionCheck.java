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
        int creatureLeftCollumn = creatureLeftX/ gamePanel.getSpriteSize();
        int creatureRightCollumn = creatureRightX/ gamePanel.getSpriteSize();
        int creatureUpRow = creatureUpY/ gamePanel.getSpriteSize();
        int creatureDownRow = creatureDownY/ gamePanel.getSpriteSize();

        int sprite1,sprite2;

        if(creature.getDirection() == "up"){
            creatureUpRow = (creatureUpY + creature.getSpeed())/ gamePanel.getSpriteSize(); // calculating what sprites is the hero trying to step in
            sprite1 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureRightCollumn][creatureUpRow];//up and right
            if ( gamePanel.getBackgroundTileManager().backgroundTile[sprite1].getCollision() || gamePanel.getBackgroundTileManager().backgroundTile[sprite2].getCollision())//if either is a solid sprite turn the creature's collision on
            {
                creature.setCollision(true);
            }
        } else if (creature.getDirection() =="down") {
            creatureDownRow = (creatureDownY + creature.getSpeed())/ gamePanel.getSpriteSize();
            sprite1 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureLeftCollumn][creatureDownRow]; //down and left
            sprite2 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureRightCollumn][creatureDownRow];//down and right
            if ( gamePanel.getBackgroundTileManager().backgroundTile[sprite1].getCollision() || gamePanel.getBackgroundTileManager().backgroundTile[sprite2].getCollision())
            {
                creature.setCollision(true);
            }
        } else if (creature.getDirection() =="left") {
            creatureLeftCollumn = (creatureLeftX + creature.getSpeed())/ gamePanel.getSpriteSize();
            sprite1 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureLeftCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureLeftCollumn][creatureDownRow];//down and left
            if ( gamePanel.getBackgroundTileManager().backgroundTile[sprite1].getCollision() || gamePanel.getBackgroundTileManager().backgroundTile[sprite2].getCollision())
            {
                creature.setCollision(true);
            }
        } else if (creature.getDirection() =="right") {
            creatureRightCollumn = (creatureRightX + creature.getSpeed())/ gamePanel.getSpriteSize();
            sprite1 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureRightCollumn][creatureUpRow]; //up and left
            sprite2 = gamePanel.getBackgroundTileManager().mapLayout[gamePanel.getCurrentMap()][creatureRightCollumn][creatureDownRow];//up and right
            if ( gamePanel.getBackgroundTileManager().backgroundTile[sprite1].getCollision() || gamePanel.getBackgroundTileManager().backgroundTile[sprite2].getCollision())
            {
                creature.setCollision(true);
            }
        }

    }

    public  int checkObject(Creature creature, boolean hero){
        int index=9; //source of the bug this is why there must always be an object #9
        for (int i = 0; i < gamePanel.getSuperObject()[1].length; i++) {
            if(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i] != null){
                //calculating creature's hitbox position
                creature.hitbox.x = creature.getWorldX() + creature.hitbox.x;
                creature.hitbox.y = creature.getWorldY() + creature.hitbox.y;
                //calculating object's hitbox position
                gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox().x = gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getWorldX();
                gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox().y = gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getWorldY();


                if(creature.getDirection() == "up"){
                    creature.hitbox.y -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox())){
                        if(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }
                    }
                } else if (creature.getDirection() =="down") {
                    creature.hitbox.y += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox())){
                        if(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }

                    }
                } else if (creature.getDirection() =="left") {
                    creature.hitbox.x -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox())){
                        if(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].isCollision()){
                            creature.setCollision(true);
                        }
                        if(hero){
                            index = i;
                        }

                    }
                } else if (creature.getDirection() =="right") {
                    creature.hitbox.x += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox())){
                        if(gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].isCollision()){
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
                gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox().x = gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitboxX();
                gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitbox().y = gamePanel.getSuperObject()[gamePanel.getCurrentMap()][i].getHitboxY();
            }
        }
        return index;
    }
    //  creature to creature collision
    public int checkCreature(Creature creature, Creature[][] target){
        int index = 666;
        for (int i = 0; i <target[1].length; i++) {
            if(target[gamePanel.getCurrentMap()][i] != null){
                //calculating creature's hitbox position
                creature.hitbox.x = creature.getWorldX() + creature.hitbox.x;
                creature.hitbox.y = creature.getWorldY() + creature.hitbox.y;
                //calculating target's hitbox position
                target[gamePanel.getCurrentMap()][i].hitbox.x = target[gamePanel.getCurrentMap()][i].getWorldX();
                target[gamePanel.getCurrentMap()][i].hitbox.y = target[gamePanel.getCurrentMap()][i].getWorldY();


                if(creature.getDirection() == "up"){
                    creature.hitbox.y -= creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.getCurrentMap()][i].hitbox)){
                            creature.setCollision(true);
                            index = i;
                    }
                } else if (creature.getDirection() =="down") {
                    creature.hitbox.y += creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.getCurrentMap()][i].hitbox)){
                        creature.setCollision(true);
                        index = i;
                    }
                } else if (creature.getDirection() =="left") {
                    creature.hitbox.x -= creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.getCurrentMap()][i].hitbox)){
                        creature.setCollision(true);
                        index = i;
                    }
                } else if (creature.getDirection() =="right") {
                    creature.hitbox.x += creature.getSpeed();
                    if(creature.hitbox.intersects(target[gamePanel.getCurrentMap()][i].hitbox)){
                        creature.setCollision(true);
                        index = i;
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.getHitboxX();
                creature.hitbox.y = creature.getHitboxY();
                target[gamePanel.getCurrentMap()][i].hitbox.x = target[gamePanel.getCurrentMap()][i].getHitboxX();
                target[gamePanel.getCurrentMap()][i].hitbox.y = target[gamePanel.getCurrentMap()][i].getHitboxY();
            }
        }
        return index;

    }
    public void checkPlayer(Creature creature){


                //calculating creature's hitbox position
                creature.hitbox.x = creature.getWorldX() + creature.hitbox.x;
                creature.hitbox.y = creature.getWorldY() + creature.hitbox.y;
                //calculating target's hitbox position
                gamePanel.getHero().hitbox.x = gamePanel.getHero().getWorldX();
                gamePanel.getHero().hitbox.y = gamePanel.getHero().getWorldY();


                if(creature.getDirection() == "up"){
                    creature.hitbox.y -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getHero().hitbox)){
                        creature.setCollision(true);
                    }
                } else if (creature.getDirection() =="down") {
                    creature.hitbox.y += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getHero().hitbox)){
                        creature.setCollision(true);
                    }
                } else if (creature.getDirection() =="left") {
                    creature.hitbox.x -= creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getHero().hitbox)){
                        creature.setCollision(true);
                    }
                } else if (creature.getDirection() =="right") {
                    creature.hitbox.x += creature.getSpeed();
                    if(creature.hitbox.intersects(gamePanel.getHero().hitbox)){
                        creature.setCollision(true);
                    }
                }
                //resetting to default
                creature.hitbox.x = creature.getHitboxX();
                creature.hitbox.y = creature.getHitboxY();
                gamePanel.getHero().hitbox.x = gamePanel.getHero().getHitboxX();
                gamePanel.getHero().hitbox.y = gamePanel.getHero().getHitboxY();
            }
        }


