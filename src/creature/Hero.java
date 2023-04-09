package creature;


import Items.Consumable;
import Items.Item;
import Items.consumables.*;
import Items.equipment.Armor;
import Items.equipment.LegendaryPen;
import Items.equipment.PurpleSword;

import Items.equipment.Weapon;

import main.GamePanel;
import main.KeyboardInputs;
import object.Chest;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Hero extends Creature {

    final private int screenX;
    final private int screenY;
    private KeyboardInputs keyboardInputs;
    private int level,maxHealth,health,maxMana,mana, nextLevelExp,exp,baseDefence,defence,baseStrength,strength,dexterity,gold;
    private Weapon currentWeapon;
    private Armor currentArmor;
    private boolean encounteredEnemy = false;
    private boolean encounteredNPC = false;
    private String[] attackMove = new String[12];
    private int[] attackPower = new int[12];
    private int[] attackAccuracy = new int[12];
    private int[] attackSoundIndex = new int[12];
    private int[] attackCost = new int[12];
    private ArrayList<Item> inventory = new ArrayList<>();
    private int inventorySize = 25;
    private Chest currentChest;


    public Hero(GamePanel gamePanel, KeyboardInputs keyboardInputs) {
        super(gamePanel);
        screenX = gamePanel.getScreenWidth() /2 - gamePanel.getSpriteSize() /2; // centering the "camera" to the player model
        screenY = gamePanel.getScreenHeight() /2 - gamePanel.getSpriteSize() /2;
        this.keyboardInputs = keyboardInputs;
        getSprites("src/sprites/hero/");
        hitbox = new Rectangle(12,24,56,56);
        hitboxX = 12;
        hitboxY = 24;
        worldX = gamePanel.getSpriteSize() *1;
        worldY = gamePanel.getSpriteSize() *1;

        direction = "down";



        setStats();
        heroMoves();
        setInventory();


    }

    public void setDefault(){
        worldX = gamePanel.getSpriteSize() *1;
        worldY = gamePanel.getSpriteSize() *1;

        direction = "down";
        setStats();
        heroMoves();
        setInventory();

    }

    public boolean lootEnemyDrop(int index){

        if (inventory.size() != inventorySize){
            Random random = new Random();
            int roll = random.nextInt(10) + 1 + gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].dropChance;
            if (roll >= 10&&canObtainItem(gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].drop)){
                System.out.println("looted");
                //inventory.add(gamePanel.enemy[gamePanel.currentMap][index].drop);
                return true;
            }
        }
        return false;
    }

    public int lootEnemyGold(int index){
        Random random = new Random();
        int goldLoot = (random.nextInt(10) + 1)* gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].goldDrop;
        System.out.println(goldLoot);
        this.gold += goldLoot;
        return goldLoot;
    }

    private void setInventory() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentArmor);
        inventory.add((new Mushroom(gamePanel)));
        inventory.add(new HealthPotion(gamePanel));
        inventory.add(new ManaPotion(gamePanel));

    }

    public void setStats() {
        level = 1;
        speed = 5;
        maxHealth = 30;
        health = maxHealth;
        maxMana = 30;
        mana = maxMana;
        baseStrength = 10;
        baseDefence = 1;
        dexterity = 4;
        exp = 0;
        nextLevelExp = 80;
        gold = 500;
        currentWeapon = new PurpleSword(gamePanel);
        currentArmor = new Armor(gamePanel);
        strength = baseStrength + currentWeapon.getAttack();
        defence = baseDefence + currentArmor.getDefence();



    }
    public void heroMoves() {
        attackMove[0] = "Punch";
        attackPower[0] = 30;
        attackAccuracy[0] = 10;
        attackSoundIndex[0] = 8;
        attackCost[0] = 0;
        attackMove[1] = "Kick";
        attackPower[1] = 35;
        attackAccuracy[1] = 6;
        attackSoundIndex[1] = 5;
        attackAccuracy[2] = 7;
        attackCost[1] = 0;
        attackMove[2] = "Headbutt";
        attackPower[2] = 40;
        attackSoundIndex[2] = 5;
        attackAccuracy[3] = 8;
        attackCost[2] = 0;
        attackMove[3] = "Suplex";
        attackPower[3] = 50;
        attackAccuracy[3] = 5;
        attackSoundIndex[3] = 5;
        attackCost[3] = 0;
        attackMove[4] = "Fireball";
        attackPower[4] = 75;
        attackAccuracy[4] = 10;
        attackSoundIndex[4] = 8;
        attackCost[4] = 5;
        attackMove[5] = "Flamestrike";
        attackPower[5] = 150;
        attackAccuracy[5] = 6;
        attackSoundIndex[5] = 5;
        attackCost[5] = 10;
        attackMove[6] = "IceSpear";
        attackPower[6] = 100;
        attackAccuracy[6] = 7;
        attackSoundIndex[6] = 5;
        attackCost[6] = 8;
        attackMove[7] = "Blizzard";
        attackPower[7] = 1000;
        attackAccuracy[7] = 5;
        attackSoundIndex[7] = 5;
        attackCost[7] = 20;


    }

    public void equipItem() {
        int itemIndex = gamePanel.getUi().getItemIndex();
        if(itemIndex < inventory.size()){
            Item selectedItem = inventory.get(itemIndex);
            if(selectedItem instanceof Armor ){
                currentArmor = (Armor) selectedItem;
                currentArmor.recalculateHeroStats(gamePanel);
            }
            else if(selectedItem instanceof Weapon ){
                currentWeapon = (Weapon) selectedItem;
                currentWeapon.recalculateHeroStats(gamePanel);
            }
            else if(selectedItem instanceof Consumable){
                 ((Consumable) selectedItem).overWorldUse();
                 if (selectedItem.getAmount() >1){
                     selectedItem.setAmount(selectedItem.getAmount()-1);
                 } else {
                     inventory.remove(itemIndex);
                 }
            }
        }
    }

    public void useItemInBattle(int index){
        Item selectedItem = inventory.get(index);
        ((Consumable) selectedItem).battleUse();
        if (selectedItem.getAmount()>1){
            selectedItem.setAmount(selectedItem.getAmount()-1);
        } else {
            inventory.remove(index);
        }

    }

    public int searchInventory(String itemName) {
        int itemIndex = 666;
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getName().equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Item item){
        boolean canObtain = false;
        if(item.isStackable()){
            int index = searchInventory(item.getName());
            if(index != 666){
                inventory.get(index).setAmount(inventory.get(index).getAmount() +1);
                canObtain = true;
            }
            else {
                if(inventory.size() != inventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else {
            if(inventory.size() != inventorySize){
                inventory.add(item);
                canObtain = true;
            }

        }
        return  canObtain;
    }

    @Override
    public void refresh() {
        if (keyboardInputs.isUp() || keyboardInputs.isDown() || keyboardInputs.isLeft() || keyboardInputs.isRight()){ //checking for keyboard inputs so the sprite wont change when character is standing still
            if(keyboardInputs.isUp()){
                direction = "up";
            } else if (keyboardInputs.isDown()) {
                direction = "down";
            } else if (keyboardInputs.isLeft()) {
                direction = "left";
            } else if (keyboardInputs.isRight()) {
                direction = "right";
            }
            //background tile collision check
            collision = false;
            gamePanel.getCollisionCheck().checkBackgroundTile(this);

            //npc collision check
            int npcIndex = gamePanel.getCollisionCheck().checkCreature(this, gamePanel.getNpc());
            npcInteraction(npcIndex);

            //object collision check
            int objectIndex = gamePanel.getCollisionCheck().checkObject(this, true);
            pickUpObject(objectIndex);

            //monster collision
            int monsterIndex = gamePanel.getCollisionCheck().checkCreature(this, gamePanel.getEnemy());
            enemyInteraction(monsterIndex);

            //event check
            gamePanel.getEventHandler().checkEvent();

            gamePanel.getKeyboardInputs().setEnterPressed(false);

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
                 encounteredEnemy = true;
                gamePanel.getBattleHandler().startBattle(index);


        }
    }

    public void npcInteraction(int index) {
        if (index != 666) {
            if (gamePanel.getKeyboardInputs().isEnterPressed()) {
                encounteredNPC = true;
                gamePanel.getNpc()[gamePanel.getCurrentMap()][index].speak();

            }
        }

    }

    public void  pickUpObject(int index) {
        if (index !=666) { //if the hero does not touch any objects (any number above the object limit is fine
            String objectName = gamePanel.getSuperObject()[gamePanel.getCurrentMap()][index].getName();

                switch (objectName) {
                    case "Chest":
                        currentChest = (Chest) gamePanel.getSuperObject()[gamePanel.getCurrentMap()][index];
                        if (!canObtainItem(currentChest.getContent())){

                            gamePanel.getUi().setCurrentDialogue("Inventory is full");
                            gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);

                        } else {
                            gamePanel.playSoundEffect(2);
                            gamePanel.getUi().setCurrentDialogue("Hero found: " + currentChest.getContent().getName());

                            gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
                            gamePanel.getSuperObject()[gamePanel.getCurrentMap()][index] = null;
                        }

                        break;
                    case "Door":
                            gamePanel.playSoundEffect(3);

                            gamePanel.getUi().setCurrentDialogue("Hero opened the door");
                            gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
                            gamePanel.getSuperObject()[gamePanel.getCurrentMap()][index] = null;
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
        g2.drawImage(sprite, screenX,screenY, gamePanel.getSpriteSize(), gamePanel.getSpriteSize(),null);
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

    public int getScreenX() {
        return screenX;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHealth(int health) {
        this.health = health;
    }



    public int getAttackPower(int index) {
        return attackPower[index];
    }

    public int getAttackAccuracy(int index) {
        return attackAccuracy[index];
    }


    public int getAttackCost(int index) {
        return attackCost[index];
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
            baseStrength += 2;
            defence += 2;
            baseDefence += 2;
            dexterity += 1;
            gamePanel.playSoundEffect(6);
            gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
            gamePanel.getUi().setCurrentDialogue("Level Up! Your Stats Have Been Raised!");


        }
    }

    public boolean isEncounteredNPC() {
        return encounteredNPC;
    }

    public int getScreenY() {
        return screenY;
    }

    public boolean isEncounteredEnemy() {
        return encounteredEnemy;
    }

    public String[] getAttackMove() {
        return attackMove;
    }

    public int[] getAttackPower() {
        return attackPower;
    }

    public int[] getAttackAccuracy() {
        return attackAccuracy;
    }

    public int[] getAttackSoundIndex() {
        return attackSoundIndex;
    }

    public int[] getAttackCost() {
        return attackCost;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setCurrentArmor(Armor currentArmor) {
        this.currentArmor = currentArmor;
    }

    public void setEncounteredEnemy(boolean encounteredEnemy) {
        this.encounteredEnemy = encounteredEnemy;
    }

    public void setEncounteredNPC(boolean encounteredNPC) {
        this.encounteredNPC = encounteredNPC;
    }
}



