package main;

import Items.Consumable;
import Items.Item;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class KeyboardInputs implements KeyListener {

    public boolean enterPressed, looted;
    private boolean itemUsed;
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public GamePanel gamePanel;
    //debug
    public boolean debugMode = false;
    public int playerChoice, enemyChoice, goldlooted;


    int i = 0;


    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //unused for now? tbd
    }

    @Override
    public void keyPressed(KeyEvent e) {  //standard WASD controls
        int code = e.getKeyCode();
        if (gamePanel.gameState == GamePanel.Gamestate.CUTSCENE) {
            cutscene(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.PLAYSTATE) {
            playState(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.PAUSESTATE) {
            pauseState(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.DIALOGUESTATE) {
            dialogueState(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.TITLESCREEM) {
            titleScreen(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.OPTIONSMENU) {
            optionsMenu(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.CREDITS) {
            credits(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLESTATEHERO) {
            battleStateHero(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLESTATEENEMY) {
            battleStateEnemy();
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGHERO) {
            battleLogHero(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGENEMY) {
            battleLogEnemy(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLEWON) {
            battleWon(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOST) {
            battleLost(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.HEROSTATS) {
            heroStats(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.TRADEMENU) {
            tradeMenu(code);
        } else if (gamePanel.gameState == GamePanel.Gamestate.TRADEDIALOGUE) {
            tradeDialogue(code);
        }

    }

    private void credits(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.ui.resetCreditsY();
            gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;
        }
    }

    private void tradeDialogue(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.gameState = GamePanel.Gamestate.TRADEMENU;
        }
    }

    private void tradeMenu(int code) {

        if (gamePanel.ui.tradeState == UI.TradeState.SELECT){
            if (code == KeyEvent.VK_ENTER) {
                if(gamePanel.ui.commandIndex == 0){
                    gamePanel.ui.tradeState = UI.TradeState.BUY;
                } else if (gamePanel.ui.commandIndex == 1) {
                    gamePanel.ui.tradeState = UI.TradeState.SELL;
                } else if (gamePanel.ui.commandIndex == 2) {
                    gamePanel.ui.currentDialogue = "I just know that you will be \n coming back real soon";

                    gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;

                }
            }
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.commandIndex--;
                if (gamePanel.ui.commandIndex < 0) {
                    gamePanel.ui.commandIndex = 2;
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.commandIndex++;
                if (gamePanel.ui.commandIndex > 2) {
                    gamePanel.ui.commandIndex = 0;
                }
            }

         }else if(gamePanel.ui.tradeState == UI.TradeState.BUY){
            if ( code == KeyEvent.VK_ENTER){
                if(gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex).price>gamePanel.hero.gold){
                    gamePanel.ui.currentDialogue = "Not enough gold";
                    gamePanel.gameState = GamePanel.Gamestate.TRADEDIALOGUE;
                    gamePanel.ui.drawTradeDialogue();

                } else if (!gamePanel.hero.canObtainItem(gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex))){
                    gamePanel.ui.currentDialogue = "Full inventory";
                    gamePanel.gameState = GamePanel.Gamestate.TRADEDIALOGUE;
                    gamePanel.ui.drawTradeDialogue();
                } else {
                    gamePanel.hero.gold -= gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex).price;
                    //gamePanel.hero.inventory.add(gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex));
                    gamePanel.gameState = GamePanel.Gamestate.TRADEDIALOGUE;
                    gamePanel.ui.currentDialogue = "Thank you for your purchase!";
                    gamePanel.ui.drawTradeDialogue();

                }
            }

            if ( code == KeyEvent.VK_ESCAPE){
                gamePanel.ui.currentDialogue = "Hey psst want some deals?";
                gamePanel.ui.tradeState = UI.TradeState.SELECT;
            }
            if (code == KeyEvent.VK_W){
                if(gamePanel.ui.slotRow != 0){
                    gamePanel.ui.slotRow--;
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_A){
                if(gamePanel.ui.slotCollumn != 0){
                    gamePanel.ui.slotCollumn--;
                    gamePanel.playSoundEffect(7);
                }

            }
            if (code == KeyEvent.VK_S){
                if(gamePanel.ui.slotRow != 4){
                    gamePanel.ui.slotRow++;
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_D){
                if(gamePanel.ui.slotCollumn != 4){
                    gamePanel.ui.slotCollumn++;
                    gamePanel.playSoundEffect(7);
                }

            }

        }
        else if(gamePanel.ui.tradeState == UI.TradeState.SELL){
            if ( code == KeyEvent.VK_ENTER) {
                if (gamePanel.hero.inventory.get(gamePanel.ui.itemIndex) == gamePanel.hero.currentArmor ||
                        gamePanel.hero.inventory.get(gamePanel.ui.itemIndex) == gamePanel.hero.currentWeapon) {
                    gamePanel.ui.currentDialogue = "You are currently wearing that!";
                    gamePanel.gameState = GamePanel.Gamestate.TRADEDIALOGUE;
                    gamePanel.ui.drawTradeDialogue();

                } else {
                    gamePanel.hero.gold += gamePanel.hero.inventory.get(gamePanel.ui.itemIndex).price;
                    if (gamePanel.hero.inventory.get(gamePanel.ui.itemIndex).amount>1){
                        gamePanel.hero.inventory.get(gamePanel.ui.itemIndex).amount--;
                    } else {
                        gamePanel.hero.inventory.remove(gamePanel.ui.itemIndex);
                    }
                    // gamePanel.hero.inventory.add(gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex));
                    gamePanel.gameState = GamePanel.Gamestate.TRADEDIALOGUE;
                    gamePanel.ui.currentDialogue = "I'll take that off your hands!";
                    gamePanel.ui.drawTradeDialogue();
                }
            }

            if ( code == KeyEvent.VK_ESCAPE){
                gamePanel.ui.currentDialogue = "Hey psst want some deals?";
                gamePanel.ui.tradeState = UI.TradeState.SELECT;
            }
            if (code == KeyEvent.VK_W){
                if(gamePanel.ui.slotRow != 0){
                    gamePanel.ui.slotRow--;
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_A){
                if(gamePanel.ui.slotCollumn != 0){
                    gamePanel.ui.slotCollumn--;
                    gamePanel.playSoundEffect(7);
                }

            }
            if (code == KeyEvent.VK_S){
                if(gamePanel.ui.slotRow != 4){
                    gamePanel.ui.slotRow++;
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_D){
                if(gamePanel.ui.slotCollumn != 4){
                    gamePanel.ui.slotCollumn++;
                    gamePanel.playSoundEffect(7);
                }

            }

        }

    }

    private void optionsMenu(int code) {
        if (code == KeyEvent.VK_ENTER) {
            //enterPressed = true;
            if (gamePanel.ui.commandIndex == 0){
               if (gamePanel.fullScreenOn) {
                    gamePanel.fullScreenOn =false;
               } else {
                   gamePanel.fullScreenOn = true;
               }
            }
            if(gamePanel.ui.commandIndex == 3){
                gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;
                gamePanel.ui.commandIndex =0;
            }
        }
        if (code == KeyEvent.VK_W) {
            gamePanel.playSoundEffect(7);
            gamePanel.ui.commandIndex--;
            if (gamePanel.ui.commandIndex < 0) {
                gamePanel.ui.commandIndex = 3;
            }
        } else if (code == KeyEvent.VK_S) {
            gamePanel.playSoundEffect(7);
            gamePanel.ui.commandIndex++;
            if (gamePanel.ui.commandIndex > 3) {
                gamePanel.ui.commandIndex = 0;
            }
        } else if (code == KeyEvent.VK_A) {
            if (gamePanel.ui.commandIndex == 1 && gamePanel.music.volumeScale > 0) {
                gamePanel.music.volumeScale--;
                gamePanel.music.checkVolume();
                gamePanel.playSoundEffect(7);
            }
            if (gamePanel.ui.commandIndex == 2 && gamePanel.soundEffect.volumeScale > 0) {
                gamePanel.soundEffect.volumeScale--;
                gamePanel.playSoundEffect(7);
            }
        } else if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.commandIndex == 1 && gamePanel.music.volumeScale < 10) {
                gamePanel.playSoundEffect(7);
                gamePanel.music.volumeScale++;
                gamePanel.music.checkVolume();
            }
            if (gamePanel.ui.commandIndex == 2 && gamePanel.soundEffect.volumeScale < 10) {
                gamePanel.playSoundEffect(7);
                gamePanel.soundEffect.volumeScale++;
            }
        }
    }

    private void titleScreen(int code) {
        if (code == KeyEvent.VK_W) {
            gamePanel.playSoundEffect(7);
            gamePanel.ui.commandIndex--;
            if (gamePanel.ui.commandIndex < 0) {
                gamePanel.ui.commandIndex = 4;
            }
        } else if (code == KeyEvent.VK_S) {
            gamePanel.playSoundEffect(7);
            gamePanel.ui.commandIndex++;
            if (gamePanel.ui.commandIndex > 4) {
                gamePanel.ui.commandIndex = 0;
            }

        } else if (code == KeyEvent.VK_ENTER) {
            gamePanel.playSoundEffect(7);
            if (gamePanel.ui.commandIndex == 0) {
                gamePanel.restart();
                gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
                System.out.println("playstate");
                gamePanel.playMusic(0);
            } else if (gamePanel.ui.commandIndex == 1) {
                gamePanel.saveLoad.load();
                gamePanel.playMusic(0);
                gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
                gamePanel.ui.commandIndex =0;

            } else if (gamePanel.ui.commandIndex == 2) {
                gamePanel.gameState = GamePanel.Gamestate.OPTIONSMENU;
                gamePanel.ui.commandIndex =0;

            } else if (gamePanel.ui.commandIndex == 3) {
                gamePanel.gameState = GamePanel.Gamestate.CREDITS;
            }
            else if (gamePanel.ui.commandIndex == 4) {
                System.exit(0);
            }

        }
    }

    private void playState(int code) {
        if (code == KeyEvent.VK_W) {
            up = true;

        } else if (code == KeyEvent.VK_A) {
            left = true;
        } else if (code == KeyEvent.VK_S) {
            down = true;
        } else if (code == KeyEvent.VK_D) {
            right = true;
        } else if (code == KeyEvent.VK_ESCAPE) {

            gamePanel.gameState = GamePanel.Gamestate.PAUSESTATE;
        } else if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        } else if (code == KeyEvent.VK_H) {
            gamePanel.gameState = GamePanel.Gamestate.HEROSTATS;
        }
        //debug
        if (code == KeyEvent.VK_T) {
            if (!debugMode) {
                debugMode = true;
            } else {
                debugMode = false;
            }
        }
    }
    private void cutscene(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.stopMusic();
            gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;

        }
    }
    private void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
            gamePanel.ui.commandIndex = 0;
        }
        if (code == KeyEvent.VK_W) {
            gamePanel.playSoundEffect(7);
            gamePanel.ui.commandIndex--;
            if (gamePanel.ui.commandIndex < 0) {
                gamePanel.ui.commandIndex = 3;
            }
        } else if (code == KeyEvent.VK_S) {
            gamePanel.playSoundEffect(7);
            gamePanel.ui.commandIndex++;
            if (gamePanel.ui.commandIndex > 3) {
                gamePanel.ui.commandIndex = 0;
            }
        } else if (code == KeyEvent.VK_ENTER) {
            gamePanel.playSoundEffect(7);
            if (gamePanel.ui.commandIndex == 0) {
                gamePanel.saveLoad.save();
                gamePanel.ui.currentDialogue = "Game Saved!";
                gamePanel.gameState = GamePanel.Gamestate.DIALOGUESTATE;

            } else if (gamePanel.ui.commandIndex == 1) {
                gamePanel.saveLoad.save();
                gamePanel.stopMusic();
                gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;
                gamePanel.ui.commandIndex = 0;

            } else if (gamePanel.ui.commandIndex == 2) {
                gamePanel.stopMusic();
                gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;
                gamePanel.ui.commandIndex = 0;
            } else if (gamePanel.ui.commandIndex == 3) {
                gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
                gamePanel.ui.commandIndex = 0;
            }

        }
    }
    private void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (!gamePanel.hero.encounteredEnemy&&gamePanel.hero.isEncounteredNPC()) {
                enterPressed = true;
            } else if (!gamePanel.hero.isEncounteredNPC()&&gamePanel.hero.encounteredEnemy){
                gamePanel.stopMusic();
                gamePanel.playMusic(4);
                gamePanel.gameState = GamePanel.Gamestate.TRANSITIONBATTLE;
                gamePanel.hero.encounteredEnemy = false;
            }else {
                gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
            }

        }

    }
    private void battleStateHero(int code){
        if (gamePanel.ui.subMenu == UI.SubMenu.INVENTORY) {
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                if (gamePanel.ui.commandIndex > 0) {
                    gamePanel.ui.commandIndex--;
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                if (gamePanel.ui.commandIndex < gamePanel.ui.getItemCounter()) {
                    gamePanel.ui.commandIndex++;
                }
            } else if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.subMenu = UI.SubMenu.MAINMENU;
                gamePanel.ui.commandIndex = 1;
            } else if (code == KeyEvent.VK_ENTER){
                gamePanel.playSoundEffect(7);
                gamePanel.ui.subMenu = UI.SubMenu.MAINMENU;
                itemUsed = true;
                gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                gamePanel.hero.useItemInBattle(gamePanel.ui.getItemBattleIndex(gamePanel.ui.commandIndex));
                gamePanel.ui.resetcommandIndex();
            }

        } else {
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.commandIndex--;
                if (gamePanel.ui.commandIndex < 0) {
                    gamePanel.ui.commandIndex= 1;
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.commandIndex++;
                if (gamePanel.ui.commandIndex > 1) {
                    gamePanel.ui.commandIndex = 0;
                }
            } else if (code == KeyEvent.VK_A) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.commandIndexX--;
                if (gamePanel.ui.commandIndexX < 0) {
                    gamePanel.ui.commandIndexX = 1;
                }
            } else if (code == KeyEvent.VK_D) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.commandIndexX++;
                if (gamePanel.ui.commandIndexX > 1) {
                    gamePanel.ui.commandIndexX = 0;
                }
            } else if (code == KeyEvent.VK_ENTER) {
                gamePanel.playSoundEffect(7);
                if (gamePanel.ui.subMenu == UI.SubMenu.ATTACKMENU) {
                    if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndex == 0) {

                        playerChoice = 0;

                        System.out.println("Player choice 0");
                        gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                        gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        gamePanel.ui.resetcommandIndex();


                    }
                    if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndex == 0) {

                        playerChoice = 1;

                        System.out.println("Player choice 1");
                        gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                        gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        gamePanel.ui.resetcommandIndex();
                    }
                    if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndex == 1) {

                        playerChoice = 2;

                        System.out.println("Player choice 2");
                        gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                        gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        gamePanel.ui.resetcommandIndex();
                    }
                    if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndex == 1) {

                        playerChoice = 3;

                        System.out.println("Player choice 3");
                        gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                        gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        gamePanel.ui.resetcommandIndex();
                    }
                } else {


                    if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndex == 0) {
                        gamePanel.ui.subMenu = UI.SubMenu.ATTACKMENU;
                        gamePanel.ui.resetcommandIndex();

                    }
                    if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndex == 0) {

                    }
                    if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndex == 1) {

                    }
                    if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndex
                            == 1) {
                        boolean flag = false;

                      for (Item item: gamePanel.hero.inventory ){
                          if (item instanceof Consumable){
                              flag = true;
                          }
                      }
                      if (flag){
                            gamePanel.ui.subMenu = UI.SubMenu.INVENTORY;
                             gamePanel.ui.resetcommandIndex();}
                    } else{
                        gamePanel.ui.setBattleTipsText("Inventory is Empty!");
                    }

                }
            } else if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.playSoundEffect(7);
                gamePanel.ui.subMenu = UI.SubMenu.MAINMENU;
            }
        }

    }

    private void battleStateEnemy(){
        Random random = new Random();
        enemyChoice = random.nextInt(3);
        gamePanel.playSoundEffect(6);

        gamePanel.battleHandler.calculateEnemyAttack(enemyChoice);
        gamePanel.ui.resetLetterbyLetter();
        gamePanel.gameState = GamePanel.Gamestate.BATTLELOGENEMY;

    }

    private void battleLogHero(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.ui.resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            gamePanel.ui.resetItemCounter();
            if(gamePanel.enemy[gamePanel.currentMap][gamePanel.battleHandler.monsterIndex].health<=0){
                gamePanel.gameState = GamePanel.Gamestate.BATTLEWON;
                looted = gamePanel.hero.lootEnemyDrop(gamePanel.battleHandler.monsterIndex);
                goldlooted = gamePanel.hero.lootEnemyGold(gamePanel.battleHandler.monsterIndex);


            }
            else{
                itemUsed = false;
                gamePanel.gameState = GamePanel.Gamestate.BATTLESTATEENEMY;
            }

        }
    }
    private void battleLogEnemy(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.ui.resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            if(gamePanel.hero.health<=0){
                gamePanel.gameState = GamePanel.Gamestate.BATTLELOST;
            }
            else {
                gamePanel.ui.resetItemCounter();
                gamePanel.gameState = GamePanel.Gamestate.BATTLESTATEHERO;
            }
        }

    }
    private void battleLost(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.ui.resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            gamePanel.music.stop();
            gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;
            gamePanel.restart();
        }
    }
    private void battleWon(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.ui.resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            gamePanel.music.stop();
            gamePanel.hero.setExp(gamePanel.hero.getExp() + gamePanel.enemy[gamePanel.currentMap][gamePanel.battleHandler.monsterIndex].getExp());
            gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
            gamePanel.playMusic(0);
            gamePanel.enemy[gamePanel.currentMap][gamePanel.battleHandler.monsterIndex] = null;
            gamePanel.hero.checkLevelUp();




        }
    }
    private void heroStats(int code){
        if (code == KeyEvent.VK_H  || code == KeyEvent.VK_ESCAPE){
            gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
        }
        if (code == KeyEvent.VK_W){
            if(gamePanel.ui.slotRow != 0){
                gamePanel.ui.slotRow--;
                gamePanel.playSoundEffect(7);
            }
        }
        if (code == KeyEvent.VK_A){
            if(gamePanel.ui.slotCollumn != 0){
                gamePanel.ui.slotCollumn--;
                gamePanel.playSoundEffect(7);
            }

        }
        if (code == KeyEvent.VK_S){
            if(gamePanel.ui.slotRow != 4){
                gamePanel.ui.slotRow++;
                gamePanel.playSoundEffect(7);
            }
        }
        if (code == KeyEvent.VK_D){
            if(gamePanel.ui.slotCollumn != 4){
                gamePanel.ui.slotCollumn++;
                gamePanel.playSoundEffect(7);
            }

        }
        if (code == KeyEvent.VK_ENTER){
            gamePanel.hero.equipItem();
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up = false;
        }
        if(code == KeyEvent.VK_A){
            left = false;
        }
        if(code == KeyEvent.VK_S){
            down = false;
        }
        if(code == KeyEvent.VK_D){
            right = false;
        }
    }

    public boolean isItemUsed() {
        return itemUsed;
    }

    public void setItemUsed(boolean itemUsed) {
        this.itemUsed = itemUsed;
    }
}
