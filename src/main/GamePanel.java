package main;

import backroundTile.BackgroundTileManager;
import creature.Enemy;
import creature.Hero;
import creature.NPC;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    final int originalSpriteSize = 16;
    final int scaling = 5;

    final public int spriteSize = originalSpriteSize * scaling; //5*16=80px
    final public int maxScreenColumn = 24;
    final public int maxScreenRow = 13;
    public int screenHeight = spriteSize*maxScreenRow; //13*80=1040px
    public int screenWidth = spriteSize* maxScreenColumn;


    final public int maxWorldColumn = 50;
    final public int maxWorldRow = 50;

    //system
    int FPS = 60;
    Config config = new Config(this);
    Thread thread;
    public KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    Sound music = new Sound();
    public Sound soundEffect = new Sound();
    BackgroundTileManager backgroundTileManager = new BackgroundTileManager(this);
    public UI ui = new UI(this);
    public AssetPlacer assetPlacer = new AssetPlacer(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public EventHandler eventHandler = new EventHandler(this);
    public BattleHandler battleHandler = new BattleHandler(this);
    public boolean fullScreenOn = false;


    //creatures and objects
    public Hero hero = new Hero(this,keyboardInputs);
    public SuperObject superObject[] = new SuperObject[10];
    public NPC npc[] = new NPC[10];
    public Enemy enemy[] = new Enemy[10];

    //game state
    public Gamestate gameState;
    public enum Gamestate {
        CUTSCENE,
        TITLESCREEM,
        OPTIONSMENU,
        PLAYSTATE,
        PAUSESTATE,
        DIALOGUESTATE,
        BATTLESTATEHERO, BATTLESTATEENEMY,
        BATTLELOGHERO, BATTLEWON, BATTLELOST, BATTLELOGENEMY,
        HEROSTATS
    }


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.magenta);
        this.setDoubleBuffered(true); // might improve rendering idk
        this.addKeyListener(keyboardInputs);
        this.setFocusable(true); //game panel will focus on receiving keyboard inputs

    }

    public void setupGame() {
        if (fullScreenOn){
            setFullScreen();
        }

        playMusic(0);
        gameState = Gamestate.CUTSCENE;
        assetPlacer.setObject();
        assetPlacer.setNPC();
        assetPlacer.setEnemy();



    }
    public void restart(){
        assetPlacer.setObject();
        assetPlacer.setNPC();
        assetPlacer.setEnemy();
        hero.setDefault();
    }

    public void beginThread(){

        thread = new Thread(this);
        thread.start();
    }


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
        if(gameState == Gamestate.PAUSESTATE){

        }
        if (gameState == Gamestate.PLAYSTATE){
            hero.refresh();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null){
                    npc[i].refresh();
                }
            }
            for (int i = 0;i< enemy.length; i++){
                if (enemy[i] != null){
                    enemy[i].refresh();
                }
            }
        }
    }
    public void setFullScreen(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double width = screenSize.getWidth();

        double height = screenSize.getHeight();

        Main.mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        screenWidth = (int) width;

        screenHeight = (int) height;




    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // changing the Graphics to Graphics2D

        if(gameState == Gamestate.TITLESCREEM){
            try {
                ui.draw(g2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } else  {
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

            //enemies
            for (int i = 0; i <enemy.length; i++) {
                if (enemy[i] !=null ){
                        enemy[i].draw(g2);
                }
            }
            //hero
            hero.draw(g2);

            //ui
            try {
                ui.draw(g2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //debug
            if(keyboardInputs.debugMode) {
                long drawEnd = System.nanoTime();
                long totalTime = drawEnd - drawStart;
                g2.setColor(Color.black);
                g2.drawString("Draw time " + totalTime, 10, 200);
            }
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
