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
    private Controller controller;

    public Game() {
        events = new ArrayList<>();
        controller = new Controller();
    }

    public void startGame() {
        team1 = new Team(controller.askText("Entrer un nom pour l'équipe 1:"));
        team2 = new Team(controller.askText("Entrer un nom pour l'équipe 2:"));

        int numberTeam1 = controller.askNumberBetween("Entrer un nombre de joueur pour l'équipe 1:", 1, 5);
        int numberTeam2 = controller.askNumberBetween("Entrer un nombre de joueur pour l'équipe 2:", 1, 5);

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
        
           //Temporaire
        initEvents();
    }

    private void fillUpCharacters(Team team, int number) {
        System.out.println("Entrer le nom des " + number + " joueurs ainsi que leur classe pour l'équipe " + team.getName() + " :");
        for (int i = 0; i < number; i++) {
            System.out.println("--------------------");
            String name = controller.askText("Choississez un nom pour le personnage n°" + (i + 1));
            String textClass = "Choississez une classe pour " + name + " ?\n"
            + "1 -> Athlete\n"
            + "2 -> Guerrier\n"
            + "3 -> Magicien";
            int classe = controller.askNumberBetween(textClass, 1, 3);
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
    }

    private void initEvents() {
        events.add(new Fight(team1, team2));
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
