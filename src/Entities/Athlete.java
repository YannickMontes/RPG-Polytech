/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Attack;

/**
 *
 * @author yannick
 */
public class Athlete extends Character implements Attack {

    public Athlete(String name) {
        super(name);
        this.attributes.put(Attribute.SPEED, 45);
        this.attributes.put(Attribute.STRENGTH, 45);
        this.attributes.put(Attribute.DEFENSE, 20);
        this.attributes.put(Attribute.DEXTERITY, 20);

        this.maxWeight = 20;
    }

    @Override
    public boolean strikeABlow(Character opponent) {
        boolean success = false;
//traitement
        return success;
    }

}
