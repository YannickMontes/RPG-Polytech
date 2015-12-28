/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.util.stream.Stream;

/**
 *
 * @author yannick
 */
public class Armor extends StuffItem
{
    private int defenseValue;
    
    public Armor(String name, int weight, int handlingAbility, int defenseValue)
    {
        super(name, weight,handlingAbility);
        this.defenseValue=defenseValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }
    
}
