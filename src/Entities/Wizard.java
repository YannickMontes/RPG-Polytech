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
public class Wizard extends Character implements Care, Block {

    public Wizard(String name) {
        super(name);
        this.attributes.put(Attribute.DEXTERITY, 55);
        this.attributes.put(Attribute.DEFENSE, 35);
        this.attributes.put(Attribute.SPEED, 25);
        this.attributes.put(Attribute.STRENGTH, 15);
        this.maxWeight = 15;
    }

    @Override
    public boolean block(Character opponent) {
        boolean success = false;
//traitement
        return success;
    }

    @Override
    public boolean dodge(Character opponent) {
        boolean success = false;
//traitement
        return success;
    }

    @Override
    public boolean nurse() {
        boolean success = false;
//traitement
        return success;
    }

}
