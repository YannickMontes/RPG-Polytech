/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entities.Attribute;
import java.util.ArrayList;
import java.util.List;
import Entities.Character;

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
}
