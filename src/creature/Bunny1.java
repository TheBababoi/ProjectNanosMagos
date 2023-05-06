package creature;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Bunny1 extends NPC {
    public Bunny1(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/bunny1");
        direction = "right";
        speed = 2;
        actionCounterMax = 30;
        setDialogue();
    }

    @Override
    public void setDialogue() {

        dialogues[0][0] = "Oh, hello!";
        dialogues[0][1] = "I was walking around the labyrinth, but my eggs fell off the basket and \n now some wild worms appeared";
        dialogues[0][2] = "I dont want my eggs \n to be eaten by these filthy worms";

        dialogues[1][0] = "Please save my eggs!";
        dialogues[1][1] = "I will even let you keep them";
        dialogues[1][2] = "Just don't let the worms eat them!";


    }















}
