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
public class Weapon extends StuffItem
{
    public static List<Weapon> listWeaponItem;
    private int damage;
    
    public Weapon(String name, int weight, int damage, int handlingAbility, int rarity, int requiredLevel) {
        super(name, weight,handlingAbility, rarity, requiredLevel);
        this.damage=damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString()
    {
        String text = "Type: Arme\n"+super.toString();
        
        text += "Dommages: "+this.damage;
        
        return text;
    }
    
    
}
