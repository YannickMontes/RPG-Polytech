/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Entities.Attribute;
import Manager.Turn;
import Entities.Character;
import Manager.Team;

/**
 *
 * @author yannick
 */
public class Fight extends Event {

    Team team1;
    Team team2;
    Turn actualTurn;

    public Fight(Team team1, Team team2) {
        this.name = "Combat";
        this.team1 = team1;
        this.team2 = team2;
        for (Character character1 : team1.getCharacters()) {
            character1.restoreLife();
        }
        for (Character character2 : team2.getCharacters()) {
            character2.restoreLife();
        }
        executeFight();
    }

    private void executeFight() {
        System.out.println("*** Début d'un combat ***");
        boolean equipe = false;
        if (team1.getTeamSpeed() > team2.getTeamSpeed()) {
            equipe = true;
        }

        do {
            executeTurn(equipe);
            equipe = !equipe;
        } while (team1.isTeamAlive() && team2.isTeamAlive());
    }

    private void executeTurn(boolean equipe) {
        if (equipe == true) {
            actualTurn = new Turn(team1, team2);
            System.out.println("");
            System.out.println("--------------------");
            System.out.println("C'est au tour de l'équipe " + actualTurn.getTeamTurn().getName());
            actualTurn.executeTurn();
        } else {
            actualTurn = new Turn(team2, team1);
            System.out.println("");
            System.out.println("--------------------");
            System.out.println("C'est au tour de l'équipe " + actualTurn.getTeamTurn().getName());
            actualTurn.executeTurnAuto();
        }

    }

}
