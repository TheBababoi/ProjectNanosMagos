package main;

import Items.Consumable;
import Items.Item;
import creature.Creature;
import creature.Merchant;
import creature.NPC;
import creature.Nikolaidis;
import org.w3c.dom.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    private GamePanel gamePanel;
    private Graphics2D g2;
    private Font lumos, ribbon;
    private BufferedImage image, heroFace, gold;
    private  String currentDialogue = "";
    private String currentBattleDialogue = "";
    private int commandIndex = 0;
    private int commandIndexX = 0;
    private int defeatedCounter = 5;
    private int slotCollumn = 0;
    private int slotRow = 0;
    private int counter = 0;
    private int itemIndex = 0;
    private  NPC npc;
    private Nikolaidis nikolaidis;
    private Merchant merchant;
    private TradeState tradeState;



    public enum TradeState {
        SELECT, BUY, SELL
    }
    private QuizState quizState;
    public enum QuizState {
        SELECT, QUIZ
    }
    private int quizIndex = 0 ;

    private int characterIndex = 0;
    private String combinedText = "";
    private String battleTipsText= "";
    private int creditsY = 1000;
    private int loadCounter = 0;
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<Integer> itemBattleIndex = new ArrayList<>();
    private int itemCounter = -1;


    private SubMenu subMenu = SubMenu.MAINMENU;

    protected enum SubMenu {
        MAINMENU,
        PHYSICALMENU,
        MAGICMENU,
        BUFFMENU,
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


    public void draw(Graphics2D g2) throws InterruptedException {
        g2.setFont(lumos);
        this.g2 = g2;
        if (gamePanel.getGameState() == GamePanel.Gamestate.TITLESCREEM) {
            drawTitleScreen();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.OPTIONSMENU) {
            drawOptionsMenu();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.CREDITS) {
            drawCredits(creditsY);
            creditsY--;
            if (creditsY < -2500) {
                resetCreditsY();
                gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
            }
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.PAUSESTATE) {
            drawPauseScreen();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.PLAYSTATE) {
            drawHeroUI();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.DIALOGUESTATE) {
            drawDialogueScreen();
        } else if ((gamePanel.getGameState() == GamePanel.Gamestate.CUTSCENE)) {
            playCutscene();
            loadCounter++;
            if (loadCounter > 800) {

                gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
                loadCounter = 0;
                gamePanel.playMusic(9);
            }
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLESTATEHERO) {
            drawBattleScreen(gamePanel.getBattleHandler().getMonsterIndex());
            drawBattleTips();
        } else if(gamePanel.getGameState() == GamePanel.Gamestate.BATTLESTATEENEMY){
            drawBattleScreen(gamePanel.getBattleHandler().getMonsterIndex());
        }
        else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGHERO) {
            drawBattleScreen(gamePanel.getBattleHandler().getMonsterIndex());
            drawBattleLogHero(gamePanel.getBattleHandler().getMonsterIndex(), gamePanel.getKeyboardInputs().getPlayerChoice());
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGENEMY) {
            drawBattleScreen(gamePanel.getBattleHandler().getMonsterIndex());
            drawBattleLogEnemy(gamePanel.getBattleHandler().getMonsterIndex(), gamePanel.getKeyboardInputs().getEnemyChoice());
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLEWON) {
            drawBattleScreen(gamePanel.getBattleHandler().getMonsterIndex());
            drawVictoryLog(gamePanel.getBattleHandler().getMonsterIndex());
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOST) {
            drawBattleScreen(gamePanel.getBattleHandler().getMonsterIndex());
            drawLossLog(gamePanel.getBattleHandler().getMonsterIndex());
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.HEROSTATS) {
            drawStatScreen();
            drawEquipmentScreen();
            drawInventory(true);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TRANSITION) {
            drawTransition();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TRANSITIONBATTLE) {
            drawTransitionToBattle();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TRADEMENU) {
            drawTradeMenu();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TRADEDIALOGUE) {
            drawTradeMenu();
            drawTradeDialogue();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZMENU) {
             drawQuizMenu();
         } else if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZRESULT) {
            drawQuizMenu();
            drawQuizResult();
         } else if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZFINAL) {
            drawQuizMenu();
            drawQuizFinal();
        }

    }

    private void drawQuizFinal() {
        int window2X = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
        int window2Y = 750;
        int width2 = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 10);
        int height2 = gamePanel.getSpriteSize() * 3;
        drawMainWindow(window2X, window2Y, width2, height2);
        int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 15);
        int y =850;
        String text = "";
        if (gamePanel.getHero().getHealth() == 1){
            text = "You have failed but I wont \n kill you your quest must go on!";
            g2.drawImage(nikolaidis.getMamaSad(), gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 12, null);
        } else {
           text = "Congratulations Mama is very Proud! \n Your Max Mana was raised!";
            g2.drawImage(nikolaidis.getNikolaidisHappy(), gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 12, null);
        }
        for (String line : text.split("\n")) {
            Color color = new Color(255, 255, 255, 255);
            g2.setColor(color);
            g2.drawString(line, x, y);
            y += 80;
        }
    }

    private void drawQuizResult() {
        int window2X = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
        int window2Y = 750;
        int width2 = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 10);
        int height2 = gamePanel.getSpriteSize() * 3;
        drawMainWindow(window2X, window2Y, width2, height2);
        int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 15);
        String text = "";
        if (gamePanel.getKeyboardInputs().getPlayerChoice() == nikolaidis.getCorrectAnswer()[quizIndex]){
            text = "Correct";
            g2.drawImage(nikolaidis.getMamaHappy(), gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 12, null);
        } else {
            g2.drawImage(nikolaidis.getMamaSad(), gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 12, null);
            text = "False";
        }
        g2.drawString(text, x, 850);

    }

    private void drawQuizMenu() {
        if (quizState == QuizState.SELECT) {
            quizSelect();
        } else if (quizState == QuizState.QUIZ) {
            quiz();
        }
        gamePanel.getKeyboardInputs().setEnterPressed(false);
    }

    private void quiz() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        counter++;
        if (counter <= 150) {
            image = nikolaidis.getNikolaidisInitial();
        } else if (counter <= 200) {
            image = nikolaidis.getMamaSpawn();
        }
        else if (counter <= 250) {
            image = nikolaidis.getMamaDefault();
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZMENU){
            g2.drawImage(image, gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 12, null);
            int windowX = 0;
            int windowY = 675;
            int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int height = gamePanel.getSpriteSize() * 4;
            drawSubWindow(windowX, windowY, width, height);
            g2.drawString(nikolaidis.getAnswer()[quizIndex][0], 100, 725);
            if (commandIndex == 0) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 725);
            }
            g2.drawString(nikolaidis.getAnswer()[quizIndex][1], 100, 800);
            if (commandIndex == 1) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 800);
            }
            g2.drawString(nikolaidis.getAnswer()[quizIndex][2], 100, 875);
            if (commandIndex == 2) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 875);
            }
            g2.drawString(nikolaidis.getAnswer()[quizIndex][3], 100, 950);
            if (commandIndex == 3) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 950);
            }
            int window2X = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int window2Y = 750;
            int width2 = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 10);
            int height2 = gamePanel.getSpriteSize() * 3;
            drawSubWindow(window2X, window2Y, width2, height2);
            int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 15);
            int y = 850;



            for (String line : nikolaidis.getQuestion()[quizIndex].split("\n")) {
                Color color = new Color(255, 255, 255, 255);
                g2.setColor(color);
                g2.drawString(line, x, y);
                y += 80;
            }
        }
        drawHeroUI();
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));


        //counter = 0;

    }

    private void quizSelect() {
        drawDialogueScreen();
        int x = gamePanel.getSpriteSize() * 20;
        int y = (int) (gamePanel.getSpriteSize() * 2.4);
        int width = gamePanel.getSpriteSize() * 3;
        int height = gamePanel.getSpriteSize() * 4;
        drawSubWindow(x, y, width, height);
        x += gamePanel.getSpriteSize();
        y += gamePanel.getSpriteSize();
        g2.drawString("Yes", x, y);
        if (commandIndex == 0) {
            g2.drawString(">", x - 30, y);
        }
        y += gamePanel.getSpriteSize();
        g2.drawString("No", x, y);
        if (commandIndex == 1) {
            g2.drawString(">", x - 30, y);
        }
        y += gamePanel.getSpriteSize();
        counter = 0;
    }

    private void drawCredits(int y) {
        String string = "                   Project Nanos magos \n \n \n\n\n            Developed by Team11 Interactive \n\n\n\n Goblin mode copy pasta \n\n\n\n\n\n\n How do I get my husband \n to stop going ‘Goblin Mode’ during sex?\n" +
                "\n" +
                "TLDR; My husband says ‘Goblin Mode activated’ \n when we start to have sex, growls and acts like a caveman,\n and then says ‘Goblin Mode off’ when we stop, \n and then pretends not to remember afterward.\n" +
                "\n" +
                "I really love my husband and he’s always been great in bed. \n But recently he’s been acting really weird. \n So, a couple of days ago, my son went on a rampage through  \n our house and said he was in ‘Goblin Mode’. \n We didn’t really know what to do with him, \n so we sent him to live with my parents so he can go to a special needs school. \n My husband a really great relationship with our son and loved him more than anything. \n Naturally, he was upset when he had to leave. \n He’s an incredibly tough man, but this was the first time I’ve ever seen him cry. \n I think since then, he’s been a little emotionally unwell. \n I’ve heard him muttering, ‘Goblin’ repeatedly when he didn’t notice me,\n staring blankly into his food, \n and just going alone by himself to do who knows what. \n I feel awful for him, but we both agreed that this was for the best. \n Last night, the day after our son went away, \n we decided to have sex to relieve our stress. \n However, my husband said ‘Goblin Mode activated’, starting growling, \n and went wild having sex with me.  \n Admittedly, it was some of the best and most experimental sex I’ve ever had, \n but I’m worried that something might be going on with my husband. \n Any advice?\n" +
                "\n" +
                "Edit: The problem isn’t the ‘Goblin Mode’, it’s that he could be ill";

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2.setFont(g2.getFont().deriveFont(40f));

        for (String line : string.split("\n")) {
            Color color = new Color(255, 255, 255, 255);

            g2.setColor(color);
            g2.drawString(line, 300, y);
            y = y + 80;
        }


    }

    void drawTradeDialogue() {
        int x = gamePanel.getSpriteSize() * 2;
        int y = gamePanel.getSpriteSize() * 10;
        int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 12);
        int height = gamePanel.getSpriteSize() * 2;
        drawMainWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        x += gamePanel.getSpriteSize();
        y += gamePanel.getSpriteSize();
        for (String line : currentDialogue.split("\n")) {
            Color color = new Color(255, 255, 255, 255);
            g2.setColor(color);
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawTradeMenu() {

        if (tradeState == TradeState.SELECT) {
            tradeSelect();
        } else if (tradeState == TradeState.BUY) {
            tradeBuy();
        } else if (tradeState == TradeState.SELL) {
            tradeSell();
        }
        gamePanel.getKeyboardInputs().setEnterPressed(false);
    }

    private void tradeSell() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        counter++;
        if (counter <= 100) {
            image = merchant.getMerchant1();
        } else if (counter <= 200) {
            image = merchant.getMerchant2();
        } else if (counter <= 300) {
            image = merchant.getMerchant3();
        } else {
            counter = 0;
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        drawSubWindow(gamePanel.getSpriteSize() * 15, gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() * 6, gamePanel.getSpriteSize() * 2);
        g2.drawString("Your         :" + gamePanel.getHero().getGold(), gamePanel.getSpriteSize() * 15 + 50, gamePanel.getSpriteSize() * 10 + 80);
        g2.drawImage(gold, gamePanel.getSpriteSize() * 17 - 5, gamePanel.getSpriteSize() * 11 - 40, 60, 60, null);
        g2.drawImage(image, gamePanel.getSpriteSize() * 3, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 7, gamePanel.getSpriteSize() * 12, null);
        itemIndex = getItemIndex();
        if (itemIndex < gamePanel.getHero().getInventory().size()) {
            drawSubWindow(gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() * 6, gamePanel.getSpriteSize() * 2);
            g2.drawString("Price        :" + gamePanel.getHero().getInventory().get(itemIndex).getPrice()/2, gamePanel.getSpriteSize() * 9 + 50, gamePanel.getSpriteSize() * 10 + 80);
            g2.drawImage(gold, gamePanel.getSpriteSize() * 11 - 10, gamePanel.getSpriteSize() * 11 - 40, 60, 60, null);
        }
        drawInventory(true);
    }

    private void tradeBuy() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        counter++;
        if (counter <= 100) {
            image = merchant.getMerchant1();
        } else if (counter <= 200) {
            image = merchant.getMerchant2();
        } else if (counter <= 300) {
            image = merchant.getMerchant3();
        } else {
            counter = 0;
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        drawSubWindow(gamePanel.getSpriteSize() * 15, gamePanel.getSpriteSize() * 10, gamePanel.getSpriteSize() * 6, gamePanel.getSpriteSize() * 2);
        g2.drawString("Your         :" + gamePanel.getHero().getGold(), gamePanel.getSpriteSize() * 15 + 50, gamePanel.getSpriteSize() * 10 + 80);
        g2.drawImage(gold, gamePanel.getSpriteSize() * 17 - 5, gamePanel.getSpriteSize() * 11 - 40, 60, 60, null);
        g2.drawImage(image, gamePanel.getSpriteSize() * 3, gamePanel.getSpriteSize() - 100, gamePanel.getSpriteSize() * 7, gamePanel.getSpriteSize() * 12, null);
        itemIndex = getItemIndex();
        if (itemIndex < merchant.getInventory().size()) {
            drawSubWindow(gamePanel.getSpriteSize() * 9, gamePanel.getSpriteSize() * 7, gamePanel.getSpriteSize() * 6, gamePanel.getSpriteSize() * 2);
            g2.drawString("Price        :" + merchant.getInventory().get(itemIndex).getPrice(), gamePanel.getSpriteSize() * 9 + 50, gamePanel.getSpriteSize() * 7 + 80);
            g2.drawImage(gold, gamePanel.getSpriteSize() * 11 - 10, gamePanel.getSpriteSize() * 8 - 40, 60, 60, null);
        }
        drawInventory(false);
        drawMerchantInventory(true);
    }

    private void tradeSelect() {
        drawDialogueScreen();
        int x = gamePanel.getSpriteSize() * 20;
        int y = (int) (gamePanel.getSpriteSize() * 2.4);
        int width = gamePanel.getSpriteSize() * 3;
        int height = gamePanel.getSpriteSize() * 4;
        drawSubWindow(x, y, width, height);
        x += gamePanel.getSpriteSize();
        y += gamePanel.getSpriteSize();
        g2.drawString("Buy", x, y);
        if (commandIndex == 0) {
            g2.drawString(">", x - 30, y);
        }
        y += gamePanel.getSpriteSize();
        g2.drawString("Sell", x, y);
        if (commandIndex == 1) {
            g2.drawString(">", x - 30, y);
        }
        y += gamePanel.getSpriteSize();
        g2.drawString("Bye!", x, y);
        if (commandIndex == 2) {
            g2.drawString(">", x - 30, y);
        }
        y += gamePanel.getSpriteSize();
        counter = 0;
    }


    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        if (counter == 50) {
            counter = 0;
            gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
            gamePanel.setCurrentMap(gamePanel.getEventHandler().getTempMap());
            gamePanel.getHero().setWorldX(gamePanel.getSpriteSize() * gamePanel.getEventHandler().getTempCol());
            gamePanel.getHero().setWorldY(gamePanel.getSpriteSize() * gamePanel.getEventHandler().getTempRow());
        }
    }

    public void drawTransitionToBattle() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        if (counter == 50) {
            counter = 0;
            gamePanel.setGameState(GamePanel.Gamestate.BATTLESTATEHERO);
        }
    }


    private void drawOptionsMenu() {
        g2.setColor(new Color(77, 10, 162));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80));
        String text = "Options";
        int x = getXforCenteredText(text);
        int y = gamePanel.getSpriteSize() * 3;
        g2.setColor((Color.black));
        g2.drawString(text, x + 10, y + 10);
        g2.setColor(Color.cyan);
        g2.drawString(text, x, y);


        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "FullScreen";
        x = getXforCenteredText(text) - 100;
        int x2 = x + gamePanel.getSpriteSize() * 3 + 40;
        y += gamePanel.getSpriteSize() * 2;
        g2.drawString(text, x, y);
        if (commandIndex == 0) {
            g2.drawString(">", x - gamePanel.getSpriteSize(), y);
            g2.drawString("Requires Game restart", getXforCenteredText("(*(Requires Game restart)") , 1000);
        }
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x2, y - 25, 30, 30);
        if (gamePanel.isFullScreenOn()) {
            g2.fillRect(x2, y - 25, 30, 30);
        }

        text = "Music";
        x = getXforCenteredText(text) - 100;
        y += gamePanel.getSpriteSize() * 2;
        g2.drawString(text, x, y);
        if (commandIndex == 1) {
            g2.drawString(">", x - gamePanel.getSpriteSize(), y);
        }
        g2.drawRect(x2, y - 25, 200, 30); //200/10 = 20;
        int volumeWidth = 20 * gamePanel.getMusic().getVolumeScale();
        g2.fillRect(x2, y - 25, volumeWidth, 30);

        text = "Sound Effects";
        x = getXforCenteredText(text) - 100;
        y += gamePanel.getSpriteSize() * 2;
        g2.drawString(text, x, y);
        if (commandIndex == 2) {
            g2.drawString(">", x - gamePanel.getSpriteSize(), y);
        }
        g2.drawRect(x2, y - 25, 200, 30);
        volumeWidth = 20 * gamePanel.getSoundEffect().getVolumeScale();
        g2.fillRect(x2, y - 25, volumeWidth, 30);

        text = "Back";
        x = getXforCenteredText(text) - 100;
        y += gamePanel.getSpriteSize() * 2;
        g2.drawString(text, x, y);
        if (commandIndex == 3) {
            g2.drawString(">", x - gamePanel.getSpriteSize(), y);
        }
        gamePanel.getConfig().saveConfig();

    }


    private void drawInventory(boolean cursor) {

        final int frameX = gamePanel.getSpriteSize() * 9, frameY = gamePanel.getSpriteSize(), frameWidth = gamePanel.getSpriteSize() * 6, frameHeight = gamePanel.getSpriteSize() * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        // slots
        final int slotXstart = frameX + 40;
        final int slotYstart = frameY + 40;
        int slotX = slotXstart;
        int slotY = slotYstart;
        // draw items
        for (int i = 0; i < gamePanel.getHero().getInventory().size(); i++) {
            if ((gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentWeapon()) || (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentArmor()) || (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentTrinket()) || (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentGem())) {
                g2.setColor(new Color(204, 195, 17, 255));
                g2.fillRoundRect(slotX, slotY, gamePanel.getSpriteSize(), gamePanel.getSpriteSize(), 20, 20);
            }


            g2.drawImage(gamePanel.getHero().getInventory().get(i).getImage(), slotX, slotY, null);
            if (gamePanel.getHero().getInventory().get(i).getAmount() > 1) {
                g2.setFont(g2.getFont().deriveFont(35f));
                String string = "" + gamePanel.getHero().getInventory().get(i).getAmount();
                g2.drawString(string, getXforRightAllignement(string, slotX) + 15, slotY + gamePanel.getSpriteSize());
            }

            slotX += gamePanel.getSpriteSize();

            if (i == 4 || i == 9 || i == 14 || i == 19) {
                slotX = slotXstart;
                slotY += gamePanel.getSpriteSize();
            }
        }

        if (cursor) {
            // cursor
            int cursorX = slotXstart + gamePanel.getSpriteSize() * slotCollumn;
            int cursorY = slotYstart + gamePanel.getSpriteSize() * slotRow;
            int cursorWidth = gamePanel.getSpriteSize();
            int cursorHeight = gamePanel.getSpriteSize();
            // draw cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 20, 20);
            // draw Item description
            int dFrameX = frameX;
            int dFramyY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gamePanel.getSpriteSize() * 3;

            int textX = dFrameX + 40;
            int textY = dFramyY + gamePanel.getSpriteSize();
            g2.setFont(g2.getFont().deriveFont(25F));
            int itemIndex = getItemIndex();
            if (itemIndex < gamePanel.getHero().getInventory().size()) {
                drawSubWindow(dFrameX, dFramyY, dFrameWidth, dFrameHeight);
                for (String line : gamePanel.getHero().getInventory().get(itemIndex).getDescription().split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 40;
                }

            }
        }

    }

    private void drawMerchantInventory(boolean showCursor) {

        final int frameX = gamePanel.getSpriteSize() * 15, frameY = gamePanel.getSpriteSize(), frameWidth = gamePanel.getSpriteSize() * 6, frameHeight = gamePanel.getSpriteSize() * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        // slots
        final int slotXstart = frameX + 40;
        final int slotYstart = frameY + 40;
        int slotX = slotXstart;
        int slotY = slotYstart;
        // draw items
        for (int i = 0; i < merchant.getInventory().size(); i++) {

            g2.drawImage(merchant.getInventory().get(i).getImage(), slotX, slotY, null);
            slotX += gamePanel.getSpriteSize();

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gamePanel.getSpriteSize();
            }
        }

        if (showCursor) {
            // cursor
            int cursorX = slotXstart + gamePanel.getSpriteSize() * slotCollumn;
            int cursorY = slotYstart + gamePanel.getSpriteSize() * slotRow;
            int cursorWidth = gamePanel.getSpriteSize();
            int cursorHeight = gamePanel.getSpriteSize();
            // draw cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 20, 20);
            // draw Item description
            int dFrameX = frameX;
            int dFramyY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gamePanel.getSpriteSize() * 3;

            int textX = dFrameX + 40;
            int textY = dFramyY + gamePanel.getSpriteSize();
            g2.setFont(g2.getFont().deriveFont(25F));
            int itemIndex = getItemIndex();
            if (itemIndex < merchant.getInventory().size()) {
                drawSubWindow(dFrameX, dFramyY, dFrameWidth, dFrameHeight);
                for (String line : merchant.getInventory().get(itemIndex).getDescription().split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 40;
                }

            }
        }

    }

    public int getItemIndex() {
        return slotCollumn + slotRow * 5;
    }

    private void drawEquipmentScreen() {
        final int frame2X = gamePanel.getSpriteSize() * 16, frame2Y = gamePanel.getSpriteSize(), frameWidth2 = gamePanel.getSpriteSize() * 6, frameHeight2 = gamePanel.getSpriteSize() * 10;
        drawSubWindow(frame2X, frame2Y, frameWidth2, frameHeight2);
        final int lineHeight = 52;
        int text2X = frame2X + 30;
        int text2Y = frame2Y + gamePanel.getSpriteSize();
        g2.drawString("Weapon", text2X, text2Y);
        text2Y += lineHeight;
        g2.drawString("Armor", text2X, text2Y + 20);
        text2Y += lineHeight;
        g2.drawString("Gem", text2X, text2Y + 40);
        text2Y += lineHeight;
        g2.drawString("Trinket", text2X, text2Y + 60);
        int tail2X = frame2X + frameWidth2 - 45;
        text2Y = frame2Y + gamePanel.getSpriteSize(); //resetting
        g2.drawImage(gamePanel.getHero().getCurrentWeapon().getImage(), tail2X - gamePanel.getSpriteSize(), text2Y - 60, null);
        text2Y += gamePanel.getSpriteSize();
        g2.drawImage(gamePanel.getHero().getCurrentArmor().getImage(), tail2X - gamePanel.getSpriteSize(), text2Y - 60, null);
        text2Y += gamePanel.getSpriteSize();
        g2.drawImage(gamePanel.getHero().getCurrentGem().getImage(), tail2X - gamePanel.getSpriteSize(), text2Y - 60, null);
        text2Y += gamePanel.getSpriteSize();
        g2.drawImage(gamePanel.getHero().getCurrentTrinket().getImage(), tail2X - gamePanel.getSpriteSize(), text2Y - 60, null);
        text2Y += gamePanel.getSpriteSize();
    }

    private void drawStatScreen() {
        final int frameX = gamePanel.getSpriteSize() * 2, frameY = gamePanel.getSpriteSize(), frameWidth = gamePanel.getSpriteSize() * 6, frameHeight = gamePanel.getSpriteSize() * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);


        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(40f));
        int textX = frameX + 30;
        int textY = frameY + gamePanel.getSpriteSize();

        final int lineHeight = 52;

        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Defence", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold", textX, textY);
        textY += lineHeight;


        int tailX = frameX + frameWidth - 45;

        textY = frameY + gamePanel.getSpriteSize(); //resetting

        String value;

        value = String.valueOf(gamePanel.getHero().getLevel());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getMaxHealth());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getMaxMana());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getStrength());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getDefence());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getDexterity());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getExp());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getNextLevelExp());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getHero().getGold());
        textX = getXforRightAllignement(value, tailX) - 15;
        g2.drawString(value, textX, textY);


    }


    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue)));
    }


    private void drawLossLog(int monsterIndex) {
        int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 15);
        int y = 850;
        String text = "Game Over!";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        char[] characters = text.toCharArray();
        displayLetterbyLetter(characters);

        for (String line : currentDialogue.split("\n")) {
            Color color = new Color(255, 255, 255, 255);
            g2.setColor(color);
            g2.drawString(line, x, y);
            y += 80;
        }
    }

    private void drawVictoryLog(int index) {
        int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 15);
        int y = 850;
        String text = "";
        if (gamePanel.getKeyboardInputs().isLooted()) {
            text = "Hero Won! Gained " + gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].getExp() + " EXP!\n" + "Hero looted 1 " + gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].drop.getName() + " and "
                    + gamePanel.getKeyboardInputs().getGoldlooted() + " gold!";
        } else {
            text = "Hero Won! Gained " + gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].getExp() + " EXP!\n" + "Hero looted " + gamePanel.getKeyboardInputs().getGoldlooted() + " gold!";
        }


        char[] characters = text.toCharArray();
        displayLetterbyLetter(characters);

        for (String line : currentDialogue.split("\n")) {
            Color color = new Color(255, 255, 255, 255);
            g2.setColor(color);
            g2.drawString(line, x, y);
            y += 80;
        }

    }

    void drawBattleScreen(int index) {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        drawHeroUI();
        g2.setColor(Color.white);
        String text = gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].name;
        int x3 = getXforCenteredText(text) - 150;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        g2.drawString(text, x3, 100);

        int x = gamePanel.getScreenWidth() / 2 -400;
        int y = 200;

        if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGENEMY && (gamePanel.getBattleHandler().getDamage() != 0)) {
            image = gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].battleImageAttack;
            defeatedCounter++;
            if (defeatedCounter <= 3) {
                changeAlpha(g2, 0f);
            }
            if (defeatedCounter > 3 && defeatedCounter <= 6) {
                changeAlpha(g2, 1f);
            }
            if (defeatedCounter > 6 && defeatedCounter <= 9) {
                changeAlpha(g2, 0f);
            }
            if (defeatedCounter > 9 && defeatedCounter <= 12) {
                changeAlpha(g2, 1f);
            }


        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGENEMY) {
            image = gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].battleImageAttack;
            defeatedCounter = 0;
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGHERO && (gamePanel.getBattleHandler().getDamage() != 0)&&!gamePanel.getKeyboardInputs().isItemUsed()) {
            image = gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].battleImageHurt;

            defeatedCounter = 0;


        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLEWON) {
            image = gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].battleImageHurt;
            defeatedCounter = 0;
        } else {
            image = gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].battleImageDefault;
            defeatedCounter = 0;
        }
        gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].setBattleSprites(image);
        g2.drawImage(image, gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].spriteX, gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].spriteY, gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].spritesizeX, gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].spritesizeY, null);
        double healthScale = (double) gamePanel.getSpriteSize() / gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].maxHealth;
        double hpBarTotal = healthScale * gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].health;
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(700, 600, gamePanel.getSpriteSize() * 5, 40);
        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(702, 602, (int) hpBarTotal * 5, 40);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        String healthText = "HP: " + gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].health + "/" + gamePanel.getEnemy()[gamePanel.getCurrentMap()][index].maxHealth;
        g2.drawString(healthText, 700 + gamePanel.getSpriteSize(), 630);
        int window2X = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
        int window2Y = 750;
        int width2 = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 10);
        int height2 = gamePanel.getSpriteSize() * 3;
        drawSubWindow(window2X, window2Y, width2, height2);
        if (subMenu == SubMenu.MAINMENU) {
            int windowX = 0;
            int windowY = 750;
            int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int height = gamePanel.getSpriteSize() * 3;
            drawSubWindow(windowX, windowY, width, height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "Physical";
            g2.drawString(text, 100, 850);
            if (commandIndexX == 0 && commandIndex == 0) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 850);
            }

            text = "Magic";
            g2.drawString(text, 350, 850);
            if (commandIndexX == 1 && commandIndex == 0) {
                g2.drawString(">", 380 - gamePanel.getSpriteSize(), 850);
            }

            text = "Buffs";
            ;
            g2.drawString(text, 100, 925);
            if (commandIndexX == 0 && commandIndex == 1) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 925);
            }

            text = "Inventory";
            g2.drawString(text, 350, 925);
            if (commandIndexX == 1 && commandIndex == 1) {
                g2.drawString(">", 380 - gamePanel.getSpriteSize(), 925);
            }
        } else if (subMenu == SubMenu.MAGICMENU) {
            int windowX = 0;
            int windowY = 750;
            int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int height = gamePanel.getSpriteSize() * 3;
            drawSubWindow(windowX, windowY, width, height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "Fireball";
            g2.drawString(text, 100, 850);
            if (commandIndexX == 0 && commandIndex == 0) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 850);
            }

            text = "Flamestrike";
            g2.drawString(text, 350, 850);
            if (commandIndexX == 1 && commandIndex == 0) {
                g2.drawString(">", 380 - gamePanel.getSpriteSize(), 850);
            }

            text = "Ice Spear";
            ;
            g2.drawString(text, 100, 925);
            if (commandIndexX == 0 && commandIndex == 1) {
                g2.drawString(">", 130 - gamePanel.getSpriteSize(), 925);
            }

            text = "Blizzard";
            g2.drawString(text, 350, 925);
            if (commandIndexX == 1 && commandIndex == 1) {
                g2.drawString(">", 380 - gamePanel.getSpriteSize(), 925);
            }
        }else if (subMenu == SubMenu.PHYSICALMENU) {
                 int windowX = 0;
                    int windowY = 750;
            int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int height = gamePanel.getSpriteSize() * 3;
            drawSubWindow(windowX, windowY, width, height);

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
                text = "Punch";
                g2.drawString(text, 100, 850);
                if (commandIndexX == 0 && commandIndex == 0) {
                    g2.drawString(">", 130 - gamePanel.getSpriteSize(), 850);
                }

                text = "Kick";
                g2.drawString(text, 350, 850);
                if (commandIndexX == 1 && commandIndex == 0) {
                    g2.drawString(">", 380 - gamePanel.getSpriteSize(), 850);
                }

                text = "Headbutt";
                ;
                g2.drawString(text, 100, 925);
                if (commandIndexX == 0 && commandIndex == 1) {
                    g2.drawString(">", 130 - gamePanel.getSpriteSize(), 925);
                }

                text = "Suplex";
                g2.drawString(text, 350, 925);
                if (commandIndexX == 1 && commandIndex == 1) {
                    g2.drawString(">", 380 - gamePanel.getSpriteSize(), 925);
                }
            }

                else if (subMenu == SubMenu.BUFFMENU) {
            int windowX = 0;
            int windowY = 750;
            int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int height = gamePanel.getSpriteSize() * 3;
            drawSubWindow(windowX, windowY, width, height);
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
                    text = "Meditate";
                    g2.drawString(text, 100, 850);
                    if (commandIndexX == 0 && commandIndex == 0) {
                        g2.drawString(">", 130 - gamePanel.getSpriteSize(), 850);
                    }

                    text = "Focus";
                    g2.drawString(text, 350, 850);
                    if (commandIndexX == 1 && commandIndex == 0) {
                        g2.drawString(">", 380 - gamePanel.getSpriteSize(), 850);
                    }

                    text = "Dance";
                    ;
                    g2.drawString(text, 100, 925);
                    if (commandIndexX == 0 && commandIndex == 1) {
                        g2.drawString(">", 130 - gamePanel.getSpriteSize(), 925);
                    }

                    text = "Enrage";
                    g2.drawString(text, 350, 925);
                    if (commandIndexX == 1 && commandIndex == 1) {
                        g2.drawString(">", 380 - gamePanel.getSpriteSize(), 925);
                    }



        } else if (subMenu == SubMenu.INVENTORY) {
            System.out.println(commandIndex);
            int windowX = 0;
            int windowY = 150;
            int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 16);
            int height = gamePanel.getSpriteSize() * 10;
            drawSubWindow(windowX, windowY, width, height);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            int x2 = 100;
            int y2 = 300;
            if (itemCounter == -1) {
                itemNames.clear();
                itemBattleIndex.clear();
                for (Item item : gamePanel.getHero().getInventory()) {
                    if (item instanceof Consumable) {
                        itemCounter++;
                        itemNames.add(item.getName());
                        itemBattleIndex.add(gamePanel.getHero().getInventory().indexOf(item));

                    }
                }

            }

            for (int i = 0; i <= itemCounter; i++) {
                g2.drawString(itemNames.get(i), x2, y2);
                g2.drawString(String.valueOf("x" + gamePanel.getHero().getInventory().get(itemBattleIndex.get(i)).getAmount()), x2 + 430, y2);
                if (commandIndex == i) {
                    g2.drawString(">", x2 - gamePanel.getSpriteSize(), y2);
                }
                y2 += 75;
            }


        }

    }

    public void drawBattleTips() {
        int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() * 15);
        int y = 850;
        if (subMenu == SubMenu.MAINMENU) {
            if (commandIndexX == 0 && commandIndex == 0) {
                battleTipsText = "List of Physical Attacks";

            }
            if (commandIndexX == 1 && commandIndex == 0) {
                battleTipsText = "List of Magic Attacks";

            }
            if (commandIndexX == 0 && commandIndex == 1) {
                battleTipsText = "List of Support Moves";
            }
            if (commandIndexX == 1 && commandIndex == 1) {

                boolean flag = false;

                for (Item item: gamePanel.getHero().getInventory()){
                    if (item instanceof Consumable) {
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    battleTipsText = "Hero's Inventory";
            } else{
                gamePanel.getUi().setBattleTipsText("Inventory is Empty!");
            }

            }
        } else if (subMenu == SubMenu.INVENTORY) {
            Item item = gamePanel.getHero().getInventory().get(itemBattleIndex.get(commandIndex));

            battleTipsText = ((Consumable) item).getBattleDescription();
        } else if (subMenu == SubMenu.MAGICMENU || subMenu == SubMenu.PHYSICALMENU) {
            int index = 0;
            int i = 0;
            if (subMenu == SubMenu.MAGICMENU) {
                i = 4;
            }
            if (commandIndexX == 0 && commandIndex == 0) {
                index = i;

            }
            if (commandIndexX == 1 && commandIndex == 0) {
                index = i + 1;

            }
            if (commandIndexX == 0 && commandIndex == 1) {
                index = i + 2;
            }
            if (commandIndexX == 1 && commandIndex == 1) {
                index = i + 3;
            }
            if (gamePanel.getHero().getAttackCost(index) > gamePanel.getHero().getMana()) {
                battleTipsText = "Not enough mana!";
            } else {
                battleTipsText = "Power: " + gamePanel.getHero().getAttackPower(index) + " Accuracy: " + gamePanel.getHero().getAttackAccuracy(index) + "\nMana Cost: " + gamePanel.getHero().getAttackCost(index);
            }
        }
        else if (subMenu == SubMenu.BUFFMENU) {
                if (commandIndexX == 0 && commandIndex == 0) {
                    battleTipsText = "Restores 1/3 of Hero's mana";
                }
                if (commandIndexX == 1 && commandIndex == 0) {
                    if (!gamePanel.getBattleHandler().isUsedFocus()){
                        battleTipsText = "Raises Hero's accuracy by 1 once";
                    }else {
                        battleTipsText = "Can't focus anymore";
                    }

                }
                if (commandIndexX == 0 && commandIndex == 1) {
                    if (!gamePanel.getBattleHandler().isUsedDance()){
                        battleTipsText = "Raises Hero's dexterity by 1 once";
                    } else {
                        battleTipsText = "Can't dance anymore";
                    }

                }
                if (commandIndexX == 1 && commandIndex == 1) {
                    if(!gamePanel.getBattleHandler().isUsedEnrage()){
                        battleTipsText = "Raises Hero's attack by 5 once";
                    } else {
                        battleTipsText = "Can't get any more enraged!";
                    }
                }

            }



        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));for(String line : battleTipsText.split("\n")){
                Color color = new Color(255,255,255,255);
                g2.setColor(color);
                g2.drawString(line,x,y);
                y += 80;
            }

        }



        public void drawBattleLogHero(int enemyIndex, int moveIndex){
            int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() *15);
            int y = 850;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
            //System.out.println("damage damage" + damage);
            char[] characters = currentBattleDialogue.toCharArray();
            displayLetterbyLetter(characters);


            for(String line : currentDialogue.split("\n")){
                Color color = new Color(255,255,255,255);
                g2.setColor(color);
                g2.drawString(line,x,y);
                y += 80;
            }

    }

    public void drawBattleLogEnemy(int enemyIndex, int moveIndex){
        int x = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() *15);
        int y = 850;
        String text = "";
        int damage = gamePanel.getBattleHandler().getDamage();
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        if (damage>0){

            text = gamePanel.getEnemy()[gamePanel.getCurrentMap()][enemyIndex].name + " used" + gamePanel.getEnemy()[gamePanel.getCurrentMap()][enemyIndex].attackMove[moveIndex]
                    + "\n" + " and caused " + damage + " damage!";
        }
        else {
            text = gamePanel.getEnemy()[gamePanel.getCurrentMap()][enemyIndex].name + " used " + gamePanel.getEnemy()[gamePanel.getCurrentMap()][enemyIndex].attackMove[moveIndex] + " but missed!";
        }
        char[] characters = text.toCharArray();
        displayLetterbyLetter(characters);
        for(String line : currentDialogue.split("\n")){
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(line,x,y);
            y += 80;
        }
    }


    public void  drawHeroUI(){

        g2.drawImage(heroFace,0,0, 100,100,null);
        //health bar
        double healthScale = (double) gamePanel.getSpriteSize() / gamePanel.getHero().getMaxHealth();
        double hpBarTotal = healthScale* gamePanel.getHero().getHealth();
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,1, gamePanel.getSpriteSize() *2,20);
        g2.setColor(new Color(255,0,30));
        g2.fillRect(102,0,(int)hpBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String healthText = "HP: " + gamePanel.getHero().getHealth() + "/" + gamePanel.getHero().getMaxHealth();
        g2.drawString(healthText,75+ gamePanel.getSpriteSize(),18);
        // mana bar
        double manaScale = (double) gamePanel.getSpriteSize() / gamePanel.getHero().getMaxMana();
        double manaBarTotal = manaScale* gamePanel.getHero().getMana();
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,22, gamePanel.getSpriteSize() *2,20);
        g2.setColor(new Color(30,0,255));
        g2.fillRect(102,21,(int)manaBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String manaText = "Mana: " + gamePanel.getHero().getMana() + "/" + gamePanel.getHero().getMaxMana();
        g2.drawString(manaText,70+ gamePanel.getSpriteSize(),36);
        // mana bar
        double expScale = (double) gamePanel.getSpriteSize() / gamePanel.getHero().getNextLevelExp();
        double expBarTotal = expScale* gamePanel.getHero().getExp();
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,42, gamePanel.getSpriteSize() *2,20);
        g2.setColor(new Color(234, 230, 4, 255));
        g2.fillRect(102,41,(int)expBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String expText = "EXP: " + gamePanel.getHero().getExp() + "/" + gamePanel.getHero().getNextLevelExp();
        g2.drawString(expText,70+ gamePanel.getSpriteSize(),56);


    }

    public void playCutscene()  {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("src/sprites/Loading_Animation_Wave_2.gif");
        g2.drawImage(image,0,0,1920,1080,null);






    }


    public void drawTitleScreen() {



        g2.setColor(new Color(77, 10, 162));
        g2.fillRect(0,0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text = "Project Nanos Magos";
        int x = getXforCenteredText(text);
        int y = gamePanel.getSpriteSize() *3;
        g2.setColor((Color.black));
        g2.drawString(text,x+10,y+10);
        g2.setColor(Color.cyan);
        g2.drawString(text,x,y);
        x = 1200;
        y += gamePanel.getSpriteSize() *4;
        try {
          image=  ImageIO.read(new FileInputStream("src/sprites/a2.png"));
        }catch (IOException e){

        }
        g2.drawImage(image,x,y, 720,492,null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "New Game";
        x = getXforCenteredText(text);
        y += gamePanel.getSpriteSize();
        g2.drawString(text,x,y);
        if(commandIndex ==0 ) {
            g2.drawString(">",x- gamePanel.getSpriteSize(),y);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y += gamePanel.getSpriteSize();
        g2.drawString(text,x,y);
        if(commandIndex ==1 ) {
            g2.drawString(">",x- gamePanel.getSpriteSize(),y);
        }

        text = "options";
        x = getXforCenteredText(text);
        y += gamePanel.getSpriteSize();
        g2.drawString(text,x,y);
        if(commandIndex ==2 ) {
            g2.drawString(">",x- gamePanel.getSpriteSize(),y);
        }

        text = "Credits";
        x = getXforCenteredText(text);
        y += gamePanel.getSpriteSize();
        g2.drawString(text,x,y);
        if(commandIndex ==3 ) {
            g2.drawString(">",x- gamePanel.getSpriteSize(),y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += gamePanel.getSpriteSize();
        g2.drawString(text,x,y);
        if(commandIndex ==4 ) {
            g2.drawString(">",x- gamePanel.getSpriteSize(),y);
        }
    }

    public void drawDialogueScreen() {

        int x = gamePanel.getSpriteSize() *2;
        int y = gamePanel.getSpriteSize() /2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getSpriteSize() *3);
        int height = gamePanel.getSpriteSize() *3;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
        x += gamePanel.getSpriteSize();
        y += gamePanel.getSpriteSize();
        if(gamePanel.getHero().isEncounteredNPC()) {


            if (npc.getDialogues()[npc.getDialogueSet()][npc.getDialogueIndex()] != null) {
                char[] characters = npc.getDialogues()[npc.getDialogueSet()][npc.getDialogueIndex()].toCharArray();
                displayLetterbyLetter(characters);
                if ((gamePanel.getKeyboardInputs().isEnterPressed())) {
                    characterIndex = 0;
                    combinedText = "";
                    if ((gamePanel.getGameState() == GamePanel.Gamestate.DIALOGUESTATE)) ;
                    npc.increaseDialogueIndex();
                    gamePanel.getKeyboardInputs().setEnterPressed(false);
                }
            } else {
                npc.setDialogueIndex(0);
                if ((gamePanel.getGameState() == GamePanel.Gamestate.DIALOGUESTATE)) {
                    gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
                    gamePanel.getHero().setEncounteredNPC(false);
                }
            }
        }

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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,36F));
        int x = gamePanel.getSpriteSize() *10;
        int y = (int)(gamePanel.getSpriteSize() *2.4);
        int width = gamePanel.getSpriteSize() *5;
        int height = gamePanel.getSpriteSize() *6;
        drawSubWindow(x,y,width,height);
        x += gamePanel.getSpriteSize() - 10;
        y += gamePanel.getSpriteSize() *1.5;
        g2.drawString("Save",x,y);
        if (commandIndex == 0) {
            g2.drawString(">",x-30,y);
        }
        y += gamePanel.getSpriteSize();
        g2.drawString("Save and Exit",x,y);
        if (commandIndex == 1) {
            g2.drawString(">",x-30,y);
        }
        y += gamePanel.getSpriteSize();
        g2.drawString("Exit to Menu",x,y);
        if (commandIndex == 2) {
            g2.drawString(">",x-30,y);
        }
        y += gamePanel.getSpriteSize();
        g2.drawString("Back",x,y);
        if (commandIndex == 3) {
            g2.drawString(">",x-30,y);
        }
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
        return gamePanel.getScreenWidth() /2 -length;
    }
    public int getXforRightAllignement(String text,int tailX) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
        return tailX - length;
    }

    public void displayLetterbyLetter(char[] characters){
        if ((characterIndex < characters.length)){
            String string = String.valueOf(characters[characterIndex]);
            combinedText = combinedText + string;
            currentDialogue = combinedText;
            characterIndex++;

        }
    }

    public void resetLetterbyLetter(){
        characterIndex = 0;
        combinedText = "";
    }

    public void resetCreditsY() {
        creditsY = 1000 ;
    }

    public int getItemBattleIndex(int index) {
        return itemBattleIndex.get(index);
    }



    public int getItemCounter() {
        return itemCounter;
    }

    public void resetItemCounter() {
        itemCounter = -1;
    }
    public void resetcommandIndex(){
        commandIndex = 0;
        commandIndexX = 0;
    }

    public void setCurrentBattleDialogue(String currentBattleDialogue) {
        this.currentBattleDialogue = currentBattleDialogue;
    }

    public void setBattleTipsText(String battleTipsText) {
        this.battleTipsText = battleTipsText;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getCurrentBattleDialogue() {
        return currentBattleDialogue;
    }

    public int getCommandIndex() {
        return commandIndex;
    }

    public int getCommandIndexX() {
        return commandIndexX;
    }

    public int getSlotCollumn() {
        return slotCollumn;
    }

    public int getSlotRow() {
        return slotRow;
    }
    public Merchant getMerchant() {
        return merchant;
    }

    public TradeState getTradeState() {
        return tradeState;
    }
    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public void setCommandIndex(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    public void setCommandIndexX(int commandIndexX) {
        this.commandIndexX = commandIndexX;
    }


    public void setSlotCollumn(int slotCollumn) {
        this.slotCollumn = slotCollumn;
    }

    public void setSlotRow(int slotRow) {
        this.slotRow = slotRow;
    }
    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public void setTradeState(TradeState tradeState) {
        this.tradeState = tradeState;
    }
    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public Nikolaidis getNikolaidis() {
        return nikolaidis;
    }

    public void setNikolaidis(Nikolaidis nikolaidis) {
        this.nikolaidis = nikolaidis;
    }

    public void setQuizState(QuizState quizState) {
        this.quizState = quizState;
    }

    public QuizState getQuizState() {
        return quizState;
    }

    public int getQuizIndex() {
        return quizIndex;
    }

    public void setQuizIndex(int quizIndex) {
        this.quizIndex = quizIndex;
    }
}
