package creature;

import main.GamePanel;


import java.awt.image.BufferedImage;

public abstract class Enemy extends Creature{
    public String name;
    public BufferedImage battleImage;
    public int health,maxHealth,strength,defence,dexterity;

    public int enemyChoice =99;
    public String[] attackMove = new String[4];
    public int[] attackPower = new int[4];
    public int[] attackAccuracy = new int[4];

    public Enemy(GamePanel gamePanel) {
        super(gamePanel);


    }

    public abstract void setBattleStats();

    public abstract void setBattleSprites();

    public abstract void enemyMoves();




}
