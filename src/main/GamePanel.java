package main;

import backroundTile.BackgroundTileManager;
import creature.Hero;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalSpriteSize = 16;
    final int scaling = 5;

    final public int spriteSize = originalSpriteSize * scaling; //5*16=80px
    final public int maxScreenColumn = 24;
    final public int maxScreenRow = 13;
    final public int screenHeight = spriteSize*maxScreenRow; //13*80=1040px
    final public int screenWidth = spriteSize* maxScreenColumn;

    final public int maxWorldColumn = 50;
    final public int maxWorldRow = 50;
    final public int maxWorldWidth = maxWorldColumn*spriteSize;
    final public int maxWorldHeight = maxWorldRow*spriteSize;


    int FPS = 60;


    Thread thread;
    KeyboardInputs keyboardInputs = new KeyboardInputs();
    public Hero hero = new Hero(this,keyboardInputs);
    BackgroundTileManager backgroundTileManager = new BackgroundTileManager(this);
    public ObjectPlacer objectPlacer = new ObjectPlacer(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public SuperObject superObject[] = new SuperObject[10];

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.magenta);
        this.setDoubleBuffered(true); // might improve rendering idk
        this.addKeyListener(keyboardInputs);
        this.setFocusable(true); //game panel will focus on receiving keyboard inputs

    }

    public void setupGame() {
        objectPlacer.setObject();
    }

    public void beginThread(){

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (thread!= null){ //implementing the game loop using the "sleep" method (stack overflow is your friend)

            double drawInterval = 1000000000/FPS; //dividing 1 second by 60 frames
            double nextDrawTime = System.nanoTime() + drawInterval; // drawing every 0.0166 secs
            //System.out.println("exe is running");
            refresh();
            repaint(); // refreshing and repainting the screen constantly


            try {
                double remainingTime = nextDrawTime - System.nanoTime(); //how much time remains after every drawing
                remainingTime = remainingTime/1000000; //converting nanosecond to milli
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval; // setting next draw time

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refresh(){
        hero.refresh();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // changing the Graphics to Graphics2D
        backgroundTileManager.draw(g2);
        for (int i = 0; i <superObject.length; i++) {
            if(superObject[i]!=null){
                superObject[i].draw(g2,this);
            }
        }
        hero.draw(g2);
        
    }

}
