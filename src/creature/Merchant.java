package creature;

import Items.Item;
import Items.consumables.*;
import Items.equipment.*;
import main.GamePanel;
import main.UI;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Merchant extends NPC{
    private ArrayList<Item> inventory = new ArrayList<>();
    private BufferedImage merchant1,merchant2,merchant3;
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
        dialogues[0][0] = "Hey psst want some deals?";


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
        inventory.add(new Watermelon(gamePanel));
        inventory.add(new Mushroom(gamePanel));
        inventory.add(new LegendaryPen(gamePanel));
        inventory.add(new FortifiedPotion(gamePanel));
        inventory.add(new FortifiedPotion2(gamePanel));
        inventory.add(new HealthPotion(gamePanel));
        inventory.add(new HealthPotion2(gamePanel));
        inventory.add(new ManaPotion(gamePanel));
        inventory.add(new ManaPotion2(gamePanel));
        inventory.add(new CoolStar(gamePanel));
        inventory.add(new Diamond(gamePanel));
        inventory.add(new Lolipop(gamePanel));
        inventory.add(new MagicLock(gamePanel));
    }

    @Override
    public void speak() {
        super.speak();
        gamePanel.setGameState(GamePanel.Gamestate.TRADEMENU);
        gamePanel.getUi().setTradeState(UI.TradeState.SELECT);
        gamePanel.getUi().setMerchant(this);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public BufferedImage getMerchant1() {
        return merchant1;
    }

    public BufferedImage getMerchant2() {
        return merchant2;
    }

    public BufferedImage getMerchant3() {
        return merchant3;
    }

}
