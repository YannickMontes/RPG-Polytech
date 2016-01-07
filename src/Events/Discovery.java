/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Manager.Team;
import Entities.Character;
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
        
        generateRandomEvent();
    }
    
    private void generateRandomEvent()
    {
        int randomNumber = (int)(Math.random()*100);
        
        if(randomNumber<1)//1%
        {
            //Nouveau personnage
            Character newCh;
            randomNumber = (int)(Math.random()*(Character.NBCLASSES-1));
            switch(randomNumber)
            {
                case 0:
                    newCh = new Warrior("Sylvain Duriff");
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
        else if(randomNumber<11)//10%
        {
            
        }
    }
}
