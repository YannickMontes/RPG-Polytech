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
    
    public static final int NBMAXCHARACTERS = 4;
    private List<Character> characters;
    private String name;
    
    public Team(Team playerTeam)
    {
        this.name = "On s'en fout";
        characters = new ArrayList<>();
        this.generateRandomTeam(playerTeam);
    }

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
        int i=0;
        for(Character ch : characters)
        {
            if(!ch.isAlive())
            {
                i++;
            }
        }
        if(i==this.getNbCharacters())
        {
            return false;
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
    
    @Override
    public String toString()
    {
        String text = "Equipe "+this.name+":\n"
                + "Nombre de personnages: "+this.getNbCharacters()+"\n";
        
        for(Character c : this.characters)
        {
            text+=c.toString();
        }
        
        return text;
    }
    
    /**
     * Fonction permettant de générer aléatoirement une équipe, en fonction de l'équipe du joueur.
     * Le niveau total de l'équipe généré sera le niveau total de l'équipe du joueur +/- 1.
     * Chaque personnage aura un stuff généré également aléatoirement. 
     * @param playerTeam Equipe du joueur.
     */
    public void generateRandomTeam(Team playerTeam)
    {
        //On commence par regarder combien de personnages l'équipe adverse va contenir.
        //On génère + ou - 1 le même nombre de personnages que l'équipe du joueur.
        int deltaNbPlayer = (int)(Math.random()*3)-1;
        
        if(playerTeam.getNbCharacters()+deltaNbPlayer<1)
        {
            deltaNbPlayer = 0;
        }
        
        //On boucle pour créer le nombre de joueurs aléatoire défini. 
        for(int i=0; i<playerTeam.getNbCharacters()+deltaNbPlayer; i++)
        {
            Character newChar = null;
            
            String name = "Zazie"; //TO DO: ALLER CHERCHER PARMI LES NOMS ALEATOIRE EN JSON
            
            //Random maitrisé pour le niveau du personnage.
            int level=0;
            switch (deltaNbPlayer)
            {
                case 0://Si il y a le même nombre de personnages, alors on prends le niveau moyen + ou - 1.
                    level = (int)(Math.random()*3)-1 + playerTeam.getAverageLevel();
                    break;
                case -1://Si il y a un personnage de moins, alors on met leur niveau un peu plus haut.
                    level = (int)(Math.random()*3)-1 + ((playerTeam.getTeamLevel()/(playerTeam.getNbCharacters()+deltaNbPlayer)));
                    level -= playerTeam.getAverageLevel()/3;
                    break;
                case 1://Si il y a un joueur de plus, leur niveau sont plus bas. 
                    level = (int)(Math.random()*3)-1 + ((playerTeam.getTeamLevel()/(playerTeam.getNbCharacters()+deltaNbPlayer)));
                    break;
            }
            
            if(level<=0)
            {
                level = 1;
            }
            
            //Le niveau de la team reste quasiment toujours le même, sauf en cas d'infériorité numérique, car un niveau représente
            //beaucoup de caractéristiques supplémentaires, donc il ne faut pas que les adversaire soit trop forts.
            
            //Random pour choisir la classe du personnage
            int randomNumberClass = (int)(Math.random()*3);
            
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
            
            this.addCharacterTeam(newChar);
        }
    }
}
