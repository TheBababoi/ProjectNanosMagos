package main;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font tolkienFont,endorFont;
    public  boolean messageOn = false;
    public String message = "";
    public String currentDialogue;
    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        try {
            InputStream inputStream = new FileInputStream("src/data/fonts/LUMOS.TTF");
            endorFont = Font.createFont(Font.TRUETYPE_FONT,inputStream);
        }catch (Exception E){

        }


    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(endorFont);
        g2.setColor(Color.white);
        if (gamePanel.gameState== gamePanel.pauseState){
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.playState) {

        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            drawDialogueScreen();
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
