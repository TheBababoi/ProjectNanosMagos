package main;

import creature.Creature;

public class CollisionCheck {
    GamePanel gamePanel;

    public CollisionCheck(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkSprite(Creature creature){

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
}
