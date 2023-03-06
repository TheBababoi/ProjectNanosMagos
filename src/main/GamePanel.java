package main;

import backroundTile.BackgroundTileManager;
import creature.Hero;
import creature.NPC;
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

    //system
    int FPS = 60;
    Thread thread;
    KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    BackgroundTileManager backgroundTileManager = new BackgroundTileManager(this);
    public UI ui = new UI(this);
    public AssetPlacer assetPlacer = new AssetPlacer(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this);


    //creatures and objects
    public Hero hero = new Hero(this,keyboardInputs);
    public SuperObject superObject[] = new SuperObject[10];
    public NPC npc[] = new NPC[10];

    //game state
    public int gameState;
    public  final int playState = 1;
    public final int pauseState = 2;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.magenta);
        this.setDoubleBuffered(true); // might improve rendering idk
        this.addKeyListener(keyboardInputs);
        this.setFocusable(true); //game panel will focus on receiving keyboard inputs

    }

    public void setupGame() {
        assetPlacer.setObject();
        assetPlacer.setNPC();
        playMusic(0);
        gameState = playState;
    }

    public void beginThread(){

        thread = new Thread(this);
        thread.start();
    }

   // @Override
   // public void run() {
    //    while (thread!= null){ //implementing the game loop using the "sleep" method (stack overflow is your friend)

    //        double drawInterval = 1000000000/FPS; //dividing 1 second by 60 frames
       //     double nextDrawTime = System.nanoTime() + drawInterval; // drawing every 0.0166 secs
            //System.out.println("exe is running");
       //     refresh();
      //      repaint(); // refreshing and repainting the screen constantly


       //     try {
      //          double remainingTime = nextDrawTime - System.nanoTime(); //how much time remains after every drawing
      //         remainingTime = remainingTime/1000000; //converting nanosecond to milli
        //        Thread.sleep((long)remainingTime);
        //        nextDrawTime += drawInterval; // setting next draw time

        //    } catch (InterruptedException e) {
         //       throw new RuntimeException(e);
       //     }
       //  }
      //   }

    @Override
    public void  run() {

        double drawInterval = 1000000000/FPS; //dividing 1 second by 60 frames
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        double nextDrawTime = System.nanoTime() + drawInterval; // drawing every 0.0166 secs
        while (thread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                refresh();
                repaint();
                delta--;
            }

        }
    }

    public void refresh(){
        if(gameState == pauseState){

        }
        if (gameState == playState){
        hero.refresh();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null){
                    npc[i].refresh();
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // changing the Graphics to Graphics2D
        backgroundTileManager.draw(g2);

        //debug
        long drawStart = 0;
        if ((keyboardInputs.debugMode)){
            drawStart = System.nanoTime();
        }


        //obects
        for (int i = 0; i <superObject.length; i++) {
            if(superObject[i]!=null){
                superObject[i].draw(g2,this);
            }
        }
        //NPCs
        for (int i = 0; i <npc.length; i++) {
            if (npc[i] !=null ){
                npc[i].draw(g2);
            }
        }
        //hero
        hero.draw(g2);

        //ui
        ui.draw(g2);

        //debug
        if(keyboardInputs.debugMode) {
            long drawEnd = System.nanoTime();
            long totalTime = drawEnd - drawStart;
            g2.setColor(Color.black);
            g2.drawString("Draw time " + totalTime, 10, 200);
        }
        
    }

    public void playMusic(int index){
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int index){
        soundEffect.setFile(index);
        soundEffect.play();
    }

}
