package main;

import Items.Item;
import Items.consumables.Mushroom;
import Items.equipment.Armor;
import Items.equipment.LegendaryPen;
import Items.equipment.PurpleSword;
import Items.equipment.Weapon;
import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void save(){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("save.txt"));

            //hero
            bufferedWriter.write(String.valueOf((gamePanel.hero.level)));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.maxHealth));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.health));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.maxMana));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.mana));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.nextLevelExp));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.exp));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.baseDefence));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.defence));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.baseStrength));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.strength));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.dexterity));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.gold));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.worldX));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.worldY));

            //inventory
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.hero.inventory.size()));
            for(Item item : gamePanel.hero.inventory){
                bufferedWriter.newLine();
                bufferedWriter.write(item.getName());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(item.amount));
            }

            //equipment
            String weapon = "", armor = "";
            for (int i = 0; i <gamePanel.hero.inventory.size(); i++) {
                if (gamePanel.hero.inventory.get(i) == gamePanel.hero.currentWeapon){
                    weapon = String.valueOf(i);

                } else  if (gamePanel.hero.inventory.get(i) == gamePanel.hero.currentArmor){
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
            
            gamePanel.hero.level = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.maxHealth = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.health = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.maxMana = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.mana = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.nextLevelExp = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.exp = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.baseDefence = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.defence = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.baseStrength = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.strength = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.dexterity = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.gold = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.worldX = Integer.parseInt(string);
            string = bufferedReader.readLine();
            gamePanel.hero.worldY = Integer.parseInt(string);

            //inventory
            gamePanel.hero.inventory.clear();
            string = bufferedReader.readLine();
            int itemCount = Integer.parseInt(string);
            for (int i = 0; i < itemCount; i++) {
                string = bufferedReader.readLine();
                Item item = loadItem(string);
                gamePanel.hero.inventory.add(item);
                string = bufferedReader.readLine();
                gamePanel.hero.inventory.get(i).amount = Integer.parseInt(string);
            }

            //equipment
            string = bufferedReader.readLine();
            Weapon weapon = (Weapon)gamePanel.hero.inventory.get(Integer.parseInt(string));
            gamePanel.hero.currentWeapon = weapon;
            gamePanel.hero.currentWeapon.recalculateHeroStats(gamePanel);
            string = bufferedReader.readLine();
            Armor armor = (Armor) gamePanel.hero.inventory.get(Integer.parseInt(string));
            gamePanel.hero.currentArmor = armor;
            gamePanel.hero.currentArmor.recalculateHeroStats(gamePanel);

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
        else if (name.equals("armor")) {
            item = new Armor(gamePanel);
        }
        return item;
    }
}
