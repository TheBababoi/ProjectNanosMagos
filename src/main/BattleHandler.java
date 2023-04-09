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
        gamePanel.music.stop();
        gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
        gamePanel.ui.currentDialogue = battle;
        gamePanel.ui.drawDialogueScreen();


    }

    public void calculateHeroAttack(int index) {
        gamePanel.playSoundEffect(gamePanel.hero.getAttackSoundIndex()[index]);
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        int chanceToHit = gamePanel.hero.getAttackAccuracy()[index] + roll + accuracyBuff ;
        if (usedFocus){
            accuracyBuff = 1;
        }
        System.out.println("hit roll: " + chanceToHit +  "\n");
        gamePanel.hero.setMana(gamePanel.hero.getMana()- gamePanel.hero.getAttackCost()[index]);


        if (gamePanel.enemy[gamePanel.currentMap][monsterIndex].dexterity <= chanceToHit) {
            if (usedEnrage){
                attackBuff = 5;
            }
            damage = -gamePanel.enemy[gamePanel.currentMap][monsterIndex].defence + gamePanel.hero.getAttackPower()[index] + gamePanel.hero.getStrength() + attackBuff;
            if (damage < 0) {
                damage = 0;
            }
            gamePanel.enemy[gamePanel.currentMap][monsterIndex].health -= damage;
            if (gamePanel.enemy[gamePanel.currentMap][monsterIndex].health < 0) {
                gamePanel.enemy[gamePanel.currentMap][monsterIndex].health = 0;
            }
            System.out.println("damage " + damage + "\n");

        } else {
            damage = 0;

        }

        if (gamePanel.battleHandler.damage > 0) {
            gamePanel.ui.setCurrentBattleDialogue("Hero used   " + gamePanel.hero.getAttackMove()[index]
                    + "\n" + " and caused " + gamePanel.battleHandler.damage + " damage!");
        } else {
            gamePanel.ui.setCurrentBattleDialogue("Hero used " + gamePanel.hero.getAttackMove()[index] + " but missed!");
        }
    }

    public void calculateEnemyAttack(int choice) {
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        if (usedDance){
            dexterityBuff = 1;
        }
        int chanceToHit = gamePanel.enemy[gamePanel.currentMap][monsterIndex].attackAccuracy[choice] + roll - dexterityBuff;
        System.out.println("enemy hit roll: " + chanceToHit + "\n");


        if (gamePanel.hero.getDexterity() <= chanceToHit) {
            System.out.println("enemy choice" + choice);
            damage = -gamePanel.hero.getDefence() + gamePanel.enemy[gamePanel.currentMap][monsterIndex].attackPower[choice];
            System.out.println("enemy damage " + damage + "\n");
            if (damage < 0) {
                damage = 0;
            }
            gamePanel.hero.setHealth(gamePanel.hero.getHealth() - damage);
            if (gamePanel.hero.getHealth() < 0) {
                gamePanel.hero.setHealth(0);
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
        gamePanel.hero.setMana(gamePanel.hero.getMana() + gamePanel.hero.getMaxMana()/3);
        damage = 0;
        if (gamePanel.hero.getMaxMana()< gamePanel.hero.getMana()){
            gamePanel.hero.setMana(gamePanel.hero.getMaxMana());
        }
    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public String getBattle() {
        return battle;
    }

    public int getDamage() {
        return damage;
    }

    public int getMonsterIndex() {
        return monsterIndex;
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    public int getDexterityBuff() {
        return dexterityBuff;
    }

    public int getAccuracyBuff() {
        return accuracyBuff;
    }


    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setBattle(String battle) {
        this.battle = battle;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setMonsterIndex(int monsterIndex) {
        this.monsterIndex = monsterIndex;
    }

    public void setAttackBuff(int attackBuff) {
        this.attackBuff = attackBuff;
    }

    public void setDexterityBuff(int dexterityBuff) {
        this.dexterityBuff = dexterityBuff;
    }

    public void setAccuracyBuff(int accuracyBuff) {
        this.accuracyBuff = accuracyBuff;
    }
}

