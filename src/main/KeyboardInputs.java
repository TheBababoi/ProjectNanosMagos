package main;

import Items.Consumable;
import Items.Item;
import creature.AchatGood;
import creature.FlowerGuy;
import creature.Nikolaidis;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class KeyboardInputs implements KeyListener {

    private boolean enterPressed, looted;
    private boolean itemUsed;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private GamePanel gamePanel;
    //debug
    private boolean debugMode = false;
    private int playerChoice, enemyChoice, goldlooted;


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
        if (gamePanel.getGameState() == GamePanel.Gamestate.CUTSCENE) {

        } else if (gamePanel.getGameState() == GamePanel.Gamestate.PLAYSTATE) {
            playState(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.PAUSESTATE) {
            pauseState(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.DIALOGUESTATE) {
            dialogueState(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TITLESCREEM) {
            titleScreen(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.OPTIONSMENU) {
            optionsMenu(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.CREDITS) {
            credits(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLESTATEHERO) {
            battleStateHero(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLESTATEENEMY) {
            battleStateEnemy();
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGHERO) {
            battleLogHero(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOGENEMY) {
            battleLogEnemy(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLEWON) {
            battleWon(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.BATTLELOST) {
            battleLost(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.HEROSTATS) {
            heroStats(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TRADEMENU) {
            tradeMenu(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.TRADEDIALOGUE) {
            tradeDialogue(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZMENU) {
            quizMenu(code);
        } else if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZRESULT) {
            quizResult(code);
        }else if (gamePanel.getGameState() == GamePanel.Gamestate.QUIZFINAL) {
            quizFinal(code);
        }

    }

    private void quizFinal(int code) {
        if (code == KeyEvent.VK_ENTER) {

            if(!(gamePanel.getHero().getHealth() == 1)){
                gamePanel.getHero().setMaxMana(gamePanel.getHero().getMaxMana() +100);
                gamePanel.getHero().setMana(gamePanel.getHero().getMaxMana());
                gamePanel.getUi().getNikolaidis().setQuizStatus(1);
               // gamePanel.getUi().getNikolaidis().setDialogueIndex(1);

            }else {
                gamePanel.getUi().getNikolaidis().setQuizStatus(2);
                //gamePanel.getUi().getNikolaidis().setDialogueIndex(2);
            }
            gamePanel.getUi().setQuizIndex(0);
            gamePanel.getUi().setCurrentDialogue("Hello again");
            gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);

        }

    }

    private void quizResult(int code) {
        if (code == KeyEvent.VK_ENTER) {
           if(!(playerChoice == gamePanel.getUi().getNikolaidis().getCorrectAnswer()[gamePanel.getUi().getQuizIndex()])){
               gamePanel.getHero().setHealth(gamePanel.getHero().getHealth() - gamePanel.getHero().getMaxHealth()/3);
               if (gamePanel.getHero().getHealth()<=0){
                   gamePanel.getHero().setHealth(1);
               }
           }
           gamePanel.getUi().setQuizIndex(gamePanel.getUi().getQuizIndex() + 1);
           if(gamePanel.getHero().getHealth() == 1 || gamePanel.getUi().getQuizIndex()>4){
               gamePanel.setGameState(GamePanel.Gamestate.QUIZFINAL);
           }else {
               gamePanel.setGameState(GamePanel.Gamestate.QUIZMENU);
           }

        }

    }

    private void quizMenu(int code) {
        if (gamePanel.getUi().getQuizState() == UI.QuizState.SELECT){
            if (code == KeyEvent.VK_ENTER) {
                if(gamePanel.getUi().getCommandIndex() == 0){
                    gamePanel.getUi().setQuizState(UI.QuizState.QUIZ);
                } else if (gamePanel.getUi().getCommandIndex() == 1) {
                    gamePanel.getUi().setCurrentDialogue("You should go and study \nand make Mama Proud!");
                    gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
                    gamePanel.getUi().resetcommandIndex();

                }
            }
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() -1 );
                if (gamePanel.getUi().getCommandIndex() < 0) {
                    gamePanel.getUi().setCommandIndex(1);
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1 );
                if (gamePanel.getUi().getCommandIndex() > 1) {
                    gamePanel.getUi().setCommandIndex(0);
                }
            }

        }else if(gamePanel.getUi().getQuizState() == UI.QuizState.QUIZ){
            if ( code == KeyEvent.VK_ENTER){
                playerChoice = gamePanel.getUi().getCommandIndex();
                gamePanel.playSoundEffect(7);
                gamePanel.setGameState(GamePanel.Gamestate.QUIZRESULT);

            }
            if (code == KeyEvent.VK_W){
                System.out.println("up");
                if(gamePanel.getUi().getCommandIndex() != 0){
                    gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() - 1);
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_S){
                System.out.println("down");
                if(gamePanel.getUi().getCommandIndex() != 3){
                    gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1);
                    gamePanel.playSoundEffect(7);
                }
            }
        }
    }

    private void credits(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getUi().resetCreditsY();
            gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
        }
    }

    private void tradeDialogue(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.setGameState(GamePanel.Gamestate.TRADEMENU);
        }
    }

    private void tradeMenu(int code) {

        if (gamePanel.getUi().getTradeState() == UI.TradeState.SELECT){
            if (code == KeyEvent.VK_ENTER) {
                if(gamePanel.getUi().getCommandIndex() == 0){
                    gamePanel.getUi().setTradeState(UI.TradeState.BUY);
                } else if (gamePanel.getUi().getCommandIndex() == 1) {
                    gamePanel.getUi().setTradeState(UI.TradeState.SELL);
                } else if (gamePanel.getUi().getCommandIndex() == 2) {
                    gamePanel.getUi().setCurrentDialogue("I just know that you will be \n coming back real soon");

                    gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);
                    gamePanel.getUi().resetcommandIndex();

                }
            }
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() -1 );
                if (gamePanel.getUi().getCommandIndex() < 0) {
                    gamePanel.getUi().setCommandIndex(2);
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1 );
                if (gamePanel.getUi().getCommandIndex() > 2) {
                    gamePanel.getUi().setCommandIndex(0);
                }
            }

         }else if(gamePanel.getUi().getTradeState() == UI.TradeState.BUY){
            if ( code == KeyEvent.VK_ENTER){
                if(gamePanel.getUi().getMerchant().getInventory().get(gamePanel.getUi().getItemIndex()).getPrice() > gamePanel.getHero().getGold()){
                    gamePanel.getUi().setCurrentDialogue("Not enough gold");
                    gamePanel.setGameState(GamePanel.Gamestate.TRADEDIALOGUE);
                    gamePanel.getUi().drawTradeDialogue();

                } else if (!gamePanel.getHero().canObtainItem(gamePanel.getUi().getMerchant().getInventory().get(gamePanel.getUi().getItemIndex()))){
                    gamePanel.getUi().setCurrentDialogue("Full inventory");
                    gamePanel.setGameState(GamePanel.Gamestate.TRADEDIALOGUE);
                    gamePanel.getUi().drawTradeDialogue();
                } else {
                    gamePanel.getHero().setGold(gamePanel.getHero().getGold() - gamePanel.getUi().getMerchant().getInventory().get(gamePanel.getUi().getItemIndex()).getPrice());
                    //gamePanel.hero.inventory.add(gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex));
                    gamePanel.setGameState(GamePanel.Gamestate.TRADEDIALOGUE);
                    gamePanel.getUi().setCurrentDialogue("Thank you for your purchase!");
                    gamePanel.getUi().drawTradeDialogue();

                }
            }

            if ( code == KeyEvent.VK_ESCAPE){
                gamePanel.getUi().setCurrentDialogue("Hey psst want some deals?");
                char[] characters = gamePanel.getUi().getCurrentBattleDialogue().toCharArray();
                gamePanel.getUi().displayLetterbyLetter(characters);

                gamePanel.getUi().setTradeState(UI.TradeState.SELECT);
                gamePanel.getUi().resetcommandIndex();
            }
            if (code == KeyEvent.VK_W){
                if(gamePanel.getUi().getSlotRow() != 0){
                    gamePanel.getUi().setSlotRow(gamePanel.getUi().getSlotRow() - 1);
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_A){
                if(gamePanel.getUi().getSlotCollumn() != 0){
                    gamePanel.getUi().setSlotCollumn(gamePanel.getUi().getSlotCollumn() - 1);
                    gamePanel.playSoundEffect(7);
                }

            }
            if (code == KeyEvent.VK_S){
                if(gamePanel.getUi().getSlotRow() != 4){
                    gamePanel.getUi().setSlotRow(gamePanel.getUi().getSlotRow() + 1);
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_D){
                if(gamePanel.getUi().getSlotCollumn() != 4){
                    gamePanel.getUi().setSlotCollumn(gamePanel.getUi().getSlotCollumn() + 1);
                    gamePanel.playSoundEffect(7);
                }

            }

        }
        else if(gamePanel.getUi().getTradeState() == UI.TradeState.SELL){
            if ( code == KeyEvent.VK_ENTER) {
                if (gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()) == gamePanel.getHero().getCurrentArmor() ||
                        gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()) == gamePanel.getHero().getCurrentWeapon() ||
                        gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()) == gamePanel.getHero().getCurrentGem() ||
                        gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()) == gamePanel.getHero().getCurrentTrinket())  {
                    gamePanel.getUi().setCurrentDialogue("You are currently wearing that!");
                    gamePanel.setGameState(GamePanel.Gamestate.TRADEDIALOGUE);
                    gamePanel.getUi().drawTradeDialogue();

                } else {
                    gamePanel.getHero().setGold(gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()).getPrice()/2 + gamePanel.getHero().getGold());
                    if (gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()).getAmount() >1){
                        gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()).setAmount(gamePanel.getHero().getInventory().get(gamePanel.getUi().getItemIndex()).getAmount() -1);
                    } else {
                        gamePanel.getHero().getInventory().remove(gamePanel.getUi().getItemIndex());
                    }
                    // gamePanel.hero.inventory.add(gamePanel.ui.merchant.inventory.get(gamePanel.ui.itemIndex));
                    gamePanel.setGameState(GamePanel.Gamestate.TRADEDIALOGUE);
                    gamePanel.getUi().setCurrentDialogue("I'll take that off your hands!");
                    gamePanel.getUi().drawTradeDialogue();
                }
            }

            if ( code == KeyEvent.VK_ESCAPE){
                gamePanel.getUi().setCurrentDialogue("Hey psst want some deals?");
                gamePanel.getUi().setTradeState(UI.TradeState.SELECT);
                gamePanel.getUi().resetcommandIndex();
            }
            if (code == KeyEvent.VK_W){
                if(gamePanel.getUi().getSlotRow() != 0){

                    gamePanel.getUi().setSlotRow(gamePanel.getUi().getSlotRow() - 1);
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_A){
                if(gamePanel.getUi().getSlotCollumn() != 0){
                    gamePanel.getUi().setSlotCollumn(gamePanel.getUi().getSlotCollumn() - 1);
                    gamePanel.playSoundEffect(7);
                }

            }
            if (code == KeyEvent.VK_S){
                if(gamePanel.getUi().getSlotRow() != 4){
                    gamePanel.getUi().setSlotRow(gamePanel.getUi().getSlotRow() + 1);
                    gamePanel.playSoundEffect(7);
                }
            }
            if (code == KeyEvent.VK_D){
                if(gamePanel.getUi().getSlotCollumn() != 4){
                    gamePanel.getUi().setSlotCollumn(gamePanel.getUi().getSlotCollumn() + 1);
                    gamePanel.playSoundEffect(7);
                }

            }

        }

    }

    private void optionsMenu(int code) {
        if (code == KeyEvent.VK_ENTER) {
            //enterPressed = true;
            if (gamePanel.getUi().getCommandIndex() == 0){
               if (gamePanel.isFullScreenOn()) {
                    gamePanel.setFullScreenOn(false);
               } else {
                   gamePanel.setFullScreenOn(true);
               }
            }
            if(gamePanel.getUi().getCommandIndex() == 3){
                gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
                gamePanel.getUi().setCommandIndex(0);
                gamePanel.getUi().resetcommandIndex();
            }
        }
        if (code == KeyEvent.VK_W) {
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() -1);
            if (gamePanel.getUi().getCommandIndex() < 0) {
                gamePanel.getUi().setCommandIndex(3);
            }
        } else if (code == KeyEvent.VK_S) {
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() +1);
            if (gamePanel.getUi().getCommandIndex() > 3) {
                gamePanel.getUi().setCommandIndex(0);
            }
        } else if (code == KeyEvent.VK_A) {
            if (gamePanel.getUi().getCommandIndex() == 1 && gamePanel.getMusic().getVolumeScale() > 0) {
                gamePanel.getMusic().setVolumeScale(gamePanel.getMusic().getVolumeScale() - 1);
                gamePanel.getMusic().checkVolume();
                gamePanel.playSoundEffect(7);
            }
            if (gamePanel.getUi().getCommandIndex() == 2 && gamePanel.getSoundEffect().getVolumeScale() > 0) {
                gamePanel.getSoundEffect().setVolumeScale(gamePanel.getSoundEffect().getVolumeScale() - 1);
                gamePanel.playSoundEffect(7);
            }
        } else if (code == KeyEvent.VK_D) {
            if (gamePanel.getUi().getCommandIndex() == 1 && gamePanel.getMusic().getVolumeScale() < 10) {
                gamePanel.playSoundEffect(7);
                gamePanel.getMusic().setVolumeScale(gamePanel.getMusic().getVolumeScale() + 1);
                gamePanel.getMusic().checkVolume();
            }
            if (gamePanel.getUi().getCommandIndex() == 2 && gamePanel.getSoundEffect().getVolumeScale() < 10) {
                gamePanel.playSoundEffect(7);
                gamePanel.getSoundEffect().setVolumeScale(gamePanel.getSoundEffect().getVolumeScale() + 1);
            }
        }
    }

    private void titleScreen(int code) {
        if (code == KeyEvent.VK_W) {
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() - 1);
            if (gamePanel.getUi().getCommandIndex() < 0) {
                gamePanel.getUi().setCommandIndex(4);
            }
        } else if (code == KeyEvent.VK_S) {
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1);
            if (gamePanel.getUi().getCommandIndex() > 4) {
                gamePanel.getUi().setCommandIndex(0);
            }

        } else if (code == KeyEvent.VK_ENTER) {
            gamePanel.playSoundEffect(7);
            if (gamePanel.getUi().getCommandIndex() == 0) {
                gamePanel.restart();
                gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
                System.out.println("playstate");
                gamePanel.stopMusic();
                gamePanel.playMusic(0);
            } else if (gamePanel.getUi().getCommandIndex() == 1) {
                gamePanel.getSaveLoad().load();
                gamePanel.stopMusic();
                gamePanel.playMusic(gamePanel.getCurrentMap());
                gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
                gamePanel.getUi().setCommandIndex(0);

            } else if (gamePanel.getUi().getCommandIndex() == 2) {
                gamePanel.setGameState(GamePanel.Gamestate.OPTIONSMENU);
                gamePanel.getUi().setCommandIndex(0);

            } else if (gamePanel.getUi().getCommandIndex() == 3) {
                gamePanel.setGameState(GamePanel.Gamestate.CREDITS);
            }
            else if (gamePanel.getUi().getCommandIndex() == 4) {
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

            gamePanel.setGameState(GamePanel.Gamestate.PAUSESTATE);
        } else if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        } else if (code == KeyEvent.VK_H) {
            gamePanel.setGameState(GamePanel.Gamestate.HEROSTATS);
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
            gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);

        }
    }
    private void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
            gamePanel.getUi().setCommandIndex(0);
        }
        if (code == KeyEvent.VK_W) {
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() - 1);
            if (gamePanel.getUi().getCommandIndex() < 0) {
                gamePanel.getUi().setCommandIndex(3);
            }
        } else if (code == KeyEvent.VK_S) {
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1);
            if (gamePanel.getUi().getCommandIndex() > 3) {
                gamePanel.getUi().setCommandIndex(0);
            }
        } else if (code == KeyEvent.VK_ENTER) {
            gamePanel.playSoundEffect(7);
            if (gamePanel.getUi().getCommandIndex() == 0) {
                gamePanel.getSaveLoad().save();
                gamePanel.getUi().setCurrentDialogue("Game Saved!");
                gamePanel.setGameState(GamePanel.Gamestate.DIALOGUESTATE);

            } else if (gamePanel.getUi().getCommandIndex() == 1) {
                gamePanel.getSaveLoad().save();
                gamePanel.stopMusic();
                gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
                gamePanel.getUi().setCommandIndex(0);

            } else if (gamePanel.getUi().getCommandIndex() == 2) {
                gamePanel.stopMusic();
                gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
                gamePanel.getUi().setCommandIndex(0);
            } else if (gamePanel.getUi().getCommandIndex() == 3) {
                gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
                gamePanel.getUi().setCommandIndex(0);
            }

        }
    }
    private void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (!gamePanel.getHero().isEncounteredEnemy()&& gamePanel.getHero().isEncounteredNPC()) {
                enterPressed = true;
            } else if (!gamePanel.getHero().isEncounteredNPC()&& gamePanel.getHero().isEncounteredEnemy()){
                gamePanel.stopMusic();
                gamePanel.playMusic(4);
                gamePanel.setGameState(GamePanel.Gamestate.TRANSITIONBATTLE);
                gamePanel.getHero().setEncounteredEnemy(false);
            }else {
                gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
            }

        }

    }
    private void battleStateHero(int code){
        if (gamePanel.getUi().getSubMenu() == UI.SubMenu.INVENTORY) {
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                if (gamePanel.getUi().getCommandIndex() > 0) {
                    gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() - 1);
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                if (gamePanel.getUi().getCommandIndex() < gamePanel.getUi().getItemCounter()) {
                    gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1);
                }
            } else if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);
                gamePanel.getUi().setCommandIndex(1);
            } else if (code == KeyEvent.VK_ENTER){
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);
                itemUsed = true;
                gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGHERO);
                gamePanel.getHero().useItemInBattle(gamePanel.getUi().getItemBattleIndex(gamePanel.getUi().getCommandIndex()));
                gamePanel.getUi().resetcommandIndex();
            }

        } else {
            if (code == KeyEvent.VK_W) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() - 1);
                if (gamePanel.getUi().getCommandIndex() < 0) {
                    gamePanel.getUi().setCommandIndex(1);
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndex(gamePanel.getUi().getCommandIndex() + 1);
                if (gamePanel.getUi().getCommandIndex() > 1) {
                    gamePanel.getUi().setCommandIndex(0);
                }
            } else if (code == KeyEvent.VK_A) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndexX(gamePanel.getUi().getCommandIndexX() - 1);
                if (gamePanel.getUi().getCommandIndexX() < 0) {
                    gamePanel.getUi().setCommandIndexX(1);
                }
            } else if (code == KeyEvent.VK_D) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setCommandIndexX(gamePanel.getUi().getCommandIndexX() + 1);
                if (gamePanel.getUi().getCommandIndexX() > 1) {
                    gamePanel.getUi().setCommandIndexX(0);
                }
            } else if (code == KeyEvent.VK_ENTER) {
                gamePanel.playSoundEffect(7);
                if (gamePanel.getUi().getSubMenu() == UI.SubMenu.MAGICMENU || gamePanel.getUi().getSubMenu() == UI.SubMenu.PHYSICALMENU) {
                    int i = 0;
                    if (gamePanel.getUi().getSubMenu() == UI.SubMenu.MAGICMENU) {
                        i = 4;
                    }
                    if (gamePanel.getUi().getCommandIndexX() == 0 && gamePanel.getUi().getCommandIndex() == 0) {
                        playerChoice = i;
                        System.out.println("Player choice 0");
                    }
                    if (gamePanel.getUi().getCommandIndexX() == 1 && gamePanel.getUi().getCommandIndex() == 0) {
                        playerChoice = 1 + i;
                        System.out.println("Player choice 1");
                    }
                    if (gamePanel.getUi().getCommandIndexX() == 0 && gamePanel.getUi().getCommandIndex() == 1) {
                        playerChoice = 2 + i;
                        System.out.println("Player choice 2");
                    }
                    if (gamePanel.getUi().getCommandIndexX() == 1 && gamePanel.getUi().getCommandIndex() == 1) {
                        playerChoice = 3 + i;
                        System.out.println("Player choice 3");
                    }
                    if (gamePanel.getHero().getMana() >= gamePanel.getHero().getAttackCost(playerChoice)) {
                        gamePanel.getBattleHandler().calculateHeroAttack(playerChoice);
                        gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGHERO);
                        gamePanel.getUi().resetcommandIndex();
                        gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);
                    }
                }else if(gamePanel.getUi().getSubMenu() == UI.SubMenu.BUFFMENU){
                        if (gamePanel.getUi().getCommandIndexX() == 0 && gamePanel.getUi().getCommandIndex() == 0) {
                            gamePanel.getBattleHandler().meditate();
                            gamePanel.getUi().setCurrentBattleDialogue("Hero mediated to restore some mana!");
                            gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGHERO);
                            gamePanel.getUi().resetcommandIndex();
                            gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);

                        }
                        if (gamePanel.getUi().getCommandIndexX() == 1 && gamePanel.getUi().getCommandIndex() == 0) {
                            if (!gamePanel.getBattleHandler().isUsedFocus()){
                                gamePanel.getBattleHandler().setUsedFocus(true);
                                gamePanel.getUi().setCurrentBattleDialogue("Hero became very focused! \n His Accuracy rose!");
                                gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGHERO);
                                gamePanel.getUi().resetcommandIndex();
                                gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);

                            }

                        }
                        if (gamePanel.getUi().getCommandIndexX() == 0 && gamePanel.getUi().getCommandIndex() == 1) {
                            if (!gamePanel.getBattleHandler().isUsedDance()){
                                gamePanel.getBattleHandler().setUsedDance(true);
                                gamePanel.getUi().setCurrentBattleDialogue("Hero began to dance! \n His Dexterity rose!");
                                gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGHERO);
                                gamePanel.getUi().resetcommandIndex();
                                gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);

                            }
                        }
                        if (gamePanel.getUi().getCommandIndexX() == 1 && gamePanel.getUi().getCommandIndex() == 1) {
                            if (!gamePanel.getBattleHandler().isUsedEnrage()){
                                gamePanel.getBattleHandler().setUsedEnrage(true);
                                gamePanel.getUi().setCurrentBattleDialogue("Hero got really mad! \n His Attack rose!");
                                gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGHERO);
                                gamePanel.getUi().resetcommandIndex();
                                gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);

                            }
                        }
                    }

                    else {


                    if (gamePanel.getUi().getCommandIndexX() == 0 && gamePanel.getUi().getCommandIndex() == 0) {
                        gamePanel.getUi().setSubMenu(UI.SubMenu.PHYSICALMENU);
                        gamePanel.getUi().resetcommandIndex();

                    }
                    if (gamePanel.getUi().getCommandIndexX() == 1 && gamePanel.getUi().getCommandIndex() == 0) {
                        gamePanel.getUi().setSubMenu(UI.SubMenu.MAGICMENU);
                        gamePanel.getUi().resetcommandIndex();
                    }
                    if (gamePanel.getUi().getCommandIndexX() == 0 && gamePanel.getUi().getCommandIndex() == 1) {
                        gamePanel.getUi().setSubMenu(UI.SubMenu.BUFFMENU);
                        gamePanel.getUi().resetcommandIndex();
                    }
                    if (gamePanel.getUi().getCommandIndexX() == 1 && gamePanel.getUi().getCommandIndex() == 1) {
                        boolean flag = false;

                      for (Item item: gamePanel.getHero().getInventory()){
                          if (item instanceof Consumable){
                              flag = true;
                          }
                      }
                      if (flag){
                            gamePanel.getUi().setSubMenu(UI.SubMenu.INVENTORY);
                             gamePanel.getUi().resetcommandIndex();}
                    }

                }
            } else if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.playSoundEffect(7);
                gamePanel.getUi().setSubMenu(UI.SubMenu.MAINMENU);
            }
        }

    }

    private void battleStateEnemy(){
        Random random = new Random();
        enemyChoice = random.nextInt(3);
        gamePanel.playSoundEffect(6);

        gamePanel.getBattleHandler().calculateEnemyAttack(enemyChoice);
        gamePanel.getUi().resetLetterbyLetter();
        gamePanel.setGameState(GamePanel.Gamestate.BATTLELOGENEMY);

    }

    private void battleLogHero(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getUi().resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            gamePanel.getUi().resetItemCounter();
            if(gamePanel.getEnemy()[gamePanel.getCurrentMap()][gamePanel.getBattleHandler().getMonsterIndex()].health<=0){
                gamePanel.setGameState(GamePanel.Gamestate.BATTLEWON);
                looted = gamePanel.getHero().lootEnemyDrop(gamePanel.getBattleHandler().getMonsterIndex());
                goldlooted = gamePanel.getHero().lootEnemyGold(gamePanel.getBattleHandler().getMonsterIndex());
            }
            else{
                itemUsed = false;
                gamePanel.setGameState(GamePanel.Gamestate.BATTLESTATEENEMY);
            }

        }
    }
    private void battleLogEnemy(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getUi().resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            if(gamePanel.getHero().getHealth() <=0){
                gamePanel.setGameState(GamePanel.Gamestate.BATTLELOST);
            }
            else {
                gamePanel.getUi().resetItemCounter();
                gamePanel.setGameState(GamePanel.Gamestate.BATTLESTATEHERO);
            }
        }

    }
    private void battleLost(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getUi().resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            gamePanel.getMusic().stop();
            gamePanel.setGameState(GamePanel.Gamestate.TITLESCREEM);
            gamePanel.restart();
            gamePanel.getBattleHandler().resetBuffs();
        }
    }
    private void battleWon(int code){
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getUi().resetLetterbyLetter();
            gamePanel.playSoundEffect(7);
            gamePanel.getMusic().stop();
            gamePanel.getHero().setExp(gamePanel.getHero().getExp() + gamePanel.getEnemy()[gamePanel.getCurrentMap()][gamePanel.getBattleHandler().getMonsterIndex()].getExp());
            gamePanel.getBattleHandler().resetBuffs();
            gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
            gamePanel.playMusic(0);
            if(gamePanel.getBattleHandler().getMonsterIndex()==1){
                gamePanel.getNpc()[0][9] = new AchatGood(gamePanel);
                gamePanel.getNpc()[0][9].setWorldX(1 * gamePanel.getSpriteSize());
                gamePanel.getNpc()[0][9].setWorldY(1 * gamePanel.getSpriteSize());
            }
            gamePanel.getEnemy()[gamePanel.getCurrentMap()][gamePanel.getBattleHandler().getMonsterIndex()] = null;
            gamePanel.getHero().checkLevelUp();




        }
    }
    private void heroStats(int code){
        if (code == KeyEvent.VK_H  || code == KeyEvent.VK_ESCAPE){
            gamePanel.setGameState(GamePanel.Gamestate.PLAYSTATE);
        }
        if (code == KeyEvent.VK_W){
            if(gamePanel.getUi().getSlotRow() != 0){
                gamePanel.getUi().setSlotRow(gamePanel.getUi().getSlotRow() - 1);
                gamePanel.playSoundEffect(7);
            }
        }
        if (code == KeyEvent.VK_A){
            if(gamePanel.getUi().getSlotCollumn() != 0){
                gamePanel.getUi().setSlotCollumn(gamePanel.getUi().getSlotCollumn() - 1);
                gamePanel.playSoundEffect(7);
            }

        }
        if (code == KeyEvent.VK_S){
            if(gamePanel.getUi().getSlotRow() != 4){
                gamePanel.getUi().setSlotRow(gamePanel.getUi().getSlotRow() + 1);
                gamePanel.playSoundEffect(7);
            }
        }
        if (code == KeyEvent.VK_D){
            if(gamePanel.getUi().getSlotCollumn() != 4){
                gamePanel.getUi().setSlotCollumn(gamePanel.getUi().getSlotCollumn() + 1);
                gamePanel.playSoundEffect(7);
            }

        }
        if (code == KeyEvent.VK_ENTER){
            gamePanel.getHero().equipItem();
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


    public boolean isEnterPressed() {
        return enterPressed;
    }

    public boolean isLooted() {
        return looted;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public int getPlayerChoice() {
        return playerChoice;
    }

    public int getEnemyChoice() {
        return enemyChoice;
    }

    public int getGoldlooted() {
        return goldlooted;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
    public void setUp(boolean up) {
        this.up = up;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
