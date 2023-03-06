package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    public boolean enterPressed ;
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public GamePanel gamePanel;
    //debug
    public boolean debugMode = false;

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
        if(gamePanel.gameState == gamePanel.playState){
            if(code == KeyEvent.VK_W){
                up = true;

            }
            else if(code == KeyEvent.VK_A){
                left = true;
            }
            else if(code == KeyEvent.VK_S){
                down = true;
            }
            else if(code == KeyEvent.VK_D){
                right = true;
            }
            else if(code == KeyEvent.VK_P){

                gamePanel.gameState = gamePanel.pauseState;
           }
            else if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
        }
        else if (gamePanel.gameState == gamePanel.pauseState){
            if(code ==KeyEvent.VK_P){
                    gamePanel.gameState = gamePanel.playState;
                }
        }
        else if (gamePanel.gameState == gamePanel.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gamePanel.gameState = gamePanel.playState;
            }
        }
        //debug
        if(code ==KeyEvent.VK_T){
            if(!debugMode){
                debugMode = true;
            }else {
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
