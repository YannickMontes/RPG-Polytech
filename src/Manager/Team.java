/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Athlete;
import Entities.Attribute;
import java.util.ArrayList;
import java.util.List;
import Entities.Character;
import Entities.Thief;
import Entities.Warrior;

/**
 *
 * @author Corentin
 */
public class Team {

    private List<Character> characters;
    private String name;

    public Team(String name) {
        this.name = name;
        characters = new ArrayList<>();
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
    
    public int getTeamSpeed()
    {
        int speedTot = 0;
        for(Character ch: characters)
        {
            speedTot += ch.getAttributes().get(Attribute.SPEED);
        }
        return speedTot;
    }
    
    public boolean isTeamAlive()
    {
        for(Character ch : characters)
        {
            if(!ch.isAlive())
            {
                return false;
            }
        }
        return true;
    }
    
    public int getTeamLevel()
    {
        int lvlTot = 0;
        for(Character ch : characters)
        {
            lvlTot += ch.getLevel();
        }
        return lvlTot;
    }
    
    public int getLevelMaxInTeam()
    {
        int lvlMax = Integer.MIN_VALUE;
        for(Character ch : characters)
        {
            if(ch.getLevel()>lvlMax)
            {
                lvlMax = ch.getLevel();
            }
        }
        return lvlMax;
    }
    
    public int getNbCharacters()
    {
        return this.characters.size();
    }
    
    public int getAverageLevel()
    {
        int sum = 0;
        for(Character ch : characters)
        {
            sum += ch.getLevel();
        }
        return sum/characters.size();
    }
    
    /**
     * Fonction permettant de générer aléatoirement une équipe, en fonction de l'équipe du joueur.
     * Le niveau total de l'équipe généré sera le niveau total de l'équipe du joueur +/- 1.
     * Chaque personnage aura un stuff généré également aléatoirement. 
     * @param playerTeam Equipe du joueur.
     */
    public void generateRandomTeam(Team playerTeam)
    {
        //On commence par regarder combien de personnages l'équipe adverse va contenir. De manière aléatoire, a + ou - 1.
        int deltaNbPlayer = (int)(Math.random()*2)-1;
        
        int sumLevel = 0;
        
        //On boucle pour créer le nombre de joueurs aléatoire défini. 
        for(int i=0; i<playerTeam.getNbCharacters()+deltaNbPlayer; i++)
        {
            Character newChar = null;
            
            String name = "Zazie"; //TO DO: ALLER CHERCHER PARMI LES NOMS ALEATOIRE EN JSON
            
            //Random pour choisir la classe du personnage
            int randomNumberClass = (int)(Math.random()*Character.NBPOINTLEVELUP-1);
            
            //Random pour choisir le niveau du personnage
            int level = (int)(Math.random()*2)-1 + playerTeam.getAverageLevel();
            
            switch(randomNumberClass)
            {
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
            
            sumLevel += level;
            
            this.addCharacterTeam(newChar);
        }
    }
}
