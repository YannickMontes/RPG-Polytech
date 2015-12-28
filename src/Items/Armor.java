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
    
    public Armor(String name, int weight, int defenseValue)
    {
        super(name, weight);
        this.defenseValue=defenseValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public Stream<Object> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
