/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Manager;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import com.corentin_yannick.RPG_Polytech.Controllers.Controller;
import com.corentin_yannick.RPG_Polytech.Entities.Athlete;
import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
import com.corentin_yannick.RPG_Polytech.Events.Event;
import com.corentin_yannick.RPG_Polytech.Events.Fight;
import com.corentin_yannick.RPG_Polytech.Entities.Character;
import com.corentin_yannick.RPG_Polytech.Entities.Warrior;
import com.corentin_yannick.RPG_Polytech.Entities.Thief;
import com.corentin_yannick.RPG_Polytech.Events.Discovery;
import com.corentin_yannick.RPG_Polytech.Items.Armor;
import com.corentin_yannick.RPG_Polytech.Items.Greave;
import com.corentin_yannick.RPG_Polytech.Items.Potion;
import com.corentin_yannick.RPG_Polytech.Items.Weapon;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.CYAN;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.GREEN;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.RED;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.WHITE;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.REDBACK;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.MAGENTA;
import com.corentin_yannick.RPG_Polytech.Items.StuffItem;

/**
 *
 * @author yannick
 */
public class Game {

    private List<Event> events;
    private Team team;
    private int nbFights = 0;
    private int nbDiscovery = 0;

    public Game() {
        this.readJSONFiles();
        this.initPlot();
        this.displayBegining();
        events = new ArrayList<>();
        this.startGame();
    }

    private void initPlot() {
        Story.initStory();
    }

    private void displayBegining() {
        System.out.println(ConsoleDesign.RPG(WHITE, REDBACK));
        System.out.println(Story.getPlot());
    }

    private void createPlayerTeam() {
        System.out.println(ConsoleDesign.textBox("Création de votre équipe", WHITE, REDBACK));

        team = new Team(Controller.askText(ConsoleDesign.textBox("Entrez un nom pour votre équipe", RED)));

        int numberTeam1 = Controller.askNumberBetween(ConsoleDesign.textBox("Entrez le nombre de personnage pour votre équipe (entre 2 et 5)", RED), 2, 5);
        fillUpCharacters(numberTeam1);

        System.out.println(ConsoleDesign.textBox("Récapitulatif de votre équipe:", RED));
        System.out.println(this.team.toString());
        Story.replaceVars(team);
    }

    private void startGame() {
        this.createPlayerTeam();
        System.out.println(Story.getPlot());
        this.displayTips();
        initEvents();
    }

    private void fillUpCharacters(int number) {
        System.out.println(ConsoleDesign.textBox("Entrez le nom des " + number + " personnage(s) ainsi que leur classe", RED));
        for (int i = 0; i < number; i++) {
            String name = Controller.askText(ConsoleDesign.textDashArrow("Choississez un nom pour le personnage n°" + (i + 1), RED));
            String textClass = ConsoleDesign.textDashArrow("Choississez une classe pour " + name + " ?", RED)
                    + "\n"
                    + ConsoleDesign.text("1 -> Athlete\n", RED)
                    + ConsoleDesign.text("2 -> Guerrier\n", RED)
                    + ConsoleDesign.text("3 -> Voleur\n", RED);
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
            //character.getBasicAttributesCHEAT().cheat();
            //character.reinitStats();
            team.addCharacterTeam(character);
        }
    }

    private void initEvents() {
        while (true) {
            int choiceUser = Controller.askNumberBetween("1-> Se déplacer\n2-> Consulter son inventaire\n3-> Modifier l'équipement des personnages\n"
                    + "4-> Voir le statut de l'équipe", 1, 4);
            switch (choiceUser) {
                case 1:
                    if (this.displayStory()) {
                        int rd = (int) (Math.random() * 2);
                        if (rd == 0) {
                            events.add(new Fight(team, new Team(team)));
                            nbFights++;
                        } else {
                            events.add(new Discovery(team));
                            nbDiscovery++;
                        }
                    }
                    break;
                case 2:
                    for (Character c : this.team.getCharacters()) {
                        System.out.println(ConsoleDesign.text(c.getInvetoryToString(), GREEN));
                    }
                    break;
                case 3:
                    changeEquipmentTeam();
                case 4:
                    System.out.println(ConsoleDesign.text(this.team.toString(), CYAN));
                    break;
            }
        }
    }

    public boolean displayStory() {
        if (this.nbFights >= 2 && this.nbDiscovery >= 2 && !Story.alreadyPlot.get(2)) {
            System.out.println(Story.getPlot());
            return false;
        }
        return true;
    }

    private void readJSONFiles() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("item.json"));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray potions = (JSONArray) jsonObject.get("Potion");
            JSONArray armors = (JSONArray) jsonObject.get("Armor");
            JSONArray weapons = (JSONArray) jsonObject.get("Weapon");
            JSONArray greaves = (JSONArray) jsonObject.get("Greave");

            Iterator<JSONObject> iterator = potions.iterator();
            Potion.listPotionItem = new ArrayList<>();
            while (iterator.hasNext()) {
                JSONObject objTemp = iterator.next();
                String name = (String) objTemp.get("name");
                long weight = (long) objTemp.get("weight");
                long bonus = (long) objTemp.get("bonus");
                long rarity = (long) objTemp.get("rarity");
                Attribute att = Attribute.valueOf(objTemp.get("attribute").toString());
                Potion.listPotionItem.add(new Potion(name, (int) weight, (int) bonus, (int) rarity, att));
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
                long requiredLevel = (long) objTemp.get("requiredLevel");
                Armor.listeArmorItem.add(new Armor(name, (int) weight, (int) handlingAbility, (int) defensevalue, (int) rarity, (int) requiredLevel));
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
                long requiredLevel = (long) objTemp.get("requiredLevel");
                Weapon.listWeaponItem.add(new Weapon(name, (int) weight, (int) handlingAbility, (int) attackvalue, (int) rarity, (int) requiredLevel));
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
                long requiredLevel = (long) objTemp.get("requiredLevel");
                Greave.listGreaveItem.add(new Greave(name, (int) weight, (int) handlingAbility, (int) defenseValue, (int) rarity, (int) requiredLevel));
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

    public void changeEquipment(Character chara, Class classe) {
        System.out.println("Equipement actuel:");
        System.out.println(chara.getEquipment(classe));
        System.out.println(chara.getEquipeableItemToString(classe));
        int choiceUser = Controller.askNumberBetween("Choisissez le numéro de l'item que vous voulez remplacer (0 pour rien changer)", 0, chara.getNbEquipableItem(classe));
        if (choiceUser == 0) {
            return;
        }
        System.out.println(chara.replaceEquipment((StuffItem)chara.getInInventoryItemOfType(Weapon.class, choiceUser - 1), chara.getEquipment(Weapon.class)));
    }

    private void displayTips() {
        System.out.println(ConsoleDesign.text("Bonjour, et bienvenue dans Beat the Invador! Les règles du jeu sont simples.", GREEN)
                + "\n" + ConsoleDesign.text("Vous êtes aux commandes d'une équipe que vous avez créer.", GREEN)
                + "\n" + ConsoleDesign.text("Durant votre périple, vous allez être ammenés à vous déplacer sur Terre, pour récupérer des informations.", GREEN)
                + "\n" + ConsoleDesign.text("Cependant, les milieux sont hostiles! Vous pouvez a tout moment rencontrer une horde d'ennemis, qui sont généralement vos semblables (bien qu'ils ne vous ressemblent plus énormément)", GREEN)
                + "\n" + ConsoleDesign.text("Vous devrez éliminer ces groupes pour pouvoir avancer. A la fin de chaque rencontre, votre vie vous est rendue.", GREEN)
                + "\n" + ConsoleDesign.text("Entres chaque combat, vous pourrez gérer votre équipement et votre inventaire.", GREEN)
                + "\n" + ConsoleDesign.text("Qui sait, peut-être que vous ferrez des découvertes au fil du temps...", GREEN)
                + "\n" + ConsoleDesign.text("Allez, c'est à vous!", GREEN));
        System.out.println("\n");
    }

    private void changeEquipmentTeam() {
        String text = "Modifer l'équipement du personnage:\n";
        int i = 0;
        for (Character c : this.team.getCharacters()) {
            text += i + "-> " + this.team.getCharacters().get(i).getName() + "\n";
            i++;
        }
        text += i + "-> Ne pas modifier";
        int choice = Controller.askNumberBetween(text, 0, i);
        if (choice == i) {
            return;
        } else {
            Character c = this.team.getCharacters().get(choice);
            System.out.println(ConsoleDesign.text(c.getEquipmentToString(), GREEN));
            boolean result = Controller.askYesNo("Voulez-vous modifier votre équipement? [O/N]");
            while (result) {
                int choiceUser = Controller.askNumberBetween(ConsoleDesign.text("1-> Remplacer Arme\n2-> Remplacer Armure\n3-> Remplacer Jambières\n4-> Ne rien remplacer", MAGENTA), 1, 4);
                switch (choiceUser) {
                    case 1:
                        changeEquipment(c, Weapon.class);
                        break;
                    case 2:
                        changeEquipment(c, Armor.class);
                        break;
                    case 3:
                        changeEquipment(c, Greave.class);
                        break;
                    case 4:
                        result = false;
                        break;
                }
            }
        }
    }
}
