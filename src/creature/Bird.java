package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Bird extends NPC {
    public Bird(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/bird");
        direction = "right";
        speed = 2;
        actionCounterMax = 30;
        setDialogue();
    }

    @Override
    public void setDialogue() {

        dialogues[0][0] = "Hello Hero!";
        dialogues[0][1] = "I'm the Flower Guy";
        dialogues[0][2] = "I can only walk like a crab \n because of my roots!";

        dialogues[1][0] = "One day this game might be completed";
        dialogues[1][1] = "One day  I might get some proper dialogue";
        dialogues[1][2] = "I'm sick at looking at this code";

        dialogues[2][0] = "I wonder if anyone will even help me \n with this shit";
    }















}
