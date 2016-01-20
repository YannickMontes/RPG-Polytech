/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Manager;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import com.corentin_yannick.RPG_Polytech.Entities.Athlete;
import com.corentin_yannick.RPG_Polytech.Entities.Character;
import com.corentin_yannick.RPG_Polytech.Entities.Warrior;
import com.corentin_yannick.RPG_Polytech.Entities.Thief;
import com.corentin_yannick.RPG_Polytech.Items.Item;
import com.corentin_yannick.RPG_Polytech.Items.UseableItem;
import com.corentin_yannick.RPG_Polytech.Controllers.Controller;
import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
import java.util.ArrayList;
import java.util.List;

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

    public void turnAttack(Character character) {
        Character opponent = null;
        String actionText = ConsoleDesign.textDashArrow("Choississez un adversaire", ConsoleDesign.redText) + "\n";
        int num = 0;
        for (Character op : opponentsTeam.getCharacters()) {
            if (op.isAlive()) {
                actionText += ConsoleDesign.text(Integer.toString(num) + " -> " + op.getName(), ConsoleDesign.redText) + "\n";
            }
            num++;
        }

        int opponentNumber;
        do {
            opponentNumber = Controller.askNumberBetween(actionText, 0, num - 1);
        } while (!opponentsTeam.getCharacters().get(opponentNumber).isAlive());

        opponent = opponentsTeam.getCharacters().get(opponentNumber);
        if (character instanceof Warrior) {
            System.out.println(ConsoleDesign.text(((Warrior) character).strikeABlow(opponent), ConsoleDesign.redText));
        } else if (character instanceof Athlete) {
            System.out.println(ConsoleDesign.text(((Athlete) character).strikeABlow(opponent), ConsoleDesign.redText));
        } else if (character instanceof Thief) {
            System.out.println(ConsoleDesign.text(((Thief) character).strikeABlow(opponent), ConsoleDesign.redText));
        }
    }

    public void turnDefense(Character character) {
        String text = ConsoleDesign.textDashArrow("Quelle parade voulez-vous utiliser ?", ConsoleDesign.redText) + " \n";
        text += ConsoleDesign.text("1 -> Blocage", ConsoleDesign.redText) + "\n";
        text += ConsoleDesign.text("2 -> Esquive", ConsoleDesign.redText) + "\n";
        int blockNumber = Controller.askNumberBetween(text, 1, 2);
        if (character instanceof Warrior) {
            switch (blockNumber) {
                case 1:
                    System.out.println(ConsoleDesign.text(((Warrior) character).block(), ConsoleDesign.redText));
                    break;
                case 2:
                    System.out.println(ConsoleDesign.text(((Warrior) character).dodge(), ConsoleDesign.redText));
                    break;
            }
        } else if (character instanceof Athlete) {
            switch (blockNumber) {
                case 1:
                    System.out.println(ConsoleDesign.text(((Athlete) character).block(), ConsoleDesign.redText));
                    break;
                case 2:
                    System.out.println(ConsoleDesign.text(((Athlete) character).dodge(), ConsoleDesign.redText));
                    break;
            }
        } else if (character instanceof Thief) {
            switch (blockNumber) {
                case 1:
                    System.out.println(ConsoleDesign.text(((Thief) character).block(), ConsoleDesign.redText));
                    break;
                case 2:
                    System.out.println(ConsoleDesign.text(((Thief) character).dodge(), ConsoleDesign.redText));
                    break;
            }
        }
    }
    
    public void turnObject(Character character) {
        UseableItem useableItem = null;
        if (character.numberUseableItem() > 0) {
            String careText = ConsoleDesign.textDashArrow("Quel item voulez-vous utiliser ?", ConsoleDesign.redText);
            int numObject = -1;
            for (UseableItem item : character.getUseableItems()) {
                numObject++;
                System.out.println(ConsoleDesign.text("("+numObject+") "+item.toString(), ConsoleDesign.greenText));
            }
            int useableNumber = Controller.askNumberBetween(careText, 0, numObject);
            useableItem = character.getUseableItems().get(useableNumber);
        }
        if (character instanceof Warrior) {
            System.out.println(ConsoleDesign.text(((Warrior) character).useItem(useableItem), ConsoleDesign.redText));
        } else if (character instanceof Athlete) {
            System.out.println(ConsoleDesign.text(((Athlete) character).useItem(useableItem), ConsoleDesign.redText));
        } else if (character instanceof Thief) {
            System.out.println(ConsoleDesign.text(((Thief) character).useItem(useableItem), ConsoleDesign.redText));
        }
        turnOf(character,true);
    }

    public void turnOf(Character character, boolean usedObject) {
        if(!usedObject)
        {
            character.reinitStats();   
        }
        int limitAction = 0;
        String text = ConsoleDesign.textDashArrow("Le personnage " + character.getName() + " (" + character.getClass().getSimpleName() + " - Niveau: " + character.getLevel() + " - Vie: " + character.getAttributes().get(Attribute.HEALTH) + ")" + " doit joué", ConsoleDesign.cyanText);
        text += "\n \n" + ConsoleDesign.textDashArrow("Quelle action voulez-vous réaliser pour " + character.getName() + " ?", ConsoleDesign.redText);
        for (String capacity : character.getCapacities()) {
            if(!this.opponentsTeam.isTeamAlive())
            {
                return;
            }
            if (capacity.equals("Attaquer")) {
                text += "\n" + ConsoleDesign.text("1 -> Attaquer un personnage", ConsoleDesign.redText);
                limitAction++;
            } else if (capacity.equals("Bloquer")) {
                text += "\n" + ConsoleDesign.text("2 -> Utiliser une parade", ConsoleDesign.redText);
                limitAction++;
            } else if (capacity.equals("Utiliser un item") && !usedObject) {
                text += "\n" + ConsoleDesign.text("3 -> Utiliser un objet", ConsoleDesign.redText);
                limitAction++;
            }
        }

        int actionNumber = Controller.askNumberBetween(text, 1, limitAction);
        switch (actionNumber) {
            case 1:
                turnAttack(character);
                break;
            case 2:
                turnDefense(character);
                break;
            case 3:
                turnObject(character);
                break;
        }
        System.out.println("");
    }

    public boolean executeTurn() {
        for (Character character : team.getCharacters()) {
            if (character.isAlive()) {
                turnOf(character, false);
            } else {
                System.out.println();
                System.out.println(ConsoleDesign.textDash("Le joueur " + character.getName() + " est mort et ne peut plus jouer pour ce combat !", ConsoleDesign.redText));
            }
        }
        return true;
    }

    public Team getTeamTurn() {
        return team;
    }

    public boolean executeTurnAuto() {
        List<String> attacks = new ArrayList<>();
        List<String> blocks = new ArrayList<>();
        List<String> cares = new ArrayList<>();

        for (Character character : team.getCharacters()) {
            if (character.isAlive()) {
                character.reinitStats();
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
                        String resultAttack = "";
                        if (character instanceof Warrior) {
                            resultAttack = ((Warrior) character).strikeABlow(opponent);
                            attacks.add(ConsoleDesign.text(resultAttack, ConsoleDesign.redText));
                        } else if (character instanceof Athlete) {
                            resultAttack = ((Athlete) character).strikeABlow(opponent);
                            attacks.add(ConsoleDesign.text(resultAttack, ConsoleDesign.redText));
                        } else if (character instanceof Thief) {
                            resultAttack = ((Thief) character).strikeABlow(opponent);
                            attacks.add(ConsoleDesign.text(resultAttack, ConsoleDesign.redText));
                        }
                        break;
                    case 2:
                        int blockNumber = (int) (1 + Math.random());
                        String resultBlock = "";
                        if (character instanceof Warrior) {
                            switch (blockNumber) {
                                case 1:
                                    resultBlock = ((Warrior) character).block();
                                    blocks.add(ConsoleDesign.text(resultBlock, ConsoleDesign.redText));
                                    break;
                                case 2:
                                    resultBlock = ((Warrior) character).dodge();
                                    blocks.add(ConsoleDesign.text(resultBlock, ConsoleDesign.redText));
                                    break;
                            }
                        } else if (character instanceof Athlete) {
                            switch (blockNumber) {
                                case 1:
                                    resultBlock = ((Athlete) character).block();
                                    blocks.add(ConsoleDesign.text(resultBlock, ConsoleDesign.redText));
                                    break;
                                case 2:
                                    resultBlock = ((Athlete) character).dodge();
                                    blocks.add(ConsoleDesign.text(resultBlock, ConsoleDesign.redText));
                                    break;
                            }
                        } else if (character instanceof Thief) {
                            switch (blockNumber) {
                                case 1:
                                    resultBlock = ((Thief) character).block();
                                    blocks.add(ConsoleDesign.text(resultBlock, ConsoleDesign.redText));
                                    break;
                                case 2:
                                    resultBlock = ((Thief) character).dodge();
                                    blocks.add(ConsoleDesign.text(resultBlock, ConsoleDesign.redText));
                                    break;
                            }
                        }
                        break;
                    case 3:
                        String resultCare = "";
                        UseableItem useableItem = null;
                        if (character.numberUseableItem() > 0) {
                            if (character instanceof Warrior) {
                                resultCare = ((Warrior) character).useItem(useableItem);
                            } else if (character instanceof Athlete) {
                                resultCare = ((Athlete) character).useItem(useableItem);
                            } else if (character instanceof Thief) {
                                resultCare = ((Thief) character).useItem(useableItem);
                            }
                            cares.add(ConsoleDesign.text(resultCare, ConsoleDesign.redText));
                        }
                        break;
                }
            } else {
                System.out.println(ConsoleDesign.text("Le joueur " + character.getName() + " est mort !", ConsoleDesign.redText));
            }
        }
        if (attacks.size() > 0) {
            System.out.println(ConsoleDesign.textDash("Liste des attaques réalisées par l'équipe " + team.getName(), ConsoleDesign.redText));
        }
        for (String text : attacks) {
            System.out.println(text);
        }
        if (blocks.size() > 0) {
            System.out.println(ConsoleDesign.textDash("Liste des blocks réalisées par l'équipe " + team.getName(), ConsoleDesign.redText));
        }
        for (String text : blocks) {
            System.out.println(text);
        }
        if (cares.size() > 0) {
            System.out.println(ConsoleDesign.textDash("Liste des soins utilisées par l'équipe " + team.getName(), ConsoleDesign.redText));
        }
        for (String text : cares) {
            System.out.println(text);
        }
        return true;
    }

}
