package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class AchatGood extends NPC {
    public AchatGood(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/achatGood");
        direction = "right";
        speed = 0;
        actionCounterMax = 30;
        setDialogue();
    }

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

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setDialogue() {

        dialogues[0][0] = "Ughh... What happened?";
        dialogues[0][1] = "My mind is blank right now.\n I don't remember anything.";
        dialogues[0][2] = " Feels like I was in a deep sleep.";

        dialogues[1][0] = "(Hero talks about the fight)";
        dialogues[1][1] = " So, we fought and you beat me?";
        dialogues[1][2] = "Could it be by defeating me \n you freed me from some sort of mind control?";
        dialogues[1][3] = " Whatever the case is,\n thank you very much for freeing me.";

        dialogues[2][0] = "I feel better by every passing second.";
        dialogues[2][1] = "Memories are coming back, even from my old life.";
        dialogues[2][2] = "(Hero asks about his old life)";
        dialogues[2][3] = "Before we entered this world, we were from another universe.";

        dialogues[3][0] = "One day though I received a laptop\n as a gift from an anonymous sender.";
        dialogues[3][1] = "Upon turning it on, it sucked me\n and everyone around inside this \"digital world\".";
        dialogues[3][2] = "Unfortunately, we don't know how \n to return to the real world.";
        dialogues[3][3] = "Most of us here have forgotten about \n our real lives, it's been a long time.";

        dialogues[3][0] = "I cannot help you more, but maybe my friend, Kokkinidis,\n could give you more information.";
        dialogues[3][1] = "He lives in the next area, Sunny Bay.";
        dialogues[3][2] = "As a thank you for releasing me from \n the mind control, I will  let you move forward.";
        dialogues[3][3] = " Wishing you the best of luck, brave hero.\n" +
                "                We shall meet again.";
    }















}
