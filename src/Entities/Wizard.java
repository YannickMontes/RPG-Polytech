/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Block;
import Actions.Care;

/**
 *
 * @author yannick
 */
public class Wizard extends Character implements Care, Block{
    
    public Wizard(String name)
    {
        super(name);
        this.attributes.put(Attribute.DEXTERITY, 40);
        this.attributes.put(Attribute.DEFENSE, 30);
        this.attributes.put(Attribute.SPEED, 20);
        this.attributes.put(Attribute.STRENGTH, 10);
        this.maxWeight = 15;
    }
    
}
