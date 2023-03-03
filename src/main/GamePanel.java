package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalSpriteSize = 16;
    final int scaling =5;

    final int spriteSize = originalSpriteSize * scaling; //5*16=80px
    final int maxScreenCollumn = 24;
    final int maxScreenRow = 13;
    final int screenHeight = spriteSize*maxScreenRow; //13*80=1040px
    final int screenWidth = spriteSize*maxScreenCollumn;


    Thread thread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.magenta);
        this.setDoubleBuffered(true); // might improve rendering idk

    }

    public void beginThread(){

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

    }
}
