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
    public final static int MAXMANA = 150;
    
    @Override
    public Integer put(Attribute key, Integer value)
    {
        String message = verifyExcess(key, value);
        if(!message.equals(""))
        {
            throw new IllegalArgumentException(message);
        }
        return super.put(key, value); 
    }    

    @Override
    public Integer replace(Attribute key, Integer value)
    {
        String message = verifyExcess(key, value);
        if(!message.equals(""))
        {
            throw new IllegalArgumentException("L'attribut suivant à dépassé sa valeur maximale: "+key);
        }
        return super.replace(key, value);
    }
    
    private String verifyExcess(Attribute key, Integer value)
    {
        String message = "";
        switch (key)
        {
            case DEFENSE:
                if(value>MAXDEFENSE)
                {
                    message = "L'attribut suivant à dépassé sa valeur maximale: "+key;
                }   
                else if(value >= (int)(this.get(Attribute.STRENGTH)*2) || value >= (int)(this.get(Attribute.DEXTERITY)*2))
                {
                    message = "Il n'est pas possible d'augmenter votre défense d'une valeur supérieure à 2 fois votre force ou 2 fois votre dextérité.";
                }
                break;
                
            case DEXTERITY:
                if(value>MAXDEXTERITY)
                {
                    message = "L'attribut suivant à dépassé sa valeur maximale: "+key;
                }   
                else if(value >= (int)(this.get(Attribute.STRENGTH)*2))
                {
                    message = "Il n'est pas possible d'augmenter votre vitesse d'une valeur supérieure à 2 fois votre force.";
                }
                break;
            
            case SPEED:
                if(value>MAXSPEED)
                {
                    message = "L'attribut suivant à dépassé sa valeur maximale: "+key;
                }   
                else if(value >= (int)(this.get(Attribute.STRENGTH)*1.5) || value >= (int)(this.get(Attribute.DEXTERITY)*1.5))
                {
                    message = "Il n'est pas possible d'augmenter votre vitesse d'une valeur supérieure à 1,5 fois votre force et dextérité.";
                }
                break;
            
            case STRENGTH:
                if(value>MAXSTRENGTH)
                {
                    message = "L'attribut suivant à dépassé sa valeur maximale: "+key;
                }   
                else if(value >= (int)(this.get(Attribute.DEFENSE)*1.5) || value >= (int)(this.get(Attribute.DEXTERITY)*2))
                {
                    message = "Il n'est pas possible d'augmenter votre force d'une valeur supérieure à 2 fois votre dextérité, ou 1,5 fois votre défense.";
                }
                break;
            
            case HEALTH:
                if(value>MAXHEALTH)
                {
                    message = "L'attribut suivant à dépassé sa valeur maximale: "+key;
                }   
                break;
            
            case MANA:
                if(value>MAXMANA)
                {
                    message = "L'attribut suivant à dépassé sa valeur maximale: "+key;
                }   
                break;
            
            default:
                break;
        }
        return message;
    }
    
    
}
