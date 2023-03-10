package main;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {


        JFrame mainWindow = new JFrame();
        GamePanel gamePanel = new GamePanel();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Project Nanos Magos");
        mainWindow.add(gamePanel);
        mainWindow.pack(); //makes window fit the panel
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

        gamePanel.setupGame();
        gamePanel.beginThread();






    }


}
