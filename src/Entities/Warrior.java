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
public class Warrior extends Character implements Attack, Block, Care {

    public Warrior(String name) {
        super(name, "Guerrier");
        this.basicAttributes.put(Attribute.STRENGTH, 50);
        this.basicAttributes.put(Attribute.DEXTERITY, 35);
        this.basicAttributes.put(Attribute.DEFENSE, 30);
        this.basicAttributes.put(Attribute.SPEED, 15);
        this.restoreAttributes();
        this.maxWeight = 25;

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
        boolean success = false;
//traitement
        return success;
    }

    @Override
    public boolean dodge() {
        boolean success = false;
//traitement
        return success;
    }

    @Override
    public boolean heal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
