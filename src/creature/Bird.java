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

        dialogues[0][0] = "You look weak.\n Go away.";
        dialogues[0][1] = "I wont allow a weakling like you to talk to my master.";
        dialogues[0][2] = "(You hear a gentle voice from inside of the house\n calling the bird s name)";

        dialogues[1][0] = "???: Pierce, be kind to the stranger.";
        dialogues[1][1] = "Pierce: But sir, he s far too weak\n and his intelligence doesn't seem to be high either.";
        dialogues[1][2] = "???: Do not be rude to others my friend.\n You don't know the story and the pains of the others.";
        dialogues[1][3] = "However, you may be right. He doesn't seem strong.";
        dialogues[1][4] = "If you wish I shall test your strength and wisdom";
        dialogues[1][5] = "If you pass the test, you will be rewarded.";
    }



















}
