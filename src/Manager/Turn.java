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
import Items.Item;
import Items.UseableItem;
import java.util.Scanner;
import Controller.Controller;

/**
 *
 * @author yannick
 */
public class Turn {

    private Team team;
    private Team opponentsTeam;
    private Controller controller;

    public Turn(Team team, Team opponentsTeam) {
        this.team = team;
        this.opponentsTeam = opponentsTeam;
        this.controller = new Controller();
    }

    public boolean executeTurn() {
        for (Character character : team.getCharacters()) {
            character.restoreAttributes();
            String text = "C'est votre tour: " + character.getName() + "\n"
            + "--------------------\n"
            + "Quel action voulez-vous réaliser ?\n"
            + "1 -> Attaquer un personnage\n"
            + "2 -> Utiliser une parade\n"
            + "3 -> Utiliser un soin";
            int actionNumber = controller.askNumberBetween(text, 1, 3);
            switch (actionNumber) {
                case 1:
                    Character opponent = null;
                    String actionText = "Choississez un adversaire\n";
                    int num = 0;
                    for (Character op : opponentsTeam.getCharacters()) {
                        actionText += Integer.toString(num) + " -> " + op.getName() + "\n";
                        num++;
                    }
                    int opponentNumber = controller.askNumberBetween(actionText,0, num);

                    opponent = opponentsTeam.getCharacters().get(opponentNumber);
                    if (character instanceof Warrior) {
                        System.out.println(((Warrior) character).strikeABlow(opponent));
                    } else if (character instanceof Athlete) {
                        System.out.println(((Athlete) character).strikeABlow(opponent));
                    } else if (character instanceof Wizard) {
                        System.out.println(((Wizard) character).strikeABlow(opponent));
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
                    UseableItem useableItem = null;
                    if (character.numberUseableItem() > 0) {
                        String careText = "Choississez un soin";
                        int numCare = 0;
                        for (Item item : character.getInventory()) {
                            if (item instanceof UseableItem) {
                                System.out.println(Integer.toString(numCare) + " -> " + item.getName() + " de bonus " + ((UseableItem) item).getBonusValue());
                            }
                            numCare++;
                        }
                        int useableNumber = controller.askNumberBetween(careText,0, numCare);
                        useableItem = (UseableItem) character.getInventory().get(useableNumber);
                    }
                    if (character instanceof Warrior) {
                        System.out.println(((Warrior) character).heal(useableItem));
                    } else if (character instanceof Athlete) {
                        System.out.println(((Athlete) character).heal(useableItem));
                    } else if (character instanceof Wizard) {
                        System.out.println(((Wizard) character).heal(useableItem));
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
