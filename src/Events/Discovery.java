/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Entities.Athlete;
import Manager.Team;
import Entities.Character;
import Entities.Thief;
import Entities.Warrior;

/**
 *
 * @author yannick
 */
public class Discovery extends Event
{
    public Team team;
    
    public Discovery(Team t)
    {
        this.name="Découverte!";
        this.team = t;
        
        generateRandomDiscovery();
    }
    
    private void generateRandomDiscovery()
    {
        int randomNumber = (int)(Math.random()*99);
        
        if(randomNumber==0)//1%
        {
            //Nouveau personnage
            Character newCh = null;
            randomNumber = (int)(Math.random()*(Character.NBCLASSES-1));
            switch(randomNumber)
            {
                case 0:
                    newCh = new Warrior("Sylvain Duriff", (int)(Math.random()*(2))+team.getLevelMaxInTeam()-1);
                    break;
                case 1:
                    newCh = new Thief("EddineLanger", (int)(Math.random()*(2))+team.getLevelMaxInTeam()-1);
                    break;
                case 2:
                    newCh = new Athlete("Pierre Kiroule", (int)(Math.random()*(2))+team.getLevelMaxInTeam()-1);
                    break;
            }
            team.addCharacterTeam(newCh);
        }
        else if(randomNumber<=10)//10%
        {
            //Arme rare aléatoire
        }
        else if(randomNumber<=20)//10%
        {
            //Armure rare aléatoire
        }
        else if(randomNumber<=50)//30%
        {
            //Arme 
        }
        else if(randomNumber<=80)
        {
            //Armure
        }
        else
        {
            //Objet
        }
    }
}
