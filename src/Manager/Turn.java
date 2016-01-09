/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Athlete;
import Entities.Character;
import Entities.Warrior;
import Entities.Thief;
import Items.Item;
import Items.UseableItem;
import Controller.Controller;
import java.util.ArrayList;
import java.util.List;
import me.grea.antoine.utils.Log;

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

    public void turnAttack(Character character) {
        Character opponent = null;
        String actionText = "Choississez un adversaire\n";
        int num = 0;
        for (Character op : opponentsTeam.getCharacters()) {
            actionText += Integer.toString(num) + " -> " + op.getName() + "\n";
            num++;
        }
        int opponentNumber = controller.askNumberBetween(actionText, 0, num);

        opponent = opponentsTeam.getCharacters().get(opponentNumber);
        if (character instanceof Warrior) {
            System.out.println(((Warrior) character).strikeABlow(opponent));
        } else if (character instanceof Athlete) {
            System.out.println(((Athlete) character).strikeABlow(opponent));
        } else if (character instanceof Thief) {
            System.out.println(((Thief) character).strikeABlow(opponent));
        }
    }

    public void turnDefense(Character character) {
        String text = "Quelle parade voulez-vous utiliser ? \n"
                + "1 -> Blocage\n"
                + "2 -> Esquive\n";
        int blockNumber = controller.askNumberBetween(text, 1, 2);
        if (character instanceof Warrior) {
            switch (blockNumber) {
                case 1:
                    ((Warrior) character).block();
                    break;
                case 2:
                    ((Warrior) character).dodge();
                    break;
            }
        } else if (character instanceof Athlete) {
            switch (blockNumber) {
                case 1:
                    ((Athlete) character).block();
                    break;
                case 2:
                    ((Athlete) character).dodge();
                    break;
            }
        } else if (character instanceof Thief) {
            switch (blockNumber) {
                case 1:
                    ((Thief) character).block();
                    break;
                case 2:
                    ((Thief) character).dodge();
                    break;
            }
        }
    }

    public void turnCare(Character character) {
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
            int useableNumber = controller.askNumberBetween(careText, 0, numCare);
            useableItem = (UseableItem) character.getInventory().get(useableNumber);
        }
        if (character instanceof Warrior) {
            System.out.println(((Warrior) character).heal(useableItem));
        } else if (character instanceof Athlete) {
            System.out.println(((Athlete) character).heal(useableItem));
        } else if (character instanceof Thief) {
            System.out.println(((Thief) character).heal(useableItem));
        }
    }

    public void turnOf(Character character) {
        character.restoreAttributes();
        int limitAction = 1;
        String text = "C'est votre tour: " + character.getName() + "\n"
                + "--------------------\n"
                + "Quel action voulez-vous réaliser ?\n";
        for (String capacity : character.getCapacities()) {
            if (capacity.equals("Attaquer")) {
                text += "1 -> Attaquer un personnage\n";
            } else if (capacity.equals("Bloquer")) {
                text += "2 -> Utiliser une parade\n";;
            } else if (capacity.equals("Soigner") && character.numberUseableItem() != 0) {
                text += "3 -> Utiliser un soin";
            }
            limitAction++;
        }

        int actionNumber = controller.askNumberBetween(text, 1, limitAction);
        switch (actionNumber) {
            case 1:
                turnAttack(character);
                break;
            case 2:
                turnDefense(character);
                break;
            case 3:
                turnCare(character);
                break;
        }
    }

    public boolean executeTurn() {
        for (Character character : team.getCharacters()) {
            if (character.isAlive()) {
                turnOf(character);
            } else {
                System.out.println("Le joueur " + character.getName() + " est mort !");
            }
        }
        return true;
    }

    public Team getTeamTurn() {
        return team;
    }

    public boolean executeTurnAuto() {
        List<String> attacks = new ArrayList<>();
        for (Character character : team.getCharacters()) {
            if (character.isAlive()) {
                character.restoreAttributes();
                Log.i("Le joueur " + character.getName() + " a joué.");
                int limitProbaAction = 90;
                if (character.numberUseableItem() != 0) {
                    limitProbaAction = 100;
                }
                int actionNumber;
                int probaAction = (int) (Math.random() * limitProbaAction);
                if (probaAction > 90 && probaAction <= 100) {
                    actionNumber = 3;
                } else if (probaAction > 75 && probaAction <= 90) {
                    actionNumber = 2;
                } else {
                    actionNumber = 1;
                }
                switch (actionNumber) {
                    case 1:
                        Character opponent = null;
                        int opponentNumber = 0;
                        int probaOpponent = (int) (Math.random() * (opponentsTeam.getCharacters().size() * 100));
                        opponentNumber = probaOpponent / 100;
                        opponent = opponentsTeam.getCharacters().get(opponentNumber);
                        if (character instanceof Warrior) {
                            attacks.add("-Une attaque a été effectuée contre " + opponent.getName() + " -> " + ((Warrior) character).strikeABlow(opponent));
                            Log.i(((Warrior) character).strikeABlow(opponent));
                        } else if (character instanceof Athlete) {
                            attacks.add("-Une attaque a été effectuée contre " + opponent.getName() + " -> " + ((Athlete) character).strikeABlow(opponent));
                            Log.i(((Athlete) character).strikeABlow(opponent));
                        } else if (character instanceof Thief) {
                            attacks.add("-Une attaque a été effectuée contre " + opponent.getName() + " -> " + ((Thief) character).strikeABlow(opponent));
                            Log.i(((Thief) character).strikeABlow(opponent));
                        }
                        break;
                    case 2:
                        int blockNumber = (int) (1 + Math.random());
                        switch (blockNumber) {
                            case 1:
                                Log.i("Utilistation bloque");
                                break;
                            case 2:
                                Log.i("Utilistation esquive");
                                break;
                        }
                        if (character instanceof Warrior) {
                            switch (blockNumber) {
                                case 1:
                                    ((Warrior) character).block();
                                    break;
                                case 2:
                                    ((Warrior) character).dodge();
                                    break;
                            }
                        } else if (character instanceof Athlete) {
                            switch (blockNumber) {
                                case 1:
                                    ((Athlete) character).block();
                                    break;
                                case 2:
                                    ((Athlete) character).dodge();
                                    break;
                            }
                        } else if (character instanceof Thief) {
                            switch (blockNumber) {
                                case 1:
                                    ((Thief) character).block();
                                    break;
                                case 2:
                                    ((Thief) character).dodge();
                                    break;
                            }
                        }
                        break;
                    case 3:
                        UseableItem useableItem = null;
                        if (character.numberUseableItem() > 0) {
                            Log.i("Utilistation soin");
                            if (character instanceof Warrior) {
                                System.out.println(((Warrior) character).heal(useableItem));
                            } else if (character instanceof Athlete) {
                                System.out.println(((Athlete) character).heal(useableItem));
                            } else if (character instanceof Thief) {
                                System.out.println(((Thief) character).heal(useableItem));
                            }
                        } else {

                        }
                        break;
                }
            } else {
                System.out.println("Le joueur " + character.getName() + " est mort !");
            }
        }
        System.out.println("Liste des attaques réalisées par l'équipe " + team.getName());
        for (String text : attacks) {
            System.out.println(text);
        }
        return true;
    }

}
