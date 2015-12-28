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
    
    public Weapon(String name, int weight, int damage, int handlingAbility) {
        super(name, weight,handlingAbility);
        this.damage=damage;
    }

    public int getDamage() {
        return damage;
    }
    
}
