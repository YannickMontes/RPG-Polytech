/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Events;

import com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign;
import com.corentin_yannick.RPG_Polytech.Controllers.Controller;
import com.corentin_yannick.RPG_Polytech.Manager.Turn;
import com.corentin_yannick.RPG_Polytech.Entities.Character;
import com.corentin_yannick.RPG_Polytech.Entities.Level;
import com.corentin_yannick.RPG_Polytech.Manager.Team;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.RED;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.WHITE;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.REDBACK;
import static com.corentin_yannick.RPG_Polytech.Controllers.ConsoleDesign.CYANBACK;
import com.corentin_yannick.RPG_Polytech.Manager.Game;

/**
 *
 * @author yannick
 */
public class Fight extends Event {

    Team playerTeam;
    Team ennemyTeam;
    Turn actualTurn;

    public Fight(Team team1, Team team2) {
        this.name = "Combat";
        this.playerTeam = team1;
        this.ennemyTeam = team2;
        for (Character character1 : team1.getCharacters()) {
            character1.restoreLife();
        }
        for (Character character2 : team2.getCharacters()) {
            character2.restoreLife();
        }
        executeFight();
    }

    private void executeFight() {
        System.out.println(ConsoleDesign.textBox("Début d'un combat", WHITE, REDBACK));
        boolean equipe = false;
        if (playerTeam.getTeamSpeed() > ennemyTeam.getTeamSpeed()) {
            equipe = true;
        }

        do {
            executeTurn(equipe);
            equipe = !equipe;
        } while (playerTeam.isTeamAlive() && ennemyTeam.isTeamAlive());
        if (playerTeam.isTeamAlive()) {
            endFight(playerTeam);
        } else {
            endFight(ennemyTeam);
        }
    }

    private void executeTurn(boolean equipe) {
        System.out.println("");
        if (equipe == true) {
            String text = ConsoleDesign.textDashArrow("Souhaitez-vous en savoir plus sur l'état de vos troupes et de l'adversaire ? [O/N]", RED);
            if (Controller.askYesNo(text) == true) {
                System.out.println(ConsoleDesign.textBox("Etats des troupes", WHITE, CYANBACK));
                System.out.println(ConsoleDesign.textDash("Votre équipe", RED));
                for (Character c : playerTeam.getCharacters()) {
                    System.out.println(c.toString());
                    System.out.println("");
                }
                System.out.println(ConsoleDesign.textDash("Votre adversaire", RED));
                for (Character c : ennemyTeam.getCharacters()) {
                    System.out.println(c.toString());
                    System.out.println("");
                }
            } else {
                System.out.println("");
            }
            actualTurn = new Turn(playerTeam, ennemyTeam);
            System.out.println(ConsoleDesign.textBox("C'est au tour de l'équipe " + actualTurn.getTeamTurn().getName(), WHITE, CYANBACK));
            actualTurn.executeTurn();
        } else {
            actualTurn = new Turn(ennemyTeam, playerTeam);
            System.out.println(ConsoleDesign.textBox("C'est au tour de l'équipe " + actualTurn.getTeamTurn().getName(), WHITE, CYANBACK));
            actualTurn.executeTurnAuto();
        }
    }

    private void endFight(Team winner) {
        System.out.println(ConsoleDesign.textBox("Victoire de l'équipe " + winner.getName() + "!", WHITE, CYANBACK));

        if (winner == playerTeam) {
            System.out.println(ConsoleDesign.textDashArrow("Votre équipe a gagné! Chaque membre de l'équipe va gagner un certain nombre de points d'expérience.", RED));
            for (Character c : playerTeam.getCharacters()) {
                int experience = this.ennemyTeam.getAverageLevel() * 4000 * this.ennemyTeam.getNbCharacters();
                experience = (experience / ((Level.MAX_LEVEL + 1) - c.getLevel()));
                c.increaseExperience(experience);
                System.out.println(ConsoleDesign.text("Le personnage " + c.getName() + " a gagné " + experience + " points d'expérience.", RED));
                System.out.println(c.getActuelLevelState());
            }
            System.out.println("");
        } else {
            System.out.println(ConsoleDesign.text("Votre équipe à perdu. Vous ne gagnez pas de points d'expérience.", RED));
            System.out.println("");
            Game.game = false;          
        }
    }
}
