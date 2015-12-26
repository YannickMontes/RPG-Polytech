/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Attack;
import Actions.Block;

/**
 *
 * @author yannick
 */
public class Warrior extends Character implements Attack, Block {
    
    public Warrior(String name)
    {
        super(name);
        this.attributes.put(Attribute.STRENGTH, 40);
        this.attributes.put(Attribute.DEXTERITY, 25);
        this.attributes.put(Attribute.DEFENSE, 25);
        this.attributes.put(Attribute.SPEED, 10);
        this.maxWeight = 25;
    }
    
}
