/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Controller.ConsoleDesign;
import Manager.Turn;
import Entities.Character;
import Entities.Level;
import Manager.Team;

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
        System.out.println(ConsoleDesign.textBox("Début d'un combat", ConsoleDesign.whiteText, ConsoleDesign.redBack));
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
        if (equipe == true) {
            actualTurn = new Turn(playerTeam, ennemyTeam);
            System.out.println("");
            System.out.println("--------------------");
            System.out.println("C'est au tour de l'équipe " + actualTurn.getTeamTurn().getName());
            actualTurn.executeTurn();
        } else {
            actualTurn = new Turn(ennemyTeam, playerTeam);
            System.out.println("");
            System.out.println("--------------------");
            System.out.println("C'est au tour de l'équipe " + actualTurn.getTeamTurn().getName());
            actualTurn.executeTurnAuto();
        }

    }

    private void endFight(Team winner) {
        System.out.println("--------------------");
        System.out.println("Combat terminé. \nVictoire de l'équipe " + winner.getName() + "!");
        
        if(winner==playerTeam)
        {
            System.out.println("Votre équipe a gagné! Chaque membre de l'équipe va gagner un certain nombre de points d'expérience.");
            int experience = this.ennemyTeam.getAverageLevel() * 1500 * this.ennemyTeam.getNbCharacters();
            for(Character c : playerTeam.getCharacters())
            {
                c.increaseExperience(experience/((Level.MAX_LEVEL+1)-c.getLevel()));
                System.out.println("Le personnage " + c.getName() + " a gagné " + experience + " points d'expérience.");
                c.printActualLevelState();
            }
        }
        else
        {
            System.out.println("Votre équipe à perdu. Vous ne gagnez pas de points d'expérience.");
        }
    }
}
