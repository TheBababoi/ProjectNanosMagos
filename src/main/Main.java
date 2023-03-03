package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame mainWindow = new JFrame();
        mainWindow.setVisible(true);
        mainWindow.setTitle("Project Team11");
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel();
        mainWindow.add(gamePanel);
        mainWindow.pack(); //makes window fit the panel


    }
}
