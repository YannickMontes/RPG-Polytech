/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Actions.Attack;
import Actions.Block;
import Actions.Care;
import Items.UseableItem;

/**
 *
 * @author yannick
 */
public class Warrior extends Character implements Attack, Block, Care {

    public Warrior(String name) {
        super(name, "Guerrier");
        this.basicAttributes.put(Attribute.STRENGTH, 35);
        this.basicAttributes.put(Attribute.DEXTERITY, 22);
        this.basicAttributes.put(Attribute.DEFENSE, 23);
        this.basicAttributes.put(Attribute.SPEED, 15);
        this.basicAttributes.put(Attribute.INTELLIGENCE, 0);
        this.restoreAttributes();

        capacities.add("Attaquer");
        capacities.add("Bloquer");
    }

    @Override
    public void putRandomPoint()
    {
        this.basicAttributes.replace(Attribute.STRENGTH, this.basicAttributes.get(Attribute.STRENGTH)+1);
    }
    
    @Override
    public String strikeABlow(Character opponent) {
        if (opponent != null) {
            if (capacities.contains("Attaquer")) {
                boolean success = verifySuccess("attack");
                int damages = 0;
                if (success == true) {
                    damages = measureImpact("attack", opponent, null);
                    opponent.takeABlow(damages);
                }
                String text = attackResult(success, opponent, damages);
                return text;
            }
            return "Vous ne possédez pas la capacité d'attaque actuellement";
        }
        return "Votre adversaire est inconnu";
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
    public String heal(UseableItem useableItem) {
       if (useableItem != null) {
            if (inventory.contains(useableItem)) {
                if (capacities.contains("Soigner")) {
                    boolean success = verifySuccess("care");
                    int care = 0;
                    if (success == true) {
                        care = measureImpact("care", null, useableItem);
                        this.attributes.replace(Attribute.HEALTH, this.attributes.get(Attribute.HEALTH) + care);
                    }
                    String text = careResult(success, care);
                    return text;
                }
                return "Vous ne possedez pas la capacité de soigner actuellement";
            }
            return "Vous ne possedez pas cet objet dans votre inventaire";
        }
        return "Vous ne possedez pas d'objets utilisables dans votre inventaire";
    }

}
