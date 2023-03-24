package main;

import creature.Creature;
import creature.Merchant;
import creature.NPC;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font lumos, ribbon;
    BufferedImage image, heroFace,gold;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialogue;
    public int commandIndex = 0;
    public int commandIndexX = 0, commandIndexY = 0;
    public int defeatedCounter = 5;
    int slotCollumn = 0;
    int slotRow = 0;
    int MerchantslotCollumn = 0;
    int MerchantslotRow = 0;
    int counter = 0;
    int itemIndex = 0;
    public Merchant merchant;
    public TradeState tradeState;
    public enum TradeState{
        SELECT,BUY,SELL
    }


    public SubMenu subMenu = SubMenu.MAINMENU;

    public enum SubMenu {
        MAINMENU,
        ATTACKMENU,
        INVENTORY,

    }

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            heroFace = ImageIO.read(new FileInputStream("src/sprites/UIElements/face.jpg"));
            gold = ImageIO.read(new FileInputStream("src/sprites/Items/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = new FileInputStream("src/data/fonts/LUMOS.TTF");
            lumos = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            InputStream inputStream2 = new FileInputStream("src/data/fonts/DavysRibbons.ttf");
            ribbon = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (Exception E) {

        }


    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) throws InterruptedException {
        g2.setFont(lumos);
        this.g2 = g2;
        if (gamePanel.gameState == GamePanel.Gamestate.TITLESCREEM) {
            drawTitleScreen();
        } else if (gamePanel.gameState == GamePanel.Gamestate.OPTIONSMENU) {
            drawOptionsMenu();
        } else if (gamePanel.gameState == GamePanel.Gamestate.PAUSESTATE) {
            drawPauseScreen();
        } else if (gamePanel.gameState == GamePanel.Gamestate.PLAYSTATE) {
            drawHeroUI();

        } else if (gamePanel.gameState == GamePanel.Gamestate.DIALOGUESTATE) {
            drawDialogueScreen();
        } else if ((gamePanel.gameState == GamePanel.Gamestate.CUTSCENE)) {
            playCutscene();
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLESTATEHERO || gamePanel.gameState == GamePanel.Gamestate.BATTLESTATEENEMY) {
            drawBattleScreen(gamePanel.battleHandler.monsterIndex);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGHERO) {
            drawBattleScreen(gamePanel.battleHandler.monsterIndex);
            drawBattleLogHero(gamePanel.battleHandler.monsterIndex, gamePanel.keyboardInputs.playerChoice);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGENEMY) {
            drawBattleScreen(gamePanel.battleHandler.monsterIndex);
            drawBattleLogEnemy(gamePanel.battleHandler.monsterIndex, gamePanel.keyboardInputs.enemyChoice);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLEWON) {
            drawBattleScreen(gamePanel.battleHandler.monsterIndex);
            drawVictoryLog(gamePanel.battleHandler.monsterIndex);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOST) {
            drawBattleScreen(gamePanel.battleHandler.monsterIndex);
            drawLossLog(gamePanel.battleHandler.monsterIndex);
        } else if (gamePanel.gameState == GamePanel.Gamestate.HEROSTATS) {
            drawStatScreen();
            drawEquipmentScreen();
            drawInventory(true);
        } else if (gamePanel.gameState == GamePanel.Gamestate.TRANSITION) {
            drawTransition();
        } else if (gamePanel.gameState == GamePanel.Gamestate.TRANSITIONBATTLE) {
            drawTransitionToBattle();
        } else if (gamePanel.gameState == GamePanel.Gamestate.TRADEMENU){
            drawTradeMenu();
        } else if (gamePanel.gameState == GamePanel.Gamestate.TRADEDIALOGUE) {
            drawTradeMenu();
            drawTradeDialogue();
        }
    }

    void drawTradeDialogue() {
        int x = gamePanel.spriteSize*2;
        int y = gamePanel.spriteSize*10;
        int width = gamePanel.screenWidth - (gamePanel.spriteSize*12);
        int height = gamePanel.spriteSize*2;
        drawMainWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
        x += gamePanel.spriteSize;
        y += gamePanel.spriteSize ;
        for(String line : currentDialogue.split("\n")){
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    private void drawTradeMenu() {

        if (tradeState == TradeState.SELECT){
            tradeSelect();
        } else if (tradeState == TradeState.BUY) {
            tradeBuy();
        } else if (tradeState == TradeState.SELL) {
            tradeSell();
        }
        gamePanel.keyboardInputs.enterPressed = false;
    }

    private void tradeSell() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        counter++;
        if (counter<=100){
            image = merchant.merchant1;
        } else if (counter<=200){
            image = merchant.merchant2;
        } else if (counter<=300) {
            image = merchant.merchant3;
        }
        else {
            counter = 0;
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        drawSubWindow(gamePanel.spriteSize*15,gamePanel.spriteSize*10,gamePanel.spriteSize*6,gamePanel.spriteSize*2);
        g2.drawString("Your         :" + gamePanel.hero.gold,gamePanel.spriteSize*15+50,gamePanel.spriteSize*10+80 );
        g2.drawImage(gold,gamePanel.spriteSize*17 -5,gamePanel.spriteSize*11-40,60,60,null);
        g2.drawImage(image,gamePanel.spriteSize*3,gamePanel.spriteSize -100,  gamePanel.spriteSize*7, gamePanel.spriteSize*12,null);
        itemIndex = getItemIndex();
        if(itemIndex < gamePanel.hero.inventory.size()){
            drawSubWindow(gamePanel.spriteSize*9,gamePanel.spriteSize*10,gamePanel.spriteSize*6,gamePanel.spriteSize*2);
            g2.drawString("Price        :" + gamePanel.hero.inventory.get(itemIndex).price,gamePanel.spriteSize*9+50,gamePanel.spriteSize*10+80 );
            g2.drawImage(gold,gamePanel.spriteSize*11 -10,gamePanel.spriteSize*11-40,60,60,null);
        }
        drawInventory(true);
    }

    private void tradeBuy() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        counter++;
        if (counter<=100){
            image = merchant.merchant1;
        } else if (counter<=200){
            image = merchant.merchant2;
        } else if (counter<=300) {
            image = merchant.merchant3;
        }
        else {
            counter = 0;
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        drawSubWindow(gamePanel.spriteSize*15,gamePanel.spriteSize*10,gamePanel.spriteSize*6,gamePanel.spriteSize*2);
        g2.drawString("Your         :" + gamePanel.hero.gold,gamePanel.spriteSize*15+50,gamePanel.spriteSize*10+80 );
        g2.drawImage(gold,gamePanel.spriteSize*17 -5,gamePanel.spriteSize*11-40,60,60,null);
        g2.drawImage(image,gamePanel.spriteSize*3,gamePanel.spriteSize -100,  gamePanel.spriteSize*7, gamePanel.spriteSize*12,null);
         itemIndex = getItemIndex();
        if(itemIndex < merchant.inventory.size()){
            drawSubWindow(gamePanel.spriteSize*9,gamePanel.spriteSize*7,gamePanel.spriteSize*6,gamePanel.spriteSize*2);
            g2.drawString("Price        :" + merchant.inventory.get(itemIndex).price,gamePanel.spriteSize*9+50,gamePanel.spriteSize*7+80 );
            g2.drawImage(gold,gamePanel.spriteSize*11 -10,gamePanel.spriteSize*8-40,60,60,null);
        }
        drawInventory(false);
        drawMerchantInventory(true);
    }

    private void tradeSelect() {
        drawDialogueScreen();
        int x = gamePanel.spriteSize *20;
        int y = (int)(gamePanel.spriteSize*2.4);
        int width = gamePanel.spriteSize*3;
        int height = gamePanel.spriteSize*4;
        drawSubWindow(x,y,width,height);
        x += gamePanel.spriteSize;
        y += gamePanel.spriteSize;
        g2.drawString("Buy",x,y);
        if (commandIndex == 0) {
            g2.drawString(">",x-30,y);
        }
        y += gamePanel.spriteSize;
        g2.drawString("Sell",x,y);
        if (commandIndex == 1) {
            g2.drawString(">",x-30,y);
        }
        y += gamePanel.spriteSize;
        g2.drawString("Bye!",x,y);
        if (commandIndex == 2) {
            g2.drawString(">",x-30,y);
        }
        y += gamePanel.spriteSize;
    }


    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gamePanel.screenWidth,gamePanel.screenHeight);
        if(counter == 50){
            counter = 0;
            gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
            gamePanel.currentMap = gamePanel.eventHandler.tempMap;
            gamePanel.hero.worldX = gamePanel.spriteSize*gamePanel.eventHandler.tempCol;
            gamePanel.hero.worldY = gamePanel.spriteSize*gamePanel.eventHandler.tempRow;
        }
    }
    public void drawTransitionToBattle() {
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gamePanel.screenWidth,gamePanel.screenHeight);
        if(counter == 50){
            counter = 0;
            gamePanel.gameState = GamePanel.Gamestate.BATTLESTATEHERO;
        }
    }


    private void drawOptionsMenu(){
        g2.setColor(new Color(77, 10, 162));
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80));
        String text = "Options";
        int x = getXforCenteredText(text);
        int y = gamePanel.spriteSize*3;
        g2.setColor((Color.black));
        g2.drawString(text,x+10,y+10);
        g2.setColor(Color.cyan);
        g2.drawString(text,x,y);


        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "FullScreen";
        x = getXforCenteredText(text)-100;
        int x2 = x+gamePanel.spriteSize*3 +40;
        y += gamePanel.spriteSize*2;
        g2.drawString(text,x,y);
        if(commandIndex ==0 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x2,y -25 ,30,30);
        if (gamePanel.fullScreenOn){
            g2.fillRect(x2,y -25 ,30,30);
        }

        text = "Music";
        x = getXforCenteredText(text)-100;
        y += gamePanel.spriteSize*2;
        g2.drawString(text,x,y);
        if(commandIndex ==1 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }
        g2.drawRect(x2,y-25,200,30); //200/10 = 20;
        int volumeWidth = 20*gamePanel.music.volumeScale;
        g2.fillRect(x2,y-25,volumeWidth,30);

        text = "Sound Effects";
        x = getXforCenteredText(text)-100;
        y += gamePanel.spriteSize*2;
        g2.drawString(text,x,y);
        if(commandIndex ==2 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }
        g2.drawRect(x2,y-25,200,30);
         volumeWidth = 20*gamePanel.soundEffect.volumeScale;
        g2.fillRect(x2,y-25,volumeWidth,30);

        text = "Back";
        x = getXforCenteredText(text)-100;
        y += gamePanel.spriteSize*2;
        g2.drawString(text,x,y);
        if(commandIndex == 3 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }
        gamePanel.config.saveConfig();

    }



    private void drawInventory( boolean cursor) {

        final  int frameX = gamePanel.spriteSize*9,frameY = gamePanel.spriteSize,frameWidth = gamePanel.spriteSize*6,frameHeight = gamePanel.spriteSize*6;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        // slots
        final int slotXstart = frameX + 40;
        final int slotYstart = frameY + 40;
        int slotX = slotXstart;
        int slotY = slotYstart;
        // draw items
        for (int i = 0; i < gamePanel.hero.inventory.size(); i++){
            if((gamePanel.hero.inventory.get(i) == gamePanel.hero.currentWeapon) || (gamePanel.hero.inventory.get(i) == gamePanel.hero.currentArmor)){
                g2.setColor(new Color(204, 195, 17, 255));
                g2.fillRoundRect(slotX,slotY,gamePanel.spriteSize,gamePanel.spriteSize,20,20);
            }



            g2.drawImage(gamePanel.hero.inventory.get(i).getImage(),slotX,slotY,null);
            slotX += gamePanel.spriteSize;

            if (i == 4 || i == 9 || i== 14 || i == 19){
                slotX = slotXstart;
                slotY += gamePanel.spriteSize;
            }
        }

        if(cursor){
            // cursor
            int cursorX = slotXstart + gamePanel.spriteSize*slotCollumn;
            int cursorY = slotYstart + gamePanel.spriteSize*slotRow;
            int cursorWidth = gamePanel.spriteSize;
            int cursorHeight = gamePanel.spriteSize;
            // draw cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,20,20);
            // draw Item description
            int dFrameX = frameX;
            int dFramyY = frameY + frameHeight;
            int dFrameWidth =frameWidth;
            int dFrameHeight = gamePanel.spriteSize*3;

            int textX = dFrameX + 40;
            int textY = dFramyY + gamePanel.spriteSize;
            g2.setFont(g2.getFont().deriveFont(25F));
            int itemIndex = getItemIndex();
            if (itemIndex < gamePanel.hero.inventory.size()){
                drawSubWindow(dFrameX,dFramyY,dFrameWidth,dFrameHeight);
                for(String line : gamePanel.hero.inventory.get(itemIndex).getDescription().split("\n")){
                    g2.drawString(line,textX,textY);
                    textY += 40;
                }

            }
        }

    }

    private void drawMerchantInventory( boolean showCursor) {

        final  int frameX = gamePanel.spriteSize*15,frameY = gamePanel.spriteSize,frameWidth = gamePanel.spriteSize*6,frameHeight = gamePanel.spriteSize*6;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        // slots
        final int slotXstart = frameX + 40;
        final int slotYstart = frameY + 40;
        int slotX = slotXstart;
        int slotY = slotYstart;
        // draw items
        for (int i = 0; i < merchant.inventory.size(); i++){

            g2.drawImage(merchant.inventory.get(i).getImage(),slotX,slotY,null);
            slotX += gamePanel.spriteSize;

            if (i == 4 || i == 9 || i== 14){
                slotX = slotXstart;
                slotY += gamePanel.spriteSize;
            }
        }

        if(showCursor){
            // cursor
            int cursorX = slotXstart + gamePanel.spriteSize*slotCollumn;
            int cursorY = slotYstart + gamePanel.spriteSize*slotRow;
            int cursorWidth = gamePanel.spriteSize;
            int cursorHeight = gamePanel.spriteSize;
            // draw cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,20,20);
            // draw Item description
            int dFrameX = frameX;
            int dFramyY = frameY + frameHeight;
            int dFrameWidth =frameWidth;
            int dFrameHeight = gamePanel.spriteSize*3;

            int textX = dFrameX + 40;
            int textY = dFramyY + gamePanel.spriteSize;
            g2.setFont(g2.getFont().deriveFont(25F));
            int itemIndex = getItemIndex();
            if (itemIndex < merchant.inventory.size()){
                drawSubWindow(dFrameX,dFramyY,dFrameWidth,dFrameHeight);
                for(String line : merchant.inventory.get(itemIndex).getDescription().split("\n")){
                    g2.drawString(line,textX,textY);
                    textY += 40;
                }

            }
        }

    }

    public int getItemIndex(){
        return slotCollumn + slotRow*5;
    }

    private void drawEquipmentScreen(){
        final  int frame2X = gamePanel.spriteSize*16,frame2Y = gamePanel.spriteSize,frameWidth2 = gamePanel.spriteSize*6,frameHeight2 = gamePanel.spriteSize*10;
        drawSubWindow(frame2X,frame2Y,frameWidth2,frameHeight2);
        final int lineHeight = 52;
        int text2X = frame2X +30;
        int text2Y = frame2Y +gamePanel.spriteSize;
        g2.drawString("Weapon",text2X,text2Y);
        text2Y += lineHeight;
        g2.drawString("Armor",text2X,text2Y+20);
        int tail2X = frame2X + frameWidth2 -45;
        text2Y = frame2Y + gamePanel.spriteSize; //resetting
        g2.drawImage(gamePanel.hero.getCurrentWeapon().getImage(),tail2X - gamePanel.spriteSize,text2Y-60,null);
        text2Y += gamePanel.spriteSize;
        g2.drawImage(gamePanel.hero.getCurrentArmor().getImage(),tail2X - gamePanel.spriteSize,text2Y-60,null);
        text2X += gamePanel.spriteSize;
    }

    private void drawStatScreen() {
        final  int frameX = gamePanel.spriteSize*2,frameY = gamePanel.spriteSize,frameWidth = gamePanel.spriteSize*6,frameHeight = gamePanel.spriteSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);


        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(40f));
        int textX = frameX +30;
        int textY = frameY +gamePanel.spriteSize;

        final int lineHeight = 52;

        g2.drawString("Level",textX,textY);
        textY += lineHeight;
        g2.drawString("Life",textX,textY);
        textY += lineHeight;
        g2.drawString("Mana",textX,textY);
        textY += lineHeight;
        g2.drawString("Strength",textX,textY);
        textY += lineHeight;
        g2.drawString("Defence",textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY += lineHeight;
        g2.drawString("Exp",textX,textY);
        textY += lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY += lineHeight;
        g2.drawString("Gold",textX,textY);
        textY += lineHeight;


        int tailX = frameX + frameWidth -45;

        textY = frameY + gamePanel.spriteSize; //resetting

        String value;

        value = String.valueOf(gamePanel.hero.getLevel());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getMaxHealth());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getMaxMana());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getStrength());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getDefence());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getDexterity());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getExp());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getNextLevelExp());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.hero.getGold());
        textX = getXforRightAllignement(value,tailX)-15;
        g2.drawString(value,textX,textY);




    }




    public  void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue)));
    }


    private void drawLossLog(int monsterIndex) {
        int x = gamePanel.screenWidth - (gamePanel.spriteSize*15);
        int y = 850;
        String text = "Game Over!";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));

        for(String line : text.split("\n")){
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(line,x,y);
            y += 80;
        }
    }

    private void drawVictoryLog(int index) {
        int x = gamePanel.screenWidth - (gamePanel.spriteSize*15);
        int y = 850;
        String text = "";
        if(gamePanel.keyboardInputs.looted){
             text = "Hero Won! Gained " + gamePanel.enemy[gamePanel.currentMap][index].getExp() + " EXP!\n" + "Hero looted 1 " + gamePanel.enemy[gamePanel.currentMap][index].drop.getName()+ " and "
                    +  gamePanel.keyboardInputs.goldlooted + " gold!";
        }else {
            text = "Hero Won! Gained " + gamePanel.enemy[gamePanel.currentMap][index].getExp() + " EXP!\n" + "Hero looted " + gamePanel.keyboardInputs.goldlooted + " gold!";
        }





        for (String line : text.split("\n")){
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(line,x,y);
            y += 80;
        }

    }

    void drawBattleScreen(int index) {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        drawHeroUI();
        g2.setColor(Color.white);
        String text = gamePanel.enemy[gamePanel.currentMap][index].name;
        int x3 = getXforCenteredText(text) -150;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        g2.drawString(text,x3,100);

        int x = gamePanel.screenWidth/2 -400;
        int y = 200;

        if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGENEMY && (gamePanel.battleHandler.damage != 0)){
            image = gamePanel.enemy[gamePanel.currentMap][index].battleImageAttack;
            defeatedCounter++;
            if(defeatedCounter <= 3){
                changeAlpha(g2,0f);
            }
            if (defeatedCounter > 3 && defeatedCounter <= 6) {
                changeAlpha(g2,1f);
            }
            if (defeatedCounter > 6 && defeatedCounter <= 9 ) {
                changeAlpha(g2,0f);
            }
            if (defeatedCounter > 9 && defeatedCounter <= 12) {
                changeAlpha(g2,1f);
            }




        }
        else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGENEMY){
            image = gamePanel.enemy[gamePanel.currentMap][index].battleImageAttack;
            defeatedCounter = 0;
        }else if(gamePanel.gameState == GamePanel.Gamestate.BATTLELOGHERO && (gamePanel.battleHandler.damage != 0)){
            image = gamePanel.enemy[gamePanel.currentMap][index].battleImageHurt;

            defeatedCounter = 0;


        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLEWON) {
            image = gamePanel.enemy[gamePanel.currentMap][index].battleImageHurt;
            defeatedCounter = 0;
        } else {
            image = gamePanel.enemy[gamePanel.currentMap][index].battleImageDefault;
            defeatedCounter = 0;
        }
        gamePanel.enemy[gamePanel.currentMap][index].setBattleSprites(image);
        g2.drawImage(image,x,y, 720,492,null);
        double healthScale = (double)gamePanel.spriteSize/gamePanel.enemy[gamePanel.currentMap][index].maxHealth;
        double hpBarTotal = healthScale*gamePanel.enemy[gamePanel.currentMap][index].health;
        g2.setColor(new Color(35,35,35));
        g2.fillRect(700,600,gamePanel.spriteSize*5,40);
        g2.setColor(new Color(255,0,30));
        g2.fillRect(702,602,(int)hpBarTotal*5,40);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        String healthText = "HP: " + gamePanel.enemy[gamePanel.currentMap][index].health + "/" +gamePanel.enemy[gamePanel.currentMap][index].maxHealth;
        g2.drawString(healthText,700 + gamePanel.spriteSize,630);
        int window2X = gamePanel.screenWidth - (gamePanel.spriteSize*16);
        int window2Y = 750;
        int width2 = gamePanel.screenWidth - (gamePanel.spriteSize*10);
        int height2 = gamePanel.spriteSize*3;
        drawSubWindow(window2X,window2Y,width2,height2);
        if (subMenu == SubMenu.MAINMENU) {
            int windowX = 0;
            int windowY = 750;
            int width = gamePanel.screenWidth - (gamePanel.spriteSize*16);
            int height = gamePanel.spriteSize*3;
            drawSubWindow(windowX,windowY,width,height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "Attack";
            g2.drawString(text, 100, 850);
            if (commandIndexX == 0 && commandIndexY == 0) {
                g2.drawString(">", 100 - gamePanel.spriteSize, 850);
            }

            text = "Defend";
            g2.drawString(text, 350, 850);
            if (commandIndexX == 1 && commandIndexY == 0) {
                g2.drawString(">", 350 - gamePanel.spriteSize, 850);
            }

            text = "Taunt";
            ;
            g2.drawString(text, 100, 925);
            if (commandIndexX == 0 && commandIndexY == 1) {
                g2.drawString(">", 100 - gamePanel.spriteSize, 925);
            }

            text = "Inventory";
            g2.drawString(text, 350, 925);
            if (commandIndexX == 1 && commandIndexY == 1) {
                g2.drawString(">", 350 - gamePanel.spriteSize, 925);
            }
        }
            else if (subMenu == SubMenu.ATTACKMENU) {
                int windowX = 0;
                int windowY = 750;
                int width = gamePanel.screenWidth - (gamePanel.spriteSize*16);
                int height = gamePanel.spriteSize*3;
                drawSubWindow(windowX,windowY,width,height);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
                text = "Fireball";
                g2.drawString(text,100,850);
                if(commandIndexX == 0 && commandIndexY == 0) {
                    g2.drawString(">",100-gamePanel.spriteSize,850);
                }

                text = "Flamestrike";
                g2.drawString(text,350,850);
                if(commandIndexX == 1 && commandIndexY == 0 ) {
                    g2.drawString(">",350-gamePanel.spriteSize,850);
                }

                text = "Ice Spear";;
                g2.drawString(text,100,925);
                if(commandIndexX == 0 && commandIndexY == 1 ) {
                    g2.drawString(">",100-gamePanel.spriteSize,925);
                }

                text = "Blizzard";
                g2.drawString(text,350,925);
                if(commandIndexX == 1 && commandIndexY == 1 ) {
                    g2.drawString(">",350 -gamePanel.spriteSize,925);
                }
            }
        else if (subMenu == SubMenu.INVENTORY) {
            int windowX = 0;
            int windowY = 350;
            int width = gamePanel.screenWidth - (gamePanel.spriteSize*16);
            int height = gamePanel.spriteSize*10;
            drawSubWindow(windowX,windowY,width,height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
            int x2 = 100;
            int y2 =600;
            text = "Health Potion x5";
            g2.drawString(text,x2,y2);
            if(commandIndexY == 0 ) {
                g2.drawString(">",x2-gamePanel.spriteSize,y2);
            }
            text = "Mana potion x4";
            g2.drawString(text,x2,y2 + 75);
            if(commandIndexY == 1 ) {
                g2.drawString(">",x2-gamePanel.spriteSize,y2 + 75);
            }
            text = "Potion of Courage x7";
            g2.drawString(text,x2,y2 + 150);
            if(commandIndexY == 2 ) {
                g2.drawString(">",x2-gamePanel.spriteSize,y2 +150);
            }

            text = "Potion of Wisdom x1";
            g2.drawString(text,x2,y2 + 225);
            if(commandIndexY == 3 ) {
                g2.drawString(">",x2-gamePanel.spriteSize,y2 + 225);
            }
        }

        }



        public void drawBattleLogHero(int enemyIndex, int moveIndex){
            int x = gamePanel.screenWidth - (gamePanel.spriteSize*15);
            int y = 850;
            String text = "";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
            //System.out.println("damage damage" + damage);
            if(gamePanel.battleHandler.damage > 0){
            text =  "Hero used   " + gamePanel.hero.attackMove[moveIndex]
                    + "\n" + " and caused " + gamePanel.battleHandler.damage + " damage!";}
            else {
                text= "Hero used " + gamePanel.hero.attackMove[moveIndex] + " but missed!";
            }

            for(String line : text.split("\n")){
                Color color = new Color(255,255,255,255);
                g2.setColor(color);
                g2.drawString(line,x,y);
                y += 80;
            }
    }

    public void drawBattleLogEnemy(int enemyIndex, int moveIndex){
        int x = gamePanel.screenWidth - (gamePanel.spriteSize*15);
        int y = 850;
        String text = "";
        int damage = gamePanel.battleHandler.damage;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        if (damage>0){

            text = gamePanel.enemy[gamePanel.currentMap][enemyIndex].name + " used" + gamePanel.enemy[gamePanel.currentMap][enemyIndex].attackMove[moveIndex]
                    + "\n" + " and caused " + damage + " damage!";
        }
        else {
            text = gamePanel.enemy[gamePanel.currentMap][enemyIndex].name + " used " + gamePanel.enemy[gamePanel.currentMap][enemyIndex].attackMove[moveIndex] + " but missed!";
        }
        for(String line : text.split("\n")){
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(line,x,y);
            y += 80;
        }
    }


    public void  drawHeroUI(){

        g2.drawImage(heroFace,0,0, 100,100,null);
        //health bar
        double healthScale = (double)gamePanel.spriteSize/gamePanel.hero.maxHealth;
        double hpBarTotal = healthScale*gamePanel.hero.health;
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,1,gamePanel.spriteSize*2,20);
        g2.setColor(new Color(255,0,30));
        g2.fillRect(102,0,(int)hpBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String healthText = "HP: " + gamePanel.hero.health + "/" +gamePanel.hero.maxHealth;
        g2.drawString(healthText,75+ gamePanel.spriteSize,18);
        // mana bar
        double manaScale = (double)gamePanel.spriteSize/gamePanel.hero.maxMana;
        double manaBarTotal = manaScale*gamePanel.hero.mana;
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,22,gamePanel.spriteSize*2,20);
        g2.setColor(new Color(30,0,255));
        g2.fillRect(102,21,(int)manaBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String manaText = "Mana: " + gamePanel.hero.mana + "/" +gamePanel.hero.maxMana;
        g2.drawString(manaText,70+ gamePanel.spriteSize,36);
        // mana bar
        double expScale = (double)gamePanel.spriteSize/gamePanel.hero.nextLevelExp;
        double expBarTotal = expScale*gamePanel.hero.exp;
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,42,gamePanel.spriteSize*2,20);
        g2.setColor(new Color(234, 230, 4, 255));
        g2.fillRect(102,41,(int)expBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String expText = "EXP: " + gamePanel.hero.exp + "/" +gamePanel.hero.nextLevelExp;
        g2.drawString(expText,70+ gamePanel.spriteSize,56);


    }

    public void playCutscene()  {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("src/sprites/story.gif");
        g2.drawImage(image,0,0,1920,1040,null);






    }


    public void drawTitleScreen() {



        g2.setColor(new Color(77, 10, 162));
        g2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text = "Project Nanos Magos";
        int x = getXforCenteredText(text);
        int y = gamePanel.spriteSize*3;
        g2.setColor((Color.black));
        g2.drawString(text,x+10,y+10);
        g2.setColor(Color.cyan);
        g2.drawString(text,x,y);
        x = 1200;
        y += gamePanel.spriteSize*4;
        try {
          image=  ImageIO.read(new FileInputStream("src/sprites/a2.png"));
        }catch (IOException e){

        }
        g2.drawImage(image,x,y, 720,492,null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "New Game";
        x = getXforCenteredText(text);
        y += gamePanel.spriteSize;
        g2.drawString(text,x,y);
        if(commandIndex ==0 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y += gamePanel.spriteSize;
        g2.drawString(text,x,y);
        if(commandIndex ==1 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }

        text = "options";
        x = getXforCenteredText(text);
        y += gamePanel.spriteSize;
        g2.drawString(text,x,y);
        if(commandIndex ==2 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += gamePanel.spriteSize;
        g2.drawString(text,x,y);
        if(commandIndex ==3 ) {
            g2.drawString(">",x-gamePanel.spriteSize,y);
        }
    }

    public void drawDialogueScreen() {

        int x = gamePanel.spriteSize*2;
        int y = gamePanel.spriteSize/2;
        int width = gamePanel.screenWidth - (gamePanel.spriteSize*3);
        int height = gamePanel.spriteSize*2;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
        x += gamePanel.spriteSize;
        y += gamePanel.spriteSize;
        for(String line : currentDialogue.split("\n")){
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(line,x,y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y,int width, int height){
        Color color = new Color(0,0,0,150);
        g2.setColor(color);
        g2.fillRoundRect(x,y,width,height,50,50);

        color = new Color(255, 255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(10));
        g2.drawRoundRect(x+10,y+10,width-20,height-20,50,50);

    }
    public void drawMainWindow(int x, int y,int width, int height){
        Color color = new Color(0,0,0,255);
        g2.setColor(color);
        g2.fillRoundRect(x,y,width,height,50,50);

        color = new Color(255, 255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(10));
        g2.drawRoundRect(x+10,y+10,width-20,height-20,50,50);

    }

    public void drawPauseScreen() {
        g2.drawString("PAUSED",  getXforCenteredText("PAUSED") , gamePanel.screenHeight/2); // java does not like the x axis huh?
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
        return gamePanel.screenWidth/2 -length;
    }
    public int getXforRightAllignement(String text,int tailX) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
        return tailX - length;
    }



}
