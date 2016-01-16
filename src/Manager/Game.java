/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Controller.ConsoleDesign;
import Controller.Controller;
import Entities.Athlete;
import Events.Event;
import Events.Fight;
import Entities.Character;
import Entities.Warrior;
import Entities.Thief;
import Items.Armor;
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
    private List<Item> itemEvents;
    private Team team;



            

      
    public Game() {
        events = new ArrayList<>();
        this.itemEvents = new ArrayList<>();
    }

    public void startGame() {
        initItem();

        team = new Team(Controller.askText(ConsoleDesign.textBox("Entrez un nom pour votre équipe",ConsoleDesign.redText)));

        int numberTeam1 = Controller.askNumberBetween(ConsoleDesign.textBox("Entrez le nombre de personnage pour votre équipe",ConsoleDesign.redText), 1, 5);

        fillUpCharacters(team, numberTeam1);

        for (Character character : team.getCharacters()) {
            character.showData();
        }
        //Temporaire
        initEvents();
    }

    private void fillUpCharacters(Team team, int number) {
        System.out.println(ConsoleDesign.textBox("Entrez le nom des " + number + " personnage(s) ainsi que leur classe",ConsoleDesign.redText));
        for (int i = 0; i < number; i++) {
            String name = Controller.askText(ConsoleDesign.textDashArrow("Choississez un nom pour le personnage n°" + (i + 1),ConsoleDesign.redText));
            String textClass = ConsoleDesign.textDashArrow("Choississez une classe pour " + name + " ?",ConsoleDesign.redText)
                    + "\n"
                    + ConsoleDesign.text("1 -> Athlete\n",ConsoleDesign.redText)
                    + ConsoleDesign.text("2 -> Guerrier\n",ConsoleDesign.redText)
                    + ConsoleDesign.text("3 -> Voleur\n",ConsoleDesign.redText);
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

    private void initItem() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("item.json"));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray useableItems = (JSONArray) jsonObject.get("Useable Item");
            JSONArray armors = (JSONArray) jsonObject.get("Armor");
            JSONArray weapons = (JSONArray) jsonObject.get("Weapon");

            Iterator<JSONObject> iterator = useableItems.iterator();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long bonus = (long) objTemp.get("bonus");
                long rarity = (long) objTemp.get("rarity");
                this.itemEvents.add(new UseableItem(name, (int)weight, (int)bonus, (int)rarity));
            }
            iterator = armors.iterator();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long handlingAbility = (long) objTemp.get("handlingability");
                long defensevalue = (long) objTemp.get("defensevalue");
                long rarity = (long) objTemp.get("rarity");
                this.itemEvents.add(new Armor(name, (int)weight, (int)handlingAbility, (int)defensevalue, (int)rarity));
            }
            iterator = weapons.iterator();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long handlingAbility = (long) objTemp.get("handlingability");
                long attackvalue = (long) objTemp.get("attackvalue");
                long rarity = (long) objTemp.get("rarity");
                this.itemEvents.add(new Weapon(name, (int)weight, (int)handlingAbility, (int)attackvalue, (int)rarity));
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
