/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author yannick
 */
public class Warrior extends Character {
    
    public Warrior(String name)
    {
        super(name);
        this.attributes.put(Attribute.STRENGTH, 50);
        this.attributes.put(Attribute.DEXTERITY, 30);
        this.attributes.put(Attribute.DEFENSE, 20);
        this.maxWeight = 25;
    }
    
}
