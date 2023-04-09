package main;

import java.util.Random;

public class BattleHandler {
    private GamePanel gamePanel;
    private String battle = "Prepare for battle!";
    private int damage;
    private int monsterIndex;
    private int attackBuff = 0 ,dexterityBuff = 0, accuracyBuff =0;
    private boolean usedDance = false, usedEnrage = false , usedFocus =false;


    public BattleHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void startBattle(int index) {
        monsterIndex = index;
        gamePanel.getMusic().stop();
        gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
        gamePanel.getUi().setCurrentDialogue(battle);
        gamePanel.getUi().drawDialogueScreen();


    }

    public void calculateHeroAttack(int index) {
        gamePanel.playSoundEffect(gamePanel.getHero().getAttackSoundIndex()[index]);
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        int chanceToHit = gamePanel.getHero().getAttackAccuracy()[index] + roll + accuracyBuff ;
        if (usedFocus){
            accuracyBuff = 1;
        }
        System.out.println("hit roll: " + chanceToHit +  "\n");
        gamePanel.getHero().setMana(gamePanel.getHero().getMana()- gamePanel.getHero().getAttackCost()[index]);


        if (gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].dexterity <= chanceToHit) {
            if (usedEnrage){
                attackBuff = 5;
            }
            damage = -gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].defence + gamePanel.getHero().getAttackPower()[index] + gamePanel.getHero().getStrength() + attackBuff;
            if (damage < 0) {
                damage = 0;
            }
            gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].health -= damage;
            if (gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].health < 0) {
                gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].health = 0;
            }
            System.out.println("damage " + damage + "\n");

        } else {
            damage = 0;

        }

        if (gamePanel.getBattleHandler().damage > 0) {
            gamePanel.getUi().setCurrentBattleDialogue("Hero used   " + gamePanel.getHero().getAttackMove()[index]
                    + "\n" + " and caused " + gamePanel.getBattleHandler().damage + " damage!");
        } else {
            gamePanel.getUi().setCurrentBattleDialogue("Hero used " + gamePanel.getHero().getAttackMove()[index] + " but missed!");
        }
    }

    public void calculateEnemyAttack(int choice) {
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        if (usedDance){
            dexterityBuff = 1;
        }
        int chanceToHit = gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].attackAccuracy[choice] + roll - dexterityBuff;
        System.out.println("enemy hit roll: " + chanceToHit + "\n");


        if (gamePanel.getHero().getDexterity() <= chanceToHit) {
            System.out.println("enemy choice" + choice);
            damage = -gamePanel.getHero().getDefence() + gamePanel.getEnemy()[gamePanel.getCurrentMap()][monsterIndex].attackPower[choice];
            System.out.println("enemy damage " + damage + "\n");
            if (damage < 0) {
                damage = 0;
            }
            gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() - damage);
            if (gamePanel.getHero().getHealth() < 0) {
                gamePanel.getHero().setHealth(0);
            }


        } else {
            damage = 0;

        }

    }

    public void setUsedDance(boolean usedDance) {
        this.usedDance = usedDance;
        damage = 0;
    }

    public void setUsedEnrage(boolean usedEnrage) {
        this.usedEnrage = usedEnrage;
        damage = 0;
    }

    public void setUsedFocus(boolean usedFocus) {
        this.usedFocus = usedFocus;
        damage = 0;
    }

    public boolean isUsedDance() {
        return usedDance;
    }

    public boolean isUsedEnrage() {
        return usedEnrage;
    }

    public boolean isUsedFocus() {
        return usedFocus;

    }

    public void resetBuffs(){
        usedDance = false;
        usedEnrage = false;
        usedFocus = false;
    }

    public void meditate() {
        gamePanel.getHero().setMana(gamePanel.getHero().getMana() + gamePanel.getHero().getMaxMana()/3);
        damage = 0;
        if (gamePanel.getHero().getMaxMana()< gamePanel.getHero().getMana()){
            gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getMonsterIndex() {
        return monsterIndex;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


}

