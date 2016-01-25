/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corentin_yannick.RPG_Polytech.Manager;

import com.corentin_yannick.RPG_Polytech.Entities.Athlete;
import com.corentin_yannick.RPG_Polytech.Entities.Attribute;
import java.util.ArrayList;
import java.util.List;
import com.corentin_yannick.RPG_Polytech.Entities.Character;
import com.corentin_yannick.RPG_Polytech.Entities.Thief;
import com.corentin_yannick.RPG_Polytech.Entities.Warrior;

/**
 *
 * @author Corentin
 */
public class Team {

    public static final int NBMAXCHARACTERS = 5;
    private List<Character> characters;
    private String name;

    public Team(Team playerTeam) {
        this.name = Story.getRandomTeamName();
        characters = new ArrayList<>();
        this.generateRandomTeam(playerTeam);
    }

    public Team(String name) {
        this.name = name;
        characters = new ArrayList<>();
        if(this.name.equals("Boss"))
        {
            Team t = new Team("Boss");
            Warrior w = new Warrior("Chef", 10);
            Warrior w2 = new Warrior("Sous-fifre",4);
            Athlete w3 = new Athlete("Colosse",5);
            Thief w4 = new Thief("Pillard",5);
            t.addCharacterTeam(w);
            t.addCharacterTeam(w2);
            t.addCharacterTeam(w3);
            t.addCharacterTeam(w4);
       }
    }

    public void addCharacterTeam(Character character) {
        characters.add(character);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamSpeed() {
        int speedTot = 0;
        for (Character ch : characters) {
            speedTot += ch.getAttributeValue(Attribute.SPEED);
        }
        return speedTot;
    }

    public boolean isTeamAlive() {
        int i = 0;
        for (Character ch : characters) {
            if (!ch.isAlive()) {
                i++;
            }
        }
        if (i == this.getNbCharacters()) {
            return false;
        }
        return true;
    }

    public int getTeamLevel() {
        int lvlTot = 0;
        for (Character ch : characters) {
            lvlTot += ch.getLevel();
        }
        return lvlTot;
    }

    public int getLevelMaxInTeam() {
        int lvlMax = Integer.MIN_VALUE;
        for (Character ch : characters) {
            if (ch.getLevel() > lvlMax) {
                lvlMax = ch.getLevel();
            }
        }
        return lvlMax;
    }

    public int getNbCharacters() {
        return this.characters.size();
    }

    public int getAverageLevel() {
        int sum = 0;
        for (Character ch : characters) {
            sum += ch.getLevel();
        }
        return sum / characters.size();
    }

    @Override
    public String toString() {
        String text = "Equipe " + this.name + ":\n"
                + "Nombre de personnages: " + this.getNbCharacters() + "\n\n";

        for (Character c : this.characters) {
            text+="\n";
            text += c.toString();
        }
        text+="\n";
        return text;
    }

    /**
     * Fonction permettant de générer aléatoirement une équipe, en fonction de
     * l'équipe du joueur. Le niveau total de l'équipe généré sera le niveau
     * total de l'équipe du joueur +/- 1. Chaque personnage aura un stuff généré
     * également aléatoirement.
     *
     * @param playerTeam Equipe du joueur.
     */
    public void generateRandomTeam(Team playerTeam) {
        //On commence par regarder combien de personnages l'équipe adverse va contenir.
        //On génère + ou - 1 le même nombre de personnages que l'équipe du joueur.
        int deltaNbPlayer = (int) (Math.random() * 3) - 1;

        if (playerTeam.getNbCharacters() + deltaNbPlayer < 1 || playerTeam.getLevelMaxInTeam()==1) {
            deltaNbPlayer = 0;
        }
        
        //On boucle pour créer le nombre de joueurs aléatoire défini. 
        for (int i = 0; i < playerTeam.getNbCharacters() + deltaNbPlayer; i++) {
            Character newChar = null;

            String name = Story.getRandomPlayerName();

            //Random maitrisé pour le niveau du personnage.
            int level = 0;
            switch (deltaNbPlayer) {
                case 0://Si il y a le même nombre de personnages, alors on prends le niveau moyen + ou - 1.
                    level = (int) (Math.random() * 3) - 1 + playerTeam.getAverageLevel();
                    break;
                case -1://Si il y a un personnage de moins, alors on met leur niveau un peu plus haut.
                    level = (int) (Math.random() * 3) - 1 + ((playerTeam.getTeamLevel() / (playerTeam.getNbCharacters() + deltaNbPlayer)));
                    level -= playerTeam.getAverageLevel() / 3;
                    break;
                case 1://Si il y a un joueur de plus, leur niveau sont plus bas. 
                    level = (int) (Math.random() * 3) - 1 + ((playerTeam.getTeamLevel() / (playerTeam.getNbCharacters() + deltaNbPlayer)));
                    break;
            }

            if (level <= 0 || playerTeam.getLevelMaxInTeam()==1) {
                level = 1;
            }

            //Le niveau de la team reste quasiment toujours le même, sauf en cas d'infériorité numérique, car un niveau représente
            //beaucoup de caractéristiques supplémentaires, donc il ne faut pas que les adversaire soit trop forts.
            //Random pour choisir la classe du personnage
            int randomNumberClass = (int) (Math.random() * 3);

            switch (randomNumberClass) {
                case 0:
                    newChar = new Athlete(name, level);
                    break;
                case 1:
                    newChar = new Thief(name, level);
                    break;
                case 2:
                    newChar = new Warrior(name, level);
                    break;
            }

            this.addCharacterTeam(newChar);
        }
    }
}
