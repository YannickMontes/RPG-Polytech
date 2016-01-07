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
    public final static int MAXHEALTH = 999;
    public final static int MAXDEXTERITY = 100;
    public final static int MAXDEFENSE = 100;
    public final static int MAXSTRENGTH = 100;
    public final static int MAXSPEED = 100;
    public final static int MAXINTELLIGENCE = 100;
    public final static int MAXMANA = 150;
    
    @Override
    public Integer put(Attribute key, Integer value)
    {
        if(!verifyExcess(key, value))
        {
            throw new IllegalArgumentException("L'attribut suivant à dépassé sa valeur maximale: "+key);
        }
        return super.put(key, value); 
    }    

    @Override
    public Integer replace(Attribute key, Integer value)
    {
        if(!verifyExcess(key, value))
        {
            throw new IllegalArgumentException("L'attribut suivant à dépassé sa valeur maximale: "+key);
        }
        return super.replace(key, value);
    }
    
    private boolean verifyExcess(Attribute key, Integer value)
    {
        boolean correct = true;
        switch (key)
        {
            case DEFENSE:
                if(value>MAXDEFENSE)
                {
                    correct = false;
                }   
                break;
                
            case DEXTERITY:
                if(value>MAXDEXTERITY)
                {
                    correct = false;
                }   
                break;
            
            case SPEED:
                if(value>MAXSPEED)
                {
                    correct = false;
                }   
                break;
            
            case STRENGTH:
                if(value>MAXSTRENGTH)
                {
                    correct = false;
                }   
                break;
            
            case INTELLIGENCE:
                if(value>MAXINTELLIGENCE)
                {
                    correct = false;
                }   
                break;
            
            case HEALTH:
                if(value>MAXHEALTH)
                {
                    correct = false;
                }   
                break;
            
            case MANA:
                if(value>MAXMANA)
                {
                    correct = false;
                }   
                break;
            
            default:
                break;
        }
        return correct;
    }
    
    
}
