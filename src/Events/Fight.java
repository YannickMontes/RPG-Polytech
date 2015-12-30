/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Manager.Turn;
import java.util.ArrayList;
import java.util.List;
import Entities.Character;
import Manager.Team;

/**
 *
 * @author yannick
 */
public class Fight extends Event {

    Team team1;
    Team team2;
    List<Turn> turns;
    int numberOfTurn;

    public Fight(Team team1, Team team2, int numberOfTurn) {
        this.team1 = team1;
        this.team2 = team2;
        turns = new ArrayList<>();
        this.numberOfTurn = numberOfTurn;
        initTurn();
    }

    public boolean executeFight() {
        System.out.println("*** Début d'un combat ***");
        System.out.println("*** Nombre de tours: " + numberOfTurn + "***");
        for (Turn turn : turns) {
            System.out.println("--------------------");
            System.out.println("C'est au tour de l'équipe " + turn.getTeam());

            turn.executeTurn();
        }
        return true;
    }

    private void initTurn() {
        int rand = (int) (Math.random() * 2);
        for (int i = 0; i < numberOfTurn; i++) {
            if (i % 2 == rand) {
                turns.add(new Turn(team1, team2));
                turns.add(new Turn(team2, team1));
            } else {
                turns.add(new Turn(team2, team1));
                turns.add(new Turn(team1, team2));
            }
        }
    }

}
