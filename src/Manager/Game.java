/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Athlete;
import Events.Event;
import Events.Fight;
import Entities.Character;
import Entities.Warrior;
import Entities.Wizard;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author yannick
 */
public class Game {

    private List<Event> events;
    private Team team1;
    private Team team2;
    private final Scanner sc = new Scanner(System.in);

    public Game() {
        events = new ArrayList<>();
    }

    public void startGame() {
        System.out.println("Entrer un nom pour l'équipe 1:");
        String nameTeam1 = sc.nextLine();
        team1 = new Team(nameTeam1);
        System.out.println("Entrer un nom pour l'équipe 2:");
        String nameTeam2 = sc.nextLine();
        team2 = new Team(nameTeam2);

        int numberTeam1 = 0, numberTeam2 = 0;
        System.out.println("Entrer un nombre de joueur pour l'équipe 1:");
        do {
            try {
                String numberTeam1String = sc.nextLine();
                numberTeam1 = Integer.parseInt(numberTeam1String);
                if (numberTeam1 > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un chiffre supérieur à 0");
            }
        } while (true);

        System.out.println("Entrer un nombre de joueur pour l'équipe 2:");
        do {
            try {
                String numberTeam2String = sc.nextLine();
                numberTeam2 = Integer.parseInt(numberTeam2String);
                if (numberTeam2 > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un chiffre supérieur à 0");
            }
        } while (true);
        fillUpCharacters(team1, numberTeam1);
        fillUpCharacters(team2, numberTeam2);
        for(Character character : team1.getCharacters())
        {
            character.showData();
        }
        for(Character character : team2.getCharacters())
        {
            character.showData();
        }
    }

    private void fillUpCharacters(Team team, int number) {
        int classe = 1;
        System.out.println("Entrer le nom des " + number + " joueurs ainsi que leur classe pour l'équipe " + team.getName() + " :");
        for (int i = 0; i < number; i++) {
            System.out.println("--------------------");
            System.out.println("Choississez un nom pour le personnage n°" + (i + 1));
            String name = sc.nextLine();
            System.out.println("Choississez une classe pour " + name + " ?");
            System.out.println("1 -> Athlete");
            System.out.println("2 -> Guerrier");
            System.out.println("3 -> Magicien");
            do {
                try {
                    String classeString = sc.nextLine();
                    classe = Integer.parseInt(classeString);
                    if (classe > 0 && classe < 4) {
                        break;
                    }
                } catch (Exception e) {
                }
                System.out.println("Veuillez entrer un chiffre entre 1 et 3");
            } while (true);
            Character character = null;
            switch (classe) {
                case 1:
                    character = new Athlete(name);
                    break;
                case 2:
                    character = new Warrior(name);
                    break;
                case 3:
                    character = new Wizard(name);
                    break;
            }
            team.addCharacterTeam(character);
        }
        //Fin
        initEvents();

    }

    private void initEvents() {
        events.add(new Fight(team1, team2, 2));
    }
    
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

}
