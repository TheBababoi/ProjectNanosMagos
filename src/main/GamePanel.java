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

    final private int originalSpriteSize = 16;
    final private int scaling = 5;

    final private int spriteSize = originalSpriteSize * scaling; //5*16=80px
    final private int maxScreenColumn = 24;
    final private int maxScreenRow = 13;
    private int screenHeight = spriteSize*maxScreenRow; //13*80=1040px
    private int screenWidth = spriteSize* maxScreenColumn;



    final private int maxWorldColumn = 50;
    final private int maxWorldRow = 50;
    private final int maxMap = 10;
    private int currentMap = 0;

    //system
    private int FPS = 60;
    private Config config = new Config(this);
    private Thread thread;
    private KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    private Sound music = new Sound();
    private Sound soundEffect = new Sound();
    private BackgroundTileManager backgroundTileManager = new BackgroundTileManager(this);
    private UI ui = new UI(this);
    private AssetPlacer assetPlacer = new AssetPlacer(this);
    private CollisionCheck collisionCheck = new CollisionCheck(this);
    private EventHandler eventHandler = new EventHandler(this);
    private BattleHandler battleHandler = new BattleHandler(this);
    private SaveLoad saveLoad = new SaveLoad(this);
    private boolean fullScreenOn = false;


    //creatures and objects
    private Hero hero = new Hero(this,keyboardInputs);
    private SuperObject superObject[][] = new SuperObject[maxMap][10];
    private NPC npc[][] = new NPC[maxMap][10];
    private Enemy enemy[][] = new Enemy[maxMap][10];

    //game state
    private Gamestate gameState;


    public enum Gamestate {
        CUTSCENE,
        TITLESCREEM,
        OPTIONSMENU,
        CREDITS,
        PLAYSTATE,
        PAUSESTATE,
        DIALOGUESTATE,
        BATTLESTATEHERO, BATTLESTATEENEMY,
        BATTLELOGHERO, BATTLEWON, BATTLELOST, BATTLELOGENEMY,
        HEROSTATS,
        TRANSITION,TRANSITIONBATTLE,TRANSITIONFROMBATTLE,
        TRADEMENU,TRADEDIALOGUE,
    }


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
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
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null){
                    npc[currentMap][i].refresh();
                }
            }
            for (int i = 0;i< enemy[1].length; i++){
                if (enemy[currentMap][i] != null){
                    enemy[currentMap][i].refresh();
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
            if ((keyboardInputs.isDebugMode())){
                drawStart = System.nanoTime();
            }


            //obects
            for (int i = 0; i <superObject[1].length; i++) {
                if(superObject[currentMap][i]!=null){
                    superObject[currentMap][i].draw(g2);
                }
            }
            //NPCs
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] !=null ){
                    npc[currentMap][i].draw(g2);
                }
            }

            //enemies
            for (int i = 0; i <enemy[1].length; i++) {
                if (enemy[currentMap][i] !=null ){
                        enemy[currentMap][i].draw(g2);
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
            if(keyboardInputs.isDebugMode()) {
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


    public int getSpriteSize() {
        return spriteSize;
    }


    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getMaxWorldColumn() {
        return maxWorldColumn;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getMaxMap() {
        return maxMap;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public Config getConfig() {
        return config;
    }

    public KeyboardInputs getKeyboardInputs() {
        return keyboardInputs;
    }

    public Sound getMusic() {
        return music;
    }

    public Sound getSoundEffect() {
        return soundEffect;
    }

    public BackgroundTileManager getBackgroundTileManager() {
        return backgroundTileManager;
    }

    public UI getUi() {
        return ui;
    }

    public AssetPlacer getAssetPlacer() {
        return assetPlacer;
    }

    public CollisionCheck getCollisionCheck() {
        return collisionCheck;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public BattleHandler getBattleHandler() {
        return battleHandler;
    }

    public SaveLoad getSaveLoad() {
        return saveLoad;
    }

    public boolean isFullScreenOn() {
        return fullScreenOn;
    }

    public Hero getHero() {
        return hero;
    }

    public SuperObject[][] getSuperObject() {
        return superObject;
    }

    public NPC[][] getNpc() {
        return npc;
    }

    public Enemy[][] getEnemy() {
        return enemy;
    }

    public Gamestate getGameState() {
        return gameState;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public void setGameState(Gamestate gameState) {
        this.gameState = gameState;
    }

    public void setFullScreenOn(boolean fullScreenOn) {
        this.fullScreenOn = fullScreenOn;
    }

}
