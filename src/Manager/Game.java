/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Controller.Controller;
import Entities.Athlete;
import Events.Event;
import Events.Fight;
import Entities.Character;
import Entities.Warrior;
import Entities.Thief;
import Items.Armor;
import Items.Greave;
import Items.Item;
import Items.Rarity;
import Items.UseableItem;
import Items.Weapon;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author yannick
 */
public class Game {

    private List<Event> events;
    private Team team;

    public Game() {
        events = new ArrayList<>();
    }

    public void startGame() {
        readJSONFiles();

        for(Weapon w : Weapon.listWeaponItem)
        {
            System.out.println(w+"\n");
        }
        for(Armor a : Armor.listeArmorItem)
        {
            System.out.println(a+"\n");
        }
        for(Greave g : Greave.listGreaveItem)
        {
            System.out.println(g+"\n");
        }
        /*team = new Team(Controller.askText("Entrer un nom pour l'équipe 1:"));

        int numberTeam1 = Controller.askNumberBetween("Entrer un nombre de joueur pour l'équipe 1:", 1, 5);

        fillUpCharacters(team, numberTeam1);

        for (Character character : team.getCharacters()) {
            character.showData();
        }
        //Temporaire
        initEvents();*/
    }

    private void fillUpCharacters(Team team, int number) {
        System.out.println("Entrer le nom des " + number + " joueurs ainsi que leur classe pour l'équipe " + team.getName() + " :");
        for (int i = 0; i < number; i++) {
            System.out.println("--------------------");
            String name = Controller.askText("Choississez un nom pour le personnage n°" + (i + 1));
            String textClass = "Choississez une classe pour " + name + " ?\n"
                    + "1 -> Athlete\n"
                    + "2 -> Guerrier\n"
                    + "3 -> Magicien";
            int classe = Controller.askNumberBetween(textClass, 1, 3);
            Character character = null;
            switch (classe) {
                case 1:
                    character = new Athlete(name);
                    break;
                case 2:
                    character = new Warrior(name);
                    break;
                case 3:
                    character = new Thief(name);
                    break;
            }
            team.addCharacterTeam(character);
        }
    }

    private void initEvents() {
        Team temp = new Team("nom");
        temp.addCharacterTeam(new Warrior("Peter"));
        events.add(new Fight(team, temp));
    }

    private void readJSONFiles() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("item.json"));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray useableItems = (JSONArray) jsonObject.get("Useable Item");
            JSONArray armors = (JSONArray) jsonObject.get("Armor");
            JSONArray weapons = (JSONArray) jsonObject.get("Weapon");
            JSONArray greaves = (JSONArray) jsonObject.get("Greave");

            Iterator<JSONObject> iterator = useableItems.iterator();
            UseableItem.listUseableItem = new ArrayList<>();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long bonus = (long) objTemp.get("bonus");
                long rarity = (long) objTemp.get("rarity");
                UseableItem.listUseableItem.add(new UseableItem(name, (int)weight, (int)bonus, (int)rarity));
            }
            iterator = armors.iterator();
            Armor.listeArmorItem = new ArrayList<>();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long handlingAbility = (long) objTemp.get("handlingability");
                long defensevalue = (long) objTemp.get("defenseValue");
                long rarity = (long) objTemp.get("rarity");
                long requiredLevel = (long)objTemp.get("requiredLevel");
                Armor.listeArmorItem.add(new Armor(name, (int)weight, (int)handlingAbility, (int)defensevalue, (int)rarity, (int)requiredLevel));
            }
            iterator = weapons.iterator();
            Weapon.listWeaponItem = new ArrayList<>();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long handlingAbility = (long) objTemp.get("handlingability");
                long attackvalue = (long) objTemp.get("attackValue");
                long rarity = (long) objTemp.get("rarity");
                long requiredLevel = (long)objTemp.get("requiredLevel");
                Weapon.listWeaponItem.add(new Weapon(name, (int)weight, (int)handlingAbility, (int)attackvalue, (int)rarity, (int)requiredLevel));
            }
            iterator = greaves.iterator();
            Greave.listGreaveItem = new ArrayList<>();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long handlingAbility = (long) objTemp.get("handlingability");
                long defenseValue = (long) objTemp.get("defenseValue");
                long rarity = (long) objTemp.get("rarity");
                long requiredLevel = (long)objTemp.get("requiredLevel");
                Greave.listGreaveItem.add(new Greave(name, (int)weight, (int)handlingAbility, (int)defenseValue, (int)rarity, (int)requiredLevel));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
