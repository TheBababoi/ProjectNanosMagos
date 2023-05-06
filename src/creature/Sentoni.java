package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Sentoni extends NPC {
    public Sentoni(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/sentoni");
        direction = "down";
        speed = 2;
        actionCounterMax = 30;
        setDialogue();
    }

    @Override
    public void setDialogue() {


        dialogues[0][0] = "Hope it doesn't rain today.";
        dialogues[0][1] = "I hate getting wet.";


        dialogues[1][0] = "I saw the faucets around here.";
        dialogues[1][1] = "I wish they won't notice me.";


        dialogues[2][0] = "Don't let my face fool you. ";
        dialogues[2][1] = "I'm not evil,\n just angry with people not caring for the environment.";
        dialogues[3][0] = "Want to stay dry and clean..";
    }















}
