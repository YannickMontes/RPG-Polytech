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

    public Warrior(String name) {
        super(name);
        this.attributes.put(Attribute.STRENGTH, 50);
        this.attributes.put(Attribute.DEXTERITY, 35);
        this.attributes.put(Attribute.DEFENSE, 30);
        this.attributes.put(Attribute.SPEED, 15);
        this.maxWeight = 25;
    }

    @Override
    public boolean strikeABlow(Character opponent) {
        boolean success = false;
//traitement
        return success;
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

}
