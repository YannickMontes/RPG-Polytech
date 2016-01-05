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
public class Wizard extends Character implements Attack, Care, Block {

    public Wizard(String name) {
        super(name, "Magicien");
        this.basicAttributes.put(Attribute.DEXTERITY, 40);
        this.basicAttributes.put(Attribute.DEFENSE, 25);
        this.basicAttributes.put(Attribute.SPEED, 30);
        this.basicAttributes.put(Attribute.STRENGTH, 5);
        this.basicAttributes.put(Attribute.INTELLIGENCE, 50);
        this.restoreAttributes();
        this.maxWeight = 15;

        capacities.add("Attaquer");
        capacities.add("Bloquer");
        capacities.add("Soigner");
    }

    @Override
    public boolean heal() {
        boolean success = false;
//traitement
        return success;
    }

    @Override
    public String strikeABlow(Character opponent) {
        boolean success = verifySuccess("attack");
        int damages = 0;
        if(success==true)
        {
            damages = measureImpact("attack",opponent);
            opponent.takeABlow(damages);
        }
        String text = attackResult(success, opponent, damages);
        return text;
    }

    @Override
    public boolean block() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dodge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
