/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Attack;
import Actions.Block;
import Actions.Care;

/**
 *
 * @author yannick
 */
public class Athlete extends Character implements Attack, Block, Care {

    public Athlete(String name) {
        super(name,"Athlete");
        this.attributes.put(Attribute.SPEED, 45);
        this.attributes.put(Attribute.STRENGTH, 45);
        this.attributes.put(Attribute.DEFENSE, 20);
        this.attributes.put(Attribute.DEXTERITY, 20);

        this.maxWeight = 20;
        capacities.add("Attaquer");
        capacities.add("Bloquer");
    }

    @Override
    public boolean strikeABlow(Character opponent) {
        boolean success = false;
//traitement
        return success;
    }
    
    @Override
    public boolean block() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dodge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean heal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
