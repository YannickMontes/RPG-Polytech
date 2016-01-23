/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Manager;

import com.corentin_yannick.RPG_Polytech.Actions.Action;
import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import com.corentin_yannick.RPG_Polytech.Entities.Character;
import com.corentin_yannick.RPG_Polytech.Controllers.Controller;
import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
import java.util.ArrayList;
import java.util.List;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.RED;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.CYAN;

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
            if (character.isAlive()) {
                turnOf(character, false);
            } else {
                System.out.println();
                System.out.println(ConsoleDesign.textDash("Le joueur " + character.getName() + " est mort et ne peut plus jouer pour ce combat !", RED));
            }
        }
        return true;
    }

    public void turnOf(Character character, boolean usedObject) {
        if (!usedObject) {
            character.reinitStats();
        }
        int limitAction = 0;
        String text = ConsoleDesign.textDashArrow("Le personnage " + character.getName() + " (" + character.getClass().getSimpleName() + " - Niveau: " + character.getLevel() + " - Vie: " + character.getAttributeValue(Attribute.HEALTH) + ")" + " doit joué", CYAN);
        text += "\n \n" + ConsoleDesign.textDashArrow("Quelle action voulez-vous réaliser pour " + character.getName() + " ?", RED);
        for (String capacity : character.getCapacities()) {
            if (!this.opponentsTeam.isTeamAlive()) {
                return;
            }
            if (capacity.equals("Attaquer")) {
                text += "\n" + ConsoleDesign.text("1 -> Attaquer un personnage", RED);
                limitAction++;
            } else if (capacity.equals("Bloquer")) {
                text += "\n" + ConsoleDesign.text("2 -> Utiliser une parade", RED);
                limitAction++;
            } else if (capacity.equals("Utiliser un item") && !usedObject) {
                text += "\n" + ConsoleDesign.text("3 -> Utiliser un objet", RED);
                limitAction++;
            }
        }

        int actionNumber = Controller.askNumberBetween(text, 1, limitAction);
        switch (actionNumber) {
            case 1:
                if (new Action(character, opponentsTeam, this).makeAttack() == false) {
                    turnOf(character, false);
                }
                break;
            case 2:
                if (new Action(character, opponentsTeam, this).makeDefense() == false) {
                    turnOf(character, false);
                }
                break;
            case 3:
                if (new Action(character, opponentsTeam, this).useObject() == false) {
                    turnOf(character, false);
                }
                break;
        }
        System.out.println("");
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
                if (character.getNumberUseableItem()!= 0) {
                    limitProbaAction = 100;
                }
                int actionNumber;
                int probaAction = (int) (Math.random() * limitProbaAction);
                /*if (probaAction > 90 && probaAction <= 100) {
                    actionNumber = 3;
                } else */if (probaAction > 75 && probaAction <= 100) {
                    actionNumber = 2;
                } else {
                    actionNumber = 1;
                }
                switch (actionNumber) {
                    case 1:
                        String resultAttack = new Action(character, opponentsTeam, this).makeAutoAttack();
                        attacks.add(ConsoleDesign.text(resultAttack, RED));
                        /* if (character instanceof Warrior) {
                            resultAttack = ((Warrior) character).strikeABlow(opponent);
                            attacks.add(ConsoleDesign.text(resultAttack, RED));
                        } else if (character instanceof Athlete) {
                            resultAttack = ((Athlete) character).strikeABlow(opponent);
                            attacks.add(ConsoleDesign.text(resultAttack, RED));
                        } else if (character instanceof Thief) {
                            resultAttack = ((Thief) character).strikeABlow(opponent);
                            attacks.add(ConsoleDesign.text(resultAttack, RED));
                        }*/
                        break;
                    case 2:
                        //int blockNumber = (int) (1 + Math.random());
//                        String resultBlock = "";
                        String resultBlock = new Action(character, opponentsTeam, this).makeAutoDefense();
                        blocks.add(ConsoleDesign.text(resultBlock, RED));
                        /*if (character instanceof Warrior) {
                            switch (blockNumber) {
                                case 1:
                                    resultBlock = ((Warrior) character).block();
                                    blocks.add(ConsoleDesign.text(resultBlock, RED));
                                    break;
                                case 2:
                                    resultBlock = ((Warrior) character).dodge();
                                    blocks.add(ConsoleDesign.text(resultBlock, RED));
                                    break;
                            }
                        } else if (character instanceof Athlete) {
                            switch (blockNumber) {
                                case 1:
                                    resultBlock = ((Athlete) character).block();
                                    blocks.add(ConsoleDesign.text(resultBlock, RED));
                                    break;
                                case 2:
                                    resultBlock = ((Athlete) character).dodge();
                                    blocks.add(ConsoleDesign.text(resultBlock, RED));
                                    break;
                            }
                        } else if (character instanceof Thief) {
                            switch (blockNumber) {
                                case 1:
                                    resultBlock = ((Thief) character).block();
                                    blocks.add(ConsoleDesign.text(resultBlock, RED));
                                    break;
                                case 2:
                                    resultBlock = ((Thief) character).dodge();
                                    blocks.add(ConsoleDesign.text(resultBlock, RED));
                                    break;
                            }
                        }*/
                        break;
                    //case 3:
                        /*String resultCare = "";
                        UseableItem useableItem = null;
                        if (character.numberUseableItem() > 0) {
                            if (character instanceof Warrior) {
                                resultCare = ((Warrior) character).useItem(useableItem);
                            } else if (character instanceof Athlete) {
                                resultCare = ((Athlete) character).useItem(useableItem);
                            } else if (character instanceof Thief) {
                                resultCare = ((Thief) character).useItem(useableItem);
                            }
                            cares.add(ConsoleDesign.text(resultCare, RED));
                        }
                        break;*/
                }
            } else {
                System.out.println(ConsoleDesign.text("Le joueur " + character.getName() + " est mort !", RED));
            }
        }
        if (attacks.size() > 0) {
            System.out.println(ConsoleDesign.textDash("Liste des attaques réalisées par l'équipe " + team.getName(), RED));
        }
        for (String text : attacks) {
            System.out.println(text);
        }
        if (blocks.size() > 0) {
            System.out.println(ConsoleDesign.textDash("Liste des blocks réalisés par l'équipe " + team.getName(), RED));
        }
        for (String text : blocks) {
            System.out.println(text);
        }
       /* if (cares.size() > 0) {
            System.out.println(ConsoleDesign.textDash("Liste des objets utilisés par l'équipe " + team.getName(), RED));
        }
        for (String text : cares) {
            System.out.println(text);
        }*/
        return true;
    }

}
