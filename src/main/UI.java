package main;



import java.awt.*;


public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font font;
    public  boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        font = new Font("Forte",Font.PLAIN,40);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.white);
        if (gamePanel.gameState== gamePanel.pauseState){
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.playState) {

        }
    }

    public void drawPauseScreen() {
        g2.drawString("PAUSED",  getXforCenteredText("PAUSED") , gamePanel.screenHeight/2); // java does not like the x axis huh?
    }

    public int getXforCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2;
        return gamePanel.screenWidth/2 -length;
    }
}
