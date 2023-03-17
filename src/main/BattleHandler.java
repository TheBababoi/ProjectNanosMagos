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



    public void startBattle(int index){
        monsterIndex = index;
        gamePanel.music.stop();
        gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;
        gamePanel.ui.currentDialogue = battle;
        gamePanel.ui.drawDialogueScreen();


    }

    public void calculateHeroAttack(int index){
        gamePanel.playSoundEffect(gamePanel.hero.attackSoundIndex[index]);
        Random random = new Random();
        int roll = random.nextInt(10)+1;
        int chanceToHit = gamePanel.hero.attackAccuracy[index] + roll;
        System.out.println("hit roll: " + chanceToHit + "\n");


        if(gamePanel.enemy[monsterIndex].dexterity<= chanceToHit){
            damage = -gamePanel.enemy[monsterIndex].defence + gamePanel.hero.attackPower[index];
            gamePanel.enemy[gamePanel.battleHandler.monsterIndex].health -= damage;
            if(gamePanel.enemy[gamePanel.battleHandler.monsterIndex].health<0){
                gamePanel.enemy[gamePanel.battleHandler.monsterIndex].health = 0;
            }
            System.out.println("damage " + damage + "\n");

        }
        else {
           damage = 0;

    }
    }

    public void calculateEnemyAttack(int choice){
        Random random = new Random();
        int roll = random.nextInt(10)+1;
        int chanceToHit = gamePanel.enemy[monsterIndex].attackAccuracy[choice] + roll;
        System.out.println("enemy hit roll: " + chanceToHit + "\n");


        if(gamePanel.hero.dexterity<= chanceToHit){
            System.out.println("enemy choice" + choice);
            damage = -gamePanel.hero.defence + gamePanel.enemy[monsterIndex].attackPower[choice];
            System.out.println("enemy damage " + damage + "\n");
            gamePanel.hero.health -= damage;
            if(gamePanel.hero.health< 0){
                gamePanel.hero.health = 0;
            }


        }
        else {
            damage = 0;

        }

    }





    private void enemyTurn() {

        //gamePanel.hero.health -= gamePanel.enemy[monsterIndex].atackPower[gamePanel.enemy[monsterIndex].enemyChoice];



    }

    private void heroTurn() {


            gamePanel.ui.drawBattleScreen(monsterIndex);
            int i = gamePanel.keyboardInputs.playerChoice;

            if (i <= 3) {
               // gamePanel.enemy[monsterIndex].health -= gamePanel.hero.atackPower[i];
                gamePanel.keyboardInputs.playerChoice = 99;

            }


        }
    }

