package main;

import java.util.Random;

public class BattleHandler {
    GamePanel gamePanel;
    String battle = "Prepare for battle!";
    int damage;
    int monsterIndex;


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
        gamePanel.playSoundEffect(gamePanel.hero.attackSoundIndex[index]);
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        int chanceToHit = gamePanel.hero.attackAccuracy[index] + roll;
        System.out.println("hit roll: " + chanceToHit + "\n");


        if (gamePanel.enemy[gamePanel.currentMap][monsterIndex].dexterity <= chanceToHit) {
            damage = -gamePanel.enemy[gamePanel.currentMap][monsterIndex].defence + gamePanel.hero.attackPower[index] + gamePanel.hero.strength;
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
            gamePanel.ui.setCurrentBattleDialogue("Hero used   " + gamePanel.hero.attackMove[index]
                    + "\n" + " and caused " + gamePanel.battleHandler.damage + " damage!");
        } else {
            gamePanel.ui.setCurrentBattleDialogue("Hero used " + gamePanel.hero.attackMove[index] + " but missed!");
        }
    }

    public void calculateEnemyAttack(int choice) {
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        int chanceToHit = gamePanel.enemy[gamePanel.currentMap][monsterIndex].attackAccuracy[choice] + roll;
        System.out.println("enemy hit roll: " + chanceToHit + "\n");


        if (gamePanel.hero.dexterity <= chanceToHit) {
            System.out.println("enemy choice" + choice);
            damage = -gamePanel.hero.defence + gamePanel.enemy[gamePanel.currentMap][monsterIndex].attackPower[choice];
            System.out.println("enemy damage " + damage + "\n");
            if (damage < 0) {
                damage = 0;
            }
            gamePanel.hero.health -= damage;
            if (gamePanel.hero.health < 0) {
                gamePanel.hero.health = 0;
            }


        } else {
            damage = 0;

        }

    }
}

