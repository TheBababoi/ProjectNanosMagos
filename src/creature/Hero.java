package creature;


import Items.Item;
import Items.consumables.Gold;
import Items.consumables.Key;
import Items.consumables.Mushroom;
import Items.equipment.Armor;
import Items.equipment.Weapon;

import main.GamePanel;
import main.KeyboardInputs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Hero extends Creature {

    final public int screenX;
    final public int screenY;
    KeyboardInputs keyboardInputs;
    public int hasKey = 0;
    public int level,maxHealth,health,maxMana,mana, nextLevelExp,exp,defence,strength,dexterity,gold;
    public Weapon currentWeapon;
    public Armor currentArmor;
    public boolean friendOrFoe;
    public String[] attackMove = new String[4];
    public int[] attackPower = new int[4];
    public int[] attackAccuracy = new int[4];
    public int[] attackSoundIndex = new int[4];
    public ArrayList<Item> inventory = new ArrayList<>();
    public int inventorySize = 20;


    public Hero(GamePanel gamePanel, KeyboardInputs keyboardInputs) {
        super(gamePanel);
        screenX = gamePanel.screenWidth/2 - gamePanel.spriteSize/2; // centering the "camera" to the player model
        screenY = gamePanel.screenHeight/2 - gamePanel.spriteSize/2;
        this.keyboardInputs = keyboardInputs;
        getSprites("src/sprites/hero/");
        hitbox = new Rectangle(12,24,56,56);
        hitboxX = 12;
        hitboxY = 24;
        worldX = gamePanel.spriteSize *15;
        worldY = gamePanel.spriteSize *4;

        direction = "down";



        setStats();
        heroMoves();
        setInventory();


    }

    private void setInventory() {

        inventory.add((currentWeapon));
        inventory.add(currentArmor);
        inventory.add((new Mushroom(gamePanel)));
        inventory.add(new Gold(gamePanel));
        inventory.add((new Mushroom(gamePanel)));
        inventory.add(new Gold(gamePanel));
        inventory.add(new Gold(gamePanel));
        inventory.add((new Mushroom(gamePanel)));
    }

    public void setStats() {
        level = 1;
        speed = 5;
        maxHealth = 30;
        health = maxHealth;
        maxMana = 30;
        mana = maxMana;
        strength = 10;
        defence = 1;
        dexterity = 8;
        exp = 0;
        nextLevelExp = 20;
        gold = 0;
        currentWeapon = new Weapon(gamePanel);
        currentArmor = new Armor(gamePanel);
        strength += currentWeapon.getAttack();
        defence += currentArmor.getDefence();


    }
    public void heroMoves() {
        attackMove[0] = "Fireball";
        attackPower[0] = 75;
        attackAccuracy[0] = 10;
        attackSoundIndex[0] = 8;
        attackMove[1] = "Flamestrike";
        attackPower[1] = 150;
        attackAccuracy[1] = 6;
        attackSoundIndex[1] = 5;
        attackAccuracy[2] = 7;
        attackMove[2] = "IceSpear";
        attackPower[2] = 100;
        attackSoundIndex[2] = 5;
        attackAccuracy[3] = 8;
        attackMove[3] = "Blizzard";
        attackPower[3] = 800;
        attackAccuracy[3] = 5;
        attackSoundIndex[3] = 5;

    }

    @Override
    public void refresh() {
        if (keyboardInputs.up || keyboardInputs.down || keyboardInputs.left || keyboardInputs.right){ //checking for keyboard inputs so the sprite wont change when character is standing still
            if(keyboardInputs.up){
                direction = "up";
            } else if (keyboardInputs.down) {
                direction = "down";
            } else if (keyboardInputs.left) {
                direction = "left";
            } else if (keyboardInputs.right) {
                direction = "right";
            }
            //background tile collision check
            collision = false;
            gamePanel.collisionCheck.checkBackgroundTile(this);

            //npc collision check
            int npcIndex = gamePanel.collisionCheck.checkCreature(this,gamePanel.npc);
            npcInteraction(npcIndex);

            //object collision check
            int objectIndex = gamePanel.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);

            //monster collision
            int monsterIndex = gamePanel.collisionCheck.checkCreature(this,gamePanel.enemy);
            enemyInteraction(monsterIndex);

            //event check
            gamePanel.eventHandler.checkEvent();

            gamePanel.keyboardInputs.enterPressed = false;

            //if !collision, hero will move
            if(!collision){
                if(direction=="up"){
                    worldY -= speed;
                } else if (direction=="down") {
                    worldY += speed;
                } else if (direction=="left") {
                    worldX -= speed;
                } else if (direction=="right") {
                    worldX += speed;
                }
            }


            spriteCounter++; // counter gets upped 60 times per second
            if(spriteCounter > 15){   //every 60/15 = 4 times a second
                if(spriteNumber == 1){ // forcing sprite to change 4 times a second
                    spriteNumber = 2;
                } else{
                    spriteNumber = 1;
                }
                spriteCounter = 0; // resetting counter
            }
        }
    }

    private void enemyInteraction(int index) {
        if (index != 666) {
                gamePanel.battleHandler.startBattle(index);
                friendOrFoe = false;
        }
    }

    public void npcInteraction(int index) {
        if (index != 666) {
            if (gamePanel.keyboardInputs.enterPressed) {
                gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
                gamePanel.npc[index].speak();
                friendOrFoe = true;
            }
        }

    }

    public void  pickUpObject(int index) {
        if (index !=666) { //if the hero does not touch any objects (any number above the object limit is fine
            String objectName = gamePanel.superObject[index].name;

                switch (objectName) {
                    case "Tampouris":
                        //gamePanel.ui.showMessage("Gave Tampouris a kiss for good luck!");
                       // gamePanel.playSoundEffect(4);
                        break;
                    case "Key":
                        gamePanel.playSoundEffect(1);
                        hasKey++;
                        gamePanel.ui.showMessage("Hero picked up a key!");
                        gamePanel.superObject[index] = null;
                        break;
                    case "Chest":
                        if (hasKey > 0) {
                            gamePanel.playSoundEffect(2);
                            hasKey--;
                            gamePanel.ui.showMessage("Hero unlocked the Chest!");
                            gamePanel.superObject[index] = null;
                        }else {
                            gamePanel.ui.showMessage("Hero needs a key!");
                        }
                        break;
                    case "Door":
                        if (hasKey > 0) {
                            gamePanel.playSoundEffect(3);
                            hasKey--;
                            gamePanel.ui.showMessage("Hero unlocked the Door!");
                            gamePanel.superObject[index] = null;
                        }else {
                            gamePanel.ui.showMessage("Hero needs a key!");
                        }
                        break;

                }
            }
        }


    public void draw(Graphics2D g2) {

        BufferedImage sprite = null;

        if(direction=="up"){
            if(spriteNumber == 1){
                sprite = up1;
            }
            if(spriteNumber == 2){
                sprite = up2;
            }

        } else if (direction=="down") {
            if(spriteNumber == 1){
                sprite = down1;
            }
            if(spriteNumber == 2){
                sprite = down2;
            }
        } else if (direction=="left") {
            if(spriteNumber == 1){
                sprite = left1;
            }
            if(spriteNumber == 2){
                sprite = left2;
            }
        } else if (direction=="right") {
            if(spriteNumber == 1){
                sprite = right1;
            }
            if(spriteNumber == 2){
                sprite = right2;
            }
        }
        g2.drawImage(sprite, screenX,screenY, gamePanel.spriteSize,gamePanel.spriteSize,null);
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public int getExp() {
        return exp;
    }



    public int getDefence() {
        return defence;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getGold() {
        return gold;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public Armor getCurrentArmor() {
        return currentArmor;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp){
            level++;
            nextLevelExp *= 3;
            maxHealth += 5;
            health = maxHealth;
            maxMana +=5;
            mana = maxMana;
            strength += 2;
            defence += 2;
            dexterity += 1;
            gamePanel.playSoundEffect(6);
            friendOrFoe = true;
            gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
            gamePanel.ui.currentDialogue = "Level Up! Your Stats Have Been Raised!";


        }
    }
}
