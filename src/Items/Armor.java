/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.util.List;

/**
 *
 * @author yannick
 */
public class Armor extends StuffItem
{
    private int defenseValue;
    public static List<Armor> listeArmorItem;
    
    public Armor(String name, int weight, int handlingAbility, int defenseValue, int rarity, int requiredLevel)
    {
        super(name, weight,handlingAbility, rarity, requiredLevel);
        this.defenseValue=defenseValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }
    
    @Override
    public String toString()
    {
        String text = "Type: Armure\n"+super.toString();
        
        text += "Defense: "+this.defenseValue;
        
        return text;
    }
}
