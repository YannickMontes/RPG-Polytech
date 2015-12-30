/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Events.Event;
import Events.Fight;
import Entities.Character;
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

    public Game() {
        events = new ArrayList<>();
        initEvents();
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer un nom pour l'équipe 1:");
        String nameTeam1 = sc.nextLine();
        team1 = new Team(nameTeam1);
        System.out.println("Entrer un nom pour l'équipe 2:");
        String nameTeam2 = sc.nextLine();
        team2 = new Team(nameTeam2);

        System.out.println("Entrer un nombre de joueur pour l'équipe 1:");
        do {
            try {
                String numberTeam1 = sc.nextLine();
                int actionNumber = Integer.parseInt(numberTeam1);
                if (actionNumber > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un chiffre supérieur à 0");
            }
        } while (true);
        
        System.out.println("Entrer un nombre de joueur pour l'équipe 2:");
        do {
            try {
                String numberTeam2 = sc.nextLine();
                int actionNumber = Integer.parseInt(numberTeam2);
                if (actionNumber > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Veuillez entrer un chiffre supérieur à 0");
            }
        } while (true);
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
