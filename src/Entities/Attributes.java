/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.HashMap;

/**
 *
 * @author yannick
 */
public class Attributes extends HashMap<Attribute, Integer>
{
    public final int MAXHEALTH = 999;
    public final int MAXDEXTERITY = 100;
    public final int MAXDEFENSE = 100;
    public final int MAXSTRENGTH = 100;
    public final int MAXSPEED = 100;
    public final int MAXINTELLIGENCE = 100;
    public final int MAXMANA = 150;
    
    @Override
    public Integer put(Attribute key, Integer value)
    {
        boolean correct = true;
        if(key == Attribute.DEFENSE)
        {
            if(value>MAXDEFENSE)
            {
                correct = false;
            }
        }
        else if(key == Attribute.DEXTERITY)
        {
            if(value>MAXDEXTERITY)
            {
                correct = false;
            }
        }
        else if(key == Attribute.SPEED)
        {
            if(value>MAXSPEED)
            {
                correct = false;
            }
        }
        else if(key == Attribute.STRENGTH)
        {
            if(value>MAXSTRENGTH)
            {
                correct = false;
            }
        }
        else if(key == Attribute.INTELLIGENCE)
        {
            if(value>MAXINTELLIGENCE)
            {
                correct = false;
            }
        }
        else if(key == Attribute.HEALTH)
        {
            if(value>MAXHEALTH)
            {
                correct = false;
            }
        }
        else if(key == Attribute.MANA)
        {
            if(value>MAXMANA)
            {
                correct = false;
            }
        }
        if(!correct)
        {
            throw new IllegalArgumentException("La valeur suivante à dépassé sa valeur:"+key);
        }
        return super.put(key, value); 
        
    }    
}
