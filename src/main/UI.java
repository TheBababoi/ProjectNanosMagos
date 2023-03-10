package main;
import backroundTile.BackgroundTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font lumos,ribbon;
    BufferedImage image,heroFace;
    public  boolean messageOn = false;
    public String message = "";
    public String currentDialogue;
    public int commandIndex = 0;
    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        try{
            heroFace = ImageIO.read(new FileInputStream("src/sprites/UIElements/face.jpg"));
        } catch(IOException e){
            e.printStackTrace();
        }
        try {
            InputStream inputStream = new FileInputStream("src/data/fonts/LUMOS.TTF");
            lumos = Font.createFont(Font.TRUETYPE_FONT,inputStream);
            InputStream inputStream2 = new FileInputStream("src/data/fonts/DavysRibbons.ttf");
            ribbon = Font.createFont(Font.TRUETYPE_FONT,inputStream);
        }catch (Exception E){

        }


    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) throws InterruptedException {
        g2.setFont(lumos);
        this.g2 = g2;
        if(gamePanel.gameState == GamePanel.Gamestate.TITLESCREEM){
            drawTitleScreen();
        }
        if (gamePanel.gameState== GamePanel.Gamestate.PAUSESTATE){
            drawPauseScreen();
        } else if (gamePanel.gameState == GamePanel.Gamestate.PLAYSTATE) {
            drawHeroUI();

        } else if (gamePanel.gameState == GamePanel.Gamestate.DIALOGUESTATE) {
            drawDialogueScreen();
        } else if ((gamePanel.gameState == GamePanel.Gamestate.CUTSCENE)) {
            playCutscene();
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
        double expScale = (double)gamePanel.spriteSize/gamePanel.hero.maxExp;
        double expBarTotal = expScale*gamePanel.hero.exp;
        g2.setColor(new Color(35,35,35));
        g2.fillRect(101,42,gamePanel.spriteSize*2,20);
        g2.setColor(new Color(234, 230, 4, 255));
        g2.fillRect(102,41,(int)expBarTotal*2,20);
        g2.setColor((Color.black));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        String expText = "EXP: " + gamePanel.hero.exp + "/" +gamePanel.hero.maxExp;
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
        for(String line : currentDialogue.split("\n"));{
            Color color = new Color(255,255,255,255);
            g2.setColor(color);
            g2.drawString(currentDialogue,x,y);
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

    public void drawPauseScreen() {
        g2.drawString("PAUSED",  getXforCenteredText("PAUSED") , gamePanel.screenHeight/2); // java does not like the x axis huh?
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
        return gamePanel.screenWidth/2 -length;
    }
}
