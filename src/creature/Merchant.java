package creature;

import Items.Item;
import Items.consumables.Gold;
import Items.consumables.Mushroom;
import Items.equipment.LegendaryPen;
import main.GamePanel;
import main.UI;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Merchant extends NPC{
    public ArrayList<Item> inventory = new ArrayList<>();
    public int inventorySize = 25;
    public BufferedImage merchant1,merchant2,merchant3;
    public Merchant(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/merchant");
        direction = "down";
        speed = 0;
        actionCounterMax = 300;
        setDialogue();
        setItems();
    }

    @Override
    public void setDialogue() {
        dialogues[0] = "Hey psst want some deals?";
        dialogues[0] = "Hey psst want some deals?";

    }

    @Override
    public void getSprites(String filePath) {
        try{
            up1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            down1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            left1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            right1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            merchant1 = ImageIO.read(new FileInputStream(filePath+"/merchant1.png"));
            merchant2 = ImageIO.read(new FileInputStream(filePath+"/merchant2.png"));
            merchant3 = ImageIO.read(new FileInputStream(filePath+"/merchant3.png"));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setItems(){
        inventory.add(new Mushroom(gamePanel));
        inventory.add(new Mushroom(gamePanel));
        inventory.add(new Gold(gamePanel));
        inventory.add(new LegendaryPen(gamePanel));
    }

    @Override
    public void speak() {
        super.speak();
        gamePanel.gameState = GamePanel.Gamestate.TRADEMENU;
        gamePanel.ui.tradeState = UI.TradeState.SELECT;
        gamePanel.ui.merchant = this;
    }
}
