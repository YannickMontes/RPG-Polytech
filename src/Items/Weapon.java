/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

/**
 *
 * @author yannick
 */
public class Weapon extends StuffItem
{
    private int damage;
    private int handlingAbility;
    
    public Weapon(String name, int weight, int damage, int handlingAbility) {
        super(name, weight);
        this.damage=damage;
        this.handlingAbility=handlingAbility;
    }

    public int getDamage() {
        return damage;
    }

    public int getHandlingAbility() {
        return handlingAbility;
    }
    
    
}
