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
            bufferedWriter.write(String.valueOf((gamePanel.getHero().getLevel())));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getMaxHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getMaxMana()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getMana()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getNextLevelExp()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getExp()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getBaseDefence()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getDefence()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getBaseStrength()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getStrength()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getDexterity()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getGold()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getWorldX()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getWorldY()));

            //inventory
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(gamePanel.getHero().getInventory().size()));
            for(Item item : gamePanel.getHero().getInventory()){
                bufferedWriter.newLine();
                bufferedWriter.write(item.getName());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(item.getAmount()));
            }

            //equipment
            String weapon = "", armor = "";
            for (int i = 0; i < gamePanel.getHero().getInventory().size(); i++) {
                if (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentWeapon()){
                    weapon = String.valueOf(i);

                } else  if (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentArmor()){
                    armor = String.valueOf(i);

                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write(weapon);
            bufferedWriter.newLine();
            bufferedWriter.write(armor);

            //enemies and objects
            for (int i = 0; i < gamePanel.getMaxMap(); i++) {
                for (int j = 0; j <10; j++) {
                    if(gamePanel.getSuperObject()[i][j]!=null){
                        bufferedWriter.newLine();
                        bufferedWriter.write("EXISTS");
                    } else if (gamePanel.getSuperObject()[i][j]==null) {
                        bufferedWriter.newLine();
                        bufferedWriter.write("NULL");
                    }
                    if(gamePanel.getEnemy()[i][j]!=null){
                        bufferedWriter.newLine();
                        bufferedWriter.write("EXISTS");
                    } else if (gamePanel.getEnemy()[i][j]==null) {
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
            
            gamePanel.getHero().setLevel(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setMaxHealth(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setHealth(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setMaxMana(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setMana(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setNextLevelExp(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setExp(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setBaseDefence(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setDefence(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setBaseStrength(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setStrength(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setDexterity(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setGold(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setWorldX(Integer.parseInt(string));
            string = bufferedReader.readLine();
            gamePanel.getHero().setWorldY(Integer.parseInt(string));

            //inventory
            gamePanel.getHero().getInventory().clear();
            string = bufferedReader.readLine();
            int itemCount = Integer.parseInt(string);
            for (int i = 0; i < itemCount; i++) {
                string = bufferedReader.readLine();
                Item item = loadItem(string);
                gamePanel.getHero().getInventory().add(item);
                string = bufferedReader.readLine();
                gamePanel.getHero().getInventory().get(i).setAmount(Integer.parseInt(string));
            }

            //equipment
            string = bufferedReader.readLine();
            Weapon weapon = (Weapon) gamePanel.getHero().getInventory().get(Integer.parseInt(string));
            gamePanel.getHero().setCurrentWeapon(weapon);
            gamePanel.getHero().getCurrentWeapon().recalculateHeroStats(gamePanel);
            string = bufferedReader.readLine();
            Armor armor = (Armor) gamePanel.getHero().getInventory().get(Integer.parseInt(string));
            gamePanel.getHero().setCurrentArmor(armor);
            gamePanel.getHero().getCurrentArmor().recalculateHeroStats(gamePanel);

            //enemies and objects
            for (int i = 0; i < gamePanel.getMaxMap(); i++) {
                for (int j = 0; j <10; j++) {
                    string = bufferedReader.readLine();
                    if(string.equals("NULL")){
                        gamePanel.getSuperObject()[i][j] = null;
                    }
                    string = bufferedReader.readLine();
                    if(string.equals("NULL")){
                        gamePanel.getEnemy()[i][j] = null;
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
        } else if (name.equals("Health Potion")) {
            item = new HealthPotion(gamePanel);
       }
       else if (name.equals("armor")) {
            item = new Armor(gamePanel);
        }
       else if (name.equals("Mana Potion")) {
           item = new ManaPotion(gamePanel);
       }
        return item;
    }
}
