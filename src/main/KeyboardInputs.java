package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class KeyboardInputs implements KeyListener {

    public boolean enterPressed ;
    public int damage;
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public GamePanel gamePanel;
    //debug
    public boolean debugMode = false;
    public int playerChoice,enemyChoice;

    int i =0;


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
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;

            }
        } else if (gamePanel.gameState == GamePanel.Gamestate.PLAYSTATE) {
            if (code == KeyEvent.VK_W) {
                up = true;

            } else if (code == KeyEvent.VK_A) {
                left = true;
            } else if (code == KeyEvent.VK_S) {
                down = true;
            } else if (code == KeyEvent.VK_D) {
                right = true;
            } else if (code == KeyEvent.VK_P) {

                gamePanel.gameState = GamePanel.Gamestate.PAUSESTATE;
            } else if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        } else if (gamePanel.gameState == GamePanel.Gamestate.PAUSESTATE) {
            if (code == KeyEvent.VK_P) {
                gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
            }
        } else if (gamePanel.gameState == GamePanel.Gamestate.DIALOGUESTATE) {
            if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.hero.friendOrFoe) {
                    gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
                } else {
                    gamePanel.playMusic(4);
                    gamePanel.gameState = GamePanel.Gamestate.BATTLESTATEHERO;
                }

            }
        } else if (gamePanel.gameState == GamePanel.Gamestate.TITLESCREEM) {
            if (code == KeyEvent.VK_W) {
                gamePanel.ui.commandIndex--;
                if (gamePanel.ui.commandIndex < 0) {
                    gamePanel.ui.commandIndex = 3;
                }
            } else if (code == KeyEvent.VK_S) {
                gamePanel.ui.commandIndex++;
                if (gamePanel.ui.commandIndex > 3) {
                    gamePanel.ui.commandIndex = 0;
                }

            } else if (code == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandIndex == 0) {
                    gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
                    gamePanel.playMusic(0);
                } else if (gamePanel.ui.commandIndex == 1) {

                } else if (gamePanel.ui.commandIndex == 2) {

                } else if (gamePanel.ui.commandIndex == 3) {
                    System.exit(0);
                }

            }
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLESTATEHERO) {




            if (gamePanel.ui.subMenu == UI.SubMenu.INVENTORY) {
                if (code == KeyEvent.VK_W) {

                    if (gamePanel.ui.commandIndexY > 0) {
                        gamePanel.ui.commandIndexY--;
                    }
                } else if (code == KeyEvent.VK_S) {
                    if (gamePanel.ui.commandIndexY < 3) {
                        gamePanel.ui.commandIndexY++;
                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    gamePanel.ui.subMenu = UI.SubMenu.MAINMENU;
                    gamePanel.ui.commandIndexY = 1;
                }

            } else {
                if (code == KeyEvent.VK_W) {
                    gamePanel.ui.commandIndexY--;
                    if (gamePanel.ui.commandIndexY < 0) {
                        gamePanel.ui.commandIndexY = 1;
                    }
                } else if (code == KeyEvent.VK_S) {
                    gamePanel.ui.commandIndexY++;
                    if (gamePanel.ui.commandIndexY > 1) {
                        gamePanel.ui.commandIndexY = 0;
                    }
                } else if (code == KeyEvent.VK_A) {
                    gamePanel.ui.commandIndexX--;
                    if (gamePanel.ui.commandIndexX < 0) {
                        gamePanel.ui.commandIndexX = 1;
                    }
                } else if (code == KeyEvent.VK_D) {
                    gamePanel.ui.commandIndexX++;
                    if (gamePanel.ui.commandIndexX > 1) {
                        gamePanel.ui.commandIndexX = 0;
                    }
                } else if (code == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.subMenu == UI.SubMenu.ATTACKMENU) {
                        if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndexY == 0) {
                            playerChoice = 0;

                            System.out.println("Player choice 0");
                            gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                            gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;


                        }
                        if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndexY == 0) {
                            playerChoice = 1;

                            System.out.println("Player choice 1");
                            gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                            gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        }
                        if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndexY == 1) {
                            playerChoice = 2;

                            System.out.println("Player choice 2");
                            gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                            gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        }
                        if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndexY == 1) {
                            playerChoice = 3;

                            System.out.println("Player choice 3");
                            gamePanel.battleHandler.calculateHeroAttack(playerChoice);

                            gamePanel.gameState = GamePanel.Gamestate.BATTLELOGHERO;
                        }
                    } else {


                        if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndexY == 0) {
                            gamePanel.ui.subMenu = UI.SubMenu.ATTACKMENU;

                        }
                        if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndexY == 0) {

                        }
                        if (gamePanel.ui.commandIndexX == 0 && gamePanel.ui.commandIndexY == 1) {

                        }
                        if (gamePanel.ui.commandIndexX == 1 && gamePanel.ui.commandIndexY == 1) {
                            gamePanel.ui.subMenu = UI.SubMenu.INVENTORY;
                        }

                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    gamePanel.ui.subMenu = UI.SubMenu.MAINMENU;
                }
            }
        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLESTATEENEMY) {
            Random random = new Random();
             enemyChoice = random.nextInt(3);

                gamePanel.battleHandler.calculateEnemyAttack(enemyChoice);
                gamePanel.gameState = GamePanel.Gamestate.BATTLELOGENEMY;


        } else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOGHERO) {
            if (code == KeyEvent.VK_ENTER) {
                    if(gamePanel.enemy[gamePanel.battleHandler.monsterIndex].health<=0){
                        gamePanel.gameState = GamePanel.Gamestate.BATTLEWON;
                    }
                   else{
                       gamePanel.gameState = GamePanel.Gamestate.BATTLESTATEENEMY;
                    }

            }
        } else if(gamePanel.gameState == GamePanel.Gamestate.BATTLELOGENEMY){
            if (code == KeyEvent.VK_ENTER) {
                if(gamePanel.hero.health<=0){
                    gamePanel.gameState = GamePanel.Gamestate.BATTLELOST;
                }
                else {
                    gamePanel.gameState = GamePanel.Gamestate.BATTLESTATEHERO;
                }



            }


        }else if (gamePanel.gameState == GamePanel.Gamestate.BATTLEWON) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.music.stop();
                gamePanel.gameState = GamePanel.Gamestate.PLAYSTATE;
                gamePanel.playMusic(0);
                gamePanel.enemy[gamePanel.battleHandler.monsterIndex] = null;

            }

        }else if (gamePanel.gameState == GamePanel.Gamestate.BATTLELOST) {
            if (code == KeyEvent.VK_ENTER) {
               gamePanel.music.stop();
                gamePanel.gameState = GamePanel.Gamestate.TITLESCREEM;


            }

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
}
