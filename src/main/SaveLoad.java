package main;

import Items.Item;
import Items.consumables.*;
import Items.equipment.*;
import creature.Nikolaidis;
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
            bufferedWriter.write(String.valueOf((gamePanel.getCurrentMap())));
            bufferedWriter.newLine();
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

            //quiz
            bufferedWriter.newLine();
            if(gamePanel.getUi().getNikolaidis()==null){
                bufferedWriter.write("0");
            }else {
                bufferedWriter.write(String.valueOf(gamePanel.getUi().getNikolaidis().getQuizStatus()));
            }

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
            String weapon = "", armor = "", gem = "", trinket = "";
            for (int i = 0; i < gamePanel.getHero().getInventory().size(); i++) {
                if (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentWeapon()){
                    weapon = String.valueOf(i);

                } else  if (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentArmor()){
                    armor = String.valueOf(i);

                }
                else  if (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentGem()){
                    gem = String.valueOf(i);

                }
                else  if (gamePanel.getHero().getInventory().get(i) == gamePanel.getHero().getCurrentTrinket()){
                    trinket = String.valueOf(i);

                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write(weapon);
            bufferedWriter.newLine();
            bufferedWriter.write(armor);
            bufferedWriter.newLine();
            bufferedWriter.write(gem);
            bufferedWriter.newLine();
            bufferedWriter.write(trinket);

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
            gamePanel.setCurrentMap(Integer.parseInt(string));
            string = bufferedReader.readLine();
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

            //quiz
            string = bufferedReader.readLine();
            if(string.equals("0")){

                gamePanel.setQuizStatus(0);
            } else if(string.equals("1")){

                gamePanel.setQuizStatus(1);
            }else if(string.equals("2")){

                gamePanel.setQuizStatus(2);
            }
            System.out.println(gamePanel.getQuizStatus());



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
            string = bufferedReader.readLine();
            Gem gem = (Gem) gamePanel.getHero().getInventory().get(Integer.parseInt(string));
            gamePanel.getHero().setCurrentGem(gem);
            gamePanel.getHero().getCurrentGem().recalculateHeroStats(gamePanel);
            string = bufferedReader.readLine();
            Trinket trinket = (Trinket) gamePanel.getHero().getInventory().get(Integer.parseInt(string));
            gamePanel.getHero().setCurrentTrinket(trinket);
            gamePanel.getHero().getCurrentTrinket().recalculateHeroStats(gamePanel);

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
        } else if (name.equals("Watermelon")) {
            item = new Watermelon(gamePanel);
        } else if (name.equals("Lollipop")) {
            item = new Lolipop(gamePanel);
        } else if (name.equals("Cool Star")) {
            item = new CoolStar(gamePanel);
        } else if (name.equals("Shiny Mushroom")) {
            item = new ShinyMushroom(gamePanel);
        } else if (name.equals("Diamond")) {
            item = new Diamond(gamePanel);
        } else if (name.equals("Magic Key")) {
            item = new MagicKey(gamePanel);
        } else if (name.equals("Magic Lock")) {
            item = new MagicLock(gamePanel);
        }
        else if (name.equals("Legendary Pen")) {
            item = new LegendaryPen(gamePanel);
        }else if (name.equals("sword")) {
            item = new PurpleSword(gamePanel);
        } else if (name.equals("Pink Health Potion")) {
            item = new HealthPotion2(gamePanel);
       } else if (name.equals("Health Potion")) {
            item = new HealthPotion(gamePanel);
        }
       else if (name.equals("armor")) {
            item = new Armor(gamePanel);
        }
       else if (name.equals("Yel. Mana Potion")) {
           item = new ManaPotion2(gamePanel);
       }
        else if (name.equals("Mana Potion")) {
            item = new ManaPotion(gamePanel);
        }
        else if (name.equals("Fort. Potion")) {
            item = new FortifiedPotion(gamePanel);
        }
        else if (name.equals("Or. Fort. Potion")) {
            item = new FortifiedPotion2(gamePanel);
        }
        return item;
    }
}
