package main;

import Items.Item;
import Items.consumables.HealthPotion;
import Items.consumables.ManaPotion;
import Items.consumables.Mushroom;
import Items.equipment.Armor;
import Items.equipment.LegendaryPen;
import Items.equipment.PurpleSword;
import Items.equipment.Weapon;
import main.GamePanel;

import java.io.*;

public class SaveLoad {
    private GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void save(){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("save.txt"));

            //hero
            bufferedWriter.write(String.valueOf((gamePanel.hero.getLevel())));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getMaxHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getMaxMana()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getMana()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getNextLevelExp()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getExp()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getBaseDefence()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getDefence()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getBaseStrength()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getStrength()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getDexterity()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getGold()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getWorldX()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getWorldY()));

            //inventory
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.getInventory().size()));
            for(Item item : gamePanel.hero.getInventory()){
                bufferedWriter.newLine();
                bufferedWriter.write(item.getName());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(item.getAmount()));
            }

            //equipment
            String weapon = "", armor = "";
            for (int i = 0; i < gamePanel.hero.getInventory().size(); i++) {
                if (gamePanel.hero.getInventory().get(i) == gamePanel.hero.getCurrentWeapon()){
                    weapon = String.valueOf(i);

                } else  if (gamePanel.hero.getInventory().get(i) == gamePanel.hero.getCurrentArmor()){
                    armor = String.valueOf(i);

                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write(weapon);
            bufferedWriter.newLine();
            bufferedWriter.write(armor);

            //enemies and objects
            for (int i = 0; i <gamePanel.maxMap; i++) {
                for (int j = 0; j <10; j++) {
                    if(gamePanel.superObject[i][j]!=null){
                        bufferedWriter.newLine();
                        bufferedWriter.write("EXISTS");
                    } else if (gamePanel.superObject[i][j]==null) {
                        bufferedWriter.newLine();
                        bufferedWriter.write("NULL");
                    }
                    if(gamePanel.enemy[i][j]!=null){
                        bufferedWriter.newLine();
                        bufferedWriter.write("EXISTS");
                    } else if (gamePanel.enemy[i][j]==null) {
                        bufferedWriter.newLine();
                        bufferedWriter.write("NULL");
                    }
                }
            }

            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void load() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("save.txt"));
            String string = bufferedReader.readLine();
            
            gamePanel.hero.setLevel(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setMaxHealth(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setHealth(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setMaxMana(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setMana(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setNextLevelExp(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setExp(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setBaseDefence(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setDefence(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setBaseStrength(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setStrength(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setDexterity(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setGold(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setWorldX(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.hero.setWorldY(Integer.parseInt(string));

            //inventory
            gamePanel.hero.getInventory().clear();
            string = bufferedReader.readLine();
            int itemCount = Integer.parseInt(string);
            for (int i = 0; i < itemCount; i++) {
                string = bufferedReader.readLine();
                Item item = loadItem(string);
                gamePanel.hero.getInventory().add(item);
                string = bufferedReader.readLine();
                gamePanel.hero.getInventory().get(i).setAmount(Integer.parseInt(string));
            }

            //equipment
            string = bufferedReader.readLine();
            Weapon weapon = (Weapon) gamePanel.hero.getInventory().get(Integer.parseInt(string));
            gamePanel.hero.setCurrentWeapon(weapon);
            gamePanel.hero.getCurrentWeapon().recalculateHeroStats(gamePanel);
            string = bufferedReader.readLine();
            Armor armor = (Armor) gamePanel.hero.getInventory().get(Integer.parseInt(string));
            gamePanel.hero.setCurrentArmor(armor);
            gamePanel.hero.getCurrentArmor().recalculateHeroStats(gamePanel);

            //enemies and objects
            for (int i = 0; i <gamePanel.maxMap; i++) {
                for (int j = 0; j <10; j++) {
                    string = bufferedReader.readLine();
                    if(string.equals("NULL")){
                        gamePanel.superObject[i][j] = null;
                    }
                    string = bufferedReader.readLine();
                    if(string.equals("NULL")){
                        gamePanel.enemy[i][j] = null;
                    }
                }
            }


            bufferedReader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public Item loadItem(String name){
        Item item = null;
        if (name.equals("Mushroom")){
            item = new Mushroom(gamePanel);
        } else if (name.equals("Legendary Pen")) {
            item = new LegendaryPen(gamePanel);
        }else if (name.equals("sword")) {
            item = new PurpleSword(gamePanel);
        }
        else if (name.equals("Health Potion")) {
            item = new HealthPotion(gamePanel);
        }else if (name.equals("armor")) {
            item = new Armor(gamePanel);
        }
        else if (name.equals("Mana Potionr")) {
            item = new ManaPotion(gamePanel);
        }
        return item;
    }
}
