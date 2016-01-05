/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Athlete;
import Entities.Character;
import Entities.Warrior;
import Entities.Wizard;
import java.util.Scanner;

/**
 *
 * @author yannick
 */
public class Turn {

    private Team team;
    private Team opponentsTeam;

    public Turn(Team team, Team opponentsTeam) {
        this.team = team;
        this.opponentsTeam = opponentsTeam;
    }

    public boolean executeTurn() {
        for (Character character : team.getCharacters()) {
            character.restoreAttributes();
            int actionNumber = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("C'est votre tour: " + character.getName());
            System.out.println("--------------------");
            System.out.println("Quel action voulez-vous réaliser ?");
            System.out.println("1 -> Attaquer un personnage");
            System.out.println("2 -> Utiliser une parade");
            System.out.println("3 -> Utiliser un soin");
            do {
                try {
                    String textIn = sc.nextLine();
                    actionNumber = Integer.parseInt(textIn);
                    if (actionNumber > 0 && actionNumber < 4) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Veuillez entrer un chiffre entre 1 et 3");
                }
            } while (true);

            switch (actionNumber) {
                case 1:
                    Character opponent = null;
                    System.out.println("Choississez un adversaire");
                    int num = 0;
                    int opponentNumber = 0;
                    for (Character op : opponentsTeam.getCharacters()) {
                        System.out.println(Integer.toString(num) + " " + op.getName());
                        num++;
                    }
                    do {
                        try {
                            String textIn = sc.nextLine();
                            opponentNumber = Integer.parseInt(textIn);
                            if (opponentNumber >= 0 && opponentNumber < num) {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Veuillez entrer un chiffre entre 0 et " + Integer.toString(num));
                        }
                    } while (true);
                    opponent = opponentsTeam.getCharacters().get(opponentNumber);
                    if (character instanceof Warrior) {
                        ((Warrior) character).strikeABlow(opponent);
                    } else if (character instanceof Athlete) {
                        ((Athlete) character).strikeABlow(opponent);
                    } else if (character instanceof Wizard) {
                        ((Wizard) character).strikeABlow(opponent);
                    }
                    break;
                case 2:
                    if (character instanceof Warrior) {
                        ((Warrior) character).block();
                        ((Warrior) character).dodge();
                    } else if (character instanceof Athlete) {
                        ((Athlete) character).block();
                        ((Athlete) character).dodge();
                    } else if (character instanceof Wizard) {
                        ((Wizard) character).block();
                        ((Wizard) character).dodge();
                    }
                    break;
                case 3:
                    if (character instanceof Warrior) {
                        ((Warrior) character).heal();
                    } else if (character instanceof Athlete) {
                        ((Athlete) character).heal();
                    } else if (character instanceof Wizard) {
                        ((Wizard) character).heal();
                    }
                    break;
            }
        }
        return true;
    }

    public Team getTeamTurn() {
        return team;
    }

}
